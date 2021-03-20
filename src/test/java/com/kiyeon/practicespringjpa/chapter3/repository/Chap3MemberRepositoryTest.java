package com.kiyeon.practicespringjpa.chapter3.repository;

import com.kiyeon.practicespringjpa.chapter3.entity.Chap3Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class Chap3MemberRepositoryTest {

    @Autowired
    Chap3MemberRepository chap3MemberRepository;

    @Test
    public void testMember() {
        Chap3Member chap3Member = new Chap3Member("memberA");
        Chap3Member savedChap3Member = chap3MemberRepository.save(chap3Member);

        Optional<Chap3Member> findById = chap3MemberRepository.findById( savedChap3Member.getId());
        Chap3Member findChap3Member = findById.get();

        assertThat(findChap3Member.getId()).isEqualTo(savedChap3Member.getId());
        assertThat(findChap3Member.getUsername()).isEqualTo(savedChap3Member.getUsername());
        assertThat(findChap3Member).isEqualTo(savedChap3Member);
    }

    @Test
    public void basicCRUD() {
        Chap3Member chap3Member1 = new Chap3Member("member1");
        Chap3Member chap3Member2 = new Chap3Member("member2");
        chap3MemberRepository.save(chap3Member1);
        chap3MemberRepository.save(chap3Member2);

        // 단건 조회 검증
        Chap3Member findChap3Member1 = chap3MemberRepository.findById(chap3Member1.getId()).get();
        Chap3Member findChap3Member2 = chap3MemberRepository.findById(chap3Member2.getId()).get();
        assertThat(findChap3Member1).isEqualTo(chap3Member1);
        assertThat(findChap3Member2).isEqualTo(chap3Member2);

        // 리스트 조회 검증
        List<Chap3Member> all = chap3MemberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = chap3MemberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        chap3MemberRepository.delete(chap3Member1);
        chap3MemberRepository.delete(chap3Member2);
        long deletedCount = chap3MemberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}