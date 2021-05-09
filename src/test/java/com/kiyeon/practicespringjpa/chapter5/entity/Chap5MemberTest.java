package com.kiyeon.practicespringjpa.chapter5.entity;

import com.kiyeon.practicespringjpa.chapter5.repository.Chap5MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Member;

@SpringBootTest
@Transactional
@Rollback(false)
public class Chap5MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    Chap5MemberRepository memberRepository;

    @Test
    public void jpaEventBaseEntity() throws Exception {
        Chap5Member member = new Chap5Member("member1");
        memberRepository.save(member);  // @PrePersist 발생

        // 실제 서비스의 테스트 코드에서 사용은 좋지 않음
        Thread.sleep(100);
        member.setUsername("member2");

        em.flush();  // @PreUpdate
        em.clear();

        Chap5Member findMember = memberRepository.findById(member.getId()).get();

        System.out.println("findMember.createdDate = " + findMember.getCreatedDate());
        System.out.println("findMember.updatedDate = " + findMember.getLastModifiedDate());
        System.out.println("findMember.createdBy = " + findMember.getCreatedBy());
        System.out.println("findMember.updatedBy = " + findMember.getLastModifiedBy());
    }
}
