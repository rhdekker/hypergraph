package nl.knaw.huygens.hypergraph.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

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
        Stack<N> nodesToVisit = new Stack<>();
        nodesToVisit.add(root);
        List<N> result = new ArrayList<>();
        while(!nodesToVisit.isEmpty()) {
           N pop = nodesToVisit.pop();
           result.add(pop);
           Collection<E> outgoingEdges = this.getOutgoingEdges(pop);
           for (E e : outgoingEdges) {
               N target = this.getTarget(e);
               nodesToVisit.add(target);
           }
        }
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
