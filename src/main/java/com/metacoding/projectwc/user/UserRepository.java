package com.metacoding.projectwc.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager entityManager;

    public User save(User user) {
        if (user.getId() == null) {
            // 새 객체일 경우
            entityManager.persist(user);
        } else {
            // 기존 객체일 경우 merge
            user = entityManager.merge(user);
        }
        return user;
    }

    public Optional<User> findByUsername(String username) {
        try {
            Query query = entityManager.createQuery("select u from User u where u.email = :email", User.class);
            query.setParameter("email", username);
            return Optional.of((User) query.getSingleResult());
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
