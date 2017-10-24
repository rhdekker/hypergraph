package nl.knaw.huygens.hypergraph.core;

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
