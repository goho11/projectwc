package com.metacoding.projectwc.user;

import lombok.Data;

import java.sql.Timestamp;

public class UserResponse {

    @Data
    public static class UserInfoDTO {
        private Integer id;
        private String email;
        private String nickname;
        private Timestamp createdAt;
        private Boolean isDeleted;

        public UserInfoDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.createdAt = user.getCreatedAt();
            this.isDeleted = user.getIsDeleted();
        }
    }
}
