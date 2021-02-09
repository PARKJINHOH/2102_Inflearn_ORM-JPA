package com.spring.jpa;

import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
public abstract class BaseEntity {

    //  Member와 Team에 공통으로 들어간다.
//    @Column(name = "INSERT_MEMBER") // 컬럼명 변경도 가능
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

}
