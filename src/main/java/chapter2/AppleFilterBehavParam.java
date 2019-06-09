package chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//동작 파라미터화를 적용한 사과 필터
public class AppleFilterBehavParam {

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    //Apple 이외의 모든 타입에 대해서 동일한 역할을 할 수 있는 필터를 만든다.
    public static <T> List<T> filter(List<T> inventory, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T t : inventory){
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }



    public interface ApplePredicate {
        boolean test (Apple apple);
    }


    public static class AppleHeavyPredicate implements ApplePredicate{

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }
    public static class AppleGreenPredicate implements ApplePredicate{

        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    public static class AppleRedAndHeavyPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return "red".equals(apple.getColor())
            &&apple.getWeight()>150;
        }
    }
}
