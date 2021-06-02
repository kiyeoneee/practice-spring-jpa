package com.kiyeon.practicespringjpa.chapter7.repository;

import com.kiyeon.practicespringjpa.chapter7.entity.Chap7Member;
import com.kiyeon.practicespringjpa.chapter7.entity.MemberProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Chap7MemberRepository extends JpaRepository<Chap7Member, Long>, JpaSpecificationExecutor<Chap7Member> {

//    List<Chap7UsernameOnly> findProjectionsByUsername(@Param("username") String username);

//    List<Chap7UsernameOnlyDto> findProjectionsByUsername(@Param("username") String username);

    <T> List<T> findProjectionsByUsername(@Param("username") String username, Class<T> type);

    @Query(value = "select * from member where username = ?", nativeQuery = true)
    Chap7Member findByNativeQuery(String username);

    @Query(value = "select m.member_id as id, m.username as username, t.name as teamName from Chap7Member m left join Chap7Team t",
            countQuery = "select count(*) from Chap7Member",  // 네이티브 쿼리이기 때문에 카운트 쿼리 꼭 필요
            nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
