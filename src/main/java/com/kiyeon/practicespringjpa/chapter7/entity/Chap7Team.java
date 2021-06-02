package com.kiyeon.practicespringjpa.chapter7.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Chap7Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Chap7Member> chap7Members = new ArrayList<>();

    public Chap7Team(String name) {
        this.name = name;
    }
}
