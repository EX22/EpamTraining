package by.khomenko.training.finaltask05.servlet;

import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.exception.PersistentException;
import by.khomenko.training.finaltask05.service.AdminPageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;

@MultipartConfig
@WebServlet(
        name = "DispatcherServlet",
        urlPatterns = {"/adminpage.html", "/category.html", "/myimages.html"})
public class DispatcherServlet extends HttpServlet {

    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/crowdsource_db?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USER = "crowdsource_user";
    public static final String DB_PASSWORD = "crowdsource_password";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;


    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int length = request.getContextPath().length();
        String s = request.getRequestURI().substring(length);

        switch (s) {
            case "/adminpage.html":
                String string = request.getParameter("page");
                int page = 1;
                if (string != null) {
                    page = Integer.parseInt(string);
                }
                AdminPageService adminPageService = new AdminPageService();
                Map<String, Object> loadedData = adminPageService.load(page);
                for (String key : loadedData.keySet()){
                    request.setAttribute(key, loadedData.get(key));
                }
                request.getRequestDispatcher("WEB-INF/jsp/adminpage.jsp")
                        .forward(request, response);
                break;
            case "/category.html":
                request.getRequestDispatcher("WEB-INF/jsp/category.jsp")
                        .forward(request, response);
                break;
            case "/myimages.html":
                request.getRequestDispatcher("WEB-INF/jsp/myimages.jsp")
                        .forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int length = request.getContextPath().length();
        String s = request.getRequestURI().substring(length);

        switch (s) {
            case "/adminpage.html":

                AdminPageService adminPageService = new AdminPageService();
                adminPageService.addUserToBlacklist(request.getParameter("userloginadd"));
                Map<String, Object> loadedData = adminPageService.load(1);
                for (String key : loadedData.keySet()){
                    request.setAttribute(key, loadedData.get(key));
                }
                request.getRequestDispatcher("WEB-INF/jsp/adminpage.jsp")
                        .forward(request, response);

                break;
            case "/category.html":
                /*request.getRequestDispatcher("WEB-INF/jsp/category.jsp")
                        .forward(request, response);*/
                break;
            case "/myimages.html":
                /*request.getRequestDispatcher("WEB-INF/jsp/category.jsp")
                        .forward(request, response);*/
                Part part = request.getPart("description");
                byte [] arr = new byte[(int) (part.getSize())];

                part.getInputStream().read(arr);
                String string = new String(arr);
                response.getWriter().println(string);

                break;

        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER,
                    DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE,
                    DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
            destroy();
        }
    }
}
