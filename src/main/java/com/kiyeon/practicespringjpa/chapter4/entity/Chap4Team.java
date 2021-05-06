package com.kiyeon.practicespringjpa.chapter4.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Chap4Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Chap4Member> chap4Members = new ArrayList<>();

    public Chap4Team(String name) {
        this.name = name;
    }
}
