package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.error.ex.Exception401;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager entityManager;

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public User findByUsername(String username) {
        Query query = entityManager.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", username);
        try {
            return (User) query.getSingleResult();
        } catch (RuntimeException e) {
            throw new Exception401("이메일 혹은 비밀번호가 일치하지 않습니다");
        }
    }
}
