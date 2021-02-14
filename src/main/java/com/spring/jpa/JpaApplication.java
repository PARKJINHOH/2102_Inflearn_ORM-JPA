package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;


@SpringBootApplication
@Transactional
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }


    private MemberRepository memberRepository;

    public JpaApplication(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

        Member member = new Member();
        member.setUsername("member1");
        member.setHomeAddress(new Address("homeCity","street","10000"));

        //Collection
        member.getFavoriteFoods().add("치킨");
        member.getFavoriteFoods().add("족발");
        member.getFavoriteFoods().add("피자");

//        member.getAddressHistory().add(new Address("old1","street","10000"));
//        member.getAddressHistory().add(new Address("old2","street","10000"));

        memberRepository.save(member);
        memberRepository.flush();

        System.out.println("===========================");
        Member findMember = memberRepository.findById(member.getId()).get();

        System.out.println("===========================");

        // 치킨 -> 한식
        findMember.getFavoriteFoods().remove("치킨");
        findMember.getFavoriteFoods().add("한식");

        System.out.println("===========================");

    }
}
