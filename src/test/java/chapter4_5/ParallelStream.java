package chapter4_5;

import org.junit.Test;

import java.util.stream.LongStream;

public class ParallelStream {

    private int N =  Integer.MAX_VALUE;

    @Test
    public void INTEGER_MAX_더하기_일반반복문(){
        long result = 0;
        for(long i=1L; i <=N ; i++){
            result+=i;
        }
        System.out.println(result);
    }

    @Test
    public void INTEGER_MAX_더하기_병렬스트림(){
        long result = LongStream.iterate(1L, i->i+1)
                .limit(N)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(result);

    }

    @Test
    public void INTEGER_MAX_더하기_병렬스트림_RangedClosed(){
        long result = LongStream.rangeClosed(1L,N)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(result);

    }
}
