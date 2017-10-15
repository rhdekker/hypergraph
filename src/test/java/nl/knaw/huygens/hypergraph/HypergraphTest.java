package nl.knaw.huygens.hypergraph;

import nl.knaw.huygens.hypergraph.core.Hypergraph;
import org.junit.jupiter.api.Test;

class HypergraphTest {

    private class MyNode {

    }

    private class MyHyperedge {

    }

    @Test
    void testHypergraph() {
        Hypergraph<MyNode, MyHyperedge> graph = new Hypergraph<>(Hypergraph.GraphType.ORDERED);
        MyNode node = new MyNode();
        MyNode node2 = new MyNode();
        MyNode node3 = new MyNode();
        MyHyperedge edge = new MyHyperedge();
        graph.addDirectedHyperedge(edge, "S", node, node2, node3);
        
    }
}
