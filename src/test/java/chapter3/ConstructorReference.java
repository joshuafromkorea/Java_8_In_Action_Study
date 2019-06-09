package chapter3;

import chapter2.Apple;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootTest
public class ConstructorReference {

    @Test
    public void 디폴트_생성자_레퍼런스_만들기(){
        Supplier<Apple> c1 = () ->new Apple();
        Apple apple1 = c1.get();
        assertThat(apple1.getColor()).isEqualTo("default color");

        Supplier<Apple> c2 = Apple::new;
        Apple apple2 = c2.get();
        assertThat(apple2.getColor()).isEqualTo(apple1.getColor());
    }

    @Test
    public void 한개의_파라미터를_갖는_생성자_레퍼런스_만들기(){
        Function<Integer, Apple> c1 = (weight) -> new Apple(weight);
        Function<Integer, Apple> c2 = Apple::new;

        assertThat(c1.apply(100).getWeight()).isEqualTo(c2.apply(100).getWeight());

        List<Integer> weights = Arrays.asList(7,3,4,10) ;
        List<Apple> apples = map(weights, Apple::new);
        apples.stream().forEach(x-> System.out.println(x.getWeight()));
    }

    @Test
    public void 두개의_파라미터를_갖는_생성자_레퍼런스_만들기(){
        BiFunction<String, Integer, Apple> c1 = Apple::new;
        BiFunction<String, Integer, Apple> c2 = (color, weight) -> new Apple(color,weight);

        Apple apple1 = c1.apply("red", 100);
        Apple apple2 = c2.apply("red", 150);

        assertThat(apple1.getColor()).isEqualTo(apple2.getColor());
        assertThat(apple1.getWeight()>apple2.getWeight()).isEqualTo(false);


    }

    private List<Apple> map(List<Integer> list, Function<Integer, Apple>f){
        List<Apple> result = new ArrayList<>();
        for(Integer e : list){
            result.add(f.apply(e));
        }
        return result;
    }
}
