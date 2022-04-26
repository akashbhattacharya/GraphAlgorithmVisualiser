package graph;

/**
 * This class represents an edge in an graph
 */
public class AlgEdge {
    public double eWeight;
    public AlgVertex eSource;
    public AlgVertex eTarget;
    public boolean directed;

    public AlgEdge() {
        eSource = new AlgVertex();
        eTarget = new AlgVertex();
    }

    public double getWeight() {
        return eWeight;
    }

    public void setWeight(double eWeight) {
        this.eWeight = eWeight;
    }

    public AlgVertex getSource() {
        return eSource;
    }

    public AlgVertex getTarget() {
        return eTarget;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public boolean isDirected() {
        return directed;
    }
}
