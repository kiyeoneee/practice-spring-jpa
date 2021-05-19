package com.kiyeon.practicespringjpa.chapter6.repository;

import com.kiyeon.practicespringjpa.chapter6.entity.Chap6Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Chap6ItemRepository extends JpaRepository<Chap6Item, Long> {

}
