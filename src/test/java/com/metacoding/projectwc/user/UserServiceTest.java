package com.metacoding.projectwc.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;

@Import(UserService.class)
@DataJpaTest
public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    }
}
