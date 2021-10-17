package ch3;

public class Clumps {

    /**
     * Counts the number of "clumps" that are in the array. A clump is a sequence of
     * the same element with a length of at least 2.
     *
     * @param nums
     *            the array to count the clumps of. The array must be non-null and
     *            len > 0; the program returns 0 in case any pre-condition is
     *            violated.
     * @return the number of clumps in the array.
     */
    public static int countClumps(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int prev = nums[0];
        boolean inClump = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev && !inClump) {
                inClump = true;
                count += 1;
            }
            if (nums[i] != prev) {
                prev = nums[i];
                inClump = false;
            }
        }
        return count;
    }
}

