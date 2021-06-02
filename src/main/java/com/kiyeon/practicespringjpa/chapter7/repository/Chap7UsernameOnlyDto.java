package com.kiyeon.practicespringjpa.chapter7.repository;

public class Chap7UsernameOnlyDto {
    private final String username;

    public Chap7UsernameOnlyDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
