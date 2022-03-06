# 1782. Count Pairs Of Nodes
This is my solution for LeetCode's problem 1782: https://leetcode.com/problems/count-pairs-of-nodes/

## Problem Analysis
The input of the problem will be:
* **n**: the total number of nodes we'll be working with:
* **edges**: an array of each edge in the graph.
* **queries**: an array of ints representing queries.

The problem defines *incident(a, b)* as the number of edges that are connected to either node *a* or node *b*.

A *query* asks: "how many pairs of nodes *(a, b)* satisfy the following":
1. *a* < *b*.
1. *incident(a, b)* > query value.

There are a few cases the implementation will need to account for:
* We can have more than one instance of the same edge *(a, b)*.
* For each edge *(a, b)*, we may or may not have an edge *(b, a)*.
* We may have queries which have an answer of 0, or the maximum possible answer.
* Multiple queries might have the same answer.
* For *incident(a, b)*, we need to make sure not to count any edges twice.

In order to calculate *incident(a, b)*, we'll need to know the **degree** of nodes *a* and *b*. We'll also need to be sure not to count any edges twice. We can do this by keeping track of the degree of each edge. The full calculation: *incident(a, b) = nodeDegree[a] + nodeDegree[b] - edgeDegree[a, b]*. Once we have a way of calculating *incident(a, b)*, we can use this to calculate the answers for each query. To avoid redundant iteration, we will need to use additional space to store the degrees of each node + edge; otherwise, we would need to re-calculate them for each query.

## Implementation Strategy
Our algorithm will split the main problem into a series of subproblems:
1. Determine the degree of each node and store in **nodeDegrees**.
1. Determine the degree of each edge and store in **edgeDegrees**.
1. Make a new array **sortedDegrees** which is a sorted version of **nodeDegrees**.
1. Create an **answers** array, where *answers[i]* is the answer to *queries[i]*.
1. For each query, use the data above to calculate how many pairs *(a, b)* satisfy a query. Store each *queries[i]* result in *answers[i]*.

To determine the values for **nodeDegrees**, we need to create a mapping of node values to the number of connections they have. Since we are told by the problem all node values are *1...n*, we can use an ArrayList. To determine the values for **edgeDegrees**, we'll need to create a mapping of pairs *(a, b)* to the number of edges which touch both of those elements (meaning *(a, b)* and *(b, a)*). We will use a HashMap of HashMaps for this purpose. The outer key will be the *a* node, the inner key will be the *b* node, and the value will be the number of edges. For consistency, we'll store and use the keys such that the outer key is always less than the inner key.

With these data structures in place, we'll populate **nodeDegrees** and **edgeDegrees** via the steps below. For each **(a, b)** in the given **edges**:
1. Increment *nodeDegrees[a]*.
1. Increment *nodeDegrees[b]*.
1. Increment *edgeDegrees[a][b]*.
    * Note that in the actual Java code, we'll need to be sure to create the submap for *edgeDegrees(a)* if it doesn't already exist at each point.

Once the values are in place, we'll copy **nodeDegrees** into **sortedDegrees** and sort that array using Java's built-in *Arrays.sort()*.

Next, we'll iterate over the queries to populate our anwers. For each query, our implementation will determine an upper bounds for the number of satisfying incident pairs by using **sortedDegrees**, then refine the answer by removing any edges which were considered more than once by utilizing the data in **edgeDegrees**. To determine the upper bound, the algorithm will use two pointers to iterate over the **sortedDegrees**:
1. Pointer **left** is initialized to 1, not 0 because there is no 0 node.
1. Pointer **right** is initialized to **n**.
1. We'll be adjusting these pointers throughout the following loop. While *right > left*:
    1. Take the **sum** of *sortedDegrees[left] + sortedDegrees[right]*.
    1. If *sum > query*, we've just found a pair which satisfies the query:
        1. Add *right - left* to the current value of **count**.
        1. Decrement **right**.
    1. If not, increment **left**.

The algorithm above searches for pairs *(a, b)* such that *increment(a, b)* is *possibly* greater than query. To be sure, we'll need to traverse the edges to ensure we haven't counted any edge more than once. For each *(a, b)* in **edgeDegrees**:
1. Set **sumDegree** to *nodeDegrees[a] + nodeDegrees[b]*. This is similar to what we were looking for in the previous loop.
1. Set **adjustedDegree** to *sumDegree - edgeDegrees[a][b]*. This is the true value of *incidents(a, b)*.
1. If **sumDegree** was included in the count, meaning *sumDegree > query*, but *adjustedDegree < query*, decrement **count**.

At the end, set *answers[i] = count*.

## Space and Time Complexity
The space complexity is *O(n + e + q)*. We'll be using an array of size *n*, a map with *e* key-value pairs, and an array of size *q* for the answers (though we could also overwrite the queries array to save that space if we needed to). The time complexity is *O(q⋅(n+e))*. Determining the degrees of the nodes + edges takes *O(n)* time, then for each query, we iterate over the sorted list of node degrees, which takes *O(n)* time, then iterate over the list of edge degrees, which takes *O(e)* time.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)