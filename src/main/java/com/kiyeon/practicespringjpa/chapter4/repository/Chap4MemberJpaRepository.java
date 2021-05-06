package com.kiyeon.practicespringjpa.chapter4.repository;

import com.kiyeon.practicespringjpa.chapter4.entity.Chap4Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class Chap4MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Chap4Member save(Chap4Member chap4Member) {
        em.persist(chap4Member);
        return chap4Member;
    }

    public void delete(Chap4Member chap4Member) {
        em.remove(chap4Member);
    }

    public List<Chap4Member> findAll() {
        // 전체 리스트를 가져오기 위해서는 JPQL 을 사용하여 가져와야 함
        return em.createQuery("select m from Chap4Member m", Chap4Member.class)
                .getResultList();
    }

    public Optional<Chap4Member> findById(Long id) {
        Chap4Member chap4Member = em.find(Chap4Member.class, id);
        return Optional.ofNullable(chap4Member);
    }

    public long count() {
        return em.createQuery("select count(m) from Chap4Member m", Long.class)
                .getSingleResult();
    }

    public Chap4Member find(Long id) {
        return em.find(Chap4Member.class, id);
    }

    public List<Chap4Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("select m from Chap4Member m where m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    public List<Chap4Member> findByUsername(String username) {
        return em.createNamedQuery("Chap4Member.findByUsername", Chap4Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Chap4Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Chap4Member  m where m.age = :age order by m.username desc")
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long totalCount(int age) {
        return em.createQuery("select count(m) from Chap4Member  m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();

    }

    public int bulkAgePlus(int age) {
        return em.createQuery("update Chap4Member m set m.age = m.age + 1 where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
}
