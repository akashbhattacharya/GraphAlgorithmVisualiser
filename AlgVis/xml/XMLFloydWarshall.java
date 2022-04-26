package xml;


import graph.*;
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
 * This class generates an XML document when the FloydWarshall class is called
 */

public class XMLFloydWarshall {
    DocumentBuilderFactory documentFactory;
    DocumentBuilder documentBuilder;
    Document document;
    String xmlFilePath;
    Element root;
    Element table;
    Element iterations;
    int ctr;

    public void graphElement(AlgGraph struct) throws ParserConfigurationException
    {
        ctr = 0;
        HashSet<AlgVertex> vSet = new HashSet<>();
        vSet.addAll(struct.getVertices());

        HashSet<AlgEdge> eSet = new HashSet<>();
        eSet.addAll(struct.getEdges());

        xmlFilePath = "xml\\resources\\floydwarshall.xml";

        documentFactory = DocumentBuilderFactory.newInstance();

        documentBuilder = documentFactory.newDocumentBuilder();

        document = documentBuilder.newDocument();
        document.setXmlVersion("1.0");

        root = document.createElement("FloydWarshall");
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

            Element weight = document.createElement("weight");
            weight.appendChild(document.createTextNode( e.eWeight+""));
            edge.appendChild(weight);
        }

        table = document.createElement("table");
        root.appendChild(table);

        iterations = document.createElement("iterations");
        root.appendChild(iterations);
    }

    public void tableElement(double m[][], AlgVertex[] v)
    {
        Element vertices = document.createElement("vertices");
        table.appendChild(vertices);
        for(AlgVertex x : v)
        {
            Element vertex = document.createElement("vertex");
            vertex.appendChild(document.createTextNode(x.label));
            vertices.appendChild(vertex);
        }



        for(int i = 0; i<v.length;i++)
        {
            Element row = document.createElement("row");
            table.appendChild(row);
            Element rvertex = document.createElement("row_vertex");
            rvertex.appendChild(document.createTextNode(v[i].label));
            row.appendChild(rvertex);
            for(int j = 0;j<v.length;j++)
            {
                Element column= document.createElement("column");
                column.appendChild(document.createTextNode(m[i][j]+""));
                row.appendChild(column);
            }
        }

    }

    public void iterationElement(double m[][])
    {
        ctr++;
        Element iteration = document.createElement("iteration");
        iterations.appendChild(iteration);

        Element number = document.createElement("iteration_number");
        number.appendChild(document.createTextNode(ctr+""));
        iteration.appendChild(number);

        for(int i = 0; i<m.length;i++)
        {
            Element row = document.createElement("row");
            iteration.appendChild(row);
            for(int j = 0; j < m.length; j++)
            {
                Element column= document.createElement("column");
                column.appendChild(document.createTextNode(m[i][j]+" "));
                row.appendChild(column);
            }
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
