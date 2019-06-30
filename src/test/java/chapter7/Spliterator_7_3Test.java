package chapter7;

import org.junit.Test;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static chapter7.Spliterator_7_3.*;

public class Spliterator_7_3Test {

    final String SENTENCE = "Nel mezzo del cammin di nostra vita " +
    "mi ritrovai    per una    selva oscura " +
    "ch la diritta   via   era smarrita";

    @Test
    public void countWordIteratively(){
        System.out.println("Found "+countWordsIteratively(SENTENCE)+ " words");
    }

    @Test
    public void countWordWithWordCounter(){
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found "+countWords(stream)+ " words");
    }

    @Test
    public void countWordWithWordCounterParallel(){
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found "+countWords(stream.parallel())+ " words");
    }

    @Test
    public void countWordWithSpliterator(){
        System.out.println("Found "+countWordsWithSpliterator(SENTENCE)+ " words");

    }
}
