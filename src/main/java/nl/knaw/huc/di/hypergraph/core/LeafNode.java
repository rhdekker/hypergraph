package nl.knaw.huc.di.hypergraph.core;


import java.util.Arrays;
import java.util.Optional;

/*
 * Node in a b-tree plus object.
 *
 * @author: Ronald Haentjens Dekker
 * @date: 22-11-2018
 *
 * Every node contains a small array of keys...
 * If the keys is present in an internal node.
 * than we go to the connected leaf node?
 * The connected leaf node than has the real value?
 *
 */
public class LeafNode<K, V> implements Node {
    private K[] keysInThisNode; // this might not be needed.
    private V[] valuesInThisNode; // this is were the actual values are stored.
    private int itemsInArray;

    public LeafNode() {
        this.keysInThisNode = (K[]) new Object[4];
    }

    public boolean doesItContain(K key) {
        Optional<K> found = Arrays.stream(keysInThisNode).filter(k -> k == key).findFirst();
        return found.isPresent();
    }

    public boolean isNodeFull() {
        // ok we can calculate this, or store this.
        // For now we store this.
        return (itemsInArray != 4);
    }

    public void addKey(K key) {
        // since we store the amount of keys present
        // we know right away were to insert.
        this.keysInThisNode[itemsInArray] = key;
        itemsInArray++;
    }
}


