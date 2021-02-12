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

        Address address = new Address("city", "street", "10");

        // 값 타입 공유참조 문제
        // member1,2 모두 같은 Address를 사용하고 있음.
        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setHomeAddress(address);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setUsername("member2");
        member2.setHomeAddress(address);
        memberRepository.save(member2);

        // 여기까지는 문제가 없다. 아래부터는 문제상황.
        // member1만 수정했는데 update 쿼리가 2번 발생 (EntityManager, EntityTransaction)
        member1.getHomeAddress().setCity("newCity");

        // 해결1
        Address copyAddress = new Address("newCity", "street", "10");

        Member member3 = new Member();
        member3.setUsername("member2");
        member3.setHomeAddress(copyAddress); // 값타입 복사한것을 넣어준다.
        memberRepository.save(member3);

        // 블로그 확인하기



    }
}
