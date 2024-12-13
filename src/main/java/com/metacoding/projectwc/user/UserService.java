package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.error.ex.Exception403;
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
    public void signup(UserRequest.SignupDTO signupDTO) {
        userRepository.save(signupDTO.toEntity(passwordEncoder));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public UserResponse.UserInfoDTO getUserInfo(User user) {
        return new UserResponse.UserInfoDTO(user);
    }

    @Transactional
    public User updateUser(Integer id, UserRequest.UpdateDTO updateDTO) {
        User userPS = userRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 ID의 사용자를 찾을 수 없습니다." + id));

        if (!passwordEncoder.matches(updateDTO.getPassword(), userPS.getPassword())) {
            throw new Exception403("비밀번호가 틀렸습니다.");
        }

        userPS.update(updateDTO.getNickname(), updateDTO.getNewPassword(passwordEncoder));
        return userPS;
    }
}

