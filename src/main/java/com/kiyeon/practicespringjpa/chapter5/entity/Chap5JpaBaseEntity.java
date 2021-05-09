package com.kiyeon.practicespringjpa.chapter5.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/*
* 아래의 어노테이션이 없으면 그냥 클래스 멤버만 상속해서 사용
* 아래의 어노테이션 추가를 통해 JPA가 테이블에도 적용이 가능하도록 추가
* */
@MappedSuperclass
@Getter
public class Chap5JpaBaseEntity {

    @Column(updatable = false, insertable = true)
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
