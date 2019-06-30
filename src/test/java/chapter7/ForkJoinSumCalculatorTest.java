package chapter7;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;

public class ForkJoinSumCalculatorTest {

    @Test
    public void forkJoinSumTest(){
        long num = 10_000_000;
        System.out.println("iterative : " + measureSumPerf(ForkJoinSumCalculatorTest::forkJoinSum, num)+"ms");
    }

    private static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    private long measureSumPerf(Function<Long, Long> adder, long n){
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10 ; i++){
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime()- start) / 1_000_000;
            if(duration<fastest) fastest = duration;
        }
        return fastest;
    }
}
