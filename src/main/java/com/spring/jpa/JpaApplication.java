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

        Team team = new Team();
        team.setName("team1");
        teamRepository.save(team);

        Member member = new Member();
        member.setName("user1");
        member.setTeam(team);
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();
        System.out.println(findMember.getId());
        System.out.println(findMember.getName());

        // 그러면 필요할 때만 가지고 오고 싶어할 때 프록시를 생각하면 된다.
        System.out.println("findMember.class = " + findMember.getTeam().getClass());
        System.out.println("=========================================");

        // 프록시 조회가 아님.
        System.out.println(findMember.getClass());

    }

}
