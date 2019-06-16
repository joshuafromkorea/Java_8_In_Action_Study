package chapter4_5;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class FilteringAndSlicing {
    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void 프레디케이트_필터링(){
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegeterian)
                .collect(toList());
        assertThat(vegetarianMenu.size()).isEqualTo(3);
    }

    @Test
    public void 고유요소_필터링_짝수(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(i->i%2==0)
                .collect(toList());
        assertThat(evenNumbers.size()).isEqualTo(5);
    }

    @Test
    public void 고유요소_필터링_고기(){
        List<Dish> meatMenu = menu.stream()
                .filter(x->x.getType()== Dish.Type.MEAT)
                .collect(toList());
        assertThat(meatMenu.size()).isEqualTo(3);
    }

    @Test
    public void 스트림_축소(){
        List<Dish> firstThreeMenus = menu.stream()
                .filter(Dish::isVegeterian)
                .limit(2)
                .collect(toList());
        firstThreeMenus.stream().forEach(System.out::println);
    }

    @Test
    public void 요소건너뛰기(){
        List<Dish> exceptFIrstTwoMenu = menu.stream()
                .skip(2)
                .collect(toList());
        assertThat(exceptFIrstTwoMenu.size()).isEqualTo(7);
    }

    @Test
    public void 퀴즈_5_1(){
        //스트림을 이용해서 처음 등장하는 두 고기 요리를 필터링 하시오
        List<Dish> firstTwoMeatMenu = menu.stream()
                .filter(x->x.getType()== Dish.Type.MEAT)
                .collect(toList());

        assertThat(firstTwoMeatMenu.get(0).getName()).isEqualTo("pork");
        assertThat(firstTwoMeatMenu.get(1).getName()).isEqualTo("beef");
    }
}
