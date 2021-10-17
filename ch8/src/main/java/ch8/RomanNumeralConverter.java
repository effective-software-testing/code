package ch8;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeralConverter {
    private static Map<Character, Integer> table =
            new HashMap<Character, Integer>() {{
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }};

    public int convert(String numberInRoman) {
        int finalNumber = 0;
        int lastNeighbor = 0;
        for(int i = numberInRoman.length() - 1; i >= 0; i--) {

            // get integer referent to current symbol
            int current = table.get(numberInRoman.charAt(i));

            // if right is lower, multiply it
            // by -1 to turn it negative
            int multiplier = 1;
            if(current < lastNeighbor) multiplier = -1;

            finalNumber += current * multiplier;

            // update neighbor at right
            lastNeighbor = current;
        }
        return finalNumber;
    }
}
