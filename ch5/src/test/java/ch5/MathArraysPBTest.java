package ch5;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MathArraysPBTest {

    @Property
    // an array of size 100 with doubles between [0,20] will definitely have repeated numbers
    void unique(@ForAll @Size(value = 100) List<@IntRange(min = 1, max = 20) Integer> numbers) {

        int[] doubles = convertListToArray(numbers);
        int[] result = MathArrays.unique(doubles);

        assertThat(result)
                .contains(doubles) // contains all the elements
                .doesNotHaveDuplicates() // no duplicates
                .isSortedAccordingTo(Comparator.reverseOrder()); // in descending order
    }

    /** Use this method to convert a list of integers to an array */
    private int[] convertListToArray(List<Integer> numbers) {
        int[] array = numbers.stream().mapToInt(x -> x).toArray();
        return array;
    }
}
