package com.kiyeon.practicespringjpa.chapter5.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})  // mapping 된 필드는 추가하면 해당 객체도 출력하면서 무한루프 탈 수도 있음
@NamedQuery(
        name="Chap5Member.findByUsername",
        query="select m from Chap5Member m where m.username = :username"
)
@NamedEntityGraph(name = "Chap5Member.all", attributeNodes = @NamedAttributeNode("team"))
public class Chap5Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)  // 실무는 무조건 LAZY로 걸자!
    @JoinColumn(name = "team_id") // foreign key 이름으로 매핑
    private Chap5Team team;

    public Chap5Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Chap5Member(String username, int age, Chap5Team chap5Team) {
        this.username = username;
        this.age = age;
        if (chap5Team != null) {
            changeTeam(chap5Team);
        }
    }

    public void changeTeam(Chap5Team chap5Team) {
        this.team = chap5Team;
        chap5Team.getChap5Members().add(this);
    }
}
