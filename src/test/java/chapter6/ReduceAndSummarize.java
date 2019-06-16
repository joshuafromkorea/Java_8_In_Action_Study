package chapter6;

import chapter4_5.Dish;
import chapter4_5.MenuBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ReduceAndSummarize {
    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void 요리수_계산하기(){
        long howManyDishes = menu.stream().collect(counting());
        assertThat(howManyDishes).isEqualTo(menu.stream().count());
        System.out.println(howManyDishes);
    }

    @Test
    public void 최대칼로리_maxBy(){
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloryDish = menu.stream().collect(maxBy(dishComparator));
        assertThat(mostCaloryDish.get().toString()).isEqualTo("pork");
    }

    @Test
    public void 칼로리총합_summingInt(){
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        assertThat(totalCalories).isEqualTo(4200);
    }

    @Test
    public void 칼로리_평균_averagingDouble(){
        System.out.println(menu.stream().collect(averagingDouble(Dish::getCalories)));
    }

    @Test
    public void 서머라이징(){
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
    }

    @Test
    public void 문자열_연결(){
        System.out.println(
                menu.stream().map(Dish::getName)
                            .collect(joining())
        );

        System.out.println(
                menu.stream().map(Dish::toString).collect(joining(", "))
        );
    }

    @Test
    public void 범용_리듀싱_총합(){
        assertThat(menu.stream().collect(summingInt(Dish::getCalories)))
                .isEqualTo(menu.stream().collect(reducing(0,Dish::getCalories, (i,j) -> i+j)));
    }

    @Test
    public void 범용_리듀싱_최대값(){
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        assertThat(menu.stream().collect(maxBy(dishComparator)).get())
                .isEqualTo(menu.stream().collect(reducing((d1,d2)
                                -> d1.getCalories() > d2.getCalories()? d1: d2)).get());
    }

    @Test
    public void 유연성_총합(){
        assertThat(menu.stream().collect(reducing(0,Dish::getCalories, (i,j )-> i+j)))
                .isEqualTo(menu.stream().collect(reducing(0,Dish::getCalories,Integer::sum)));
    }

    @Test
    public void 유연성_갯수(){
        assertThat(menu.stream().count()).isEqualTo(
                menu.stream().collect(reducing(0L, e->1L, Long::sum))
        );
    }

}
