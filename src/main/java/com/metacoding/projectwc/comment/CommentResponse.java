package com.metacoding.projectwc.comment;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class CommentResponse {

    @Data
    public static class FindAllDTO {
        private Integer id;
        private String nickname;
        private String content;
        private String winnername;
        private Integer userId;
        private Integer worldcupId;
        private Boolean idEqualComment;
        private Timestamp createdAt;

        public FindAllDTO(Comment comment, Integer userId, Integer worldcupId) {
            this.id = comment.getId();
            this.nickname = comment.getNickname();
            this.content = comment.getContent();
            this.winnername = comment.getWinnername();
            this.userId = comment.getUser().getId();
            this.worldcupId = worldcupId;
            this.createdAt = comment.getCreatedAt();
            this.idEqualComment = false;
            if (comment.getUser().getId().equals(userId)) {
                this.idEqualComment = true;
            }
        }
    }

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
