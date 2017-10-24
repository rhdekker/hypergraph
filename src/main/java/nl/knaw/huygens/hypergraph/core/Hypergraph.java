package nl.knaw.huygens.hypergraph.core;

/*
* Generic Hypergraph definition
* Directed, labelled, hyperedges (one to many)
* Are we going to make the child nodes ordered?
* @author: Ronald Haentjens Dekker
*/


import java.util.*;
import java.util.function.Function;

public class Hypergraph<N, H> {
    private final GraphType graphType;
    private final Function<N, Collection<H>> mappingFunction;
    private Set<N> nodes;
    private final Map<N, Collection<H>> incomingEdges;
    private final Map<N, Collection<H>> outgoingEdges;
    private final Map<H, N> sourceNode;
    private final Map<H, Collection<N>> targetNodes;
    private final Map<H, String> edgeLabels;
    private final Map<N, String> nodeLabels;

    public Hypergraph(GraphType graphType) {
        this.graphType = graphType;
        this.nodes = new HashSet<>();
        this.incomingEdges = new HashMap<>();
        this.outgoingEdges = new HashMap<>();
        this.sourceNode = new HashMap<>();
        this.targetNodes = new HashMap<>();
        this.edgeLabels = new HashMap<>();
        this.nodeLabels = new HashMap<>();
        // create switch
        if (GraphType.ORDERED == this.graphType) {
            this.mappingFunction = e -> new ArrayList<>();
        } else {
            this.mappingFunction = e -> new HashSet<>();
        }
    }

    public void addNode(N node, String label) {
        this.nodes.add(node);
        this.nodeLabels.put(node, label);
    }

    public void addDirectedHyperedge(H edge, String label, N source, N... targets) {
        //TODO: check whether source node is in nodes
        //NOTE: The way it is done now, is that nodes are not added explicitly to the graph
        //NOTE: but rather indirectly through the edges.
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
            incomingEdges.computeIfAbsent(target, mappingFunction).add(edge);
        }
        // set outgoing
        outgoingEdges.computeIfAbsent(source, mappingFunction).add(edge);
        // set label
        edgeLabels.put(edge, label);
    }

    Collection<N> getTargets(H e) {
        return targetNodes.get(e);
    }


    Collection<H> getOutgoingEdges(N node) {
        return outgoingEdges.getOrDefault(node, Collections.EMPTY_LIST);
    }

    public enum GraphType {
        ORDERED,
        UNORDERED
    }
}

