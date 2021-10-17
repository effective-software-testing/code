package appendix;

public class BlockCounter {

    public int maxBlock(String str) {
        if(str.isEmpty())
            return 0;

        int largestBlockSize = 1;
        int currentBlockSize = 1;
        char lastChar = str.charAt(0);

        for(int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            currentBlockSize = (currentChar == lastChar ? currentBlockSize+1 : 1);
            lastChar = currentChar;
            largestBlockSize = Math.max(largestBlockSize, currentBlockSize);
        }

        return largestBlockSize;
    }


}
