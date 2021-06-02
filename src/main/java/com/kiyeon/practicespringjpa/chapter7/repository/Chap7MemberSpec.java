package com.kiyeon.practicespringjpa.chapter7.repository;

import com.kiyeon.practicespringjpa.chapter7.entity.Chap7Member;
import com.kiyeon.practicespringjpa.chapter7.entity.Chap7Team;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class Chap7MemberSpec {
    public static Specification<Chap7Member> teamName(final String teamName) {
        return (Specification<Chap7Member>) (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(teamName)) {
                return null;
            }

            // Jpa criteria 문법
            Join<Chap7Member, Chap7Team> t = root.join("team", JoinType.INNER); // 회원과 조인
            return criteriaBuilder.equal(t.get("name"), teamName);
        };
    }

    public static Specification<Chap7Member> username(final String username) {
        return (Specification<Chap7Member>) (root, query, builder) ->
                builder.equal(root.get("username"), username);
    }
}
