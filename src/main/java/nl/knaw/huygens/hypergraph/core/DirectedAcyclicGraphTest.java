package nl.knaw.huygens.hypergraph.core;

import org.junit.jupiter.api.Test;

import java.util.List;

public class DirectedAcyclicGraphTest {

    @Test
    public void testBla() {
        DirectedAcyclicGraph<String> g = new DirectedAcyclicGraph<>();
        g.setRootNode("root");
        g.addNode("child1", "");
        g.addNode("child2", "");
        g.addNode("child3", "");
        g.addDirectedEdge( "root", "child1");
        g.addDirectedEdge( "root", "child2");

        // traverse nodes in order
        List<String> i = g.traverse();
        System.out.println(i);

    }
}
