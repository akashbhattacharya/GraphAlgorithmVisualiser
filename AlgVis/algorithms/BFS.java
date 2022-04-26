/*
 * This code is derived from Gralog's BreathFirstSearchTree.java class, Copyright (c) 2016-2018 LaS group, TU Berlin
 */
package algorithms;

import graph.*;
import xml.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * This is an implementation of the Breath First Search algorithm meant to run on an AlgGraph object
 */
public class BFS {

    public static void breadthFirstSearch(AlgVertex start,
                                          HashMap<AlgVertex, AlgVertex> predecessor,
                                          HashMap<AlgVertex, AlgEdge> edgeFromPredecessor) throws ParserConfigurationException,
            TransformerException {
        ArrayDeque<AlgVertex> queue = new ArrayDeque<>();
        queue.addLast(start);
        predecessor.put(start, null);
        edgeFromPredecessor.put(start, null);
        XMLBFS bfs = new XMLBFS();
        while (!queue.isEmpty()) {
            AlgVertex v = queue.removeFirst();
            bfs.vertexElement(v);
            for (AlgEdge e : v.getIncidentEdges()) {
                if (e.getSource() != v && e.isDirected()) // incoming (directed) edge
                    continue;
                bfs.edgeElement(e);
                AlgVertex other = e.getTarget();
                if (other == v)
                    other = e.getSource();

                if (predecessor.containsKey(other)) // successor already in the tree
                    continue;
                predecessor.put(other, v);
                edgeFromPredecessor.put(other, e);
                queue.addLast(other);
            }
        }
        bfs.transformer();
    }
}
