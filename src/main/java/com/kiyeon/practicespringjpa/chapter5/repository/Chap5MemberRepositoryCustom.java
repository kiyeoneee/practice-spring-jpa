package com.kiyeon.practicespringjpa.chapter5.repository;

import com.kiyeon.practicespringjpa.chapter5.entity.Chap5Member;

import java.util.List;

public interface Chap5MemberRepositoryCustom {
    List<Chap5Member> findMemberCustom();
}
