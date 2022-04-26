//This code is not a part of Gralog's original source code, Gralog's code base has been modified to include this file
package gralog.xml;

import gralog.structure.Edge;
import gralog.structure.Vertex;

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
 * This class generates an XML file when the BreadthFirstSearchTree class is called in Gralog
 */

public class XMLBFS {
    DocumentBuilderFactory documentFactory;
    DocumentBuilder documentBuilder;
    Document document;
    String xmlFilePath;
    Element root,visited_vertex,edges;
    public XMLBFS() throws ParserConfigurationException
    {
        xmlFilePath = "gralog-core\\src\\main\\java\\gralog\\xml\\resources\\bfs.xml";
        documentFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
        document.setXmlVersion("1.0");

        root = document.createElement("BreathFirstSearch");
        document.appendChild(root);

        visited_vertex = document.createElement("VisitedVertex");
        root.appendChild(visited_vertex);

        edges = document.createElement("Edges");
        root.appendChild(edges);
    }
    public void vertexElement(Vertex v) throws ParserConfigurationException
    {

        Element vertex = document.createElement("vertex");
        visited_vertex.appendChild(vertex);

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

    public void edgeElement(Edge e)
    {
        Element edge = document.createElement("edge");
        edges.appendChild(edge);

        Element endpoint1 = document.createElement("endpoint1");
        endpoint1.appendChild(document.createTextNode(e.getSource().label));
        edge.appendChild(endpoint1);

        Element endpoint2 = document.createElement("endpoint2");
        endpoint2.appendChild(document.createTextNode(e.getTarget().label));
        edge.appendChild(endpoint2);
        
        Element weight = document.createElement("weight");
        weight.appendChild(document.createTextNode( e.weight+""));
        edge.appendChild(weight);
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
        x.main("bfs.xml","bfsslides.xsl");

    }
}
