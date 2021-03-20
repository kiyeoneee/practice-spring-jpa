package com.kiyeon.practicespringjpa.chapter3.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Chap3Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Chap3Member> chap3Members = new ArrayList<>();

    public Chap3Team(String name) {
        this.name = name;
    }
}
