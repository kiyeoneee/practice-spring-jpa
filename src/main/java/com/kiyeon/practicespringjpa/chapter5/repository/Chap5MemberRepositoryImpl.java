package com.kiyeon.practicespringjpa.chapter5.repository;

import com.kiyeon.practicespringjpa.chapter5.entity.Chap5Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class Chap5MemberRepositoryImpl implements Chap5MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Chap5Member> findMemberCustom() {
        return em.createQuery("select m from Chap5Member m")
                .getResultList();
    }
}
