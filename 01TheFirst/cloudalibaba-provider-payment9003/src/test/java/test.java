import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @author peove
 * @date 2022-01-05-16:56
 */
@SpringBootTest
public class test {

    @Test
    public void m1() {
        for (int i = 0; i < 3; i++) {
            System.out.println("UUID.randomUUID().toString() = " + UUID.randomUUID().toString());
        }
    }
}
