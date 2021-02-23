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

            logic1(em);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic1(EntityManager em) {
        System.out.println("==========경로 표현식 특징===========");

        Member member1 = new Member();
        member1.setUsername("관리자1");
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("관리자1");
        em.persist(member2);

        em.flush();
        em.clear();

        // 상태필드 username에서 더이상 참조할 수 있는게 없다.
        String query1 = "select m.username from Member m";
//        List<String> result = em.createQuery(query1, String.class).getResultList();

        // 묵시적인 내부 조인 발생 ** 중요, 탐색이 가능하다. m.team.name
        String query2 = "select m.team from Member m";
//        List<Team> teamResult = em.createQuery(query2, Team.class).getResultList();

        // 묵시적 내부 조인 발생, 탐색이 안된다.
        String query3 = "select t.members from Team t";
        // From절에서 명시적으로 조인을 통해 탐색이 가능하다.
        String query31 = "select m.team.name from Team t join t.members m";
        List result = em.createQuery(query31, Collection.class).getResultList();

        /*
        * 가급적 경로 표현식은 사용하지 않는게 좋다.
        * 묵시적 조인이 일어나기 때문이다.
        * 명시적(join 키워드를 직접 입력) 조인을 해야 튜닝하기도 쉽다.
        */

    }
}
