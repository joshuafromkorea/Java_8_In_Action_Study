package chapter4_5_6;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class Reducing {
    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void 일부터_구까지_더하기_초기값_십(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
        int sum = numbers.stream().reduce(10,(a,b)->a+b);
        assertThat(sum).isEqualTo(55);
    }

    @Test
    public void 일부터_십까지_더하기_초기값_없음(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Optional<Integer> sum = numbers.stream()
                                        .reduce(Integer::sum);
        assertThat(sum.get()).isEqualTo(55);
    }

    @Test
    public void 최대값_구하기(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Optional<Integer> sum = numbers.stream()
                                        .reduce(Integer::max);
        assertThat(sum.get()).isEqualTo(10);
    }

    @Test
    public void Quiz_5_3(){
        //map 과 reduce를 이용해서 스트림의 요리 개수를 계산하시오
        int sum = menu.stream()
                    .map(d->1)
                    .reduce(0,Integer::sum);
        assertThat(sum).isEqualTo(9);
    }

}
