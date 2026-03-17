import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}