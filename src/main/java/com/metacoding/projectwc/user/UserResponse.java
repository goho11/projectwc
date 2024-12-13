package com.metacoding.projectwc.user;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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

        @Builder
        public UserInfoDTO(Integer id, String email, String nickname, Timestamp createdAt, Boolean isDeleted) {
            this.id = id;
            this.email = email;
            this.nickname = nickname;
            this.createdAt = createdAt;
            this.isDeleted = isDeleted;
        }
    }
}
