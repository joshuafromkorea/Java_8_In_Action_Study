package chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Spliterator_7_3 {

    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if(lastSpace){
                    counter++;
                }
                lastSpace=false;
            }
        }
        return counter;
    }

    public static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                                                    WordCounter::accumulate,
                                                    WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static int countWordsWithSpliterator(String string){
        Spliterator<Character> spliterator  = new WordCounterSpliterator(string);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        return countWords(stream);
    }

    private static class WordCounter {
        private final int counter;
        private final boolean lastSpace;

        private WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        private WordCounter accumulate(Character c){
            if(Character.isWhitespace(c)){
                return lastSpace ?
                        this :
                        new WordCounter(counter, true);
            }else{
                return lastSpace ?
                        new WordCounter((counter + 1), false):
                        this;
            }
        }

        public WordCounter combine(WordCounter wordCounter){
            return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
        }

        public int getCounter(){
            return counter;
        }
    }

    private static class WordCounterSpliterator implements Spliterator<Character> {

        private final String string;
        private int currentChar = 0;

        public WordCounterSpliterator(String string){
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if(currentSize < 10){
                return null;
            }
            for(int splitPos = currentSize/2 + currentChar ;
                    splitPos < string.length() ; splitPos++){
                if(Character.isWhitespace(string.charAt(splitPos))){
                    Spliterator<Character> spliterator =
                            new WordCounterSpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length()-currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED+SIZED+SUBSIZED+NONNULL+IMMUTABLE;
        }
    }
}
