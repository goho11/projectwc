package com.metacoding.projectwc.loginlog;

import com.metacoding.projectwc.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "login_log_tb")
@Entity
public class LoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String userAgent;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;

    @Builder
    public LoginLog(Integer id, User user, String userAgent, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.userAgent = userAgent;
        this.createdAt = createdAt;
    }
}
