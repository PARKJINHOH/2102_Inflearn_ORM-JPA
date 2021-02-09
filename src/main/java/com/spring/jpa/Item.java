package com.spring.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn // DTYPE 에 테이블 명을 넣는다
@Setter
@Getter
public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
