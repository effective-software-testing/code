package ch2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.of;

public class NumberUtilsTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void shouldReturnCorrectResult(List<Integer> left, List<Integer> right, List<Integer> expected) {
        assertThat(new NumberUtils().add(left, right))
                .isEqualTo(expected);
    }

    static Stream<Arguments> testCases() {

        /*
         * left:
         * - empty
         * - null
         * - single digit
         * - multiple digits
         * - zeroes on the left
         *
         * right:
         * - empty
         * - null
         * - single digit
         * - multiple digits
         * - zeroes on the left
         *
         * (left, right):
         * - len(left) > len(right)
         * - len(right) > len(left)
         * - len(left) = len(right)
         *
         * carry:
         * - sum without carry
         * - sum with carry
         *   - one carry at the beginning
         *   - one carry in the middle
         *   - many carries
         *   - many carries, not in a row
         *   - carry in the last digit
         *
         * Test cases:
         *
         * T1 = left null
         * T2 = left empty
         * T3 = right null
         * T4 = right empty
         *
         * single digit:
         * T5 = single digit, no carry
         * T6 = single digit, carry
         *
         * multiple digits:
         * T7 = no carry
         * T8 = carry in the least significant digit
         * T9 = carry in the middle
         * T10 = many carries
         * T11 = many carries, not in a row
         * T12 = left over
         *
         * multiple digits, different length:
         * T13 = no carry
         * T14 = carry in the least significant digit
         * T15 = carry in the middle
         * T16 = many carries
         * T17 = many carries, not in a row
         * T18 = left over
         *
         * zeroes on the left:
         * T19 = no carry
         * T20 = carry
         * (do not see reason to combine with all the carries again)
         *
         * boundary:
         * T21 = carry to a new most significant digit, by one (e.g., 99+1).
         */

        return Stream.of(
                // nulls and empties should return null
                of(null, numbers(7,2), null), // T1
                of(numbers(), numbers(7,2), numbers(7,2)), // T2
                of(numbers(9,8), null, null), // T3
                of(numbers(9,8), numbers(), numbers(9,8)), // T4

                // single digits
                of(numbers(1), numbers(2), numbers(3)), // T5
                of(numbers(9), numbers(2), numbers(1,1)), // T6

                // multiple digits
                of(numbers(2,2), numbers(3,3), numbers(5,5)), // T7
                of(numbers(2,9), numbers(2,3), numbers(5,2)), // T8
                of(numbers(2,9,3), numbers(1,8,3), numbers(4,7,6)), // T9
                of(numbers(1,7,9), numbers(2,6,8), numbers(4,4,7)), // T10
                of(numbers(1,9,1,7,1), numbers(1,8,1,6,1), numbers(3,7,3,3,2)), // T11
                of(numbers(9,9,8), numbers(1,7,2), numbers(1,1,7,0)), // T12

                // multiple digits, different length, with and without carry
                // (from both sides)
                of(numbers(2,2), numbers(3), numbers(2,5)), // T13.1
                of(numbers(3), numbers(2,2), numbers(2,5)), // T13.2

                of(numbers(2,2), numbers(9), numbers(3,1)), // T14.1
                of(numbers(9), numbers(2,2), numbers(3,1)), // T14.2

                of(numbers(1,7,3), numbers(9,2), numbers(2,6,5)), // T15.1
                of(numbers(9,2), numbers(1,7,3), numbers(2,6,5)), // T15.2

                of(numbers(3,1,7,9), numbers(2,6,8), numbers(3,4,4,7)), // T16.1
                of(numbers(2,6,8), numbers(3,1,7,9), numbers(3,4,4,7)), // T16.2

                of(numbers(1,9,1,7,1), numbers(2,1,8,1,6,1), numbers(2,3,7,3,3,2)), // T17.1
                of(numbers(2,1,8,1,6,1), numbers(1,9,1,7,1), numbers(2,3,7,3,3,2)), // T17.2

                of(numbers(9,9,8), numbers(9,1,7,2), numbers(1,0,1,7,0)), // T18.1
                of(numbers(9,1,7,2), numbers(9,9,8), numbers(1,0,1,7,0)), // T18.2

                // zeroes on the left
                of(numbers(0,0,0,1,2), numbers(0,2,3), numbers(3,5)), // T19
                of(numbers(0,0,0,1,2), numbers(0,2,9), numbers(4,1)), // T20,

                // boundary
                of(numbers(9,9), numbers(1), numbers(1,0,0)) // T21
        );
    }

    @ParameterizedTest
    @MethodSource("digitsOutOfRange")
    void shouldThrowExceptionWhenDigitsAreOutOfRange(List<Integer> left, List<Integer> right) {
        assertThatThrownBy(() -> new NumberUtils().add(left, right))
                .isInstanceOf(IllegalArgumentException.class);

    }

    static Stream<Arguments> digitsOutOfRange() {
        return Stream.of(
                of(numbers(1,-1,1), numbers(1)),
                of(numbers(1), numbers(1,-1,1)),
                of(numbers(1,11,1), numbers(1)),
                of(numbers(1), numbers(1,11,1))
        );
    }

    // returns a mutable list of integers
    private static List<Integer> numbers(int... nums) {
        List<Integer> list = new ArrayList<>();
        for(int n : nums)
            list.add(n);
        return list;
    }

}
