package com.metacoding.projectwc.worldcup;

import lombok.Data;

public class WorldcupRequest {

    @Data
    public static class FindAllDTO {
        private String searchKeyword = ""; // 검색 단어
        private String sortBy = "Popularity"; // 정렬 기준, Latest, Popularity
        private Integer page = 1; // 현재 페이지 번호
        private Integer size = 10; // 페이지 크기 (한 페이지당 아이템 개수)
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String description;
        private int visibility;
    }
}
