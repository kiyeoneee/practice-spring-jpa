package com.kiyeon.practicespringjpa.chapter5.repository;

import com.kiyeon.practicespringjpa.chapter5.entity.Chap5Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
public class Chap5MemberRespositoryTest {
    @Autowired
    Chap5MemberRepository memberRepository;

    @Test
    public void callCustom() {
        List<Chap5Member> result = memberRepository.findMemberCustom();
    }
}
