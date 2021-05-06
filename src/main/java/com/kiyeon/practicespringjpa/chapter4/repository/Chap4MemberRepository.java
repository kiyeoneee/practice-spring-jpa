package com.kiyeon.practicespringjpa.chapter4.repository;

import com.kiyeon.practicespringjpa.chapter4.dto.Chap4MemberDto;
import com.kiyeon.practicespringjpa.chapter4.entity.Chap4Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Chap4MemberRepository extends JpaRepository<Chap4Member, Long> {
    List<Chap4Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Chap4Member> findHelloBy();

    List<Chap4Member> findTop3HelloBy();

//    NamedQuery 방식
//    @Query(name = "Chap4Member.findByUsername")
    List<Chap4Member> findByUsername(@Param("username") String username);

    @Query("select m from Chap4Member m where m.username = :username and m.age = :age")
    List<Chap4Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Chap4Member m")
    List<String> findUsernameList();

    @Query("select new com.kiyeon.practicespringjpa.chapter4.dto.Chap4MemberDto(m.id, m.username, t.name) from Chap4Member m join m.team t")
    List<Chap4MemberDto> findMemberDto();

    @Query("select m from Chap4Member m where m.username in :names")
    List<Chap4Member> findByNames(@Param("names") Collection<String> names);

    List<Chap4Member> findListByUsername(String username);
    Chap4Member findMemberByUsername(String username);
    Optional<Chap4Member> findOptionalByUsername(String username);

    @Query(value = "select m from Chap4Member  m left join m.team t",
            countQuery = "select count(m.username) from Chap4Member m")
    Page<Chap4Member> findByAge(int age, Pageable pageable);
//    Slice<Chap4Member> findByAge(int age, Pageable pageable);

    @Modifying  // executeUpdate 설정 어노테이션
    @Query("update Chap4Member  m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    // fetch join을 하게 되면 연관된 데이터를 한번에 가져옴
    @Query("select m from Chap4Member  m left join fetch m.team")
    List<Chap4Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Chap4Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Chap4Member m")
    List<Chap4Member> findMembeEntityGraph();

    @EntityGraph(attributePaths = ("team"))
    List<Chap4Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Chap4Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Chap4Member> findLockByUsername(String username);
}
