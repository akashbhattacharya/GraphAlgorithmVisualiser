/*
 * This code is derived from Gralog's DepthFirstSearchTree.java class, Copyright (c) 2016-2018 LaS group, TU Berlin
 */
package algorithms;

import graph.*;
import xml.*;
        
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;

/**
 * This is an implementation of the Depth First Search algorithm meant to run on an AlgGraph object
 */
public class DFS {
    private static XMLDFS dfs;
    public DFS() throws ParserConfigurationException
    {
        dfs = new XMLDFS();
    }

    public static void depthFirstSearch(AlgVertex v,
                                        HashMap<AlgVertex, AlgVertex> predecessor,
                                        HashMap<AlgVertex, AlgEdge> edgeFromPredecessor) throws ParserConfigurationException {
        dfs.vertexElement(v);
        for (AlgEdge e : v.getIncidentEdges()) {
            if (e.getSource() != v && e.isDirected()) // incoming (directed) edge
                continue;

            AlgVertex other = e.getTarget();
            if (other == v)
                other = e.getSource();

            if (predecessor.containsKey(other)) // successor already in the tree
                continue;
            dfs.edgeElement(e);
            predecessor.put(other, v);
            edgeFromPredecessor.put(other, e);
            depthFirstSearch(other, predecessor, edgeFromPredecessor);
        }
    }

    public void run(AlgVertex Start,HashMap<AlgVertex, AlgVertex> predecessor, HashMap<AlgVertex,
            AlgEdge> edgeFromPredecessor ) throws ParserConfigurationException, TransformerException {
        predecessor.put(Start, null);
        edgeFromPredecessor.put(Start, null);
        depthFirstSearch(Start, predecessor, edgeFromPredecessor);
        dfs.transformer();
    }

}
