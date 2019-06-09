package chapter3;

import chapter2.Apple;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static chapter2.AppleFilterBehavParam.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
public class LamdaUtilMethodTest {

    private List<Apple> inventory = new ArrayList<>();


    @Before
    public void 사과준비하기(){
        inventory.add(new Apple("green", 140));
        inventory.add(new Apple("green", 150));
        inventory.add(new Apple("red", 130));
        inventory.add(new Apple("blue", 170));
        inventory.add(new Apple("red", 140));
        inventory.add(new Apple("red", 180));
    }

    @Test
    public void Comparator_역정렬(){
        printSortedApples(inventory);
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println("sort then reverse");
        printSortedApples(inventory);
    }

    @Test
    public void Comparator_조합(){
        printSortedApples(inventory);
        inventory.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));
        System.out.println("sort then sort again with color");
        printSortedApples(inventory);
    }

    @Test
    public void Predicate_조합(){
        Predicate<Apple> redApple = (Apple a) -> a.getColor() == "red";
        List<Apple> redApples = filter(inventory, redApple);
        System.out.println("red apples:");
        printSortedApples(redApples);

        Predicate<Apple> notRedApple = redApple.negate();
        List<Apple> nonRedApples = filter(inventory, notRedApple);
        System.out.println("non red apples");
        printSortedApples(nonRedApples);

        Predicate<Apple> redAndHeavyOrJustGreenApple = redApple.and(a -> a.getWeight()>150).or(a -> a.getColor()=="green");
        List<Apple> redAndHeavyOrJustGreenApples = filter(inventory, redAndHeavyOrJustGreenApple);
        System.out.println("red and heavy or just green");
        printSortedApples(redAndHeavyOrJustGreenApples);
    }

    @Test
    public void Function_조합(){
        Function<Integer, Integer> f = x-> x+1;
        Function<Integer, Integer> g = x-> x*2;
        Function<Integer, Integer> andThen = f.andThen(g);
        Function<Integer, Integer> compose = f.compose(g);

        // 2 + 1 = 2
        assertThat(f.apply(2)).isEqualTo(3);
        // 2 * 2 = 4
        assertThat(g.apply(2)).isEqualTo(4);

        // (2+1) * 2 = 6
        assertThat(andThen.apply(2)).isEqualTo(6);

        //  2 * 2 + 1 = 5
        assertThat(compose.apply(2)).isEqualTo(5);

    }

    public void printSortedApples(List<Apple> apples){
        apples.forEach(x-> System.out.println(x.getWeight()+" "+x.getColor()));
    }
}
