package nl.knaw.huc.di.hypergraph.core;

/*
 * @author: Ronald Haentjens Dekker
 * @date: 22-11-2018
 *
 */
public class BPlusMap<I extends Number, S> {
    // this field cannot be final because the root can change.
    private Node root;

    BPlusMap() {
        this.root = new LeafNode<>();
    }

    public void put(I key, S s) {
        // zoek in de root leaf node of
        // i know this cast is dangerous.
        LeafNode<I, S> leafnode = (LeafNode) root;
        boolean found = leafnode.doesItContain(key);

        if (found) {
            throw new RuntimeException("Item is already in this map!");
        }
        // check whether the node is full
        if (leafnode.isNodeFull()) {
            throw new RuntimeException("Node is full! We need to split this node.");
        }
        // add key to this node.
        leafnode.addKey(key);
    }

    public Node getRoot() {
        return root;
    }
}
