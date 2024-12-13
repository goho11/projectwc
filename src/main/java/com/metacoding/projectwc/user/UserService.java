package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.error.ex.Exception404;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserRequest.signupDTO signupDTO) {
        userRepository.save(signupDTO.toEntity(passwordEncoder));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다: " + username));
    }

    public boolean checkPassword(User user, String currentPassword) {
        return passwordEncoder.matches(currentPassword, user.getPassword());
    }

    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);  // 여기서 save() 호출 (UserRepository에서 merge() 처리됨)
    }
}
