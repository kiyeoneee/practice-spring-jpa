package com.kiyeon.practicespringjpa.chapter6.repository;

import com.kiyeon.practicespringjpa.chapter6.entity.Chap6Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Chap6ItemRepositoryTest {

    @Autowired
    Chap6ItemRepository itemRepository;

    @Test
    public void save() {
        Chap6Item item = new Chap6Item("A");
        itemRepository.save(item);
    }
}