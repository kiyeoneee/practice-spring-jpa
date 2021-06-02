package com.kiyeon.practicespringjpa.chapter7.repository;

import com.kiyeon.practicespringjpa.chapter7.entity.Chap7Member;
import com.kiyeon.practicespringjpa.chapter7.entity.Chap7Team;
import com.kiyeon.practicespringjpa.chapter7.entity.MemberProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Rollback(false)
class Chap7MemberRepositoryTest {

    @Autowired
    Chap7MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void specBasic() {
        Chap7Team teamA = new Chap7Team("teamA");
        em.persist(teamA);

        Chap7Member m1 = new Chap7Member("m1", 0, teamA);
        Chap7Member m2 = new Chap7Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        Specification<Chap7Member> spec = Chap7MemberSpec.username("m1").and(Chap7MemberSpec.teamName("teamA"));
        List<Chap7Member> result = memberRepository.findAll(spec);

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void queryByExample() {
        Chap7Team teamA = new Chap7Team("teamA");
        em.persist(teamA);

        Chap7Member m1 = new Chap7Member("m1", 0, teamA);
        Chap7Member m2 = new Chap7Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        Chap7Member member = new Chap7Member("m1");

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age");
        Example<Chap7Member> example = Example.of(member, matcher);

        // JpaRepository가 상속하는 QueryByExampleExecutor<T> 에서 구현
        List<Chap7Member> result = memberRepository.findAll(example);

        assertThat(result.get(0).getUsername()).isEqualTo("m1");
    }

//    @Test
//    public void projections() {
//        Chap7Team teamA = new Chap7Team("teamA");
//        em.persist(teamA);
//
//        Chap7Member m1 = new Chap7Member("m1", 0, teamA);
//        Chap7Member m2 = new Chap7Member("m2", 0, teamA);
//        em.persist(m1);
//        em.persist(m2);
//
//        em.flush();
//        em.clear();
//
//        List<Chap7UsernameOnly> result = memberRepository.findProjectionsByUsername("m1");
//
//        for (Chap7UsernameOnly usernameOnly : result) {
//            System.out.println("usernameOnly = " + usernameOnly);
//        }
//    }

//    @Test
//    public void projectionsDto() {
//        Chap7Team teamA = new Chap7Team("teamA");
//        em.persist(teamA);
//
//        Chap7Member m1 = new Chap7Member("m1", 0, teamA);
//        Chap7Member m2 = new Chap7Member("m2", 0, teamA);
//        em.persist(m1);
//        em.persist(m2);
//
//        em.flush();
//        em.clear();
//
//        // Dto class의 경우 생성자의 parameter 명을 기준으로 필드를 조회하므로 주의
//        List<Chap7UsernameOnlyDto> result = memberRepository.findProjectionsByUsername("m1");
//
//        for (Chap7UsernameOnlyDto usernameOnly : result) {
//            System.out.println("usernameOnly = " + usernameOnly);
//        }
//    }

//    @Test
//    public void projectionsDto() {
//        Chap7Team teamA = new Chap7Team("teamA");
//        em.persist(teamA);
//
//        Chap7Member m1 = new Chap7Member("m1", 0, teamA);
//        Chap7Member m2 = new Chap7Member("m2", 0, teamA);
//        em.persist(m1);
//        em.persist(m2);
//
//        em.flush();
//        em.clear();
//
//        List<Chap7UsernameOnlyDto> result = memberRepository.findProjectionsByUsername("m1", Chap7UsernameOnlyDto.class);
//
//        for (Chap7UsernameOnlyDto usernameOnly : result) {
//            System.out.println("usernameOnly = " + usernameOnly);
//        }
//    }

    @Test
    public void projectionsDto() {
        Chap7Team teamA = new Chap7Team("teamA");
        em.persist(teamA);

        Chap7Member m1 = new Chap7Member("m1", 0, teamA);
        Chap7Member m2 = new Chap7Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        List<NestedClosedProjections> result = memberRepository.findProjectionsByUsername("m1", NestedClosedProjections.class);

        for (NestedClosedProjections nestedClosedProjections : result) {
            String username = nestedClosedProjections.getUsername();
            System.out.println("username = " + username);
            String teamName = nestedClosedProjections.getTeam().getName();
            System.out.println("teamName = " + teamName);
        }
    }

    @Test
    public void nativeQuery() {
        Chap7Team teamA = new Chap7Team("teamA");
        em.persist(teamA);

        Chap7Member m1 = new Chap7Member("m1", 0, teamA);
        Chap7Member m2 = new Chap7Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        Chap7Member result = memberRepository.findByNativeQuery("m1");
        System.out.println("result = " + result);
    }

    @Test
    public void findByNativeProjection() {
        Chap7Team teamA = new Chap7Team("teamA");
        em.persist(teamA);

        Chap7Member m1 = new Chap7Member("m1", 0, teamA);
        Chap7Member m2 = new Chap7Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        Page<MemberProjection> result = memberRepository.findByNativeProjection(PageRequest.of(1, 10));
        List<MemberProjection> content = result.getContent();
        for (MemberProjection memberProjection : content) {
            System.out.println("memberProjection = " + memberProjection.getUsername());
            System.out.println("memberProjection = " + memberProjection.getTeamName());
        }
    }
}
