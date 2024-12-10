package com.metacoding.projectwc.loginlog;

import com.metacoding.projectwc.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginLogService {
    private final LoginLogRepository loginLogRepository;
}
