class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        // keep track of the number of each occurence
        Map<Integer, Integer> countMap = new HashMap<>();

        // iterate and add each item
        for (int item : arr) {
            countMap.put(
                item,
                countMap.getOrDefault(item, 0) + 1
            );
        }

        // get the values and put in a set to filter out duplicates
        Set<Integer> counts = new HashSet<>(countMap.values());

        // ensure the unique value count equals the unique occurence count
        return counts.size() == countMap.size();
    }
}