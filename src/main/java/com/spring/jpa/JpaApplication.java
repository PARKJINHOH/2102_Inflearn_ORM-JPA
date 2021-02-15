package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    private MemberRepository memberRepository;

    public JpaApplication(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

        Member member = new Member();
        member.setUsername("member1");
        memberRepository.save(member);

        // 기본문법 블로그 참조
        // https://adg0609.tistory.com/37
    }
}
