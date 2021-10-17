package appendix;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class BlockCounterParameterizedTest {

    @ParameterizedTest
    @CsvSource({
            "aabbbcc,3",
            "aabbbccc,3",
            "abc,1",
            "aa,2",
            "aabbbb,4",
            "'',0"
    })
    void countTheNumberOfMaxBlocks(String input, int expectedOutput) {
        int blockSize = new BlockCounter().maxBlock(input);
        assertThat(blockSize).isEqualTo(expectedOutput);
    }

}
