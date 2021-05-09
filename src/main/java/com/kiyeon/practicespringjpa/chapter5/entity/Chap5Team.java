package com.kiyeon.practicespringjpa.chapter5.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Chap5Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Chap5Member> chap5Members = new ArrayList<>();

    public Chap5Team(String name) {
        this.name = name;
    }
}
