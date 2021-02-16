package com.jpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaTest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {
            tx.begin();

            logic3(em);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic1(EntityManager em) {
        System.out.println("============================");
        // 프로젝션으로 Entity를 가지고와도 영속성 컨텍스트에 의해 관리가 된다.
        Member member = new Member();
        member.setUsername("member1");
        member.setAge(10);
        em.persist(member);

        em.flush();
        em.clear();

        // 영속성 컨텍스트에 관리가 됨
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        Member findMember = result.get(0);

        // 영속성 컨텍스트에 관리가 되기 때문에 변경에 대해 update 쿼리문이 나감감
        findMember.setAge(20);

    }

    private static void logic2(EntityManager em) {
        System.out.println("============================");
        // 임베디드 타입 프로텍션
        Member member = new Member();
        member.setUsername("member1");
        member.setAge(10);
        em.persist(member);

        em.flush();
        em.clear();

        // Order에 있는 Address 컬럼 출력
        em.createQuery("select o.address from Order o", Address.class).getResultList();


    }

    private static void logic3(EntityManager em) {
        System.out.println("============================");
        // 스칼라 타입 프로젝션
        Member member = new Member();
        member.setUsername("member1");
        member.setAge(10);
        em.persist(member);

        em.flush();
        em.clear();

        // 타입이 2개인데 어떻게 받을 수 있을까?
//        em.createQuery("select distinct m.username, m.age from Member m").getResultList();

        // 1. Query 타입 조회
        Query query = em.createQuery("select distinct m.username, m.age from Member m");

        // 2. Obejct[] 타입
        List<Object[]> resultList = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
        Object[] result = resultList.get(0);
        System.out.println("username = " + result[0]);
        System.out.println("age = " + result[1]);

        // 3. new 명령어로 조회
        List<MemberDTO> resultNew = em.createQuery("select new com.jpa.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
        MemberDTO memberDTO = resultNew.get(0);
        System.out.println("memberDTO.username = " + memberDTO.getUsername());
        System.out.println("memberDTO.age = " + memberDTO.getAge());
    }
}
