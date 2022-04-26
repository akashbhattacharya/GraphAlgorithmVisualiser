package graph;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;

import java.io.IOException;

/**
 * This class is used to parse a graph file and read it into an AlgGraph object
 */
public class AlgCreateGraph {
    AlgGraph graph;

    public AlgCreateGraph() {
        graph = new AlgGraph();
    }

    public AlgGraph parseXML(String fname) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(fname);

        String label, id, x, y, weight, directed, target, source;

        NodeList vertices = doc.getElementsByTagName("node");
        for (int i = 0; i < vertices.getLength(); i++) {
            AlgVertex v = new AlgVertex();
            Element e = (Element) vertices.item(i);
            label = e.getAttributes().getNamedItem("label").getNodeValue();
            v.setLabel(label);
            id = e.getAttributes().getNamedItem("id").getNodeValue();
            v.setId(id);
            x = e.getAttributes().getNamedItem("x").getNodeValue();
            v.setxCoordinate(Double.parseDouble(x));
            y = e.getAttributes().getNamedItem("y").getNodeValue();
            v.setyCoordinate(Double.parseDouble(y));
            graph.addVertex(v);

        }

        NodeList edges = doc.getElementsByTagName("edge");
        for (int i = 0; i < edges.getLength(); i++) {
            AlgEdge v = new AlgEdge();
            Element e = (Element) edges.item(i);
            weight = e.getAttributes().getNamedItem("weight").getNodeValue();
            v.setWeight(Double.parseDouble(weight));
            directed = e.getAttributes().getNamedItem("isdirected").getNodeValue();
            v.setDirected(Boolean.parseBoolean(directed));
            target = e.getAttributes().getNamedItem("target").getNodeValue();
            for (AlgVertex a : graph.vertices) {
                if (a.getId().equals(target)) {
                    v.eTarget = a;
                    break;
                }
            }
            source = e.getAttributes().getNamedItem("source").getNodeValue();
            for (AlgVertex a : graph.vertices) {

                if (a.getId().equals(source)) {
                    v.eSource = a;
                    break;
                }
            }
            graph.addEdge(v);
        }
        for (AlgVertex a : graph.vertices) {
            for (AlgEdge e : graph.edges) {
                if (a.getId().equals(e.eSource.id) || a.getId().equals(e.eTarget.id))
                    a.addEdge(e);
            }
        }

        return (graph);
    }

    public void printGraph() // Prints AlgGraph object information
    {
        for (AlgVertex a : graph.vertices) {
            System.out.println("ID- " + a.getId());
            System.out.println("Label- " + a.getLabel());
            System.out.println("x- " + a.getxCoordinate());
            System.out.println("y- " + a.getyCoordinate());
            for (AlgEdge e : a.getIncidentEdges()) {
                System.out.println(e.getSource().label + " - " + e.getTarget().label);
            }
        }
        for (AlgEdge e : graph.edges) {
            System.out.println("Weight- " + e.getWeight());
            System.out.println("Is Directed- " + e.isDirected());
            System.out.println("Source- " + e.eSource.getLabel());
            System.out.println("Target- " + e.eTarget.getLabel());
        }
    }

}
