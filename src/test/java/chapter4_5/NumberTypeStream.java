package chapter4_5;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberTypeStream {
    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void 기본형_특화스트림(){
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);

        OptionalDouble averageCalory = menu.stream()
                .mapToInt(Dish::getCalories)
                .average();
        System.out.println(averageCalory.getAsDouble());
    }

    @Test
    public void 객체_스트림_복원(){
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        IntStream intStream2 = menu.stream().mapToInt(Dish::getCalories);
        System.out.println(intStream2.sum());
        Stream<Integer> stream = intStream.boxed();
        System.out.println(stream.reduce(0, Integer::sum));
    }

    @Test
    public void rangeClosed(){
        IntStream stream = IntStream.rangeClosed(1,100);
        System.out.println(stream.sum());
    }

    @Test
    public void 피타고라스_수(){
        Stream<double[]> pythagoreanTriples =
                IntStream.rangeClosed(1,100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a,100)
                        .mapToObj(
                        b-> new double[]{a,b,Math.sqrt(a*a+b*b)})
                        .filter(t->t[2] %1 ==0));
        pythagoreanTriples.forEach(t-> System.out.println((int)t[0]+","+(int)t[1]+","+(int)t[2]));
    }

}
