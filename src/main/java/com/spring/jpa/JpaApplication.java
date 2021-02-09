package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    private MemberRepository memberRepository;

    public JpaApplication(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

        Member member = new Member();
        member.setName("user1");
        member.setCreatedBy("kim");
        member.setCreatedDate(LocalDateTime.now());
        memberRepository.save(member);

    }
}
