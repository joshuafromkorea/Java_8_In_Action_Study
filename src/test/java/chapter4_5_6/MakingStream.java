package chapter4_5_6;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MakingStream {

    @Test
    public void 값으로_스트림(){
        Stream<Integer> numbers = Stream.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println(numbers.mapToInt(x->x).sum());

    }

    @Test
    public void 배열로_스트림(){
        int[] numbers = {1,2,3,4,5,6,7,8,9,10};
        int sum= Arrays.stream(numbers).sum();
    }

    @Test
    public void 무한스트림_iterate(){
        IntStream stream = IntStream.iterate(0, n->n+1)
                .limit(100);
        stream.forEach(System.out::println);
    }

    @Test
    public void Quiz_5_4(){
        Stream.iterate(new int[]{0,1}, a-> new int[] {a[1],a[0]+a[1]})
                .limit(20)
                .map(t->t[0])
                .forEach(System.out::println);
    }

    @Test
    public void 무한스트림_generate(){
        DoubleStream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    public void 피보나치수열_with_generate(){
        IntStream.generate(new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        }).limit(10).forEach(System.out::println);

    }
}
