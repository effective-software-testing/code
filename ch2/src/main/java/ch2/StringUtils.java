package ch2;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * Searches a String for substrings delimited by a start and end tag,
     * returning all matching substrings in an array.
     *
     * @param str  the String containing the substrings, null returns null, empty returns empty
     * @param open  the String identifying the start of the substring, empty returns null
     * @param close  the String identifying the end of the substring, empty returns null
     * @return a String Array of substrings, or {@code null} if no match
     */
    public static String[] substringsBetween(final String str, final String open, final String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        final int strLen = str.length();
        if (strLen == 0) {
            return EMPTY_STRING_ARRAY;
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            final int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(EMPTY_STRING_ARRAY);
    }
}
