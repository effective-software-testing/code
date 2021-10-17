package ch8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralConverterTest {

    @ParameterizedTest
    @CsvSource({"I,1","V,5", "X,10","L,50", "C, 100", "D, 500", "M, 1000"})
    void shouldUnderstandOneCharNumbers(String romanNumeral, int expectedNumberAfterConversion) {
        RomanNumeralConverter roman = new RomanNumeralConverter();
        int convertedNumber = roman.convert(romanNumeral);
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion);
    }

    @ParameterizedTest
    @CsvSource({"II,2","III,3","VI, 6", "XVIII, 18", "XXIII, 23", "DCCLXVI, 766"})
    void shouldUnderstandMultipleCharNumbers(String romanNumeral, int expectedNumberAfterConversion) {
        RomanNumeralConverter roman = new RomanNumeralConverter();
        int convertedNumber = roman.convert(romanNumeral);
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion);
    }

    @ParameterizedTest
    @CsvSource({"IV,4","XIV,14", "XL, 40","XLI,41", "CCXCIV, 294"})
    void shouldUnderstandSubtractiveNotation(String romanNumeral, int expectedNumberAfterConversion) {
        RomanNumeralConverter roman = new RomanNumeralConverter();
        int convertedNumber = roman.convert(romanNumeral);
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion);
    }

    // if you prefer everything in a single test method
    @ParameterizedTest
    @CsvSource({
            // single character numbers
            "I,1","V,5", "X,10","L,50", "C, 100", "D, 500", "M, 1000",
            // multiple character numbers
            "II,2","III,3", "V,5","VI, 6", "XVIII, 18", "XXIII, 23", "DCCLXVI, 766",
            // subtractive notation
            "IV,4","XIV,14", "XL, 40","XLI,41", "CCXCIV, 294"
    })
    void convertRomanNumerals(String romanNumeral, int expectedNumberAfterConversion) {
        RomanNumeralConverter roman = new RomanNumeralConverter();
        int convertedNumber = roman.convert(romanNumeral);
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion);
    }

}
