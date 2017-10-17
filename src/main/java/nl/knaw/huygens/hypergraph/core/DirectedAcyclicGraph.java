package nl.knaw.huygens.hypergraph.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// nu zou ik wel topological sort willen hebben
// teveel gedoe, kan ook gewoon een root node maken
public class DirectedAcyclicGraph<N, E> extends Hypergraph<N, E> {
    private N root;

    public DirectedAcyclicGraph() {
      super(GraphType.ORDERED);
    }

    public void addNode(N node) {
        super.addNode(node);
    }

    public void setRootNode(N root) {
        this.root = root;
    }

    //Question: do we want labels here?
    public void addDirectedEdge(E edge, N source, N target) {
        super.addDirectedHyperedge(edge, "", source, target);
    }

    public List<N> traverse() {
        N start = root;
        List<N> result = new ArrayList<>();
        result.add(start);
        Collection<E> outgoingEdges = this.getOutgoingEdges(start);
        for (E e : outgoingEdges) {
            result.add(this.getTarget(e));
        }
//      //        while (!outgoingEdges.isEmpty()) {
//        }
        // de recursie mist nog!
        // heb een stack nodig! Nou ja voor nu geen zin in..
        return result;
    }

    public N getTarget(E e) {
        Collection<N> nodes = super.getTargets(e);
        if (nodes.size()!=1) {
            throw new RuntimeException("trouble!");
        }
        return nodes.iterator().next();
    }

}
