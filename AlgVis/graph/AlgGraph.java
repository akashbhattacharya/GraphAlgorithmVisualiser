package graph;

import java.util.HashSet;
import java.util.Set;
/**
 * This class represents an graph consisting of edges and vertices
 */
public class AlgGraph {
    public Set<AlgVertex> vertices;
    public Set<AlgEdge> edges;

    public AlgGraph() {
        vertices = new HashSet<AlgVertex>();
        edges = new HashSet<AlgEdge>();
    }

    public Set<AlgVertex> getVertices() {
        return vertices;
    }

    public Set<AlgEdge> getEdges() {
        return edges;
    }

    public void addVertex(AlgVertex v) {
        vertices.add(v);
    }

    public void addEdge(AlgEdge e) {
        edges.add(e);
    }
}
