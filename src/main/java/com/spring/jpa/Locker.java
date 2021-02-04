package com.spring.jpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    // 읽기 전용
    // 일대일 양방향
    @OneToOne(mappedBy = "locker")
    private Member member;
}
