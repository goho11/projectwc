package com.metacoding.projectwc.worldcup.game.match;

import com.metacoding.projectwc.worldcup.game.WorldcupGame;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import lombok.Data;

public class WorldcupMatchResponse {

    @Data
    public static class SaveWorldcupMatchDTO {
        private int id;
        private WorldcupGame worldcupGame;
        private WorldcupItem worldcupItem1;
        private WorldcupItem worldcupItem2;
        private Integer matchNum;
        private String round;

        public SaveWorldcupMatchDTO(WorldcupMatch match) {
            this.id = match.getId();
            this.worldcupGame = match.getWorldcupGame();
            this.worldcupItem1 = match.getWorldcupItem1();
            this.worldcupItem2 = match.getWorldcupItem2();
            this.matchNum = match.getMatchNum();
            this.round = match.getRound() + "강";
            if (match.getRound() == 4) {
                this.round = "준결승";
            }
            if (match.getRound() == 2) {
                this.round = "결승";
            }
        }
    }
}
