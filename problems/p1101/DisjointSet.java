class DisjointSet {

    // 0 will indicate no connection
    private int[] items;
    private int largestSetSize = 0;

    public DisjointSet(int n) {
        items = new int[n + 1];
    }

    public void union(int a, int b) {
        // treat as 1-(n+1)
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        int thisSetSize = 0;

        // both are disconnected, connect to each other
        if (items[a] == 0 && items[b] == 0) {
            items[a] = 2;
            items[b] = -a;
            thisSetSize = 2;
        } else if (items[a] == 0) { //just one is disconnected
            items[a] = -b;
            items[b] ++;
            thisSetSize = items[b];
        } else if (items[b] == 0) {
            items[b] = -a;
            items[a] ++;
            thisSetSize = items[a];
        } else {
            // swap to ensure a is larger
            if (items[b] > items[a]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            items[a] += items[b];
            items[b] = -a;
            thisSetSize = items[a];
        }

        largestSetSize = Math.max(
            largestSetSize,
            thisSetSize
        );
    }

    public int find(int a) {
        a++;
        return findInternal(a);
    }

    private int findInternal(int a) {
        // base case, no connections
        if (items[a] >= 0) {
            return a;
        }

        items[a] = -findInternal(-items[a]);
        return -items[a];
    }

    public int getLargestSetSize() {
        return largestSetSize;
    }
}

/**
 * 
 * [0, -2, -3, 3]
 * 
 * 1:
 *  - -find(items[2])
 *  - 2:
 *      - -find(items[3])
 *      - 3:
 *          return 3
 * 
 * 
 */