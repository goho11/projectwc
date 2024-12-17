package com.metacoding.projectwc.comment;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

public class CommentResponse {

    @Data
    public static class ResponsePageDTO {
        private Integer currentPage;
        private Integer totalItems;
        private Integer totalPages;
        private Integer size;

        private boolean isFirstPage;
        private boolean isLastPage;
        private Integer prevPage;
        private Integer nextPage;

        private List<Map<String, Object>> pages;

        @Builder
        public ResponsePageDTO(Integer currentPage, Integer totalPages, Integer totalItems, Integer size, boolean isFirstPage, boolean isLastPage, Integer prevPage, Integer nextPage, List<Map<String, Object>> pages) {
            this.currentPage = currentPage;
            this.totalItems = totalItems;
            this.totalPages = totalPages;
            this.size = size;
            this.isFirstPage = isFirstPage;
            this.isLastPage = isLastPage;
            this.prevPage = prevPage;
            this.nextPage = nextPage;
            this.pages = pages;
        }
    }
}
