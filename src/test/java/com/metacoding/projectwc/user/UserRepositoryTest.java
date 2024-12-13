package com.metacoding.projectwc.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void login_test() {
        User user = User.builder()
                .email("test")
                .password("1234")
                .build();
        User user2 = User.builder()
                .email("test2")
                .password("1234")
                .build();
        User user3 = User.builder()
                .email("test3")
                .password("12345")
                .build();

        User loginUser = userRepository.findByUsername(user.getUsername());
        System.out.println("loginUser = " + loginUser.getUsername());
        if (loginUser.getPassword().equals(user.getPassword())) {
            System.out.println("비밀번호 일치");
        } else {
            System.out.println("비밀번호 불일치");
        }
    }
}
