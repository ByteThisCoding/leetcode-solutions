class RandomizedSet {

    private int initialSize = 10;

    // resize later if needed
    private int[] items = new int[initialSize];
    // keep track of values and their indices
    private Map<Integer, Integer> valuesMap = new HashMap<>();

    private int size = 0;
    
    public boolean insert(int val) {
        // don't do anything if value already exists
        if (valuesMap.containsKey(val)) {
            return false;
        }

        // insert item
        items[size] = val;
        valuesMap.put(val, size);
        size ++;

        resizeIfNeeded();

        return true;
    }
    
    public boolean remove(int val) {
        // don't do anything if value doesn't exist
        if (!valuesMap.containsKey(val)) {
            return false;
        }

        // remove item
        int index = valuesMap.get(val);
        // replace index with the last item in the list
        int lastItem = items[size-1];
        valuesMap.put(lastItem, index);
        valuesMap.remove(val);
        items[index] = lastItem;
        size --;

        return true;
    }
    
    public int getRandom() {
        int randomIndex = (int) (Math.random()*size);

        return items[randomIndex];
    }

    private void resizeIfNeeded() {
        if (items.length != size) {
            return;
        }

        int[] newItems = new int[size*2];
        for (int i=0; i<items.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }
}