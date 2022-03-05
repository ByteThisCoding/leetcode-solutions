package problems.p1705;

/**
 * A "basket of apples" with the expiration date + count
 * 
 * More than one basket can exist in the queue
 */
public class AppleBasket implements Comparable<AppleBasket> {

    public int count = 0;
    public int expiration = 0;

    AppleBasket(int count, int expiration) {
        this.count = count;
        this.expiration = expiration;
    }

    @Override
    public int compareTo(AppleBasket o) {
        return expiration - o.expiration;
    }
}
