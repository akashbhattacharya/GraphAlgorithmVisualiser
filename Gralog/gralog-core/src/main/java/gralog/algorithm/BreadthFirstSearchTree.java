/* This file is part of Gralog, Copyright (c) 2016-2018 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later. */
//This code has been altered from its original form by Akash Bhattacharya for the COMP3931 Project

package gralog.algorithm;

import gralog.progresshandler.ProgressHandler;
import gralog.structure.Edge;
import gralog.structure.Structure;
import gralog.structure.Vertex;
import gralog.xml.XMLBFS;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@AlgorithmDescription(
        name = "Breadth-First Search-Tree",
        text = "Constructs a Breadth-First Search-Tree",
        url = "https://en.wikipedia.org/wiki/Breadth-first_search"
)
public class BreadthFirstSearchTree extends Algorithm  {

    public static void breadthFirstSearch(Vertex start,
                                          HashMap<Vertex, Vertex> predecessor,
                                          HashMap<Vertex, Edge> edgeFromPredecessor) throws ParserConfigurationException,
            TransformerException {
        ArrayDeque<Vertex> queue = new ArrayDeque<>();
        queue.addLast(start);
        predecessor.put(start, null);
        edgeFromPredecessor.put(start, null);
        XMLBFS bfs = new XMLBFS();
        while (!queue.isEmpty()) {
            Vertex v = queue.removeFirst();
            bfs.vertexElement(v); //Creates vertex element
            for (Edge e : v.getIncidentEdges()) {
                if (e.getSource() != v && e.isDirected) // incoming (directed) edge
                    continue;
                bfs.edgeElement(e); //Creates edge element
                Vertex other = e.getTarget();
                if (other == v)
                    other = e.getSource();

                if (predecessor.containsKey(other)) // successor already in the tree
                    continue;
                predecessor.put(other, v);
                edgeFromPredecessor.put(other, e);
                queue.addLast(other);
            }
        }
        bfs.transformer(); //Applies transformer to create XML document
    }


    public Object run(Structure s, AlgorithmParameters p, Set<Object> selection,
                      ProgressHandler onprogress) throws ParserConfigurationException,TransformerException {
        HashMap<Vertex, Vertex> predecessor = new HashMap<>();
        HashMap<Vertex, Edge> edgeFromPredecessor = new HashMap<>();
        Vertex v = selectedUniqueVertex(selection);

        if (v == null)
            return "Please, select exactly one vertex to start BFS from.";
        breadthFirstSearch(v, predecessor, edgeFromPredecessor);

        HashSet<Edge> tree = new HashSet<>();
        tree.addAll(edgeFromPredecessor.values());
        tree.remove(null);
        return tree;
    }

}
