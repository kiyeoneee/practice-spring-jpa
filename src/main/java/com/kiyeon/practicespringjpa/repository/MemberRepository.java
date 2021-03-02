package com.kiyeon.practicespringjpa.repository;

import com.kiyeon.practicespringjpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
