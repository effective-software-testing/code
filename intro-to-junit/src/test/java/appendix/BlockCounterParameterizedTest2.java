package appendix;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class BlockCounterParameterizedTest2 {

    @ParameterizedTest
    @MethodSource("inputs")
    void countTheNumberOfMaxBlocks(String input, int expectedOutput) {
        int blockSize = new BlockCounter().maxBlock(input);
        assertThat(blockSize).isEqualTo(expectedOutput);
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("aabbbcc", 3),
                Arguments.of("aabbbccc", 3),
                Arguments.of("abc", 1),
                Arguments.of("aa", 2),
                Arguments.of("aabbbb", 4),
                Arguments.of("", 0)
        );
    }
}
