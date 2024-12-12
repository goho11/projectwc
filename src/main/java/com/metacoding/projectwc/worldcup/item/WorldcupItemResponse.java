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
            private int champRate;
            private int winRate;

            public itemDTO(WorldcupItem item, int gamesCompleted) {
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
}
