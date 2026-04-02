import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    @BeforeEach // 테스트 케이스 실행하기 이전에 실행
    public void prepareEachTest(){
        System.out.println("prepareEachTest");

    }

    @AfterEach // 각 테스트 케이스 실행한 이후 실행
    public void afterEachTest(){
        System.out.println("afterEachTest");
    }

    @BeforeAll // 모든 테스트 실행전 static 메소드로 만들어야함
    static void PrepareAllTest(){
        System.out.println("PrepareAllTest");
    }

    @AfterAll // 모든 테스트 수행후
    static void CleanAllTest(){
        System.out.println("CleanAllTest");
    }


    @Test
    public void test1(){

        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }
}
