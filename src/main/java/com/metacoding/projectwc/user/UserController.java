package com.metacoding.projectwc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    @GetMapping("/s/user-form")
    public String userForm(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByUsername(email);
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/s/user")
    public String updateUser(@ModelAttribute UserRequest.updateDTO updateDTO, Authentication authentication, RedirectAttributes redirectAttributes) {
        String email = authentication.getName();
        User user = userService.findByUsername(email);

        // 비밀번호 확인
        if (!userService.checkPassword(user, updateDTO.getCurrentPassword())) {
            // 비밀번호 틀린 경우
            redirectAttributes.addFlashAttribute("errorMessage", "현재 비밀번호가 틀립니다.");
            return "redirect:/s/user-form";  // 에러 발생 후 다시 수정 페이지로 리다이렉트
        }

        if (!userService.checkNickname(user, updateDTO.getNickname())) {
            redirectAttributes.addFlashAttribute("errorMessage", "현재 닉네임과 동일합니다.");
            return "redirect:/s/user-form";  // 동일할 경우 리다이렉트
        }

        // 닉네임과 비밀번호 업데이트
        user.setNickname(updateDTO.getNickname());
        if (updateDTO.getNewPassword() != null && !updateDTO.getNewPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateDTO.getNewPassword()));  // 비밀번호 암호화
        }
        userService.updateUser(user);

        // 수정 후 성공 메시지
        redirectAttributes.addFlashAttribute("successMessage", "회원정보가 수정되었습니다.");
        return "redirect:/s/user-form";
    }

}
