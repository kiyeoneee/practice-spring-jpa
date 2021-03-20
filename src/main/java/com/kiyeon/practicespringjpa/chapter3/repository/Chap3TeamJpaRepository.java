package com.kiyeon.practicespringjpa.chapter3.repository;

import com.kiyeon.practicespringjpa.chapter3.entity.Chap3Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class Chap3TeamJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Chap3Team save(Chap3Team chap3Team) {
        em.persist(chap3Team);
        return chap3Team;
    }

    public void delete(Chap3Team chap3Team) {
        em.remove(chap3Team);
    }

    public List<Chap3Team> findAll() {
        return em.createQuery("select t from Chap3Team t", Chap3Team.class)
                .getResultList();
    }

    public Optional<Chap3Team> findById(Long id) {
        Chap3Team chap3Team = em.find(Chap3Team.class, id);
        return Optional.ofNullable(chap3Team);
    }

    public long count() {
        return em.createQuery("select count(t) from Chap3Team t", Long.class)
                .getSingleResult();
    }

    public Chap3Team find(Long id) {
        return em.find(Chap3Team.class, id);
    }
}
