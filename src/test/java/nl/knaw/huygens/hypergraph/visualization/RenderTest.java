package nl.knaw.huygens.hypergraph.visualization;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class RenderTest {
    @Test
    public void test() throws IOException {
        Graph g = graph("example1").directed().with(node("a").link(node("b")));
        Graphviz.fromGraph(g).width(200).render(Format.PNG).toFile(new File("/Users/ronalddekker/Desktop/ex1.png"));
    }
}
