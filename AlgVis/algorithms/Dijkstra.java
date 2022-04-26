/*
 * This code is derived from Gralog's Dijkstra.java class, Copyright (c) 2016-2018 LaS group, TU Berlin
 */
package algorithms;

import graph.*;
import xml.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.HashSet;


/**
 * This is an implementation of Dijkstra's algorithm meant to run on an AlgGraph object
 */
public class Dijkstra {
    public static void dijkstraShortestPath(AlgGraph s, AlgVertex start,
                                            HashMap<AlgVertex, AlgVertex> predecessor,
                                            HashMap<AlgVertex, AlgEdge> edgeFromPredecessor,
                                            HashMap<AlgVertex, Double> distances)
            throws ParserConfigurationException, TransformerException {

        HashSet<AlgVertex> setQ = new HashSet<>();
        setQ.addAll(s.getVertices());
        predecessor.put(start, null);
        edgeFromPredecessor.put(start, null);
        distances.put(start, 0d);
        XMLDijkstra xd = new XMLDijkstra();
        xd.graphElement(s);

        for(AlgVertex n: setQ)
        {
            if(n!=start)
                distances.put(n,123456d);
        }
        while (!setQ.isEmpty()) {
            AlgVertex u = null;
            for (AlgVertex uit : setQ)
                if (distances.containsKey(uit))
                    if (u == null || (distances.get(uit) < distances.get(u)))
                        u = uit;
            if (u == null) // happens if there are unreachable vertices
                break;

            setQ.remove(u);

            Double distu = distances.get(u);

            for (AlgEdge e : u.getIncidentEdges()) {
                if (e.getSource() != u && e.isDirected()) // incoming (directed) edge
                    continue;
                AlgVertex other = e.getTarget();
                if (other == u)
                    other = e.getSource();

                if (!setQ.contains(other)) // shortest path already found
                    continue;

                Double alt = distu + e.eWeight;
                if ((!distances.containsKey(other)) || (alt < distances.get(other))) {
                    distances.put(other, alt);
                    predecessor.put(other, u);
                    edgeFromPredecessor.put(other, e);
                }
            }
            xd.iterationElement(u,setQ,distances);
        }
        xd.transformer();
    }
}
