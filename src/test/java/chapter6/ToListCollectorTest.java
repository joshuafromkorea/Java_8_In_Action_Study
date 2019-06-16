package chapter6;

import chapter4_5.Dish;
import chapter4_5.MenuBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToListCollectorTest {
    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void 커스텀_컬렉터_사용하기(){
        List<Dish> toListDishes = menu.stream().collect(new ToListCollector<Dish>());
        System.out.println(toListDishes);
    }

    @Test
    public void 컬렉터_구현없이_컬렉팅_하기(){
        List<Dish> dishes = menu.stream().collect(
                ArrayList::new,
                List::add,
                List::addAll
        );
        System.out.println(dishes);
    }
}
