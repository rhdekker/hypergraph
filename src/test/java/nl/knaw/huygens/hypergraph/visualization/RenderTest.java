package nl.knaw.huygens.hypergraph.visualization;

/*-
 * #%L
 * hypergraph
 * =======
 * Copyright (C) 2017 Huygens ING (KNAW)
 * =======
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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

    // In GraphViz you can have directed and undirected edges mixed
    // By using subgraphs..
    // https://stackoverflow.com/questions/13236975/graphviz-dot-mix-directed-and-undirected
    // Graphviz can various node shapes have
    // http://www.graphviz.org/doc/info/shapes.html

    // rendering means transforming the hypergraph to directed acyclic graph
    // by using two kind of node types
    // A box for the hyperedge
}
