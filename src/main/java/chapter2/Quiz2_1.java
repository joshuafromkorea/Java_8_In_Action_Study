package chapter2;

import java.util.List;

public class Quiz2_1 {

    public void prettyPrintApple(List<Apple> inventory, AppleFormatter f) {
        for(Apple apple: inventory){
            String output = f.accept(apple);
            System.out.println(output);
        }
    }

    public interface AppleFormatter{
        String accept(Apple a);
    }

    public static class WordyFormatter implements AppleFormatter{

        @Override
        public String accept(Apple a) {
            String color = (a.getColor().equals("red")) ? "붉고 먹음직스럽고" : "정체를 알 수 없으며";
            String weight = (a.weight>150) ? "크고 무거운" : "작고 미약한";
            return color + " " + weight + " 사과";
        }
    }

    public static class SimpleEnglishFormatter implements AppleFormatter{

        @Override
        public String accept(Apple a) {
            return "an Apple("+a.getWeight()+"g, "+a.getColor()+")";
        }
    }

}
