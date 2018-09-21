package cn.coderme.cblog;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/26
 * Time:14:17
 */
public class LambdaTest {

    @Test
    public void list() {
        List<Integer> longList = Arrays.asList(new Integer[]{1,2,4,7,3,873,23});
        Collections.sort(longList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        Collections.sort(longList, (o1, o2)-> o2.compareTo(o1));
        System.out.println(longList);

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        features.forEach(System.out::println);
    }
}