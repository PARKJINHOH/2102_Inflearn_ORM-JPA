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

        /*
        * 일대다, Team이 주인이 될 수 있다.
        * 하지만 객체입장에서는 가능하지 DB입장에서는 FK는 (다)쪽에 들어가기 때문에
        * 아래처럼 member를 추가할 때 Member 테이블에 업데이트 쿼리가 실행이 된다.
        * 성능상에 문제까지는 아니지만 불필요한 쿼리문이 나간다.
        * */
        team.getMembers().add(member); // ???
        teamRepository.save(team);

    }
}
