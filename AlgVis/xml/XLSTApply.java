package xml;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is used to apply an XLST stylesheet to an XML document
 */
public class XLSTApply {
    public static void main(String xmlf, String xslf) throws FileNotFoundException, IOException {
            TransformerFactory factory = TransformerFactory.newInstance();
            String xmlFilePath = "xml\\resources\\" + xmlf;
            String xslFilePath = "xml\\resources\\" + xslf;
            String latexFilePath = "xml\\resources\\output.tex";

            StreamSource xsl = new StreamSource(xslFilePath);
            StreamSource xml = new StreamSource(xmlFilePath);
            StreamResult latex = new StreamResult(latexFilePath);

            try {
                Transformer transformer = factory.newTransformer(xsl);
                transformer.transform(xml, latex);
                System.out.println("Style sheet successfully applied");

            } catch (TransformerException e) {
                //e.printStackTrace();
                System.out.println("File not found");
            }
    }
}
