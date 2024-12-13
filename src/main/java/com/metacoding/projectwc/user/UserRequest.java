package com.metacoding.projectwc.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {

    @Data
    public static class signupDTO {
        String email;
        @NotNull
        String password;
        String nickname;

        public User toEntity(PasswordEncoder passwordEncoder) {
            String encoderPassword = passwordEncoder.encode(password);
            User user = new User(null, email, encoderPassword, nickname, null, false);
            return user;
        }
    }

    @Data
    public static class loginDTO {
        String email;
        String password;
    }

    @Data
    public static class updateDTO {
        private String currentPassword; // 현재 비밀번호
        private String newPassword; // 새 비밀번호
        private String nickname;
    }
}