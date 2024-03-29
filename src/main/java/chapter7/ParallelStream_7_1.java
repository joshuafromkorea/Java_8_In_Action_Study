package chapter7;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStream_7_1 {

    public static long sequentialSum(long n){
        return Stream.iterate(1L, i->i+1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n){
        return Stream.iterate(1L, i->i+1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
    public static long iterativeSum(long n){
        long sum = 0L;
        for(int i = 0 ; i <= n; i++){
            sum+=i;
        }
        return sum;
    }

    public static long rangedSum(long n){
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long paralleRangedSum(long n){
        return LongStream.rangeClosed(1,n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long sideEffectSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        public long total = 0;
        public void add(long value) {total += value;}
    }
}
