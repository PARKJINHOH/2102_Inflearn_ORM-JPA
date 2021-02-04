package com.spring.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID") // 필수, 사용하지 않으면 조인 테이블 방식을 사용하여
    // 중간에 테이블을 하나 더 추가한다.
    private List<Member> members = new ArrayList<>();

}
