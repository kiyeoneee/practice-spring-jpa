package com.kiyeon.practicespringjpa.chapter5.repository;

import com.kiyeon.practicespringjpa.chapter5.dto.Chap5MemberDto;
import com.kiyeon.practicespringjpa.chapter5.entity.Chap5Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Chap5MemberRepository extends JpaRepository<Chap5Member, Long>, Chap5MemberRepositoryCustom{
    List<Chap5Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Chap5Member> findHelloBy();

    List<Chap5Member> findTop3HelloBy();

//    NamedQuery 방식
//    @Query(name = "Chap5Member.findByUsername")
    List<Chap5Member> findByUsername(@Param("username") String username);

    @Query("select m from Chap5Member m where m.username = :username and m.age = :age")
    List<Chap5Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Chap5Member m")
    List<String> findUsernameList();

    @Query("select new com.kiyeon.practicespringjpa.chapter5.dto.Chap5MemberDto(m.id, m.username, t.name) from Chap5Member m join m.team t")
    List<Chap5MemberDto> findMemberDto();

    @Query("select m from Chap5Member m where m.username in :names")
    List<Chap5Member> findByNames(@Param("names") Collection<String> names);

    List<Chap5Member> findListByUsername(String username);
    Chap5Member findMemberByUsername(String username);
    Optional<Chap5Member> findOptionalByUsername(String username);

    @Query(value = "select m from Chap5Member  m left join m.team t",
            countQuery = "select count(m.username) from Chap5Member m")
    Page<Chap5Member> findByAge(int age, Pageable pageable);
//    Slice<Chap5Member> findByAge(int age, Pageable pageable);

    @Modifying  // executeUpdate 설정 어노테이션
    @Query("update Chap5Member  m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    // fetch join을 하게 되면 연관된 데이터를 한번에 가져옴
    @Query("select m from Chap5Member  m left join fetch m.team")
    List<Chap5Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Chap5Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Chap5Member m")
    List<Chap5Member> findMembeEntityGraph();

    @EntityGraph(attributePaths = ("team"))
    List<Chap5Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Chap5Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Chap5Member> findLockByUsername(String username);
}
