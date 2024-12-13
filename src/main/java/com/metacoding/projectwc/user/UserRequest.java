package com.metacoding.projectwc.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {
    @Data
    public static class SignupDTO {
        String email;
        String password;
        String nickname;

        public User toEntity(PasswordEncoder passwordEncoder) {
            String encoderPassword = passwordEncoder.encode(password);
            User user = new User(null, email, encoderPassword, nickname, null, false);
            return user;
        }
    }

    @Data
    public static class LoginDTO {
        String email;
        String password;
    }

    @Data
    public static class UpdateDTO {
        private String password;
        private String nickname;
    }
}