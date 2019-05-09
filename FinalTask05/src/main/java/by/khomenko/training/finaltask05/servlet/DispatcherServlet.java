package by.khomenko.training.finaltask05.servlet;

import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Image;
import by.khomenko.training.finaltask05.entity.RecognizedImg;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;
import by.khomenko.training.finaltask05.exception.ValidationException;
import by.khomenko.training.finaltask05.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet(
        name = "DispatcherServlet",
        urlPatterns = {"/adminpage.html", "/category.html", "/forgotpass.html",
                "/home.html", "/login.html", "/myimages.html", "/profile.html",
                "/registration.html", "/logout.html", "/profilesettings.html"})
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

    public static final String ANSWER = "answer-";
    public static final String CATEGORY = "category-";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int length = request.getContextPath().length();
        String s = request.getRequestURI().substring(length);

        try {

            switch (s) {

                case "/adminpage.html":
                    String string = request.getParameter("page");
                    int page = 1;
                    if (string != null) {
                        page = Integer.parseInt(string);
                    }
                    AdminPageService adminPageService = new AdminPageService();
                    Map<String, Object> loadedData = adminPageService.load(page);
                    for (String key : loadedData.keySet()) {
                        request.setAttribute(key, loadedData.get(key));
                    }
                    request.getRequestDispatcher("WEB-INF/jsp/adminpage.jsp")
                            .forward(request, response);
                    break;

                case "/category.html":

                    showCategory(request, response);

                    break;

                case "/forgotpass.html":

                    request.getRequestDispatcher("WEB-INF/jsp/forgotpass.jsp")
                            .forward(request, response);

                    break;

                case "/home.html":

                    String hps = request.getParameter("page");
                    int hp = 1;
                    if (hps != null) {
                        hp = Integer.parseInt(hps);
                    }
                    HomePageService homePageService = new HomePageService();
                    Map<String, Object> hpd = homePageService.load(hp);
                    for (String key : hpd.keySet()) {
                        request.setAttribute(key, hpd.get(key));
                    }
                    request.getRequestDispatcher("WEB-INF/jsp/home.jsp")
                            .forward(request, response);

                    break;

                case "/login.html":

                    request.getRequestDispatcher("WEB-INF/jsp/login.jsp")
                            .forward(request, response);

                    break;

                case "/myimages.html":

                    showMyImages(request, response);

                    break;

                case "/profile.html":

                    showProfileInfo(request, response);

                    Integer uId = (Integer) request.getSession().getAttribute("userId");
                    Integer cuId = getCurrentUserId(request);
                    if (cuId.equals(uId)) {
                        request.getRequestDispatcher("WEB-INF/jsp/profile.jsp")
                                .forward(request, response);
                    } else {

                        request.getRequestDispatcher("WEB-INF/jsp/registration.jsp")
                                .forward(request, response);
                    }
                    break;

                case "/registration.html":

                    request.getRequestDispatcher("WEB-INF/jsp/registration.jsp")
                            .forward(request, response);
                    break;

                case "/logout.html":

                    request.getSession().removeAttribute("userId");
                    response.sendRedirect("login.html");

                    break;

                case "/profilesettings.html":

                    showProfileInfo(request, response);

                    request.getRequestDispatcher("WEB-INF/jsp/profilesettings.jsp")
                            .forward(request, response);

                    break;


            }

        } catch (PersistentException e) {
            LOGGER.error("Some exception in doGet method", e);

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int length = request.getContextPath().length();
        String s = request.getRequestURI().substring(length);

        try {

            switch (s) {

                case "/adminpage.html":

                    AdminPageService adminPageService = new AdminPageService();
                    adminPageService.addUserToBlacklist(request.getParameter("userloginadd"));
                    adminPageService.removeUserFromBlacklist(request.getParameter("userloginremove"));
                    Map<String, Object> loadedData = adminPageService.load(1);
                    for (String key : loadedData.keySet()) {
                        request.setAttribute(key, loadedData.get(key));
                    }

                    String fs = request.getParameter("fileSize");
                    String fex = request.getParameter("fileExtension");
                    adminPageService.setSettings(fs, fex);


                    request.getRequestDispatcher("WEB-INF/jsp/adminpage.jsp")
                            .forward(request, response);

                    break;

                case "/category.html":

                    CategoryPageService categoryPageService = new CategoryPageService();
                    List<RecognizedImg> recognizedImgList = new ArrayList<>();
                    Integer userId = getCurrentUserId(request);
                    for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements(); ) {
                        String id = e.nextElement();
                        if (id.startsWith(ANSWER)) {
                            RecognizedImg recognizedImg = new RecognizedImg();
                            String answer = request.getParameter(id);
                            recognizedImg.setImageId(Integer.parseInt(id.substring(ANSWER.length())));
                            recognizedImg.setAnswer(answer);
                            recognizedImg.setUserId(userId);
                            recognizedImgList.add(recognizedImg);
                        }
                    }

                    categoryPageService.saveRecognizedImages(recognizedImgList);
                    showCategory(request, response);

                    break;

                case "/forgotpass.html":
                    request.getRequestDispatcher("WEB-INF/jsp/forgotpass.jsp")
                            .forward(request, response);
                    break;

                case "/home.html":
                    request.getRequestDispatcher("WEB-INF/jsp/home.jsp")
                            .forward(request, response);
                    break;

                case "/login.html":

                    LogInPageService logInPageService = new LogInPageService();
                    String lpl = request.getParameter("login");
                    String lpp = request.getParameter("password");
                    User loggedUser = logInPageService.logInUser(lpl, lpp);
                    if (loggedUser != null) {
                        request.getSession().setAttribute("userId", loggedUser.getId());
                        response.sendRedirect("profile.html");
                    } else {
                        request.getRequestDispatcher("WEB-INF/jsp/registration.jsp")
                                .forward(request, response);
                    }
                    break;

                case "/myimages.html":

                    MyImagesPageService myImagesPageService = new MyImagesPageService();
                    List<Image> addedImagesList = new ArrayList<>();
                    for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements(); ) {

                        String id = e.nextElement();
                        if (id.startsWith(CATEGORY)) {
                            Image image = new Image();
                            String category = request.getParameter(id);
                            image.setId(Integer.parseInt(id.substring(CATEGORY.length())));
                            image.setCategoryId(Integer.parseInt(category));
                            addedImagesList.add(image);
                        }
                    }

                    Part fp = request.getPart("imageToUpload");
                    InputStream fc = fp.getInputStream();
                    Files.copy(fc, Paths.get("C:\\Users"
                                    + "\\Georgy\\IdeaProjects\\EpamTraining"
                                    + "\\FinalTask05\\target\\crowdsourcing"
                                    + "\\imagesdir\\",
                            Paths.get(fp.getSubmittedFileName())
                                    .getFileName().toString()));

                    myImagesPageService.updateImgCategories(addedImagesList);
                    request.getRequestDispatcher("WEB-INF/jsp/myimages.jsp")
                            .forward(request, response);

                    break;

                case "/profile.html":

                    request.getRequestDispatcher("WEB-INF/jsp/profile.jsp")
                            .forward(request, response);
                    break;

                case "/registration.html":

                    String rps = request.getParameter("login");
                    String rpps = request.getParameter("password");
                    String rpcps = request.getParameter("confirmPassword");

                    RegistrationPageService registrationPageService = new RegistrationPageService();
                    Integer rUserId;
                    try {
                        rUserId = registrationPageService.createUser(rps, rpps, rpcps);
                        request.getSession().setAttribute("userId", rUserId);
                        response.sendRedirect("profile.html");
                    } catch (ValidationException e) {
                        request.getSession().setAttribute("regLogin", rps);
                        request.getRequestDispatcher("WEB-INF/jsp/registration.jsp")
                                .forward(request, response);
                    }

                    break;

                case "/profilesettings.html":

                    Part filePart = request.getPart("photoToUpload");
                    InputStream fileContent = filePart.getInputStream();
                    Files.copy(fileContent, Paths.get("C:\\Users"
                                    + "\\Georgy\\IdeaProjects\\EpamTraining"
                                    + "\\FinalTask05\\target\\crowdsourcing"
                                    + "\\avatars\\",
                            Paths.get(filePart.getSubmittedFileName())
                                    .getFileName().toString()));

                    Integer cuid = getCurrentUserId(request);
                    String nuns = request.getParameter("newUserName");
                    String nups = request.getParameter("newPassword");
                    String cnups = request.getParameter("confirmNewPassword");

                    ProfilePageService profilePageService = new ProfilePageService();
                    profilePageService.updateProfile(cuid, nuns, nups, cnups);

                    break;

            }
        } catch (PersistentException | IOException e) {
            LOGGER.error("Some exception's message in doPost.");
            //TODO Put an appropriate message in log and throw some exception here.
        } catch (ValidationException e) {
            e.printStackTrace();
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

    private void showCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer uid = getCurrentUserId(request);
        String ids = request.getParameter("id");
        int cid = 1;
        if (ids != null) {
            cid = Integer.parseInt(ids);
        }
        CategoryPageService categoryPageService = new CategoryPageService();
        Map<String, Object> cpd = categoryPageService.load(uid, cid);
        for (String key : cpd.keySet()) {
            request.setAttribute(key, cpd.get(key));
        }
        request.getRequestDispatcher("WEB-INF/jsp/category.jsp")
                .forward(request, response);
    }

    private void showMyImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer uid = getCurrentUserId(request);
        String ids = request.getParameter("id");
        int cid = 1;
        if (ids != null) {
            cid = Integer.parseInt(ids);
        }
        MyImagesPageService myImagesPageService = new MyImagesPageService();
        Map<String, Object> mpd = myImagesPageService.load(uid, cid);
        for (String key : mpd.keySet()) {
            request.setAttribute(key, mpd.get(key));
        }

        request.getRequestDispatcher("WEB-INF/jsp/myimages.jsp")
                .forward(request, response);
    }

    private void showProfileInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProfilePageService profilePageService = new ProfilePageService();
        Map<String, Object> ppd = profilePageService.loadProfile(getCurrentUserId(request));
        for (String key : ppd.keySet()) {
            request.setAttribute(key, ppd.get(key));
        }
    }


    private Integer getCurrentUserId(HttpServletRequest request) {
        //TODO Remove this user id.
        //request.getSession().setAttribute("userId", 2);

        return (Integer) request.getSession().getAttribute("userId");
    }
}
