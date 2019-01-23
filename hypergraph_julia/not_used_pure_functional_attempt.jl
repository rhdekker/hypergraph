#=
aantekeningen:
- Julia version: 1.0.3
- Author: rhdekker
- Date: 2019-01-22
=#


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
    open_source_nodes = [(he, idx) for (he) in hypergraph_to_replace_with for (idx, source) in enumerate(he.source) if source == "_"]

    # we zijn  benieuwd!
    # println(open_source_nodes)

    # alle externe nodes zodeken van de gelabelde hyperedge die vervangen gaat worden
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
    map_internal_open_node_to_external_node = [(hyperedge, source_node_position, hyperedge_to_replace.source[idx]) for (idx, (hyperedge, source_node_position)) in enumerate(open_source_nodes)]
    println(map_internal_open_node_to_external_node)

    # met makkelijkst is om hier een dictionary van te maken
    # Ik wil de eerste twee velden van de tuple maPPEN als de key en de derde als de value.
    m = Dict([(tuple[1], tuple[2]) => tuple[3] for tuple in map_internal_open_node_to_external_node])
    println(m)

    # Nu moeten we alle edges en de bijbehorende source nodes af ... als het niet in de dictionary voorkomt maken we simpelweg
    # een kopie. Anders linken we naar een bestaande node.
    n = Dict([(he,idx) => (he, idx) in keys(m) ? m[(he, idx)] : source for (he) in hypergraph_to_replace_with for (idx, source) in enumerate(he.source)])
    println(n)

    # Dit is leuk maar dan zijn we er nog niet helemaal de nodes moeten nu weer gegroepeerd worden per hyperedge.
    # Dat kan met een defaultDict en een array.
    # NOTE: Julia begrijpt niet wat ik bedoel dus ik het maar anders
#     o = DefaultDict(e -> [], [he => n[(he, idx)] for (he, idx) in keys(n)])
    o = DefaultDict([])
    [push!(o, he => n[he,idx]) for (he, idx) in keys(n)]
    println(o)



    # nu moeten we hetzelfde doen voor de target nodes
    open_target_nodes = [(he, idx) for he in hypergraph_to_replace_with for (idx, target) in enumerate(he.target) if target == "_"]
    map_open_target_nodes_to_external_target_nodes = [(hyperedge, target_node_position, hyperedge_to_replace.target[idx]) for (idx, (hyperedge, target_node_position)) in enumerate(open_target_nodes)]
    # println(map_open_target_nodes_to_external_target_nodes)


#=
    # er zit nog een uitdaging dat we met deze aanpak alleen tuples hebben van hyperedges en posities die daadwerkelijk open nodes hebben.
    # Voor het daadwerkelijke replacement proces is dit lastig aangezien we een kopie moeten maken van de RHS van de rule om de nieuwe hyperedges daadwerkelijk in
    # de graaf te plaatsen. Dat betreft dus ook de niet open nodes.

    # We zouden alle source en target nodes van de hyperedge een nummer kunnen geven.

    Je hebt eigenlijk twee vormen van nummering.
    Een oplopende voor alle nodes
    en een oplopende voor alleen de open nodes
    Een andere mogelijkheid is een dictionary for the open nodes
    ophet moment dat je dan alle nodes afloopt check je de dictionary
    maar dan is de hyperedge plus de positie een indicator
    Dat is niet lekker hashen.
    Waarschijnlijk kan het wel (een tuple hashen) maar willen we het niet.

=#
