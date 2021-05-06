package com.kiyeon.practicespringjpa.chapter4.repository;

import com.kiyeon.practicespringjpa.chapter4.entity.Chap4Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class Chap4MemberJpaRepositoryTest {

    @Autowired
    Chap4MemberJpaRepository memberJpaRepository;

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Chap4Member m1 = new Chap4Member("AAA", 10);
        Chap4Member m2 = new Chap4Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Chap4Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
    }

    @Test
    public void testNamedQuery() {
        Chap4Member m1 = new Chap4Member("AAA", 10);
        Chap4Member m2 = new Chap4Member("BBB", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Chap4Member> result = memberJpaRepository.findByUsername("AAA");

        assertThat(result.get(0)).isEqualTo(m1);
    }

    @Test
    public void paging() {
        // given
        memberJpaRepository.save(new Chap4Member("member1", 10));
        memberJpaRepository.save(new Chap4Member("member2", 10));
        memberJpaRepository.save(new Chap4Member("member3", 10));
        memberJpaRepository.save(new Chap4Member("member4", 10));
        memberJpaRepository.save(new Chap4Member("member5", 10));

        int age = 10, offset = 0, limit = 3;

        // when
        List<Chap4Member> members = memberJpaRepository.findByPage(age, offset, limit);
        long totalCount = memberJpaRepository.totalCount(age);

        // then
        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(5);
    }

    @Test
    public void bulkUpdate() {
        memberJpaRepository.save(new Chap4Member("member1", 10));
        memberJpaRepository.save(new Chap4Member("member2", 19));
        memberJpaRepository.save(new Chap4Member("member3", 20));
        memberJpaRepository.save(new Chap4Member("member4", 21));
        memberJpaRepository.save(new Chap4Member("member5", 40));

        int resultCount = memberJpaRepository.bulkAgePlus(20);

        assertThat(resultCount).isEqualTo(3);
    }
}