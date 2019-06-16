package chapter6;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;


public class PrimeNumbersCollectorTest {

    private long fastest;
    @Before
    public void setMaxValue(){
        fastest = Long.MAX_VALUE;
    }


    @Test
    public void 기존_컬렉터_테스트(){
        for(int i = 0 ; i< 10 ; i++){
            long start = System.nanoTime();
            primePartitioner(1_100_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration<fastest) {;
                fastest = duration;
            }
        }
        System.out.println("legacy execution in "+fastest+"msecs");
    }

    @Test
    public void 커스텀_컬렉터_테스트(){
        for(int i = 0 ; i< 10 ; i++){
            long start = System.nanoTime();
            customPrimePartitioner(1_100_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration<fastest) {
                fastest = duration;
            }
        }
        System.out.println("custom execution in "+fastest+"msecs");
    }

    @Test
    public void 컬렉터_없는_테스트(){
        for(int i = 0 ; i< 10 ; i++){
            long start = System.nanoTime();
            noCollectorPrimePartitioner(1_100_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration<fastest) {
                fastest = duration;
            }
        }
        System.out.println("no collector execution in "+fastest+"msecs");
    }

    //커스텀 컬렉터
    private Map<Boolean, List<Integer>> customPrimePartitioner(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    //레거시 컬렉터
    private Map<Boolean, List<Integer>> primePartitioner(int n){
        return IntStream.rangeClosed(2,n).boxed()
                .collect(partitioningBy(i->isPrime(i)));
    }

    //컬렉터 없는 컬렉터
    private Map<Boolean, List<Integer>> noCollectorPrimePartitioner(int n){
        return IntStream.rangeClosed(2,n).boxed()
                .collect(
                        ()->new HashMap<Boolean, List<Integer>>(){{
                            put(true, new ArrayList<>());
                            put(false, new ArrayList<>());
                        }},
                        (acc, candidate) -> {
                            acc.get(PrimeNumbersCollector.isPrime(acc.get(true),candidate))
                                    .add(candidate);
                        },
                        (map1, map2)->{
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        }

                );
    }

    private boolean isPrime(int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidateRoot)
                .noneMatch(i->candidate %i ==0);
    }

}
