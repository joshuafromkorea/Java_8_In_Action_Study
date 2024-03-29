package chapter6;

import chapter4_5.Dish;
import chapter4_5.MenuBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Grouping {
    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void 메뉴_그룹화하기(){
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        assertThat(dishesByType.size()).isEqualTo(3);
        assertThat(dishesByType.get(Dish.Type.MEAT).size()).isEqualTo(3);
        assertThat(dishesByType.get(Dish.Type.OTHER).size()).isEqualTo(4);
    }

    @Test
    public void 람다표현식_그룹화(){
        Map<String, List<Dish>> dishesByCaloryLevel =
                menu.stream().collect(groupingBy(dish->
                        {
                            if (dish.getCalories() > 500){
                                return "Over500";
                            }else{
                                return "Under500";
                            }
                        }
                ));
        System.out.println(dishesByCaloryLevel.get("Over500"));
        System.out.println(dishesByCaloryLevel.get("Under500"));
    }

    @Test
    public void 다수준_그룹화(){
        Map<Dish.Type, Map<String, List<Dish>>> dishesByTypeCaloryLevel =
                menu.stream().collect(groupingBy(Dish::getType,
                        groupingBy( dish->
                                {
                                    if(dish.getCalories()>500){
                                        return "Over500";
                                    }else{
                                        return "Under500";
                                    }
                                }
                        ))
                );
        System.out.println(dishesByTypeCaloryLevel);
    }

    @Test
    public void 서브그룹_데이터_수집(){
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByTypeWithoutOptional =
                menu.stream().collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricByTypeWithoutOptional);
    }

    @Test
    public void 타입별로_칼로리총합(){
        Map<Dish.Type, Integer> caloryByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
        System.out.println(caloryByType);
    }

}
