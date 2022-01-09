import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peove
 * @date 2021-12-27-14:12
 */
@SpringBootTest
public class test {

    @Test
    public void m1 () {
        System.out.println("true = " + true);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println("list = " + list);
        String s = list.get(1);
        System.out.println("s = " + s);
    }
}
