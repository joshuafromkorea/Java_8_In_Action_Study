package chapter3;

import chapter2.Apple;

import java.util.Comparator;

public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return ((Integer) o1.getWeight()).compareTo(o2.getWeight());
    }
}
