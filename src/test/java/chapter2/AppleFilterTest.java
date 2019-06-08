package chapter2;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AppleFilterTest {

    private List<Apple> inventory = new ArrayList<>();

    private AppleFilter legacyFilter;
    private AppleFilterBehavParam behaviorParamFilter;

    @Before
    public void 사과준비하기(){
        legacyFilter = new AppleFilter();
        behaviorParamFilter = new AppleFilterBehavParam();

        inventory.add(new Apple("green", 140));
        inventory.add(new Apple("green", 150));
        inventory.add(new Apple("red", 130));
        inventory.add(new Apple("blue", 170));
        inventory.add(new Apple("red", 140));
        inventory.add(new Apple("red", 180));
    }

    @Test
    public void 녹색사과만_필터링(){
        assertThat(legacyFilter.filterGreenApples(inventory).size()).isEqualTo(2);
    }
    @Test
    public void 원하는컬러로_필터링(){
        assertThat(legacyFilter.filterApplesByColor(inventory,"green").size()).isEqualTo(2);
        assertThat(legacyFilter.filterApplesByColor(inventory,"red").size()).isEqualTo(3);
    }
    @Test
    public void 무게로_필터링(){
        assertThat(legacyFilter.filterApplesByWeight(inventory, 150).size()).isEqualTo(2);
    }

    /**
     * filterApples() method의 동작을 결정하는 것은 interface로 정의된 ApplePredicate 구현체이다.
     * 결국 동작하는 코드를 파라미터화 해서 전달하는 것인데, 기존 자바의 한계상 객체(new)로 감싸서 전달한다.
     */
    @Test
    public void 동작파라미터화_방식(){
        //기존 녹색사과만_필터링()의 테스트와 비교
        assertThat(legacyFilter.filterApplesByColor(inventory,"green").size())
                .isEqualTo(behaviorParamFilter.filterApples(inventory, new AppleFilterBehavParam.AppleGreenPredicate()).size());

        //전략패턴을 사용해서 두가지 비교를 한번에 하는 predicate 파라미터화 전달
        assertThat(behaviorParamFilter.filterApples(inventory, new AppleFilterBehavParam.AppleRedAndHeavyPredicate()).size()).isEqualTo(1);
    }

    /**
     * 동작 파라미터화 방식을 매번 새로운 조건을 가지는 predicate를 위한 클래스를 구현하는 방식에서,
     * 익명 클래스를 파라미터로 넘길때 포함하는 방식으로 변경하였다. 클라이언트 코드가 장황해지며,
     * 여전히 많은 공간을 차지하고, 반복이 일어날 가능성이 높다.
     */
    @Test
    public void 익명클래스_동작파라미터화_방식(){
        assertThat(behaviorParamFilter.filterApples(inventory, new AppleFilterBehavParam.ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        }).size()).isEqualTo(legacyFilter.filterApplesByColor(inventory,"red").size());
    }

    /**
     * 람다표현식을 사용하면 지금까지 사용한 동작파라미터화 방식에, 간결함과 가독성을 더할 수 있다.
     */
    @Test
    public void 람다표현식_동작파라미터화_방식(){
        assertThat(behaviorParamFilter.filterApples(inventory, (Apple apple)->"red".equals(apple.getColor())).size())
            .isEqualTo(legacyFilter.filterApplesByColor(inventory,"red").size());
    }

    @Test
    public void 리스트추상화_동작파라미터화_방식(){
        assertThat(behaviorParamFilter.filter(inventory, (Apple apple)->"red".equals(apple.getColor())).size())
            .isEqualTo(legacyFilter.filterApplesByColor(inventory,"red").size());

    }
}
