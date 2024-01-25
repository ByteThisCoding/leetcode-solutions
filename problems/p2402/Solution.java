public class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // don't assume meetings are sorted
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // keep track of room counts
        int[] roomUsageCount = new int[n];
        int maxRoomUsageCount = 0;
        int maxRoomUsageRoom = 0;

        // [room number, end time]
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        PriorityQueue<int[]> bookedMeetings = new PriorityQueue<>((a, b) -> {
            // tie breaker, use room number
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            
            // soonest end time
            return a[1] - b[1];
        });

        // initially populate priority queue for available rooms
        for (int i=0; i<n; i++) {
            availableRooms.add(i);
        }

        // add meetings and record room usage
        for (int i=0; i<meetings.length; i++) {
            int[] meeting = meetings[i];

            // keep track of meeting delays and what room to use
            int meetingStartOffset = meeting[0];
            int roomToBook = -1;

            // if any meetings are now over, remove them from booked meetings
            while (!bookedMeetings.isEmpty() && bookedMeetings.peek()[1] <= meeting[0]) {
                int[] expiredMeeting = bookedMeetings.poll();
                availableRooms.add(expiredMeeting[0]);
            }

            // if no room is available, use the room from the first
            // booked meeting (the one that will end the soonest / lowest room number)
            if (availableRooms.isEmpty()) {
                int[] bookedMeeting = bookedMeetings.poll();
                roomToBook = bookedMeeting[0];
                meetingStartOffset = bookedMeeting[1];
            } else {
                roomToBook = availableRooms.poll();
            }

            // increment usage count
            roomUsageCount[roomToBook] ++;

            // update end time based on this meeting and room's end time
            int newEndTime = meetingStartOffset + meeting[1] - meeting[0];
            bookedMeetings.add(new int[] {roomToBook, newEndTime});

            // update running total so we don't have to iterate later
            if (roomUsageCount[roomToBook] > maxRoomUsageCount) {
                maxRoomUsageCount = roomUsageCount[roomToBook];
                maxRoomUsageRoom = roomToBook;
            } else if (roomToBook < maxRoomUsageRoom && roomUsageCount[roomToBook] == maxRoomUsageCount) {
                maxRoomUsageRoom = roomToBook;
            }
        }

        return maxRoomUsageRoom;
    }
}