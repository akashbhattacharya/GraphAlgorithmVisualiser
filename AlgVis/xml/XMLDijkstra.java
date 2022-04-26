package xml;

import graph.*;
import java.util.HashMap;
import java.util.HashSet;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class XMLDijkstra {
    DocumentBuilderFactory documentFactory;
    DocumentBuilder documentBuilder;
    Document document;
    String xmlFilePath;
    Element root;
    Element iterations;
    int ctr;
    /**
     * This class generates an XML document when the Dijkstra class is called
     */

    public void graphElement(AlgGraph struct) throws ParserConfigurationException
    {
        ctr=0;
        HashSet<AlgVertex> vSet = new HashSet<>();
        vSet.addAll(struct.getVertices());

        HashSet<AlgEdge> eSet = new HashSet<>();
        eSet.addAll(struct.getEdges());

        xmlFilePath = "xml\\resources\\dijkstra.xml";

        documentFactory = DocumentBuilderFactory.newInstance();

        documentBuilder = documentFactory.newDocumentBuilder();

        document = documentBuilder.newDocument();
        document.setXmlVersion("1.0");

        root = document.createElement("Dijkstra");
        document.appendChild(root);

        Element graph = document.createElement("graph");
        root.appendChild(graph);

        Element vertices = document.createElement("vertices");
        graph.appendChild(vertices);

        for(AlgVertex v : vSet)
        {
            Element vertex = document.createElement("vertex");
            vertices.appendChild(vertex);

            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(v.label));
            vertex.appendChild(id);


            Element xCoordinate = document.createElement("x_coordinate");
            xCoordinate.appendChild(document.createTextNode(v.getxCoordinate()+""));
            vertex.appendChild(xCoordinate);

            Element yCoordinate = document.createElement("y_coordinate");
            yCoordinate.appendChild(document.createTextNode(v.getyCoordinate()+""));
            vertex.appendChild(yCoordinate);
        }

        Element edges = document.createElement("edges");
        graph.appendChild(edges);

        for(AlgEdge e : eSet)
        {
            Element edge = document.createElement("edge");
            edges.appendChild(edge);

            Element endpoint1 = document.createElement("endpoint1");
            endpoint1.appendChild(document.createTextNode(e.getSource().label));
            edge.appendChild(endpoint1);

            Element endpoint2 = document.createElement("endpoint2");
            endpoint2.appendChild(document.createTextNode(e.getTarget().label));
            edge.appendChild(endpoint2);

            // lastname element
            Element weight = document.createElement("weight");
            weight.appendChild(document.createTextNode( e.getWeight()+""));
            edge.appendChild(weight);
        }
        iterations = document.createElement("iterations");
        root.appendChild(iterations);
    }

    public void iterationElement(AlgVertex v, HashSet<AlgVertex> setE, HashMap<AlgVertex, Double> distances)
    {
        ctr++;
        Element iteration = document.createElement("iteration");
        iterations.appendChild(iteration);

        Element number = document.createElement("iteration_number");
        number.appendChild(document.createTextNode(ctr+""));
        iteration.appendChild(number);

        Element vAlgVertex = document.createElement("visited_vertex");
        iteration.appendChild(vAlgVertex);

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(v.label));
        vAlgVertex.appendChild(id);

        Element xCoordinate = document.createElement("x_coordinate");
        xCoordinate.appendChild(document.createTextNode(v.getxCoordinate()+""));
        vAlgVertex.appendChild(xCoordinate);

        Element yCoordinate = document.createElement("y_coordinate");
        yCoordinate.appendChild(document.createTextNode(v.getyCoordinate()+""));
        vAlgVertex.appendChild(yCoordinate);


        Element queue = document.createElement("queue");
        iteration.appendChild(queue);

        for(AlgVertex i : setE)
        {
            Element vertex = document.createElement("vertex");
            queue.appendChild(vertex);

            Element qid = document.createElement("id");
            qid.appendChild(document.createTextNode(i.label));
            vertex.appendChild(qid);


            Element qxCoordinate = document.createElement("x_coordinate");
            qxCoordinate.appendChild(document.createTextNode(i.getxCoordinate()+""));
            vertex.appendChild(qxCoordinate);

            Element qyCoordinate = document.createElement("y_coordinate");
            qyCoordinate.appendChild(document.createTextNode(i.getyCoordinate()+""));
            vertex.appendChild(qyCoordinate);
        }

        Element dist = document.createElement("distances");
        iteration.appendChild(dist);

        var d = distances.keySet();
        for(AlgVertex e:d)
        {
            Element distance = document.createElement("distance");
            dist.appendChild(distance);

            Element dAlgVertex = document.createElement("vertex");
            dAlgVertex.appendChild(document.createTextNode(e.label));
            distance.appendChild(dAlgVertex);

            Element dValue = document.createElement("value");
            dValue.appendChild(document.createTextNode(distances.get(e)+""));
            distance.appendChild(dValue);
        }

    }

    public void transformer() throws TransformerException
    {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        transformer.transform(domSource, streamResult);
        System.out.println("Done creating XML File");

    }

}


