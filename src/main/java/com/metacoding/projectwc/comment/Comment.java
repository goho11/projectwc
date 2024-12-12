package com.metacoding.projectwc.comment;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "comment_tb")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    private String winnername;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Worldcup worldcup;

    @Column(nullable = false)
    private String nickname;

    // 논리 삭제
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;

    @Builder
    public Comment(Integer id, String content, String winnername, Timestamp createdAt, User user, Worldcup worldcup, String nickname, boolean isDeleted) {
        this.id = id;
        this.content = content;
        this.winnername = winnername;
        this.createdAt = createdAt;
        this.user = user;
        this.worldcup = worldcup;
        this.nickname = nickname;
        this.isDeleted = isDeleted;
    }

    @PrePersist
    public void prePersist() {
        if (isDeleted == null)
            isDeleted = false;
    }
}
