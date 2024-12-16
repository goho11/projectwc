package com.metacoding.projectwc.user;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

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

    @Test
    public void save_test() {
        User user = User.builder()
                .email("ssar@test.com")
                .password("1234")
                .nickname("ssar")
                .build();

        userRepository.save(user);

        entityManager.clear();
//        System.out.println("PC 캐쉬 삭제");
        User user2 = userRepository.findById(3).orElseThrow();

        System.out.println("닉네임은 : " + user2.getNickname());
    }
}
