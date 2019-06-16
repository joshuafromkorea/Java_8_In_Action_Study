package chapter4_5;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static java.util.stream.Collectors.*;

@SpringBootTest
public class IntermediateOperation {

    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void 중간연산_게으름_테스트(){
        List<String> names = menu.stream()
                .filter(d->{
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() >300;
                })
                .map(d->{
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(names);
    }

}
