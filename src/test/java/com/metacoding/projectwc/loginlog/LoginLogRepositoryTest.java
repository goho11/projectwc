package com.metacoding.projectwc.loginlog;

import com.metacoding.projectwc.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(LoginLogRepository.class)
@DataJpaTest
public class LoginLogRepositoryTest {

    @Autowired
    private LoginLogRepository loginLogRepository;

    @Test
    public void save_test() {
        // given
        LoginLog loginLog = LoginLog.builder().user(User.builder().id(1).build()).userAgent("asdf").build();

        // when
        LoginLog loginlogPS= loginLogRepository.save(loginLog);

        // then
        System.out.println(loginlogPS.getId());
    }
}
