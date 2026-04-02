import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class JUnitTest {

    @DisplayName("1+2 = 3")
    @Test
    public void JUnitTest() {
        int n1 = 1;
        int n2 = 2;

        int sum = n1 + n2;

        Assertions.assertEquals(3 , sum);
    }

    @DisplayName("1+3 = 4")
    @Test
    public void junitFailTest() {
        int n1 = 1;
        int n2 = 3;

        int sum = n1 + n2;

        Assertions.assertEquals(3 , sum);
    }
}
