#=
types:
- Julia version: 1.0.3
- Author: rhdekker
- Date: 2019-01-14
=#

struct HyperEdge{N}
    label::String
    source::Array{N}
    target::Array{N}
end

struct HyperGraph{N} <: AbstractArray{HyperEdge, 1}
    edges::Array{HyperEdge{N}}
end

# minimum methods for abstract array; I delegate everything to the edges array
Base.size(HG::HyperGraph) = size(HG.edges)

Base.IndexStyle(::Type{<:HyperGraph}) = IndexLinear()

Base.getindex(HG::HyperGraph, i::Int) = getindex(HG.edges, i)

# make the hypergraph mutable
Base.deleteat!(HG::HyperGraph, i::Integer) = deleteat!(HG.edges, i)

Base.append!(HG::HyperGraph, edges::Array{HyperEdge{N}}) where N = append!(HG.edges, edges)

function find_hyperedge_in_hypergraph_by_label(hypergraph::HyperGraph, label::String)
    found = first(filter(e -> e.label == label, hypergraph))
    return found
end

# since the hypergraph is just an array and not a set or a dict we can not just tell the array
# to remove an item
# we first need to find the index.
function delete_hyperedge_in_hypergraph!(hypergraph::HyperGraph, hyperedge::HyperEdge)
    index = findfirst(e -> e == hyperedge, hypergraph)
    deleteat!(hypergraph, index)
end

