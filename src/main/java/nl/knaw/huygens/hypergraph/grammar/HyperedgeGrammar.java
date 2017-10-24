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

import nl.knaw.huygens.hypergraph.core.Hypergraph;

import java.util.HashMap;
import java.util.Map;

public class HyperedgeGrammar<N, H> {
    private final Map<String, Hypergraph<N,H>> rules;

    HyperedgeGrammar() {
        this.rules = new HashMap<>();
    }

    // label of the hyperedge to replace, followed by the hypergraph to replace it with.
    public void addRule(String leftHandSide, Hypergraph<N, H> rightHandSide) {
        rules.put(leftHandSide, rightHandSide);
    }
}
