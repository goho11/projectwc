package com.metacoding.projectwc.loginlog;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginLogService {
    private final LoginLogRepository loginLogRepository;

    @Transactional
    public void save(User user, String userAgent) {
        LoginLog loginLog = LoginLog.builder().user(user).userAgent(userAgent).build();
        loginLogRepository.save(loginLog);
    }
}
