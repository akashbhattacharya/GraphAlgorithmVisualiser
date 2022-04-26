package algorithms;


import graph.*;
import xml.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.HashSet;
import java.util.Set;


/**
 * This is an implementation of the Floyd-Warshall algorithm meant to run on an AlgGraph object
 */
public class FloydWarshall {


    public static void fwSolution(AlgGraph s) throws ParserConfigurationException, TransformerException
    {
        XMLFloydWarshall xml = new XMLFloydWarshall();
        xml.graphElement(s);
        HashSet<AlgVertex> setV = new HashSet<>();
        setV.addAll(s.getVertices());
        HashSet<AlgEdge> setE = new HashSet<>();
        setE.addAll(s.getEdges());

        AlgVertex v[] = new AlgVertex[setV.size()];
        int ctr=0, x =0 , y = 0;
        for (AlgVertex temp: setV)
        {
            v[ctr]=temp;
            ctr++;
        }
        boolean found = false;
        double matrix[][] = new double[v.length][v.length];
        for(int m = 0;m<v.length;m++)
        {
            for(int n=0;n< v.length;n++)
            {    found = false;
                if(m==n)
                {
                    matrix[x][y]=0.0d;
                    y++;
                    continue;
                }
                for(AlgEdge e:setE)
                {
                    if(e.isDirected())
                    {
                        if(e.getSource()==v[m]&&e.getTarget()==v[n])
                        {
                            matrix[x][y]=e.eWeight;
                            found = true;
                            y++;
                            break;
                        }
                    }
                    else
                    {
                        if(e.getTarget()==v[m]&&e.getSource()==v[n]||e.getTarget()==v[n]&&e.getSource()==v[m])
                        {
                            matrix[x][y]=e.eWeight;
                            found = true;
                            y++;
                            break;
                        }
                    }
                }
                if(!found)
                {
                    matrix[x][y]=12345.6d;
                    y++;
                }
            }
            x++;
            y=0;
        }

        xml.tableElement(matrix,v);

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
                xml.iterationElement(matrix);
            }
        }
        else
            {
                System.out.println("Negative edge weights and cycles are not allowed");
            }
        xml.transformer();
    }
}
