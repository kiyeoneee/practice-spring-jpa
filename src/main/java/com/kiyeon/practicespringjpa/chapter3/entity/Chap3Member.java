package com.kiyeon.practicespringjpa.chapter3.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})  // mapping 된 필드는 추가하면 해당 객체도 출력하면서 무한루프 탈 수도 있음
public class Chap3Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id") // foreign key 이름으로 매핑
    private Chap3Team team;

    public Chap3Member(String username) {
        this.username = username;
    }

    public Chap3Member(String username, int age, Chap3Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Chap3Team chap3Team) {
        this.team = chap3Team;
        chap3Team.getChap3Members().add(this);
    }
}
