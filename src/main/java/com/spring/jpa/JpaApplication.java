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

        // Member를 조회하면 select에 Team까지 Join문 쿼리가 출력된다.
        //    @ManyToOne
        //    @JoinColumn(name = "TEAM_ID")
        //    private Team team;

        // FetchType.LAZY를 설정하면 Member만 조회한다.
        //    @ManyToOne(fetch = FetchType.LAZY)
        //    @JoinColumn(name = "TEAM_ID")
        //    private Team team;
        Member findMember = memberRepository.findById(member.getId()).get();
        System.out.println(findMember.getId());
        System.out.println(findMember.getName());

        // 그러면 필요할 때만 가지고 오고 싶어할 때 프록시를 생각하면 된다.
        System.out.println("findMember.class = " + findMember.getTeam().getClass());
        System.out.println("=========================================");

        // Error가 나지만 실제 Service단에서 실행하면 가능.
//        System.out.println("getName : " + findMember.getTeam().getName());


    }

}
