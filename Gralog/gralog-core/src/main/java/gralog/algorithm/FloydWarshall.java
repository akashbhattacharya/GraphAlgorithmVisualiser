//This code is not a part of Gralog's original source code, Gralog's code base has been modified to include this file
package gralog.algorithm;

import gralog.xml.XMLFLoydWarshall;
import gralog.progresshandler.ProgressHandler;
import gralog.structure.Edge;
import gralog.structure.Structure;
import gralog.structure.Vertex;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.HashSet;
import java.util.Set;

@AlgorithmDescription(
        name = "FloydWarshall",
        text = "Finds the shortest path between every pair of vertices in a graph",
        url = "https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm"
)

public class FloydWarshall extends Algorithm {


    public static void fwSolution(Structure s) throws ParserConfigurationException, TransformerException {
        XMLFLoydWarshall xml = new XMLFLoydWarshall();
        xml.graphElement(s); //Creates graph element
        HashSet<Vertex> setV = new HashSet<>();
        setV.addAll(s.getVertices());
        HashSet<Edge> setE = new HashSet<>();
        setE.addAll(s.getEdges());

        Vertex[] v = new Vertex[setV.size()];
        int ctr = 0, x = 0, y = 0;
        for (Vertex temp : setV) {
            v[ctr] = temp;
            ctr++;
        }
        boolean found = false;
        double[][] matrix = new double[v.length][v.length];
        for (int m = 0; m < v.length; m++) {
            for (int n = 0; n < v.length; n++) {
                found = false;
                if (m == n) {
                    matrix[x][y] = 0.0d;
                    y++;
                    continue;
                }
                for (Edge e : setE) {
                    if (e.isDirected()) {
                        if (e.getSource() == v[m] && e.getTarget() == v[n]) {
                            matrix[x][y] = e.weight;
                            found = true;
                            y++;
                            break;
                        }
                    } else {
                        if (e.getTarget() == v[m] && e.getSource() == v[n] || e.getTarget() == v[n] && e.getSource() == v[m]) {
                            matrix[x][y] = e.weight;
                            found = true;
                            y++;
                            break;
                        }
                    }
                }
                if (!found) {
                    matrix[x][y] = 12345.6d;
                    y++;
                }
            }
            x++;
            y = 0;
        }

        xml.tableElement(matrix, v); //Creates table element

        boolean negative = false;
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j< matrix.length; j++)
            {
                if(matrix[i][j]<0)
                    negative = true;
            }
        }
        if(!negative) {
            for (int k = 0; k < v.length; k++) {
                for (int i = 0; i < v.length; i++) {
                    for (int j = 0; j < v.length; j++) {
                        if (matrix[i][j] > (matrix[i][k] + matrix[k][j]))
                            matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
                xml.iterationElement(matrix); //Creates iteration element
            }
        }
        xml.transformer(); //Applies transformer to create XML document
    }


    public Object run(Structure s, AlgorithmParameters p, Set<Object> selection,
                      ProgressHandler onprogress) throws Exception {
        for (Edge e : (Set<Edge>) s.getEdges()) {
            if (e.weight < 0d)
                return ("Negative edge weights and cycles are not allowed");
        }
        fwSolution(s);
        return null;
    }
}
