package ch2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberUtilsNonSystematicTest {

    @Test
    void t1() {
        assertThat(new NumberUtils().add(numbers(1), numbers(1)))
        .isEqualTo(numbers(2));

        assertThat(new NumberUtils().add(numbers(1,5), numbers(1,0)))
                .isEqualTo(numbers(2, 5));

        assertThat(new NumberUtils().add(numbers(1,5), numbers(1,5)))
                .isEqualTo(numbers(3,0));

        assertThat(new NumberUtils().add(numbers(5,0,0), numbers(2,5,0)))
                .isEqualTo(numbers(7,5,0));
    }

    private static List<Integer> numbers(int... nums) {
        List<Integer> list = new ArrayList<>();
        for(int n : nums)
            list.add(n);
        return list;
    }

}
