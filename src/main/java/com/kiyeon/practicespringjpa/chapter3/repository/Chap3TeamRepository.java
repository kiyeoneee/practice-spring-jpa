package com.kiyeon.practicespringjpa.chapter3.repository;

import com.kiyeon.practicespringjpa.chapter3.entity.Chap3Team;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository, CrudRepository 를 상속받으면 springJpa 가 구현체 생성
// JpaRepository 상속받으면 @Repository 생략 가능 -> spring 이 직접 구현체를 만듦
public interface Chap3TeamRepository extends JpaRepository<Chap3Team, Long> {
}
