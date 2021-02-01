package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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

        Team team = new Team();
        team.setName("TeamA");
        teamRepository.save(team);

        Member member = new Member();
        member.setUsername("member1");
        member.setTeam(team);
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();
        List<Member> members = findMember.getTeam().getMembers(); // member에서 team을 찾고 team의 member를 찾는다.

        for (Member m : members) {
            System.out.println("member = " + m.getUsername());
        }

    }
}
