package com.kiyeon.practicespringjpa.chapter7.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(of = {"id", "username", "age"})  // mapping 된 필드는 추가하면 해당 객체도 출력하면서 무한루프 탈 수도 있음
//@NamedQuery(
//        name="Chap7Member.findByUsername",
//        query="select m from Chap7Member m where m.username = :username"
//)
public class Chap7Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)  // 실무는 무조건 LAZY로 걸자!
    @JoinColumn(name = "team_id") // foreign key 이름으로 매핑
    private Chap7Team team;

    public Chap7Member(String username) {
        this.username = username;
    }

    public Chap7Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Chap7Member(String username, int age, Chap7Team chap7Team) {
        this.username = username;
        this.age = age;
        if (chap7Team != null) {
            changeTeam(chap7Team);
        }
    }

    public void changeTeam(Chap7Team chap7Team) {
        this.team = chap7Team;
        chap7Team.getChap7Members().add(this);
    }

}
