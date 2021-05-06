package com.kiyeon.practicespringjpa.chapter4.repository;

import com.kiyeon.practicespringjpa.chapter4.entity.Chap4Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class Chap4TeamJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Chap4Team save(Chap4Team chap4Team) {
        em.persist(chap4Team);
        return chap4Team;
    }

    public void delete(Chap4Team chap4Team) {
        em.remove(chap4Team);
    }

    public List<Chap4Team> findAll() {
        return em.createQuery("select t from Chap4Team t", Chap4Team.class)
                .getResultList();
    }

    public Optional<Chap4Team> findById(Long id) {
        Chap4Team chap4Team = em.find(Chap4Team.class, id);
        return Optional.ofNullable(chap4Team);
    }

    public long count() {
        return em.createQuery("select count(t) from Chap4Team t", Long.class)
                .getSingleResult();
    }

    public Chap4Team find(Long id) {
        return em.find(Chap4Team.class, id);
    }
}
