/* This file is part of Gralog, Copyright (c) 2016-2018 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later. */
//This code has been altered from its original form by Akash Bhattacharya for the COMP3931 Project

package gralog.algorithm;

import gralog.progresshandler.ProgressHandler;
import gralog.structure.Edge;
import gralog.structure.Structure;
import gralog.structure.Vertex;
import gralog.xml.XMLDFS;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@AlgorithmDescription(
        name = "Depth-First Search-Tree",
        text = "Constructs a Depth-First Search-Tree",
        url = "https://en.wikipedia.org/wiki/Depth-first_search"
)
public class DepthFirstSearchTree extends Algorithm {
    private static XMLDFS dfs;

    public DepthFirstSearchTree() throws ParserConfigurationException {
        dfs = new XMLDFS();
    }

    public static void depthFirstSearch(Vertex v,
                                        HashMap<Vertex, Vertex> predecessor,
                                        HashMap<Vertex, Edge> edgeFromPredecessor) throws ParserConfigurationException {
        dfs.vertexElement(v); //Creates vertex element
        for (Edge e : v.getIncidentEdges()) {
            if (e.getSource() != v && e.isDirected) // incoming (directed) edge
                continue;

            Vertex other = e.getTarget();
            if (other == v)
                other = e.getSource();

            if (predecessor.containsKey(other)) // successor already in the tree
                continue;
            dfs.edgeElement(e); //Creates edge element
            predecessor.put(other, v);
            edgeFromPredecessor.put(other, e);
            depthFirstSearch(other, predecessor, edgeFromPredecessor);
        }
    }

    public Object run(Structure s, AlgorithmParameters p, Set<Object> selection,
                      ProgressHandler onprogress) throws ParserConfigurationException, TransformerException {
        HashMap<Vertex, Vertex> predecessor = new HashMap<>();
        HashMap<Vertex, Edge> edgeFromPredecessor = new HashMap<>();
        Vertex v = selectedUniqueVertex(selection);
        if (v == null)
            return "Please, select exactly one vertex to start DFS from.";

        predecessor.put(v, null);
        edgeFromPredecessor.put(v, null);
        depthFirstSearch(v, predecessor, edgeFromPredecessor);
        dfs.transformer(); //Applies transformer to create XML document
        HashSet<Edge> tree = new HashSet<>();
        tree.addAll(edgeFromPredecessor.values());
        tree.remove(null);
        return tree;
    }

}
