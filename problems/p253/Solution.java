class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int[] startTimes = new int[intervals.length];
        int[] endTimes = new int[intervals.length];

        for (int i=0; i<intervals.length; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }

        // sort start and end times
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int startIndex = 0;
        int endIndex = 0;

        int maxNumRooms = 0;
        int numRooms = 0;

        while (startIndex < startTimes.length) {
            // iterate start times until we reach the next end time
            while (startIndex < startTimes.length
                    && startTimes[startIndex] < endTimes[endIndex]
            ) {
                numRooms ++;
                maxNumRooms = Math.max(numRooms, maxNumRooms);
                startIndex ++;
            }

            // iterate end times until we reach the next start time
            while (startIndex < startTimes.length
                    && endIndex < endTimes.length
                    && endTimes[endIndex] <= startTimes[startIndex]
            ) {
                numRooms --;
                endIndex ++;
            }
        }

        return maxNumRooms;
    }
}