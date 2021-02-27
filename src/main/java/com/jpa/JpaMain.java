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
        System.out.println("==========컬렉션 페치조인===========");

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

        String query = "select t from Team t join fetch t.members";

        List<Team> resultList = em.createQuery(query, Team.class).getResultList();

        for (Team team : resultList) {
            System.out.println("team = " + team.getName() + "| members = " + team.getMembers().size());
            for(Member member : team.getMembers()){
                System.out.println(" -> member = " + member);
            }
//            team = 팀A| members = 2
//            -> member = Member{id=3, username='회원1', age=0}
//            -> member = Member{id=4, username='회원2', age=0}
//            team = 팀A| members = 2                            -> 중복
//            -> member = Member{id=3, username='회원1', age=0}
//            -> member = Member{id=4, username='회원2', age=0} -

//            team = 팀B| members = 1
//            -> member = Member{id=5, username='회원3', age=0}
        }

    }

    private static void logic2(EntityManager em) {
        System.out.println("==========컬렉션 페치조인 (중복제거)===========");

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

        String query = "select DISTINCT t from Team t join fetch t.members";

        List<Team> resultList = em.createQuery(query, Team.class).getResultList();

        for (Team team : resultList) {
            System.out.println("team = " + team.getName() + "| members = " + team.getMembers().size());
            for(Member member : team.getMembers()){
                System.out.println(" -> member = " + member);
            }
        }

    }

    private static void logic3(EntityManager em) {
        System.out.println("==========컬렉션 페치조인 (페치조인과 일반조인 차이)===========");

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

        // 일반조인
       String query = "select t from Team t join t.members";

        List<Team> resultList = em.createQuery(query, Team.class).getResultList();

        for (Team team : resultList) {
            System.out.println("team = " + team.getName() + "| members = " + team.getMembers().size());
            for(Member member : team.getMembers()){
                System.out.println(" -> member = " + member);
            }
        }

        // SQL문이 3번 출력된다.
        // 처음 쿼리를 날릴 때 SELECT T에 대한 Team만 조회가 되며, 지연로딩으로 member가 실제 사용될 떄 sql문이 나감.

    }
}
