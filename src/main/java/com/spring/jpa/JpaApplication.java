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


    }
}
