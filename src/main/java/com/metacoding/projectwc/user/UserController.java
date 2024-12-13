package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.util.Resp;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

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
    public Resp<?> updateUser(@RequestBody UserRequest.UpdateDTO updateDTO, @AuthenticationPrincipal User user) {
        String nickname = user.getNickname(); // 옛날 닉네임
//        userService.updateUser(user.getId(), updateDTO, nickname);
        return Resp.ok("수정됨");
    }

}
