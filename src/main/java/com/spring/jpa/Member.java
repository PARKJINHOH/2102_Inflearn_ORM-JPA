package com.spring.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 일대다 양항뱡(추가)
    @ManyToOne
    /*
    * insertable = false, updatable = false
    * 위 2개의 옵션을 false로 지정하면 읽기 전용으로 된다.
    * 참조 Entity를 읽기 전용으로 매핑
    * */
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

}
