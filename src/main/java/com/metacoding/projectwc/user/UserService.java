package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.error.ex.APIException403;
import com.metacoding.projectwc._core.error.ex.APIException404;
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
        if(user.getIsDeleted()){
            throw new Exception404("탈퇴한 회원입니다.");
        }
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

    // 회원탈퇴
    @Transactional
    public void deleteUser(Integer id, UserRequest.DeleteDTO deleteDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new APIException404("해당 ID의 사용자를 찾을 수 없습니다." + id));
        // 비밀번호 확인
        if (!passwordEncoder.matches(deleteDTO.getPassword(), user.getPassword())) {
            throw new APIException403("비밀번호가 틀렸습니다.");
        }

        user.softDelete();
    }

}

