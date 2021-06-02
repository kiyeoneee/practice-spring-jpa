package com.kiyeon.practicespringjpa.chapter7.repository;

import org.springframework.beans.factory.annotation.Value;

public interface Chap7UsernameOnly {

    // username과 타겟(멤버)의 age 값을 가져와서 문자열로 더해서 넣어
    @Value("#{target.username + ' ' + target.age}")  // Open Projection
    String getUsername();
}
