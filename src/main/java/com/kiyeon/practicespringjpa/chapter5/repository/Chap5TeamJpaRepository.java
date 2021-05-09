package com.kiyeon.practicespringjpa.chapter5.repository;

import com.kiyeon.practicespringjpa.chapter5.entity.Chap5Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class Chap5TeamJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Chap5Team save(Chap5Team chap5Team) {
        em.persist(chap5Team);
        return chap5Team;
    }

    public void delete(Chap5Team chap5Team) {
        em.remove(chap5Team);
    }

    public List<Chap5Team> findAll() {
        return em.createQuery("select t from Chap5Team t", Chap5Team.class)
                .getResultList();
    }

    public Optional<Chap5Team> findById(Long id) {
        Chap5Team chap5Team = em.find(Chap5Team.class, id);
        return Optional.ofNullable(chap5Team);
    }

    public long count() {
        return em.createQuery("select count(t) from Chap5Team t", Long.class)
                .getSingleResult();
    }

    public Chap5Team find(Long id) {
        return em.find(Chap5Team.class, id);
    }
}
