package test;

import graph.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AlgCreateGraphTest {

    @org.junit.jupiter.api.Test
    void testParseXML1() throws IOException, SAXException, ParserConfigurationException {
        AlgGraph a1;
        AlgCreateGraph create = new AlgCreateGraph();
        a1 = create.parseXML("test1.graphml");
        assertEquals(6, a1.getVertices().size());
        assertEquals(7, a1.getEdges().size());

    }

    @org.junit.jupiter.api.Test
    void testParseXML2() throws IOException, SAXException, ParserConfigurationException {
        AlgGraph a2;
        AlgCreateGraph create = new AlgCreateGraph();
        a2 = create.parseXML("test2.graphml");
        assertEquals(7, a2.getVertices().size());
        assertEquals(9, a2.getEdges().size());
    }

    @org.junit.jupiter.api.Test
    void testParseXML3() {
        AlgCreateGraph create = new AlgCreateGraph();
        assertThrows(FileNotFoundException.class, () -> {
            create.parseXML("test3.graphml");
        });
    }


}