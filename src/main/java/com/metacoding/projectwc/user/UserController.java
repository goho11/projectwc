package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.error.ex.Exception403;
import com.metacoding.projectwc._core.util.Resp;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signup(@Valid UserRequest.SignupDTO signupDTO, Errors errors) {
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
    public String updateUser(@AuthenticationPrincipal User user, @Valid UserRequest.UpdateDTO updateDTO, Errors errors) {
        if (updateDTO.getNewPassword(passwordEncoder) != null) {
            if (!passwordEncoder.matches(updateDTO.getPassword(), user.getPassword())) {
                throw new Exception403("현재 비밀번호가 틀립니다.");
            }
        }
        User userUpdated = userService.updateUser(user.getId(), updateDTO);
        session.setAttribute("sessionUser", userUpdated);
        return "redirect:/s/user-form?success";
    }

    @DeleteMapping("/s/user")
    public ResponseEntity<?> delete(@RequestBody UserRequest.DeleteDTO deleteDTO, @AuthenticationPrincipal User user) {
        userService.deleteUser(user.getId(), deleteDTO);
        return ResponseEntity.ok(Resp.ok("됨"));
    }

}
