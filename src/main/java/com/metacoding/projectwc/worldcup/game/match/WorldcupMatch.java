package com.metacoding.projectwc.worldcup.game.match;

import com.metacoding.projectwc.worldcup.game.WorldcupGame;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "worldcup_match_tb")
@Entity
public class WorldcupMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private WorldcupGame worldcupGame;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private WorldcupItem worldcupItem1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private WorldcupItem worldcupItem2;

    private Integer winnerPK;

    @Column(nullable = false)
    private Integer matchNum;

    @Column(nullable = false)
    private Integer round;

    @Builder
    public WorldcupMatch(Integer id, WorldcupGame worldcupGame, WorldcupItem worldcupItem1, WorldcupItem worldcupItem2, Integer winnerPK, Integer matchNum, Integer round) {
        this.id = id;
        this.worldcupGame = worldcupGame;
        this.worldcupItem1 = worldcupItem1;
        this.worldcupItem2 = worldcupItem2;
        this.winnerPK = winnerPK;
        this.matchNum = matchNum;
        this.round = round;
    }

    public void update(int worldcupMatchId) {
        this.winnerPK = worldcupMatchId;
    }
}
