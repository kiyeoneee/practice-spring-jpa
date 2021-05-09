package com.kiyeon.practicespringjpa.chapter5.repository;

import com.kiyeon.practicespringjpa.chapter5.entity.Chap5Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class Chap5MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Chap5Member save(Chap5Member chap5Member) {
        em.persist(chap5Member);
        return chap5Member;
    }

    public void delete(Chap5Member chap5Member) {
        em.remove(chap5Member);
    }

    public List<Chap5Member> findAll() {
        // 전체 리스트를 가져오기 위해서는 JPQL 을 사용하여 가져와야 함
        return em.createQuery("select m from Chap5Member m", Chap5Member.class)
                .getResultList();
    }

    public Optional<Chap5Member> findById(Long id) {
        Chap5Member chap5Member = em.find(Chap5Member.class, id);
        return Optional.ofNullable(chap5Member);
    }

    public long count() {
        return em.createQuery("select count(m) from Chap5Member m", Long.class)
                .getSingleResult();
    }

    public Chap5Member find(Long id) {
        return em.find(Chap5Member.class, id);
    }

    public List<Chap5Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("select m from Chap5Member m where m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    public List<Chap5Member> findByUsername(String username) {
        return em.createNamedQuery("Chap5Member.findByUsername", Chap5Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Chap5Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Chap5Member  m where m.age = :age order by m.username desc")
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long totalCount(int age) {
        return em.createQuery("select count(m) from Chap5Member  m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();

    }

    public int bulkAgePlus(int age) {
        return em.createQuery("update Chap5Member m set m.age = m.age + 1 where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
}
