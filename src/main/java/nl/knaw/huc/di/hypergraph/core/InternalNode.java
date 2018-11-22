package nl.knaw.huc.di.hypergraph.core;


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
public class InternalNode<K> {
    private K[] keysInThisNode;

}
