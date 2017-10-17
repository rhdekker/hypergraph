package nl.knaw.huygens.hypergraph.core;

import org.junit.jupiter.api.Test;

import java.util.List;

public class DirectedAcyclicGraphTest {

    class Node {

    }

    class Edge {

    }

    @Test
    public void testBla() {
        DirectedAcyclicGraph<String, Edge> g = new DirectedAcyclicGraph<>();
        g.setRootNode("root");
        g.addNode("child1");
        g.addNode("child2");
        g.addNode("child3");
        g.addDirectedEdge(new Edge(), "root", "child1");
        g.addDirectedEdge(new Edge(), "root", "child2");

        // traverse nodes in order
        List<String> i = g.traverse();
        System.out.println(i);

    }
}
