package com.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
        System.out.println("==========조인===========");

        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member.setUsername("member1");
        member.setUsername("TeamA"); // theat join 예제
        member.setAge(10);
        member.setTeam(team);

        em.persist(member);

        em.flush();
        em.clear();

        String left_join = "select m from Member m left join m.team";
        String inner_join = "select m from Member m inner join m.team";
        String theta_join = "select m from Member m, Team t where m.username = t.name";
        List<Member> resultList = em.createQuery(theta_join, Member.class).getResultList();
    }


    private static void logic2(EntityManager em) {
        System.out.println("==========조인 대상 필터링(on)===========");

        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member.setUsername("member1");
        member.setUsername("TeamA"); // theat join 예제
        member.setAge(10);
        member.setTeam(team);

        em.persist(member);

        em.flush();
        em.clear();

        String leftJoin = "select m from Member m left join m.team t on t.name = 'A'";
        List<Member> resultList = em.createQuery(leftJoin, Member.class).getResultList();
    }

    private static void logic3(EntityManager em) {
        System.out.println("==========연관관계 없는 조인(on)===========");

        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member.setUsername("member1");
        member.setUsername("TeamA"); // theat join 예제
        member.setAge(10);
        member.setTeam(team);

        em.persist(member);

        em.flush();
        em.clear();

        String leftJoin = "select m from Member m left join Team t on m.username = t.name";
        List<Member> resultList = em.createQuery(leftJoin, Member.class).getResultList();
    }
}
