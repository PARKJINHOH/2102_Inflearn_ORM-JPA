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
        member.setHomeAddress(new Address("city","street","10"));
        member.setWorkPeriod(new Period());

        memberRepository.save(member);


    }
}
