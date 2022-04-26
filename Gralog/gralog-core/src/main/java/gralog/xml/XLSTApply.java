//This code is not a part of Gralog's original source code, Gralog's code base has been modified to include this file
package gralog.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * This class is used to apply an XLS stylesheet to an XML doc using Java's Transformer Library
 */

public class XLSTApply {
    public static void main(String xmlf, String xslf) {
        TransformerFactory factory = TransformerFactory.newInstance();
        String xmlFilePath = "gralog-core\\src\\main\\java\\gralog\\xml\\resources\\"+xmlf;
        String xslFilePath = "gralog-core\\src\\main\\java\\gralog\\xml\\resources\\"+xslf;
        String latexFilePath = "gralog-core\\src\\main\\java\\gralog\\xml\\resources\\output.tex";

        StreamSource xsl = new StreamSource(xslFilePath);
        StreamSource xml = new StreamSource(xmlFilePath);
        StreamResult latex = new StreamResult(latexFilePath);

        try {
            Transformer transformer = factory.newTransformer(xsl);
            transformer.transform(xml,latex);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
