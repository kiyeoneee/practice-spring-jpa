package com.kiyeon.practicespringjpa.chapter3.repository;

import com.kiyeon.practicespringjpa.chapter3.entity.Chap3Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class Chap3MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Chap3Member save(Chap3Member chap3Member) {
        em.persist(chap3Member);
        return chap3Member;
    }

    public void delete(Chap3Member chap3Member) {
        em.remove(chap3Member);
    }

    public List<Chap3Member> findAll() {
        // 전체 리스트를 가져오기 위해서는 JPQL 을 사용하여 가져와야 함
        return em.createQuery("select m from Chap3Member m", Chap3Member.class)
                .getResultList();
    }

    public Optional<Chap3Member> findById(Long id) {
        Chap3Member chap3Member = em.find(Chap3Member.class, id);
        return Optional.ofNullable(chap3Member);
    }

    public long count() {
        return em.createQuery("select count(m) from Chap3Member m", Long.class)
                .getSingleResult();
    }

    public Chap3Member find(Long id) {
        return em.find(Chap3Member.class, id);
    }
}
