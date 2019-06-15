package chapter4_5_6;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchAndMatch {
    List<Dish> menu;

    @Before
    public void 메뉴_준비하기(){
        menu = MenuBuilder.getMenu();
    }

    @Test
    public void anyMatch_테스트(){
        assertThat(menu.stream().anyMatch(Dish::isVegeterian)).isEqualTo(true);
    }

    @Test
    public void allMatch_테스트(){
        assertThat(menu.stream().allMatch(x->x.getCalories()>0)).isEqualTo(true);
        assertThat(menu.stream().allMatch(x->x.getCalories()>150)).isEqualTo(false);
    }

    @Test
    public void noneMatch_테스트(){
        assertThat(menu.stream().noneMatch(x->x.getCalories()>1000)).isEqualTo(true);
    }

    @Test
    public void findAny(){
        Optional<Dish> dish = menu.stream()
                        .filter(Dish::isVegeterian)
                        .findAny();
        assertThat(dish.get().isVegeterian()).isEqualTo(true);
    }

    @Test
    public void findFirst(){
        Optional<Dish> dish = menu.stream()
                            .filter(d->{
                                System.out.println("checking "+ d.getName());
                                return d.isVegeterian();
                            })
                            .findFirst();
        assertThat(dish.get().getName()).isEqualTo("french fries");
    }
}
