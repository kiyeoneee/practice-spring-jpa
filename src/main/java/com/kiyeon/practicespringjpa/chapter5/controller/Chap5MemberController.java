package com.kiyeon.practicespringjpa.chapter5.controller;

import com.kiyeon.practicespringjpa.chapter5.dto.Chap5MemberDto;
import com.kiyeon.practicespringjpa.chapter5.entity.Chap5Member;
import com.kiyeon.practicespringjpa.chapter5.repository.Chap5MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class Chap5MemberController {
    private final Chap5MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Chap5Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Chap5Member member) {
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<Chap5MemberDto> List(@PageableDefault(size = 5, sort = "username") Pageable pageable) {
        return memberRepository.findAll(pageable)
                 .map(Chap5MemberDto::new);
    }

//    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++){
            memberRepository.save(new Chap5Member("user" + i, i));
        }
    }
}
