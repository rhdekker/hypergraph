#=
hyperedge replacment parser:
- Julia version: 1.0.3
- Author: rhdekker
- Date: 2019-01-14
=#

using DataStructures

# We hebben een hypergraph definitie nodig
# Het simpelste om te doen is is een lijst van hyperedges
# een hyperedge heeft een label en georderede lijst van source en target nodes.
# De hypergraph in de parser begint in de staat o - [S] - o
# NOTE: als S later gebruikt wordt als tag kunnen we natuurlijk de naam veranderne in star tof iets
# dergelijks
# Dan verandert de start regel de hele boel in drie non terminals
# Mary loves John
# Dan vervangen we een voor een non terminal dooor een terminal
# het is even de vraag of source en target strings of ints moeten zijn
# liever zou ik natuurlijks intss hebben, maar hoe kan ik dan de intenre edges aangeven?
# dat wordt lastig, dsu vandaar strings voor dit moment
# Voor nu fake ik de tokens uit de tokenizer gewoon
# door middel van een string array

const Node = String

struct HyperEdge
    label::String
    source::Array{Node}
    target::Array{Node}
end

const HyperGraph = Array{HyperEdge}

struct StateMachine
    hypergraph::HyperGraph
    rules::Dict{String, HyperGraph}
end

function find_hyperedge_in_hypergraph_by_label(hypergraph::HyperGraph, label::String)
    found = first(filter(e -> e.label == label, hypergraph))
    return found
end

# since the hypergraph is just an array and not a set or a dict we can not just tell the array
# to remove an item
# we first need to find the index.
function delete_hyperedge_in_hypergraph(hypergraph::HyperGraph, hyperedge::HyperEdge)
    index = findfirst(e -> e == hyperedge, hypergraph)
    deleteat!(hypergraph, index)
end

function he_replace(state_machine::StateMachine, label::String)

    # nu moeten we de ene interne source node van de righthandside van de rule vervangen door de eerse
    # externe source node van de hypergraph van de state machine

    # De hyperedge in de graph met hetzelfde label moeten we nu zien te vervangen..
    # Die we moeten we dan eerst zien te vinden; in een kleine graaf kunnen we gewoon de lijst 1 voor 1 aflopen.
    # In een grotere graaf is een dictonary beter.

    hyperedge_to_replace=find_hyperedge_in_hypergraph_by_label(state_machine.hypergraph, label)
    # println("hyperedge to replace ", hyperedge_to_replace)

    # hmmm hoe weet je bij deze aanpak of de nodes goed geconnect zijn?
    # De enige manier om achter de nodes te komen is door over de edges te lopen en ze er dan
    # uit te halen

    # In de rules dict zit de righthandside van de rule met label v d left hand side
    # eerst kijken of er wel een rule is voor ene zekr label
    # als niet, dan had de vorige al een exceptie gegooid
    hypergraph_to_replace_with = state_machine.rules[label]
    # println("hypergraph to replace with: ", hypergraph_to_replace_with)

    # maak een kopie met daarin de open source en target nodes vervangen
    # ga over alle hyperedges in the hypergraph
    # hou twee counters bij: een voor de open source nodes en een voor de open target nodes

    iter_source_nodes = Iterators.Stateful(hyperedge_to_replace.source)
    iter_target_nodes = Iterators.Stateful(hyperedge_to_replace.target)
    copy_hyperedges::Array{HyperEdge} = []
    for edge in hypergraph_to_replace_with
        # ga over alle source node en kopieer als nodig
        copy_source_nodes::Array{Node} = []
        for source_node in edge.source
            # check source node
            if source_node == "_"
                a = popfirst!(iter_source_nodes)
                push!(copy_source_nodes, a)
            else
                push!(copy_source_nodes, source_node)
            end
        end
#         println(copy_source_nodes)
        # ga over alle target node en kopieer als nodig
        copy_target_nodes::Array{Node} = []
        for target_node in edge.target
            # check target node
            if target_node == "_"
                a = popfirst!(iter_target_nodes)
                push!(copy_target_nodes, a)
            else
                push!(copy_target_nodes, target_node)
            end
        end
#         println(copy_target_nodes)
        push!(copy_hyperedges, HyperEdge(edge.label, copy_source_nodes, copy_target_nodes))
    end

    # println(copy_hyperedges)

    # We delete the old hyperedge,
    delete_hyperedge_in_hypergraph(state_machine.hypergraph, hyperedge_to_replace)

    # and add the new hyperedges to the internal hypergraph of the statemachine.
    for edge in copy_hyperedges
        push!(state_machine.hypergraph, edge)
    end
    println("result: ", state_machine.hypergraph)

end

function main()
    # create an array of tokens
    # this shoudl be doen by the tokenizer
    # but for now we don't care about that
    tokens = String["John", "loves", "Mary"]


    # create the initial state of the state machine
    hg = HyperEdge[]
    push!(hg, HyperEdge("S", ["1"], ["2"]))

    # now we need to create a set of rules to do the replacement with
    # in the rules we map a label of a hyperedge to a hypergraph
    rules = Dict{String, HyperGraph}("S" => [HyperEdge("JOHN", ["_"], ["3"]), HyperEdge("LOVES",["3"], ["_"])])
    # Hier komen nog veel meer rules


    # hier maken we de state machine aan
    state_machine = StateMachine(hg, rules)
    # TODO: misschien mot e rbij de statemachine ook nog een pointerr nar rr de huidige
    # non temrinal

    # om te beginnen moeten we nu de initial state vervangen door de state in de S rule
    # Dat is al een leuke rule
    # want daar moeten we echte replcament voor doen
    # hmm daar m oet ik zowel de hg als de rules meegeven -> state machine

    println(state_machine.hypergraph)
    he_replace(state_machine, "S")

end


main()


