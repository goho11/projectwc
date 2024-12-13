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
    public String userForm(Model model, @AuthenticationPrincipal User user) {
        String username = user.getUsername();
        model.addAttribute("username", username);
        return "user-form";
    }

    @PutMapping("/s/user")
    public Resp<?> updateUser(@RequestBody UserRequest.UpdateDTO updateDTO, @AuthenticationPrincipal User user) {
        userService.updateUser(user.getId(), updateDTO);
        return Resp.ok("수정됨");
    }

}
