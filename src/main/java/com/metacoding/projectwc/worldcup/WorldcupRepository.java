package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class WorldcupRepository  {
    private final EntityManager entityManager;

    public Worldcup save(Worldcup worldcup) {
        entityManager.persist(worldcup);
        return worldcup;
    }

    public Optional<Worldcup> findById(int id) {
        return Optional.ofNullable(entityManager.find(Worldcup.class, id));
    }

    public List<Worldcup> findAll() {
        return entityManager.createQuery("select w from Worldcup w", Worldcup.class)
                .getResultList();
    }

    // TODO: WHERE 조건문 필요 (visibility, isDeleted)
//    public List<Worldcup> findAll(String title, String sort) {
//        String jpql = "SELECT w FROM Worldcup w WHERE w.title LIKE :title ORDER BY w." + sort + " DESC";
//        return entityManager.createQuery(jpql, Worldcup.class)
//                .setParameter("title", "%" + title + "%")
//                .getResultList();
//    }
    public List<Worldcup> findAllByTiltle(String searchKeyword, String sortBy, Integer offset, Integer limit) {
        String jpql = "SELECT w FROM Worldcup w WHERE w.title LIKE :searchKeyword ORDER BY w." + sortBy + " DESC";
        TypedQuery<Worldcup> query = entityManager.createQuery(jpql, Worldcup.class)
                .setParameter("searchKeyword", "%" + searchKeyword + "%");

        // 적용할 오프셋 및 리미트 설정
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

}
