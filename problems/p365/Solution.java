class Solution {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (targetCapacity > jug1Capacity + jug2Capacity) {
            return false;
        }

        int jugGCD = gcd(jug1Capacity, jug2Capacity);
        return targetCapacity % jugGCD == 0;
    }

    private int gcd(int a, int b) {
        if (b > a) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (b > 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }

        return a;
    }
}