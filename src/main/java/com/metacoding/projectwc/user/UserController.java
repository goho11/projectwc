package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.error.ex.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/s/user")
    public String updateUser(@ModelAttribute UserRequest.updateDTO updateDTO, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));

        // 입력된 현재 비밀번호가 기존 비밀번호와 일치하는지 확인
        if (!userService.checkPassword(user, updateDTO.getCurrentPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 닉네임 수정
        user.setNickname(updateDTO.getNickname());

        // 비밀번호 변경이 필요한 경우, 새로운 비밀번호로 설정
        if (updateDTO.getNewPassword() != null && !updateDTO.getNewPassword().isEmpty()) {
            if (updateDTO.getCurrentPassword().equals(updateDTO.getNewPassword())) {
                throw new RuntimeException("새 비밀번호가 현재 비밀번호와 같을 수 없습니다.");
            }
            // 새로운 비밀번호를 암호화해서 저장
            user.setPassword(passwordEncoder.encode(updateDTO.getNewPassword()));
        }

        // 유저 정보 업데이트
        userService.updateUser(user);

        // 수정 후 유저 정보 화면으로 리다이렉트
        return "redirect:/s/user-form";
    }

}
