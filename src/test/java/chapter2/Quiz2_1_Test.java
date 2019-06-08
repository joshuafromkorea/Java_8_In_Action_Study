package chapter2;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Quiz2_1_Test {

    private List<Apple> inventory = new ArrayList<>();
    private Quiz2_1 target = new Quiz2_1();

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
    public void prettyApplePrintTest(){
        target.prettyPrintApple(inventory, new Quiz2_1.WordyFormatter());
        System.out.println("==============================");
        target.prettyPrintApple(inventory, new Quiz2_1.SimpleEnglishFormatter());
    }
}
