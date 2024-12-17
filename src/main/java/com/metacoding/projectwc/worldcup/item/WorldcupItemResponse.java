package com.metacoding.projectwc.worldcup.item;

import lombok.Data;

import java.util.List;

public class WorldcupItemResponse {
    @Data
    public static class RenderingDTO {
        private int totalPage;
        private List<itemDTO> itemDTOList;

        public RenderingDTO(int itemSize, int size, int gamesCompleted, List<WorldcupItem> worldcupItemList) {
            this.totalPage = Math.max((itemSize + size - 1) / size, 1);
            this.itemDTOList = worldcupItemList.stream().map(i -> new WorldcupItemResponse.RenderingDTO.itemDTO(i, gamesCompleted)).toList();
        }

        @Data
        public static class itemDTO {
            private int id;
            private String itemname;
            private String imgUrl;
            private double champRate;
            private double winRate;

            public itemDTO(WorldcupItem item, int gamesCompleted) {
                this.id = item.getId();
                this.itemname = item.getItemname();
                this.imgUrl = item.getImgUrl();
                if (gamesCompleted == 0)
                    this.champRate = 0;
                else
                    this.champRate = Math.round(10000.0 * item.getChampionCount() / gamesCompleted) / 100.0;
                if (item.getTotalCount() == 0)
                    this.winRate = 0;
                else
                    this.winRate = Math.round(10000.0 * item.getWinCount() / item.getTotalCount()) / 100.0;
            }
        }
    }

    @Data
    public static class RenderingDTO2 {
        private int id;
        private String itemname;
        private String imgUrl;
        private int champRate;
        private int winRate;

        public RenderingDTO2(WorldcupItem item, int gamesCompleted) {
            this.id = item.getId();
            this.itemname = item.getItemname();
            this.imgUrl = item.getImgUrl();
            if (gamesCompleted == 0)
                this.champRate = 0;
            else
                this.champRate = 100 * item.getChampionCount() / gamesCompleted;
            if (item.getTotalCount() == 0)
                this.winRate = 0;
            else
                this.winRate = 100 * item.getWinCount() / item.getTotalCount();
        }
    }

    @Data
    public static class RankDTO {
        private int id;
        private String itemname;
        private String imgUrl;
        private int champRate;
        private int winRate;

        public RankDTO(WorldcupItem item, int gamesCompleted) {
            this.id = item.getId();
            this.itemname = item.getItemname();
            this.imgUrl = item.getImgUrl();
            if (gamesCompleted == 0) {
                this.champRate = 0;
            } else {
                this.champRate = (int) Math.round(item.getChampionCount() * 100.0 / gamesCompleted);
            }
            if (item.getTotalCount() == 0) {
                this.winRate = 0;
            } else {
                this.winRate = (int) Math.round(item.getWinCount() * 100 / item.getTotalCount());
            }
        }
    }
}
