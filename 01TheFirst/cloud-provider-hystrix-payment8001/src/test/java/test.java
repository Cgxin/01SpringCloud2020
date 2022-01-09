import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.nio.ch.Net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author peove
 * @date 2021-12-28-11:06
 */
@SpringBootTest
public class test {

    @Test
    public void m1() {
//        int a = 10 / 0; // java.lang.ArithmeticException: / by zero

        System.out.println("1480149875906707461".length());
        System.out.println("true = " + true);
    }

    @Test
    public void m2() {
        IdGenerateSnowflake snowflake = new IdGenerateSnowflake();
        for (int i = 0; i < 10; i++) {
            System.out.println(snowflake.snowflakeId());
        }

//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            threadPool.submit(() -> {
//                System.err.println(snowflake.snowflakeId());
//            });
//        }
//        threadPool.shutdown();;
    }
}
