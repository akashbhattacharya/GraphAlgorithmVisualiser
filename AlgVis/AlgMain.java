import algorithms.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import graph.*;
import xml.*;

/**
 * This is the main class of the AlgVis tool and is used to run the command line interface
 */

public class AlgMain {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        int inp = 1;
        Scanner scan = new Scanner(System.in);
        while (inp != 0) {
            System.out.println("Choose option- (0 to exit)");
            System.out.println("1. Run algorithm on a selected graph and generate xml doc");
            System.out.println("2. Apply xsl stylesheet on an xml doc");
            inp = scan.nextInt();
            switch (inp) {
                case 1: {
                    try {
                        System.out.println("Enter graph filename-");
                        String graphName = scan.next();
                        graphName = graphName.trim();
                        graphName = "xml\\resources\\" + graphName;
                        AlgCreateGraph obj = new AlgCreateGraph();
                        AlgGraph g = obj.parseXML(graphName);
                        //obj.printGraph();
                        AlgVertex start = new AlgVertex();
                        System.out.println("Enter algorithm name- eg:dijkstra, bfs, dfs or floydwarshall");
                        String algName = scan.next();
                        algName = algName.trim();
                        if (algName.equals("dijkstra")) {
                            System.out.println("Enter id or label of start vertex- (case sensitive) ");
                            String name = scan.next();
                            for (AlgVertex a : g.vertices) {
                                if (a.label.equals(name) || a.id.equals(name))
                                    start = a;
                            }
                            Dijkstra d = new Dijkstra();
                            HashMap<AlgVertex, AlgVertex> predecessor = new HashMap<>();
                            HashMap<AlgVertex, AlgEdge> edgeFromPredecessor = new HashMap<>();
                            HashMap<AlgVertex, Double> distances = new HashMap<>();
                            Dijkstra.dijkstraShortestPath(g, start, predecessor, edgeFromPredecessor, distances);
                        } else if (algName.equals("bfs")) {
                            System.out.println("Enter id or label of start vertex- ");
                            String name = scan.next();
                            for (AlgVertex a : g.vertices) {
                                if (a.label.equals(name) || a.id.equals(name))
                                    start = a;
                            }
                            BFS bfs = new BFS();
                            HashMap<AlgVertex, AlgVertex> predecessor = new HashMap<>();
                            HashMap<AlgVertex, AlgEdge> edgeFromPredecessor = new HashMap<>();
                            BFS.breadthFirstSearch(start, predecessor, edgeFromPredecessor);
                        } else if (algName.equals("dfs")) {
                            System.out.println("Enter id or label of start vertex- ");
                            String name = scan.next();
                            for (AlgVertex a : g.vertices) {
                                if (a.label.equals(name) || a.id.equals(name))
                                    start = a;
                            }
                            DFS dfs = new DFS();
                            HashMap<AlgVertex, AlgVertex> predecessor = new HashMap<>();
                            HashMap<AlgVertex, AlgEdge> edgeFromPredecessor = new HashMap<>();
                            dfs.run(start, predecessor, edgeFromPredecessor);
                        } else if (algName.equals("floydwarshall")) {
                            FloydWarshall fw = new FloydWarshall();
                            FloydWarshall.fwSolution(g);
                        } else
                            System.out.println("Algorithm not found");
                    } catch (FileNotFoundException e) {
                        System.out.println("Graph file not found, please make sure the graph file is in the xml/resources directory");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter xml filename");
                    String xmlName = scan.next();
                    System.out.println("Enter xsl filename");
                    String xslName = scan.next();
                    XLSTApply x = new XLSTApply();
                    XLSTApply.main(xmlName, xslName);
                    break;
                }
                case 0: {
                    System.out.println("Exiting");
                    break;
                }
                default: {
                    System.out.println("Not a valid option");
                    break;
                }

            }
        }

    }
}
