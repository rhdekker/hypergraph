package nl.knaw.huc.di.hypergraph.core;



/*
 * @author: Ronald Haentjens Dekker
 * @date: 22-11-2018
 *
 * The idea is to create a map out of a balanced tree structure.
 *
 * NOTE: In the case of list: We use the position in the list as the key, rather than the value!
 * NOTE: But how then it is not better is it? It is only better we can put a small segment in between existing segments.
 *
 */
public class BPlusMapTest {

    public void testInsert() {
        BPlusMap<Integer, String> bPlusMap = new BPlusMap<>();
        bPlusMap.put(5, "The number 5");

        Node root = bPlusMap.getRoot();
    }
}
