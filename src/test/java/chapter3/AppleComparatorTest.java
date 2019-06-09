package chapter3;

import chapter2.Apple;
import chapter2.AppleFilter;
import chapter2.AppleFilterBehavParam;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
public class AppleComparatorTest {

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
    public void 코드_전달(){
        printSortedApples(inventory);
        inventory.sort(new AppleComparator());
        System.out.println("after sort");
        printSortedApples(inventory);
    }

    @Test
    public void 익명_클래스(){
        printSortedApples(inventory);
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return ((Integer) o1.getWeight()).compareTo(o2.getWeight());
            }
        });
        System.out.println("after sort");
        printSortedApples(inventory);
    }

    @Test
    public void 람다_표현식(){
        printSortedApples(inventory);
        inventory.sort((o1, o2) -> ((Integer)o1.getWeight()).compareTo(o2.getWeight()));
        System.out.println("lamda after");
        printSortedApples(inventory);
    }

    @Test
    public void 메소드_레퍼런스(){
        printSortedApples(inventory);
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("method reference after");
        printSortedApples(inventory);
    }

    public void printSortedApples(List<Apple> apples){
        apples.forEach(x-> System.out.println(x.getWeight()));
    }
}
