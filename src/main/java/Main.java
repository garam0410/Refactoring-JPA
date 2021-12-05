import Entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    static Long userKey;
    static Long testKey;

    // EntityManagerFactory 인스턴스를 생성하는 정적 메소드를 가지고 있는
    // Persistence에서 createEntitymanagerFactory() 진행
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");

    public static void main(String[] args){

        // 사용자 정보 저장
        System.out.println("사용자 정보 저장을 시작합니다.");
        transaction(TransactionType.SESSION1);

        // 검사 결과 저장
        System.out.println("검사가 끝났으니 저장을 진행합니다");
        transaction(TransactionType.SESSION2);

        emf.close();
    }

    public static void transaction(TransactionType session){
        // EntityManagerFactory로부터 EntityManager생성
        EntityManager em = emf.createEntityManager();

        // EntityManager의 작업을 수행해줄 EntityTransaction 선언
        EntityTransaction et = em.getTransaction();

        try{
            et.begin(); // 시작

            if(session == TransactionType.SESSION1)
                insertUserInfo(em);
            else if(session == TransactionType.SESSION2)
                insertTestResult(em);
            else if(session == TransactionType.SESSION3)
                setTestList(em);

            et.commit();

        }catch(Exception e){
            e.printStackTrace();
            et.rollback();  // 이전 commit 상태로 롤백
        }finally{
            em.close();
        }
    }

    public static void insertUserInfo(EntityManager em){

        // 1. 어떤 Test를 볼지 정하기
        Test test = new Test();
        TestInfo testInfo = em.find(TestInfo.class, (long) 4);

        // 2. 검사자 정보 등록
        Student student = new Student();
        student.setName("홍길동");
        student.setEmail("Gildong@daou.co.kr");
        test.setDate(new Date());
        test.setTestInfo(testInfo);

        student.addTest(test);

        em.persist(student);

        userKey = student.getId();
        testKey = test.getId();
    }

    public static void insertTestResult(EntityManager em){

        // 학생의 Test정보 가져오기
        Test test = em.find(Test.class,testKey);

        // Test에서 선택한 아이템 넣기
        for(int i = 1; i<11; i++){
            CheckedItem checkedItem = new CheckedItem();
            checkedItem.setItemnumber(i);
            checkedItem.setItemresult(i);
            test.addCheckedItems(checkedItem);
        }
    }

    // 검사 목록 초기화
    public static void setTestList(EntityManager em){
        for(int i = 1; i<6; i++){
            TestInfo testInfo = new TestInfo();
            testInfo.setTestname("검사"+i);
            em.persist(testInfo);
        }
    }
}
