package com.metacoding.projectwc.comment;

import com.metacoding.projectwc.worldcup.Worldcup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepository {
    private final EntityManager entityManager;

    public List<Comment> findAll(Integer worldcupId, Integer offset, Integer limit) {
        String jpql = "SELECT c FROM Comment c join fetch c.worldcup WHERE c.id = :worldcupId AND c.isDeleted = false";
        TypedQuery<Comment> query = entityManager.createQuery(jpql, Comment.class)
                .setParameter("worldcupId", worldcupId);

        // 적용할 오프셋 및 리미트 설정
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    public Integer countAllComment(Integer worldcupId) {
        String jpql = "SELECT COUNT(c) FROM Comment c join fetch c.worldcup WHERE c.id = :worldcupId AND c.isDeleted = false";
        return entityManager.createQuery(jpql, Integer.class)
                .setParameter("worldcupId", worldcupId)
                .getSingleResult()
                .intValue();

    }


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
