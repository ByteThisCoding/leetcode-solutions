class Solution {
    public int[] decompressRLElist(int[] nums) {
        int pairLength = nums.length / 2;

        // first iteration, calculate the size of the output
        int outputSize = 0;
        for (int i=0; i<pairLength; i++) {
            outputSize += nums[i*2];
        }

        // second iteration, fill the output
        int[] output = new int[outputSize];
        int outputCursor = 0;
        for (int i=0; i<pairLength; i++) {
            Arrays.fill(output, outputCursor, outputCursor + nums[i*2], nums[i*2+1]);
            outputCursor += nums[i*2];
        }

        return output;
    }
}