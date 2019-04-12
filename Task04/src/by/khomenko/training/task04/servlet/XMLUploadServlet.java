package by.khomenko.training.task04.servlet;

import by.khomenko.training.task04.dombuilder.FlowersDOMBuilder;
import by.khomenko.training.task04.entity.Flower;
import by.khomenko.training.task04.saxbuilder.FlowersSAXBuilder;
import by.khomenko.training.task04.staxbuilder.FlowersStAXBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * Interact with web part of the application.
 */
@MultipartConfig
public class XMLUploadServlet extends HttpServlet {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(XMLUploadServlet.class);

    /**
     * @param request  object that contains the request the client has made of
     *                 the servlet.
     * @param response object that contains the response the servlet sends to
     *                 the client.
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response) {

        try {

            Part filePart = request.getPart("fileToUpload");
            InputStream fileContent = filePart.getInputStream();
            ServletContext context = getServletContext();
            InputStream xsdFile = context
                    .getResourceAsStream("/WEB-INF/classes/flowers.xsd");
            boolean isValid = validateXMLByXSD(fileContent, xsdFile);
            request.setAttribute("isValid", isValid);

            String parser = request.getParameter("parser");
            request.setAttribute("parserType", parser);

            String locale = request.getParameter("locale");
            HttpSession session = request.getSession();
            session.setAttribute("localeType", locale);


            if (isValid) {
                // Get input stream again, as we have already read the first one
                fileContent = filePart.getInputStream();
                Set<Flower> flowers = null;
                switch (parser) {
                    case "StAX":
                        flowers = getStAXBuilder(fileContent);
                        break;
                    case "DOM":
                        flowers = getDOMBuilder(fileContent);
                        break;
                    case "SAX":
                        flowers = getSAXBuilder(fileContent);
                        break;
                    default:
                        break;
                }
                if (flowers != null) {
                    request.setAttribute("lst", flowers);
                }
            }

            request.getRequestDispatcher("flowerstable.jsp")
                    .forward(request, response);

        } catch (ServletException e) {
            String message = "Servlet's error: ";
            LOGGER.error(message, e);
        } catch (IOException e) {
            String message = "I/O error in doPost() method: ";
            LOGGER.error(message, e);
        }


    }


    /**
     * @param xmlStream xml file data from input stream.
     * @param xsdFile   xsd file data from input stream.
     * @return true if an xml file corresponds to xsd file.
     */
    private boolean validateXMLByXSD(final InputStream xmlStream,
                                     final InputStream xsdFile) {
        try {

            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new StreamSource(xsdFile))
                    .newValidator()
                    .validate(new StreamSource(xmlStream));

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

    /**
     * Creates StAX Builder.
     *
     * @param fileContent xml file's content from input stream.
     * @return Set of Flower's instances.
     */
    private Set<Flower> getStAXBuilder(final InputStream fileContent) {
        FlowersStAXBuilder staxBuilder = new FlowersStAXBuilder();
        staxBuilder.buildSetFlowers(fileContent);
        return staxBuilder.getFlowers();
    }


    /**
     * Creates SAX Builder.
     *
     * @param fileContent xml file's content from input stream.
     * @return Set of Flower's instances.
     */
    private Set<Flower> getSAXBuilder(final InputStream fileContent) {
        FlowersSAXBuilder saxBuilder = new FlowersSAXBuilder();
        saxBuilder.buildSetFlowers(fileContent);

        return saxBuilder.getFlowers();
    }

    /**
     * Creates DOM Builder.
     *
     * @param fileContent xml file's content from input stream.
     * @return Set of Flower's instances.
     */
    private Set<Flower> getDOMBuilder(final InputStream fileContent) {
        FlowersDOMBuilder domBuilder = new FlowersDOMBuilder();
        domBuilder.buildSetFlowers(fileContent);

        return domBuilder.getFlowers();
    }
}
