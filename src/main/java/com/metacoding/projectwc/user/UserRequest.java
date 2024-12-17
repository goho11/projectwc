package com.metacoding.projectwc.user;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {
    @Data
    public static class SignupDTO {
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        @NotBlank(message = "이메일은 필수 항목입니다.")
        String email;
        @NotBlank(message = "비밀번호는 필수 항목입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하여야 합니다.")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "비밀번호는 영문자, 숫자, 특수문자를 포함해야 합니다."
        )
        String password;
        @NotBlank(message = "닉네임은 필수 항목입니다.")
        String nickname;

        public User toEntity(PasswordEncoder passwordEncoder) {
            String encoderPassword = passwordEncoder.encode(password);
            User user = new User(null, email, encoderPassword, nickname, null, false);
            return user;
        }
    }

    @Data
    public static class UpdateDTO {
        @NotBlank(message = "닉네임은 필수 항목입니다.")
        private String nickname;
        private String password;
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하여야 합니다.")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "비밀번호는 영문자, 숫자, 특수문자를 포함해야 합니다."
        )
        private String newPassword;

        public String getNewPassword(PasswordEncoder passwordEncoder) {
            if (newPassword == null || newPassword.isEmpty()) {
                return null;
            }
            return passwordEncoder.encode(newPassword);
        }
    }

    @Data
    public static class DeleteDTO {
        private String password;
    }

}