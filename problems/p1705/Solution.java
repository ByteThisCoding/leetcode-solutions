package problems.p1705;

import java.util.PriorityQueue;

public class Solution {

    /**
     * Use a sorted map to sort expiration dates and number of apples per date
     * Value is expiry date, lower dates get eaten first
     */
    public int eatenApples(int[] apples, int[] days) {
        int numApplesEaten = 0;

        PriorityQueue<AppleBasket> appleExpirations = new PriorityQueue<>();
        // for each day, insert apples[day] apples, eat one
        for (int i = 0; i < apples.length; i++) {
            // expiration date is current date plus days[i] value
            int expirationDate = days[i] + i;
            appleExpirations.add(new AppleBasket(apples[i], expirationDate));

            // remove all expired
            while (appleExpirations.size() > 0 && appleExpirations.peek().expiration <= i) {
                appleExpirations.remove();
            }

            if (appleExpirations.size() > 0) {
                if (appleExpirations.peek().count == 1) {
                    appleExpirations.remove();
                } else {
                    appleExpirations.peek().count--;
                }
                // increment count
                numApplesEaten++;
            }
        }

        // continue eating for the remaining days
        for (int i = apples.length; true; i++) {
            // remove all expired
            while (appleExpirations.size() > 0 && appleExpirations.peek().expiration <= i) {
                appleExpirations.remove();
            }

            if (appleExpirations.size() > 0) {
                if (appleExpirations.peek().count == 1) {
                    appleExpirations.remove();
                } else {
                    appleExpirations.peek().count--;
                }
                // increment count
                numApplesEaten++;
            } else {
                break;
            }
        }

        return numApplesEaten;
    }

}
