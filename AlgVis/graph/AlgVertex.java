package graph;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a vertex in a graph
 */
public class AlgVertex {
    public String label;
    public double xCoordinate;
    public double yCoordinate;
    public String id;
    public Set<AlgEdge> incidentEdges;

    public AlgVertex() {
        incidentEdges = new HashSet<AlgEdge>();
    }

    public void addEdge(AlgEdge e) {
        incidentEdges.add(e);
    }

    public Set<AlgEdge> getIncidentEdges() {
        return incidentEdges;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String vlabel) {
        this.label = vlabel;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
