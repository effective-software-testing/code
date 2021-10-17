package appendix;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockCounterTest {

    @Test
    void oneLongestBlock() {
        int blockSize = new BlockCounter().maxBlock("aabbbcc");
        assertThat(blockSize).isEqualTo(3);
    }

    @Test
    void twoLongestBlocks() {
        int blockSize = new BlockCounter().maxBlock("aabbbccc");
        assertThat(blockSize).isEqualTo(3);
    }

    @Test
    void blockOfLength1() {
        int blockSize = new BlockCounter().maxBlock("abc");
        assertThat(blockSize).isEqualTo(1);
    }

    // this one finds the bug!
    @Test
    void singleBlock() {
        int blockSize = new BlockCounter().maxBlock("aa");
        assertThat(blockSize).isEqualTo(2);
    }

    @Test
    void longestBlockIsTheLast() {
        int blockSize = new BlockCounter().maxBlock("aabbbb");
        assertThat(blockSize).isEqualTo(4);
    }

    // finds the second bug
    @Test
    void emptyString() {
        int blockSize = new BlockCounter().maxBlock("");
        assertThat(blockSize).isEqualTo(0);
    }
}
