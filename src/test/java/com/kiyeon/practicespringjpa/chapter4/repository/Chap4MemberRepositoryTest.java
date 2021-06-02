package com.kiyeon.practicespringjpa.chapter4.repository;

import com.kiyeon.practicespringjpa.chapter4.dto.Chap4MemberDto;
import com.kiyeon.practicespringjpa.chapter4.entity.Chap4Member;
import com.kiyeon.practicespringjpa.chapter4.entity.Chap4Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@Transactional
//@Rollback(false)
class Chap4MemberRepositoryTest {

    @Autowired
    Chap4MemberRepository memberRepository;
    @Autowired
    Chap4TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;  // 같은 트랜젝션에서는 모두 같은 entity manager 사용 

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Chap4Member m1 = new Chap4Member("AAA", 10);
        Chap4Member m2 = new Chap4Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Chap4Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
    }

    @Test
    public void findHelloBy() {
        List<Chap4Member> helloBy = memberRepository.findHelloBy();
    }

    @Test
    public void findTop3HelloBy() {
        List<Chap4Member> helloBy = memberRepository.findTop3HelloBy();
    }

    @Test
    public void testNamedQuery() {
        Chap4Member m1 = new Chap4Member("AAA", 10);
        Chap4Member m2 = new Chap4Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Chap4Member> result = memberRepository.findByUsername("AAA");

        assertThat(result.get(0)).isEqualTo(m1);
    }

    @Test
    public void testQuery() {
        Chap4Member m1 = new Chap4Member("AAA", 10);
        Chap4Member m2 = new Chap4Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Chap4Member> result = memberRepository.findUser("AAA", 10);

        assertThat(result.get(0)).isEqualTo(m1);
    }

    @Test
    public void findUsernameList() {
        Chap4Member m1 = new Chap4Member("AAA", 10);
        Chap4Member m2 = new Chap4Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernames = memberRepository.findUsernameList();
        for (String s : usernames) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void findMemberDto() {
        Chap4Team team = new Chap4Team("teamA");
        teamRepository.save(team);

        Chap4Member m1 = new Chap4Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<Chap4MemberDto> memberDto = memberRepository.findMemberDto();
        for (Chap4MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void findByNames() {
        Chap4Member m1 = new Chap4Member("AAA", 10);
        Chap4Member m2 = new Chap4Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Chap4Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Chap4Member member : result) {
            System.out.println("member = " + member);
        }
    }

    @Test
    public void returnType() {
        Chap4Member m1 = new Chap4Member("AAA", 10);
        Chap4Member m1s = new Chap4Member("AAA", 30);
        Chap4Member m2 = new Chap4Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m1s);
        memberRepository.save(m2);

        List<Chap4Member> listAaa = memberRepository.findListByUsername("AAA");
        Optional<Chap4Member> OptionalAaa = memberRepository.findOptionalByUsername("BBB");

        // 단건 반환받는 타입으로 구현된 메서드에서 다건이 발견되는 경우 IncorrectResultSizeDataAccessException 발생
        assertThatExceptionOfType(IncorrectResultSizeDataAccessException.class).isThrownBy(() -> {
            memberRepository.findMemberByUsername("AAA");
        });

        // 데이터가 없는 경우 empty collection 반환해
        List<Chap4Member> lisetResult = memberRepository.findListByUsername("aebesd");
        assertThat(lisetResult).isNotNull();
        assertThat(lisetResult).hasSize(0);

        // 데이터가 없는 경우 null 반환
        Chap4Member singleResult = memberRepository.findMemberByUsername("aebesd");
        assertThat(singleResult).isNull();

        Optional<Chap4Member> optionalResult = memberRepository.findOptionalByUsername("aebesd");
        assertThat(optionalResult).isNotPresent();
    }

    @Test
    public void paging() {
        // given
        memberRepository.save(new Chap4Member("member1", 10));
        memberRepository.save(new Chap4Member("member2", 10));
        memberRepository.save(new Chap4Member("member3", 10));
        memberRepository.save(new Chap4Member("member4", 10));
        memberRepository.save(new Chap4Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

/*        // when
        Page<Chap4Member> page = memberRepository.findByAge(age, pageRequest);

        // then
        List<Chap4Member> content = page.getContent();

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
        assertThat(page.isLast()).isTrue();*/
    }

    @Test
    public void paging_slice() {
        // given
        memberRepository.save(new Chap4Member("member1", 10));
        memberRepository.save(new Chap4Member("member2", 10));
        memberRepository.save(new Chap4Member("member3", 10));
        memberRepository.save(new Chap4Member("member4", 10));
        memberRepository.save(new Chap4Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

/*        // when
        Slice<Chap4Member> page = memberRepository.findByAge(age, pageRequest);

        // then
        List<Chap4Member> content = page.getContent();

        assertThat(content.size()).isEqualTo(3);
//        assertThat(page.getTotalElements()).isEqualTo(5);  slice는 totalCount 계산을 안함
        assertThat(page.getNumber()).isEqualTo(0);
//        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
        assertThat(page.isLast()).isTrue();*/
    }

    @Test
    public void paging_map() {
        // given
        memberRepository.save(new Chap4Member("member1", 10));
        memberRepository.save(new Chap4Member("member2", 10));
        memberRepository.save(new Chap4Member("member3", 10));
        memberRepository.save(new Chap4Member("member4", 10));
        memberRepository.save(new Chap4Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        // when
        Page<Chap4Member> page = memberRepository.findByAge(age, pageRequest);
        Page<Chap4MemberDto> toMap = page.map(m -> new Chap4MemberDto(m.getId(), m.getUsername(), null));

        // then
        List<Chap4Member> content = page.getContent();

        assertThat(content.size()).isEqualTo(3);
//        assertThat(page.getTotalElements()).isEqualTo(5);  slice는 totalCount 계산을 안함
        assertThat(page.getNumber()).isEqualTo(0);
//        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
//        assertThat(page.isLast()).isTrue();
        assertThat(page.isLast()).isFalse();
    }

    @Test
    public void bulkUpdate() {
        memberRepository.save(new Chap4Member("member1", 10));
        memberRepository.save(new Chap4Member("member2", 19));
        memberRepository.save(new Chap4Member("member3", 20));
        memberRepository.save(new Chap4Member("member4", 21));
        memberRepository.save(new Chap4Member("member5", 40));

        int resultCount = memberRepository.bulkAgePlus(20);
        em.flush();
        em.clear();

        List<Chap4Member> result = memberRepository.findByUsername("member5");
        Chap4Member member5 = result.get(0);
        System.out.println("member5 = " + member5);

        assertThat(resultCount).isEqualTo(3);
    }

    @Test
    public void findMemberLazy() {
        Chap4Team teamA = new Chap4Team("teamA");
        Chap4Team teamB = new Chap4Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        Chap4Member member1 = new Chap4Member("member1", 10, teamA);
        Chap4Member member2 = new Chap4Member("member2", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Chap4Member> members = memberRepository.findAll();

        for (Chap4Member member : members) {
            System.out.println("member = " + member.getUsername());
            // member.teamClass = class com.kiyeon.practicespringjpa.chapter4.entity.Chap4Team$HibernateProxy$4dRwRwsD
            System.out.println("member.teamClass = " + member.getTeam().getClass());
            // Member 객체 내에 Team이 지연 로딩 설정되어 있다면 @ManyToOne(fetch = FetchType.LAZY)
            // 아래의 시점에 직접 데이터를 가져옴
            System.out.println("member.team = " + member.getTeam().getName());
        }
    }

    @Test
    public void queryHint() {
        Chap4Member member1 = new Chap4Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

//        Chap4Member findMember = memberRepository.findById(member1.getId()).get();
//        findMember.setUsername("member2");
        Chap4Member readOnlyMember = memberRepository.findReadOnlyByUsername(member1.getUsername());
        readOnlyMember.setUsername("member2");;

        em.flush();
    }

    @Test
    public void lock() {
        Chap4Member member1 = new Chap4Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        List<Chap4Member> findMember = memberRepository.findLockByUsername("member1");

        em.flush();
    }
}