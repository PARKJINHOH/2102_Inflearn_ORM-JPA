package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    private MemberRepository memberRepository;
    private TeamRepository teamRepository;

    public JpaApplication(MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;

        Member member = new Member();
        member.setName("user1");
        memberRepository.save(member);


        // find() 메소드를 사용하면 reference를 호출하는 시점에 select 쿼리가 출력이 된다.
        Member findMember = memberRepository.findById(member.getId()).get();
        // --여기까지 실행하면 select 쿼리가 실행된다. --
        System.out.println(findMember.getId());
        System.out.println(findMember.getName());
        // --여기까지 실행하면 select 쿼리가 실행된다. --


        // EntityManager의 getReference = JpaRepositocy의 getOne과 동일하다.

        Member findMember2 = memberRepository.getOne(member.getId());
        System.out.println(findMember2.getClass());

        // --여기까지 실행하면 select 쿼리가 실행이 안된다. --
        // System.out.println(findMember.getId());
        // System.out.println(findMember.getName());
        // --여기까지 실행하면 select 쿼리가 실행된다. --

    }
}
