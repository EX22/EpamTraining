package by.khomenko.training.task04.servlet;

import by.khomenko.training.task04.dombuilder.FlowersDOMBuilder;
import by.khomenko.training.task04.entity.Flower;
import by.khomenko.training.task04.saxbuilder.FlowersSAXBuilder;
import by.khomenko.training.task04.staxbuilder.FlowersStAXBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

@MultipartConfig
public class XMLUploadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(XMLUploadServlet.class);

    //TODO Is it necessary to use try/catch in doPost() method?
    // Is it necessary to put "flowerstable.jsp" in class constant?
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Part filePart = request.getPart("fileToUpload");
        InputStream fileContent = filePart.getInputStream();

        String parser = request.getParameter("parser");
        request.setAttribute("parserType", parser);
        switch (parser) {
            case "StAX":

                request.setAttribute("lst", getStAXBuilder(fileContent));
                request.getRequestDispatcher("flowerstable.jsp").forward(request, response);
                break;
            case "DOM":

                request.setAttribute("lst", getDOMBuilder(fileContent));
                request.getRequestDispatcher("flowerstable.jsp").forward(request, response);
                break;
            case "SAX":

                request.setAttribute("lst", getSAXBuilder(fileContent));
                request.getRequestDispatcher("flowerstable.jsp").forward(request, response);
                break;

            default:
                break;
        }


    }

    //TODO Find out if it's correct validation.
    public boolean validateXMLByXSD(File xml, File xsd) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (IOException e) {
            String message = "I/O error. ";
            LOGGER.error(message, e);
            return false;
        } catch (SAXException e) {
            String message = "XML file is not valid. ";
            LOGGER.error(message, e);
            return false;
        }
        return true;
    }

    private Set<Flower> getStAXBuilder(InputStream fileContent) {
        FlowersStAXBuilder staxBuilder = new FlowersStAXBuilder();
        staxBuilder.buildSetFlowers(fileContent);
        return staxBuilder.getFlowers();
    }


    private Set<Flower> getSAXBuilder(InputStream fileContent) {
        FlowersSAXBuilder saxBuilder = new FlowersSAXBuilder();
        saxBuilder.buildSetFlowers(fileContent);

        return saxBuilder.getFlowers();
    }

    private Set<Flower> getDOMBuilder(InputStream fileContent) {
        FlowersDOMBuilder domBuilder = new FlowersDOMBuilder();
        domBuilder.buildSetFlowers(fileContent);

        return domBuilder.getFlowers();
    }
}
