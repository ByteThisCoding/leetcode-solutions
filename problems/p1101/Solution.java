public class Solution {
    public int earliestAcq(int[][] logs, int n) {
        // ensure logs are in order
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);

        // setup map between person and group
        DisjointSet set = new DisjointSet(n);
        for (int[] log : logs) {
            set.union(log[1], log[2]);
            if (set.getLargestSetSize() == n) {
                return log[0];
            }
        }

        return -1;
    }
}