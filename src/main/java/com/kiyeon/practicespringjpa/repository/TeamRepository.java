package com.kiyeon.practicespringjpa.repository;

import com.kiyeon.practicespringjpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository, CrudRepository 를 상속받으면 springJpa 가 구현체 생성
// JpaRepository 상속받으면 @Repository 생략 가능 -> spring 이 직접 구현체를 만듦
public interface TeamRepository extends JpaRepository<Team, Long> {
}
