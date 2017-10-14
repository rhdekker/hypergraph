package nl.knaw.huygens.hypergraph.core;

/*
* Generic Hyperclass definition
* Directed, labelled, hyperedges (one to many)
* Are we going to make the child nodes ordered?
* @author: Ronald Haentjens Dekker
*/


import java.util.*;

public class Hypergraph<N, H> {
    private Set<N> nodes;
    private final Map<N, Set<H>> incomingEdges;
    private final Map<N, Set<H>> outgoingEdges;
    private final Map<H, N> sourceNode;
    private final Map<H, Set<N>> targetNodes;

    public Hypergraph() {
        this.nodes = new HashSet<>();
        this.incomingEdges = new HashMap<>();
        this.outgoingEdges = new HashMap<>();
        this.sourceNode = new HashMap<>();
        this.targetNodes = new HashMap<>();
    }

    public void addNode(N node) {
        this.nodes.add(node);
    }

    public void addDirectedHyperedge(H edge, String label, N source, N... targets) {
        //TODO: check whether source node is in nodes
        // convert Array target to set
        Set<N> targetSet = new HashSet<N>();
        targetSet.addAll(Arrays.asList(targets));
        // set targets, source
        // set source
        sourceNode.put(edge, source);
        targetNodes.put(edge, targetSet);
        // set incoming
        for (N target : targets) {
            incomingEdges.putIfAbsent(target, new HashSet<>()).add(edge);
        }
        // set outgoing
        outgoingEdges.putIfAbsent(source, new HashSet<>()).add(edge);
    }
}
