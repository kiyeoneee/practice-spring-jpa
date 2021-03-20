package com.kiyeon.practicespringjpa.chapter3.repository;

import com.kiyeon.practicespringjpa.chapter3.entity.Chap3Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Chap3MemberRepository extends JpaRepository<Chap3Member, Long> {
}
