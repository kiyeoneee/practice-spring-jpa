package com.kiyeon.practicespringjpa.chapter4.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})  // mapping 된 필드는 추가하면 해당 객체도 출력하면서 무한루프 탈 수도 있음
@NamedQuery(
        name="Chap4Member.findByUsername",
        query="select m from Chap4Member m where m.username = :username"
)
@NamedEntityGraph(name = "Chap4Member.all", attributeNodes = @NamedAttributeNode("team"))
public class Chap4Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)  // 실무는 무조건 LAZY로 걸자!
    @JoinColumn(name = "team_id") // foreign key 이름으로 매핑
    private Chap4Team team;

    public Chap4Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Chap4Member(String username, int age, Chap4Team chap4Team) {
        this.username = username;
        this.age = age;
        if (chap4Team != null) {
            changeTeam(chap4Team);
        }
    }

    public void changeTeam(Chap4Team chap4Team) {
        this.team = chap4Team;
        chap4Team.getChap4Members().add(this);
    }
}
