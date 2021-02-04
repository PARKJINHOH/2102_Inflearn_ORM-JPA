package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    private static TeamRepository teamRepository;
    private static MemberRepository memberRepository;

    public JpaApplication(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;

        Member member = new Member();
        member.setUsername("member1");
        memberRepository.save(member);

        Team team = new Team();
        team.setName("teamA");

        team.getMembers().add(member);
        teamRepository.save(team);

    }
}
