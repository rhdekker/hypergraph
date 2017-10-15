package nl.knaw.huygens.hypergraph.core;

/*
* Generic Hyperclass definition
* Directed, labelled, hyperedges (one to many)
* Are we going to make the child nodes ordered?
* @author: Ronald Haentjens Dekker
*/


import java.util.*;

public class Hypergraph<N, H> {
    private final GraphType graphType;
    private Set<N> nodes;
    private final Map<N, Collection<H>> incomingEdges;
    private final Map<N, Collection<H>> outgoingEdges;
    private final Map<H, N> sourceNode;
    private final Map<H, Collection<N>> targetNodes;

    public Hypergraph(GraphType graphType) {
        this.graphType = graphType;
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
        // set source
        sourceNode.put(edge, source);
        // set targets
        if (GraphType.ORDERED == this.graphType) {
            List<N> targetList = Arrays.asList(targets);
            targetNodes.put(edge, targetList);
        } else {
            // convert Array target to set
            Set<N> targetSet = new HashSet<N>();
            targetSet.addAll(Arrays.asList(targets));
            targetNodes.put(edge, targetSet);
        }
        // set incoming
        for (N target : targets) {
            if (GraphType.ORDERED == this.graphType) {
                incomingEdges.putIfAbsent(target, new ArrayList<>()).add(edge);
            } else {
                incomingEdges.putIfAbsent(target, new HashSet<>()).add(edge);
            }
        }
        // set outgoing
        if (GraphType.ORDERED == this.graphType) {
            outgoingEdges.putIfAbsent(source, new ArrayList<>()).add(edge);
        } else {
            outgoingEdges.putIfAbsent(source, new HashSet<>()).add(edge);
        }
    }

    public enum GraphType {
        ORDERED,
        UNORDERED
    }
}

