package com.metacoding.projectwc.loginlog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class LoginLogController {
    private final LoginLogService loginLogService;
}
