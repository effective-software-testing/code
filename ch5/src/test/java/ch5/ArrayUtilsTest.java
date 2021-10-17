package ch5;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;


public class ArrayUtilsTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testIndexOf(int[] array, int valueToFind, int startIndex, int expectedResult) {
        int result = ArrayUtils.indexOf(array, valueToFind, startIndex);
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> testCases() {
        int[] array = new int[] { 1, 2, 3, 4, 5, 4, 6, 7 };

        return Stream.of(
            of(null, 1, 1, -1),
            of(new int[] { 1 }, 1, 0, 0),
            of(new int[] { 1 }, 2, 0, -1),
            of(array, 1, 10, -1),
            of(array, 2, -1, 1),
            of(array, 4, 6, -1),
            of(array, 4, 1, 3),
            of(array, 4, 3, 3),
            of(array, 4, 1, 3),
            of(array, 4, 4, 5),
            of(array, 8, 0, -1)
        );
    }

    @Property
    void indexOfShouldFindFirstValue(
            /*
             * we generate a list with 100 numbers, ranging from -1000, 1000. Range chosen
             * randomly.
             */
            @ForAll @Size(value = 100) List<@IntRange(min = -1000, max = 1000) Integer> numbers,
            /**
             * we generate a random number that we'll insert in the list. This number is
             * outside the range of the list, so that we can easily find it.
             */
            @ForAll @IntRange(min = 1001, max = 2000) int value,
            /** We randomly pick a place to put the element in the list */
            @ForAll @IntRange(max = 99) int indexToAddElement,
            /** We randomly pick a number to start the search in the array */
            @ForAll @IntRange(max = 99) int startIndex) {
        /* we add the number to the list in the randomly chosen position */
        numbers.add(indexToAddElement, value);

        /* we convert the list to an array, as expected by the method */
        int[] array = convertListToArray(numbers);
        /**
         *
         * if we added the element after the start index, then, we expect the method to
         * return the position where we inserted the element. Else, we expect the method
         * to return -1.
         */
        int expectedIndex = indexToAddElement >= startIndex ? indexToAddElement : -1;
        assertThat(ArrayUtils.indexOf(array, value, startIndex)).isEqualTo(expectedIndex);
    }

    /** Use this method to convert a list of integers to an array */
    private int[] convertListToArray(List<Integer> numbers) {
        int[] array = numbers.stream().mapToInt(x -> x).toArray();
        return array;
    }


}
