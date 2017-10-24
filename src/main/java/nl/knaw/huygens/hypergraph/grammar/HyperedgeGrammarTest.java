package nl.knaw.huygens.hypergraph.grammar;

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

import nl.knaw.huygens.hypergraph.core.DirectedAcyclicGraph;
import nl.knaw.huygens.hypergraph.core.Hypergraph;
import org.junit.jupiter.api.Test;

class HyperedgeGrammarTest {
    @Test
    void testIDontKnow() {
        DirectedAcyclicGraph<String> dagToValidate = new DirectedAcyclicGraph<>();
        dagToValidate.setRootNode("text");
        dagToValidate.addDirectedEdge( "text", "poem");
        dagToValidate.addDirectedEdge("poem", "lg");
        // every rule of the hyperedge grammar consists of a hyperedge on the lefthandside and
        // a hypergraph on the righthandside.
        // we first construct a rule for the start situation
        // so that is a hypergraph consisting of just two nodes
        // and one hyperedge.
        // I let hyperedges start at 10
        // so that I don't get confused with the nodes numbers lower than 10
        // but maybe that is not enough
        // node 1 == start node
        // node 2 == end node
        // he 10 = "root"
        // the lefthandside is just the label
        // because we do label edge replacement.
        // now we want to add a label to the nodes... in the hypergraph
        // the labels will become the nodes in the dag.
        // I can also use real strings
        // so integers with labels on the
        // oh no ... lg are repeated, lines especially.
        HyperedgeGrammar gr = new HyperedgeGrammar();
        Hypergraph<Integer, Integer> startSituation = new Hypergraph<>(Hypergraph.GraphType.ORDERED);
        startSituation.addDirectedHyperedge(100, "*root", 1,2 );
        Hypergraph<Integer, Integer> rootToTextRule = new Hypergraph<>(Hypergraph.GraphType.ORDERED);
        rootToTextRule.addNode(3, "text");
        rootToTextRule.addDirectedHyperedge(101, "*poem", 3, 4);
        gr.addRule("*start", startSituation);
        gr.addRule("*root", rootToTextRule);
    }

}
