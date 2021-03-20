package com.kiyeon.practicespringjpa.chapter3.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
class Chap3MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() {
        Chap3Team chap3TeamA = new Chap3Team("teamA");
        Chap3Team chap3TeamB = new Chap3Team("teamB");
        em.persist(chap3TeamA);
        em.persist(chap3TeamB);

        Chap3Member chap3Member1 = new Chap3Member("member1", 10, chap3TeamA);
        Chap3Member chap3Member2 = new Chap3Member("member2", 20, chap3TeamA);
        Chap3Member chap3Member3 = new Chap3Member("member3", 30, chap3TeamB);
        Chap3Member chap3Member4 = new Chap3Member("member4", 40, chap3TeamB);

        em.persist(chap3Member1);
        em.persist(chap3Member2);
        em.persist(chap3Member3);
        em.persist(chap3Member4);

        em.flush();
        em.clear();

        List<Chap3Member> chap3Members = em.createQuery("select m from Chap3Member m", Chap3Member.class)
                .getResultList();

        for (Chap3Member chap3Member : chap3Members) {
            System.out.println("member = " + chap3Member);
            System.out.println("-> member.team = " + chap3Member.getTeam());
        }
    }
}