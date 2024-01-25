public class Solution {

    public boolean canAttendMeetings(int[][] intervals) {
        IntRangeCollection meetings = new IntRangeCollection();

        for (int[] meeting : intervals) {
            boolean isConflict = meetings.addRangeIfNoIntersection(meeting[0], meeting[1]);
            if (isConflict) {
                return false;
            }
        }

        return true;
    }
}