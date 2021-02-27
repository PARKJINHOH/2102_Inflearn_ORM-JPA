package com.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaTest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {
            tx.begin();

            logic2(em);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic1(EntityManager em) {
        System.out.println("==========페치조인 (적용 전)===========");

        Team teamA = new Team();
        teamA.setName("팀A");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("팀B");
        em.persist(teamB);

        Member member1 = new Member();
        member1.setUsername("회원1");
        member1.setTeam(teamA);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("회원2");
        member2.setTeam(teamA);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("회원3");
        member3.setTeam(teamB);
        em.persist(member3);

        em.flush();
        em.clear();

        String query = "select m from Member m";
        List<Member> resultList = em.createQuery(query, Member.class).getResultList();

        for (Member member : resultList) {
            System.out.println("member = " + member.getUsername() + ", " +
                    member.getTeam().getName());
            // 회원1, 팀A(SQL문)
            // 회원2, 팀A(1차 캐시, 영속성 컨텍스트)
            // 회원3, 팀A(SQL문)

            // 쿼리가 총 3번이 나간다.
        }

    }

    private static void logic2(EntityManager em) {
        System.out.println("==========페치조인 (적용 후)===========");

        Team teamA = new Team();
        teamA.setName("팀A");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("팀B");
        em.persist(teamB);

        Member member1 = new Member();
        member1.setUsername("회원1");
        member1.setTeam(teamA);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("회원2");
        member2.setTeam(teamA);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("회원3");
        member3.setTeam(teamB);
        em.persist(member3);

        em.flush();
        em.clear();

        String query = "select m from Member m join fetch m.team";

        // 프록시가 아니다, 실제 Entity(실제 Data)
        List<Member> resultList = em.createQuery(query, Member.class).getResultList();

        for (Member member : resultList) {
            System.out.println("member = " + member.getUsername() + ", " +
                    member.getTeam().getName());
            // 회원1, 팀A(SQL문)
            // 회원2, 팀A(1차 캐시, 영속성 컨텍스트)
            // 회원3, 팀A(SQL문)

            // 쿼리가 총 3번이 나간다.
        }

    }
}
