import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    @BeforeEach
    public void beforeEach() {
        System.out.println("테스트 시작 전 준비");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("설겆이실행");
    }

    @Test
    public void test1() {
        System.out.println("test1 실행");
    }

    @Test
    public void test2() {
        System.out.println("test2 실행");
    }

    @Test
    public void test3() {
        System.out.println("test3 실행");
    }
    @BeforeAll
    public static void prepareTotal() {
        System.out.println("모든 테스트 수행 전 준비작업");
    }
    @AfterAll
    static void cleanTotal() {
        System.out.println("모든 테스트 수행 후 마지막 설겆이 작업");
    }
}