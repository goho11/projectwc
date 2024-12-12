package com.metacoding.projectwc.comment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepository {
    private final EntityManager entityManager;

    public Optional<Comment> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    public void saveComment(Comment comment) {
        entityManager.persist(comment);
    }

    public void deleteComment(Integer id) {
        entityManager.createQuery("update Comment c set c.isDeleted = true where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
