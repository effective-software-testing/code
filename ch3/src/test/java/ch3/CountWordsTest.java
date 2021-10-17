package ch3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// this test suite is incomplete. Do proper specification-based testing here!
public class CountWordsTest {

    @Test
    void t1() {
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }

    @Test
    void t2() {
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }

    @Test
    void t3() {
        int words = new CountWords().count("car bar");
        assertThat(words).isEqualTo(2);
    }
}
