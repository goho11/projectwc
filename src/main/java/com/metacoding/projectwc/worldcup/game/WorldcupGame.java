package com.metacoding.projectwc.worldcup.game;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "worldcup_game_tb")
@Entity
public class WorldcupGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Worldcup worldcup;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String anonymity;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCompleted;

    @Column(nullable = false)
    private Integer startRound;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;

    @Builder
    public WorldcupGame(Integer id, Worldcup worldcup, User user, String anonymity, Boolean isCompleted, Integer startRound, Timestamp createdAt) {
        this.id = id;
        this.worldcup = worldcup;
        this.user = user;
        this.anonymity = anonymity;
        this.isCompleted = isCompleted;
        this.startRound = startRound;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (isCompleted == null)
            isCompleted = false;
    }
}
