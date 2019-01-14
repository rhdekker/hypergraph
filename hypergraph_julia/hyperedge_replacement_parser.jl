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


struct HyperEdge
    label::String
    source::Array{String}
    target::Array{String}
end

struct StateMachine
    hypergraph::Array{HyperEdge}
    rules::Dict{String, Array{HyperEdge}}
end

function he_replace(state_machine::StateMachine, label::String)
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


