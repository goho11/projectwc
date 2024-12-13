package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.error.ex.Exception403;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signup(UserRequest.SignupDTO signupDTO) {
        userService.signup(signupDTO);
        return "redirect:/login-form";
    }

    @GetMapping("/signup-form")
    public String signupForm() {
        return "signup-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("/s/user-form")
    public String userForm() {
        return "user-form";
    }

    @PostMapping("/s/user")
    public String updateUser(@ModelAttribute UserRequest.UpdateDTO updateDTO, @AuthenticationPrincipal User user) {
        if (updateDTO.getNewPassword(passwordEncoder) != null) {
            if (!passwordEncoder.matches(updateDTO.getPassword(), user.getPassword())) {
                throw new Exception403("현재 비밀번호가 틀립니다.");
            }
        }

        User userUpdated = userService.updateUser(user.getId(), updateDTO);
        session.setAttribute("sessionUser", userUpdated);
        return "redirect:/s/user-form";
    }

}
