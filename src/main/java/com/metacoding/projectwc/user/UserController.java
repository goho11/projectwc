package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.util.Resp;
import com.metacoding.projectwc.worldcup.item.WorldcupItemRequest;
import com.metacoding.projectwc.worldcup.item.WorldcupItemResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping("/signup")
    public String signup(UserRequest.signupDTO signupDTO) {
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
}
