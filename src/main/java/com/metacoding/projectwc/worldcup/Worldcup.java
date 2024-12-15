package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "worldcup_tb")
@Entity
public class Worldcup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, columnDefinition = "VARCHAR(50) DEFAULT '새로운 월드컵'")
    private String title;
    private String description;

    // 0 비공개 / 1 공개
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer visibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer gamesCompleted;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;

    // 논리 삭제
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;

    @Builder
    public Worldcup(Integer id, String title, String description, Integer visibility, User user, Integer gamesCompleted, Timestamp createdAt, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.visibility = visibility;
        this.user = user;
        this.gamesCompleted = gamesCompleted;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    @PrePersist
    public void prePersist() {
        if (title == null)
            title = "새로운 월드컵";
        if (visibility == null)
            visibility = 0;
        if (gamesCompleted == null)
            gamesCompleted = 0;
        if (isDeleted == null)
            isDeleted = false;

    }

    public void update(String title, String description, int visibility) {
        this.title = title;
        this.description = description;
        this.visibility = visibility;
    }

    public void softDelete() {
        isDeleted = true;
    }
}
