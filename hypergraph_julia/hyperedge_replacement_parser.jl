#=
hyperedge replacment parser:
- Julia version: 1.0.3
- Author: rhdekker
- Date: 2019-01-14
=#


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

function he_replace(state_machine::StateMachine, label::String)

    # nu moeten we de ene interne source node van de righthandside van de rule vervangen door de eerse
    # externe source node van de hypergraph van de state machine

    # De hyperedge in de graph met hetzelfde label moeten we nu zien te vervangen..
    # Die we moeten we dan eerst zien te vinden; in een kleine graaf kunnen we gewoon de lijst 1 voor 1 aflopen.
    # In een grotere graaf is een dictonary beter.

    hyperedge_to_replace=find_hyperedge_in_hypergraph_by_label(state_machine.hypergraph, label)
    println(hyperedge_to_replace)

    # hmmm hoe weet je bij deze aanpak of de nodes goed geconnect zijn?
    # De enige manier om achter de nodes te komen is door over de edges te lopen en ze er dan
    # uit te halen

    # In de rules dict zit de righthandside van de rule met label v d left hand side
    # eerst kijken of er wel een rule is voor ene zekr label
    # als niet, dan had de vorige al een exceptie gegooid
    hypergraph_to_replace_with = state_machine.rules[label]
    println(hypergraph_to_replace_with)

    # we need to create a new subhypergraph
    # we go over all the hyperedges of the hypergraph to replace with
    # Eh what to do with multiple hyperedges?
    # Is it only the first one that has replaceble nodes ?
    # well if I look at my own real life example (John loves mary), than the answer is no
    # dus een enkele hyperedge kan 1 of meerdere sources dan wel targets hebben
    # en er kunnen meerdere hyperedges zijn
    # We moeten dus op zoek naar al die dingen... voor nu maar even niet..

    # eerst groeperen we alle inkomende internal ndoes en inkomende external bij elkaar

    # Alle open source nodes van de righthandside van de replacement rule zoeken
    result_source_nodes = [(he, idx) for he in hypergraph_to_replace_with for (idx, source) in enumerate(he.source) if source == "_"]

    # we zijn  benieuwd!
    println(result_source_nodes)

    # alle externe nodes zodeken van de gelabelde hyperedge die vervangen gaat worden
    result_incoming_external_nodes = []

    # hmmm dit zijn er natuurlijk niet zoveel...
    # Er is maar 1 hyperedge om te vervangen
    # Hier is het punt veel meer dat het aantal source nodes gelijk moet aan het aantla
    # open internal source nodes


    # we gaan de result source nodes af en mappen die naar een external node... op basis van de positie in de result array
    # Ik kan de een op de ander mappen op basis van een dict.
    # Een andere optie zou zijn om een tuple te maken..
    # Dan krijg je een triple.

    # the 1st value of the triple is the hyperedge of the graph that is used as the replacement
    # and 2nd value of the triple is the position of the source node on that hyperedge
    # the 3rd value of the triple is the external closed vertex that there is a connection with
    # TODO: Maybe a named tuple is better here
    map_internal_open_node_to_external_node = [(hyperedge, source_node_position, hyperedge_to_replace.source[idx]) for (idx, (hyperedge, source_node_position)) in enumerate(result_source_nodes)]
    println(map_internal_open_node_to_external_node)

    println("Do nothing!")
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
    rules = Dict{String, Array{HyperEdge}}("S" => [HyperEdge("JOHN", ["_"], ["_"])])
    # Hier komen nog veel meer rules


    # hier maken we de state machine aan
    state_machine = StateMachine(hg, rules)
    # TODO: misschien mot e rbij de statemachine ook nog een pointerr nar rr de huidige
    # non temrinal

    # om te beginnen moeten we nu de initial state vervangen door de state in de S rule
    # Dat is al een leuke rule
    # want daar moeten we echte replcament voor doen
    # hmm daar m oet ik zowel de hg als de rules meegeven -> state machine

    he_replace(state_machine, "S")

end


main()


