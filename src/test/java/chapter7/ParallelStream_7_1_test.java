package chapter7;

import org.junit.Test;

import java.util.function.Function;

import static chapter7.ParallelStream_7_1.*;

public class ParallelStream_7_1_test {
    @Test
    public void sumTimeContest(){
        long num = 10_000_000;
        System.out.println("iterative : " + measureSumPerf(ParallelStream_7_1::iterativeSum, num)+"ms");
        System.out.println("sequential : " + measureSumPerf(ParallelStream_7_1::sequentialSum, num)+"ms");
        System.out.println("parallel : " + measureSumPerf(ParallelStream_7_1::parallelSum, num)+"ms");
        System.out.println("rangedSequential : " + measureSumPerf(ParallelStream_7_1::rangedSum, num)+"ms");
        System.out.println("rangedParallel : " + measureSumPerf(ParallelStream_7_1::paralleRangedSum, num)+"ms");
    }

    @Test
    public void sideEffectTest(){
        long num = 10_000_000;
        System.out.println("sideEffect");
        measureSumPerf(ParallelStream_7_1::sideEffectSum, num);
        System.out.println("sideEffectParallel");
        measureSumPerf(ParallelStream_7_1::sideEffectParallelSum, num);
    }

    private long measureSumPerf(Function<Long, Long> adder, long n){
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10 ; i++){
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime()- start) / 1_000_000;
            System.out.println(sum);
            if(duration<fastest) fastest = duration;
        }
        return fastest;
    }

}
