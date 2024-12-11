package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import lombok.Data;
import lombok.Builder;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class WorldcupResponse {

    @Data
    public static class findByIDDTO {
        private int id;
        private String title;
        private String description;

        public findByIDDTO(Worldcup worldcup) {
            this.id = worldcup.getId();
            this.title = worldcup.getTitle();
            this.description = worldcup.getDescription();
        }
    }
    @Data
    public static class findAllDTO {
        private Integer id;
        private String title;
        private String description;
        private Integer userId;
        private Integer gamesCompleted;
        private Timestamp createdAt;

        private String itemname1;
        private String imgUrl1;
        private String itemname2;
        private String imgUrl2;

        public findAllDTO(Worldcup worldcup, WorldcupItem worldcupItem1, WorldcupItem worldcupItem2) {
            this.id = worldcup.getId();
            this.title = worldcup.getTitle();
            this.description = worldcup.getDescription();
            this.userId = worldcup.getUser().getId();
            this.gamesCompleted = worldcup.getGamesCompleted();
            this.createdAt = worldcup.getCreatedAt();

            this.itemname1 = worldcupItem1.getItemname();
            this.imgUrl1 = worldcupItem1.getImgUrl();
            this.itemname2 = worldcupItem2.getItemname();
            this.imgUrl2 = worldcupItem2.getImgUrl();
        }
    }

    @Data
    public static class pageDTO {
        private Integer currentPage;
        private Integer totalPages;
        private Integer size;
        private String sortBy;
        private String searchKeyword;

        private boolean isFirstPage;
        private boolean isLastPage;
        private Integer prevPage;
        private Integer nextPage;

        private List<Map<String, Object>> pages;

        @Builder
        public pageDTO(Integer currentPage, Integer totalPages, Integer size, String sortBy, String searchKeyword, boolean isFirstPage, boolean isLastPage, Integer prevPage, Integer nextPage, List<Map<String, Object>> pages) {
            this.currentPage = currentPage;
            this.totalPages = totalPages;
            this.size = size;
            this.sortBy = sortBy;
            this.searchKeyword = searchKeyword;
            this.isFirstPage = isFirstPage;
            this.isLastPage = isLastPage;
            this.prevPage = prevPage;
            this.nextPage = nextPage;
            this.pages = pages;
        }
    }
}
