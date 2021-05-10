package com.kiyeon.practicespringjpa.chapter5.dto;

import com.kiyeon.practicespringjpa.chapter5.entity.Chap5Member;
import lombok.Data;

@Data
public class Chap5MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public Chap5MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    public Chap5MemberDto(Chap5Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
