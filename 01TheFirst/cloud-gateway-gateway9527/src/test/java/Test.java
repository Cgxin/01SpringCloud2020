import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

/**
 * @author peove
 * @date 2021-12-31-11:32
 */
@SpringBootTest
public class Test {

    @org.junit.Test
    public void m1() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("now = " + now); // 2021-12-31T11:33:40.403+08:00[Asia/Shanghai]
    }
}
