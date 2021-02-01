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

        Team team = new Team();
        team.setName("TeamA");
        teamRepository.save(team);

        Member member = new Member();
        member.setUsername("member1");
//        member.setTeamId(team.getId());
        member.setTeam(team);
        memberRepository.save(member);


        // 조회
        // 기존) member를 찾고 member의 teamid를 찾아서 team을 찾았음.
        // 이후) member를 찾고 member의 team을 찾음
        Member findMember = memberRepository.findById(member.getId()).get();
        Team findTeam = findMember.getTeam();
        System.out.println("findTeam = " + findTeam.getName());
    }
}
