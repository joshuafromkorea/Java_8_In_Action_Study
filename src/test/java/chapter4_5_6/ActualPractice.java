package chapter4_5_6;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActualPractice {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions;

    @Before
    public void prepareTransaction(){
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }


    @Test
    public void question_1(){
        System.out.println("Question 1");
        //2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
        List<Transaction> sorted2011s =transactions.stream()
                                        .filter(x->x.getYear()==2011)
                                        .sorted(comparing(Transaction::getValue))
                                        .collect(toList());


        assertThat(sorted2011s.size()).isEqualTo(2);
        assertThat(sorted2011s.get(0).getValue()).isEqualTo(300);
        assertThat(sorted2011s.get(1).getValue()).isEqualTo(400);
        sorted2011s.stream().forEach(System.out::println);
    }

    @Test
    public void question_2(){
        System.out.println("Question 2");
        //거래자가 근무하는 모든 도시를 중복 없이 나열하시오
        List<String> cities = transactions.stream()
                                .map(Transaction::getTrader)
                                .map(Trader::getCity)
                                .distinct()
                                .collect(toList());
        cities.stream().forEach(System.out::println);
    }

    @Test
    public void question_3(){
        System.out.println("Question 3");
        //케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오
        List<Trader> traderFromCambridge = transactions.stream()
                                            .map(Transaction::getTrader)
                                            .distinct()
                                            .filter(x->x.getCity().equals("Cambridge"))
                                            .sorted(comparing(Trader::getName))
                                            .collect(toList());
        traderFromCambridge.stream().forEach(System.out::println);
    }

    @Test
    public void question_4(){
        System.out.println("Question 4");
        //모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오
        List<String> sortedTraders = transactions.stream()
                                    .map(Transaction::getTrader)
                                    .sorted(comparing(Trader::getName))
                                    .map(Trader::getName)
                                    .distinct()
                                    .collect(toList());
        sortedTraders.stream().forEach(System.out::println);
    }

    @Test
    public void question_5(){
        System.out.println("Question 5");
        //밀라노에 거래자가 있는가?
        boolean isThereTraderInMilan = transactions.stream()
                                            .map(Transaction::getTrader)
                                            .anyMatch(x->"Milan".equals(x.getCity()));
        System.out.println(isThereTraderInMilan);
    }

    @Test
    public void question_6(){
        System.out.println("Question 6");
        //케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오
        List<Transaction> transactionByTraderInMilan = transactions.stream()
                                                        .filter(x->"Cambridge".equals(x.getTrader().getCity()))
                                                        .collect(toList());
        transactionByTraderInMilan.stream().forEach(System.out::println);
    }

    @Test
    public void question_7(){
        System.out.println("Question 7");
        //전체 트랜잭션 중 최댓값은 얼마인가?
        int biggest = transactions.stream()
                                .map(Transaction::getValue)
                                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(biggest);
    }

    @Test
    public void question_8(){
        System.out.println("Question 8");
        //전체 트랜잭션 중 최솟값은 얼마인가?
        int smallest = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println(smallest);
    }
}
