package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


@SpringBootApplication
@Transactional
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }


    private MemberRepository memberRepository;
    private EntityManager em;

    public JpaApplication(MemberRepository memberRepository, EntityManager em) {
        this.memberRepository = memberRepository;
        this.em = em;

        //JPQL
        // @Query("select m from Member m where m.username like '%kim%'")
        List<Member> byMemberName = memberRepository.findByMemberName();

        System.out.println("===============================");

        // Criteria
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        // 쿼리를 코드로 작성
        Root<Member> m = query.from(Member.class);
        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
        List<Member> resultList = em.createQuery(cq).getResultList();

        System.out.println("===============================");

        // QueryDSL
        // 세팅이 조금 필요하다.



        // 네이티브 SQL
        List resultList1 = em.createNativeQuery("select MEMBER_ID, city from MEMBER").getResultList();

        // JDBC 주의사항
        Member member = new member();
        member.setUsername("member");
        em.persist(member);

        em.flush();
        // em.flush()를 하지 않으면 db에 값이 없기 때문에 결과는 0

        // dbconn.excuteQuery("select * from member");
        for(Member member1 : resultList){
            System.out.println("member1 = " + member1);
        }
        tx.commit;

    }
}
