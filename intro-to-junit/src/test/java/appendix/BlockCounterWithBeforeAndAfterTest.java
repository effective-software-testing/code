package appendix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockCounterWithBeforeAndAfterTest {

    private BlockCounter blockCounter;

    @BeforeEach
    void setup() {
        this.blockCounter = new BlockCounter();
    }

    @Test
    void oneLongestBlock() {
        blockCounter = new BlockCounter();
        int blockSize = blockCounter.maxBlock("aabbbcc");
        assertThat(blockSize).isEqualTo(3);
    }

    @Test
    void twoLongestBlocks() {
        int blockSize = blockCounter.maxBlock("aabbbccc");
        assertThat(blockSize).isEqualTo(3);
    }

    @Test
    void blockOfLength1() {
        int blockSize = blockCounter.maxBlock("abc");
        assertThat(blockSize).isEqualTo(1);
    }

    @Test
    void singleBlock() {
        int blockSize = blockCounter.maxBlock("aa");
        assertThat(blockSize).isEqualTo(2);
    }

    @Test
    void longestBlockIsTheLast() {
        int blockSize = blockCounter.maxBlock("aabbbb");
        assertThat(blockSize).isEqualTo(4);
    }

    @Test
    void emptyString() {
        int blockSize = blockCounter.maxBlock("");
        assertThat(blockSize).isEqualTo(0);
    }
}
