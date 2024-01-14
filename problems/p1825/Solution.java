public class MKAverage {

    private IntCircularBuffer bufferValues;

    private int k;

    private int runningSum = 0;
    private int numElements;

    IntSortedMultiSet tree = new IntSortedMultiSet();

    public MKAverage(int m, int k) {
        bufferValues = new IntCircularBuffer(m);
        this.k = k;
        numElements = m - 2*k;
    }
    
    public void addElement(int num) {
        int removedItem = bufferValues.add(num);
        runningSum += num;
        // removedItem is 0 if nothing removed
        runningSum -= removedItem;

        // update min and max trees
        if (removedItem > 0) {
            tree.remove(removedItem);
        }

        tree.insert(num);
    }
    
    public int calculateMKAverage() {
        int[] values = bufferValues.getValuesIfFilled();
        // if structure is not filled, return -1
        if (values.length == 0) {
            return -1;
        }

        // iterate over smallest and largest values, updating the average
        int sum = runningSum - tree.getSumOfFirstKElements(k) - tree.getSumOfLastKElements(k);

        return sum / numElements;
    }
}