//This code is not a part of Gralog's original source code, Gralog's code base has been modified to include this file
package gralog.xml;

import gralog.structure.Edge;
import gralog.structure.Structure;
import gralog.structure.Vertex;

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

/**
 * This class generates an XML file when the Dijkstra class is called in Gralog
 */

public class XMLDijkstra {
    DocumentBuilderFactory documentFactory;
    DocumentBuilder documentBuilder;
    Document document;
    String xmlFilePath;
    Element root;
    Element iterations;
    int ctr;


    public void graphElement(Structure struct) throws ParserConfigurationException //Creates graph element in XML
    {
        ctr=0;
        HashSet<Vertex> vSet = new HashSet<>();
        vSet.addAll(struct.getVertices());

        HashSet<Edge> eSet = new HashSet<>();
        eSet.addAll(struct.getEdges());

        xmlFilePath = "gralog-core\\src\\main\\java\\gralog\\xml\\resources\\dijkstra.xml";

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

        for(Vertex v : vSet) //Appends vertices to document
        {
            Element vertex = document.createElement("vertex");
            vertices.appendChild(vertex);

            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(v.label));
            vertex.appendChild(id);


            Element xCoordinate = document.createElement("x_coordinate");
            xCoordinate.appendChild(document.createTextNode(v.getCoordinates().getX()+""));
            vertex.appendChild(xCoordinate);

            Element yCoordinate = document.createElement("y_coordinate");
            yCoordinate.appendChild(document.createTextNode(v.getCoordinates().getY()+""));
            vertex.appendChild(yCoordinate);
        }

        Element edges = document.createElement("edges");
        graph.appendChild(edges);

        for(Edge e : eSet) //Appends edges to document
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
            weight.appendChild(document.createTextNode( e.weight+""));
            edge.appendChild(weight);
        }
        iterations = document.createElement("iterations");
        root.appendChild(iterations);
    }

    public void iterationElement(Vertex v, HashSet<Vertex> setE, HashMap<Vertex, Double> distances) //Creates iteration elements in document
    {
        ctr++;
        Element iteration = document.createElement("iteration");
        iterations.appendChild(iteration);

        Element number = document.createElement("iteration_number");
        number.appendChild(document.createTextNode(ctr+""));
        iteration.appendChild(number);

        Element vVertex = document.createElement("visited_vertex");
        iteration.appendChild(vVertex);

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(v.label));
        vVertex.appendChild(id);

        Element xCoordinate = document.createElement("x_coordinate");
        xCoordinate.appendChild(document.createTextNode(v.getCoordinates().getX()+""));
        vVertex.appendChild(xCoordinate);

        Element yCoordinate = document.createElement("y_coordinate");
        yCoordinate.appendChild(document.createTextNode(v.getCoordinates().getY()+""));
        vVertex.appendChild(yCoordinate);


        Element queue = document.createElement("queue");
        iteration.appendChild(queue);

        for(Vertex i : setE)
        {
            Element vertex = document.createElement("vertex");
            queue.appendChild(vertex);

            Element qid = document.createElement("id");
            qid.appendChild(document.createTextNode(i.label));
            vertex.appendChild(qid);


            Element qxCoordinate = document.createElement("x_coordinate");
            qxCoordinate.appendChild(document.createTextNode(i.getCoordinates().getX()+""));
            vertex.appendChild(qxCoordinate);

            Element qyCoordinate = document.createElement("y_coordinate");
            qyCoordinate.appendChild(document.createTextNode(i.getCoordinates().getY()+""));
            vertex.appendChild(qyCoordinate);
        }

        Element dist = document.createElement("distances");
        iteration.appendChild(dist);

        var d = distances.keySet();
        for(Vertex e:d)
        {
            Element distance = document.createElement("distance");
            dist.appendChild(distance);

            Element dVertex = document.createElement("vertex");
            dVertex.appendChild(document.createTextNode(e.label));
            distance.appendChild(dVertex);

            Element dValue = document.createElement("value");
            dValue.appendChild(document.createTextNode(distances.get(e)+""));
            distance.appendChild(dValue);
        }

    }

    public void transformer() throws TransformerException //Creates and saves the XML document
    {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        transformer.transform(domSource, streamResult);
        System.out.println("Done creating XML File");
        XLSTApply x = new XLSTApply();
        x.main("dijkstra.xml","dijkstraslides.xsl");

    }

}

