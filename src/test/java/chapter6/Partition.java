package chapter6;

import chapter4_5.Dish;
import chapter4_5.MenuBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Partition {
    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void 분할함수_채식_or_not(){
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegeterian));
        System.out.println(partitionedMenu);
    }

    @Test
    public void 분할_후_그룹화(){
        Map<Boolean, Map<Dish.Type, List<Dish>>> partionedMenuThenGroupByType =
                menu.stream().collect(partitioningBy(Dish::isVegeterian,
                        groupingBy(Dish::getType)));
        System.out.println(partionedMenuThenGroupByType);
    }

    @Test
    public void 분할_후_리듀스(){
        Map<Boolean, Dish> partitionedThenMost =
                menu.stream().collect(partitioningBy(Dish::isVegeterian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                Optional::get)));
        System.out.println(partitionedThenMost);
    }

    private boolean isPrime(int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidateRoot)
                .noneMatch(i->candidate %i ==0);
    }

    @Test
    public void 소수_비소수_나누기(){
        Map<Boolean, List<Integer>> partitionPrimes =
                IntStream.rangeClosed(2, 100).boxed()
                .collect(partitioningBy(i->isPrime(i)));
        System.out.println("소수");
        System.out.println(partitionPrimes.get(true));
        System.out.println("비소수");
        System.out.println(partitionPrimes.get(false));
    }

}
