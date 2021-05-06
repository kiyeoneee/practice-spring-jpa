package com.kiyeon.practicespringjpa.chapter4.dto;

import lombok.Data;

@Data
public class Chap4MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public Chap4MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
