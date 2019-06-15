package chapter4_5_6;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class Mapping {

    List<Dish> menu;
    List<String> words;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
        words = Arrays.asList("Java", "8", "In", "Action");
    }

    @Test
    public void 요리명_추출하기(){
        List<String> menuNames = menu.stream()
                                    .map(Dish::getName)
                                    .collect(toList());
        assertThat(menuNames.size()).isEqualTo(9);
        assertThat(menuNames.get(0)).isEqualTo("pork");
    }

    @Test
    public void 단어길이_출력(){
        words.stream()
                .map(String::length)
                .forEach(System.out::println);
    }

    @Test
    public void 스트링_문자반환_어레이(){
        words.stream()
                .map(word->word.split(""))
                .distinct()
                .forEach(x-> System.out.println(x[0]));
    }

    @Test
    public void Array_stream_활용(){
        words.stream()
                .map(word->word.split(""))
                .map(Arrays::stream)
                .distinct()
                .forEach(x-> System.out.println(x.getClass()));
    }

    @Test
    public void Flatmap_으로_해결(){
        words.stream()
                .map(w->w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void Quiz_5_2_1(){
        //숫자 리스트가 주어졌을 때 각 숫자의 제곱근으로 이루어진 리스트를 반환하시오
        //예를 들어 {1,2,3,4,5} -> {1,4,9,16,25}

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        List<Integer> squareNumbers = numbers.stream()
                                            .map(x->x*x)
                                            .collect(toList());
        squareNumbers.stream().forEach(System.out::println);
    }

    @Test
    public void Quiz_5_2_2(){
        //두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하시오
        //예를들어 두개의 리스트[1,2,3] 과 [3,4]가 주어지면
        //[(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]

        List<Integer> firstList = Arrays.asList(1,2,3);
        List<Integer> secondList = Arrays.asList(3,4);
        List<int[]> pairs = firstList.stream()
                                        .flatMap(i->secondList.stream()
                                                            .map(j->new int[]{i,j}))
                                        .collect(toList());
        pairs.stream().forEach(x-> System.out.println("["+x[0]+","+x[1]+"]"));
    }

    @Test
    public void Quiz_5_2_3(){
        //위 문제에서 합이 3으로 나누어 떨어지는 쌍만 반환
        List<Integer> firstList = Arrays.asList(1,2,3);
        List<Integer> secondList = Arrays.asList(3,4);
        firstList.stream()
                .flatMap(i->secondList.stream()
                        .map(j->new int[]{i,j}))
                .filter(x->(x[0]+x[1])%3==0)
                .forEach(a-> System.out.println("["+a[0]+","+a[1]+"]"));
    }

}
