package by.khomenko.training.task04.servlet;

import by.khomenko.training.task04.entity.Flower;
import by.khomenko.training.task04.staxbuilder.FlowersStAXBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class XMLUploadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        /*response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");
        out.println("</body>");
        out.println("</html>");*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*response.setContentType("text/html");
        response.getWriter().println("Hello world");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/flowerstable.jsp");

        dispatcher.forward(request, response);*/
        //String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("fileToUpload"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        //TODO Change method parameters and pass Input Stream fileContent instead of file's name!
        FlowersStAXBuilder staxBuilder = new FlowersStAXBuilder();
        staxBuilder.buildSetFlowers("C:\\Users\\Georgy\\IdeaProjects\\EpamTraining\\Task04\\data\\flowers.xml");
        PrintWriter out = response.getWriter();
        Set<Flower> flowers = staxBuilder.getFlowers();
        request.setAttribute("lst", flowers);
        request.getRequestDispatcher("flowerstable.jsp").forward(request, response);

        //request.getAttribute()
    }
}
