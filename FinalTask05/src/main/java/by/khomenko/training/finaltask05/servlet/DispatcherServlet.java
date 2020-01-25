package by.khomenko.training.finaltask05.servlet;

import by.khomenko.training.finaltask05.dao.DaoFactory;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Favorite;
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
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@MultipartConfig
@WebServlet(
        name = "DispatcherServlet",
        urlPatterns = {"/adminpage.html", "/category.html",
                "/home.html", "/login.html", "/myimages.html", "/profile.html",
                "/registration.html", "/logout.html", "/profilesettings.html", "/welcomepage.html"})
public class DispatcherServlet extends HttpServlet {

    private static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/crowdsource_db?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "crowdsource_user";
    private static final String DB_PASSWORD = "crowdsource_password";
    private static final int DB_POOL_START_SIZE = 10;
    private static final int DB_POOL_MAX_SIZE = 1000;
    private static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;


    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DispatcherServlet.class);

    private static final String ANSWER = "answer-";
    private static final String CATEGORY = "category-";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int length = request.getContextPath().length();
        String s = request.getRequestURI().substring(length);

        String locale = request.getParameter("locale");

        if (locale != null) {
            HttpSession session = request.getSession();
            session.setAttribute("localeType", locale);
        }

        try {

            switch (s) {

                case "/adminpage.html":

                    AdminPageService adminPageService = new AdminPageService();
                    Map<String, Object> loadedData
                            = adminPageService.load();
                    for (String key : loadedData.keySet()) {
                        request.setAttribute(key, loadedData.get(key));
                    }
                    request.getRequestDispatcher("WEB-INF/jsp/adminpage.jsp")
                            .forward(request, response);
                    break;

                case "/category.html":

                    showCategory(request, response);

                    break;

                case "/home.html":

                    HomePageService homePageService = new HomePageService();
                    Map<String, Object> hpd = homePageService.load();
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

                    Integer uId = (Integer) request.getSession()
                            .getAttribute("userId");
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
                    response.sendRedirect("welcomepage.html");

                    break;

                case "/profilesettings.html":

                    showProfileInfo(request, response);

                    request.getRequestDispatcher("WEB-INF/jsp/profilesettings.jsp")
                            .forward(request, response);

                    break;

                default:

                    request.getRequestDispatcher("WEB-INF/jsp/welcomepage.jsp")
                            .forward(request, response);

            }

        } catch (PersistentException e) {
            LOGGER.error("An exception in doGet method occurred.", e);
            response.sendRedirect("error.html");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int length = request.getContextPath().length();
        String s = request.getRequestURI().substring(length);

        try {

            switch (s) {

                case "/adminpage.html":

                    AdminPageService adminPageService = new AdminPageService();
                    if ("addUserToBlacklist".equals(request.getParameter("formAction"))) {
                        adminPageService.addUserToBlacklist(request.getParameter("userToAdd"));
                    } else if ("removeUserFromBlacklist".equals(request.getParameter("formAction"))) {
                        adminPageService.removeUserFromBlacklist(request.getParameter("userRemoveFrom"));
                    } else if ("saveSettings".equals(request.getParameter("formAction"))) {
                        String fs = request.getParameter("fileSize");
                        String fex = request.getParameter("fileExtensions");
                        String floc = request.getParameter("filesLocation");
                        adminPageService.setSettings(fs, fex, floc);
                    }

                    Map<String, Object> loadedData = adminPageService.load();
                    for (String key : loadedData.keySet()) {
                        request.setAttribute(key, loadedData.get(key));
                    }

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

                    categoryPageService.saveRecognizedImages(recognizedImgList, userId);
                    showCategory(request, response);

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
                        request.getSession().setAttribute("userRole", loggedUser.getRole());
                        response.sendRedirect("profile.html");
                    } else {
                        request.setAttribute("errorMessage", "Incorrect login or password.");
                        request.getRequestDispatcher("WEB-INF/jsp/login.jsp")
                                .forward(request, response);
                    }

                    break;

                case "/myimages.html":

                    updateMyImages(request);
                    showMyImages(request, response);

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

                        request.setAttribute("errorMessage", e.getMessage());
                        request.setAttribute("regLogin", rps);
                        request.getRequestDispatcher("WEB-INF/jsp/registration.jsp")
                                .forward(request, response);
                    }

                    break;

                case "/profilesettings.html":

                    updateProfileSettings(request, response);
                    showProfileInfo(request, response);

                    break;

                default:

                    response.sendRedirect("welcomepage.html");

            }
        } catch (PersistentException | IOException e) {
            LOGGER.error("An exception in doPost method occurred.", e);
            response.sendRedirect("error.html");
        } catch (ValidationException e) {
            LOGGER.error("Validation exception in doPost method occurred.", e);
            response.sendRedirect("error.html");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER,
                    DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE,
                    DB_POOL_CHECK_CONNECTION_TIMEOUT);
            DaoFactory.getInstance().init("mysql");
        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    private void showCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PersistentException {

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

    private void showMyImages(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PersistentException {

        Integer uid = getCurrentUserId(request);
        Integer pageNumber = 1;
        String pageString = request.getParameter("page");
        if (pageString != null) {
            pageNumber = Integer.parseInt(pageString);

        }
        MyImagesPageService myImagesPageService = new MyImagesPageService();
        Map<String, Object> mpd = myImagesPageService.load(uid, pageNumber);
        for (String key : mpd.keySet()) {
            request.setAttribute(key, mpd.get(key));
        }

        request.getRequestDispatcher("WEB-INF/jsp/myimages.jsp")
                .forward(request, response);
    }

    private void showProfileInfo(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException {
        ProfilePageService profilePageService = new ProfilePageService();
        Map<String, Object> ppd = profilePageService.loadProfile(getCurrentUserId(request));
        for (String key : ppd.keySet()) {
            request.setAttribute(key, ppd.get(key));
        }
    }

    private void updateProfileSettings(HttpServletRequest request,
                                       HttpServletResponse response)
            throws ServletException, IOException, PersistentException,
            ValidationException {

        Integer userId = getCurrentUserId(request);
        String avatarFileName = null;
        Part filePart = request.getPart("photoToUpload");
        if ((filePart != null) && (!filePart.getSubmittedFileName().isEmpty())) {
            InputStream fileContent = filePart.getInputStream();
            avatarFileName = Paths.get(filePart.getSubmittedFileName())
                    .getFileName().toString();
            Files.copy(fileContent, Paths.get("C:\\Users"
                            + "\\Georgy\\IdeaProjects\\EpamTraining"
                            + "\\FinalTask05\\target\\crowdsourcing"
                            + "\\avatars\\",
                    avatarFileName));
        }

        String newUserName = request.getParameter("newUserName");
        String curUserPass = request.getParameter("currentPassword");
        String newPass = request.getParameter("newPassword");
        String confNewPass = request.getParameter("confirmNewPassword");

        ProfilePageService profilePageService = new ProfilePageService();
        profilePageService.updateProfile(userId, avatarFileName, newUserName,
                curUserPass, newPass, confNewPass);

        List<Favorite> favorites = new ArrayList<>();

        for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements(); ) {
            String id = e.nextElement();
            if (id.startsWith(CATEGORY)) {
                Favorite favorite = new Favorite();
                favorite.setCategoryId(Integer.parseInt(id.substring(CATEGORY.length())));
                favorite.setUserId(userId);
                favorites.add(favorite);
            }
        }
        profilePageService.saveFavorites(favorites, userId);
        response.sendRedirect("profile.html");
    }

    private void updateMyImages(HttpServletRequest request)
            throws PersistentException, IOException, ServletException {

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

        myImagesPageService.updateImgCategories(addedImagesList);
        Part fp = request.getPart("imageToUpload");
        SystemSettingsService systemSettingsService = new SystemSettingsService();
        Integer fileSizeInt = Integer.parseInt(systemSettingsService.getSetting("fileSize"));
        if (fp.getSize() / (1024 * 1024) > fileSizeInt) {
            request.setAttribute("errorMessage", "File's size is not valid."
                    + " Max allowed file size is " + fileSizeInt + " Mb.");

        } else {
            InputStream fc = fp.getInputStream();
            String fileName = Paths.get(fp.getSubmittedFileName())
                    .getFileName().toString();


            if (!("").equals(fileName)) {
                String stringExtensions = systemSettingsService
                        .getSetting("fileExtensions");
                String[] allowedExtensions = stringExtensions.split(",");
                boolean valid = false;
                for (String str : allowedExtensions) {
                    if (fileName.endsWith(str)) {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    fileName = generateUniqueFileName() + "_" + fileName;
                    Files.copy(fc, Paths.get(systemSettingsService.getSetting("filesLocation"),
                            fileName));
                    Image addedImage = new Image();
                    addedImage.setPath("imagesdir/" + fileName);
                    addedImage.setUserId(getCurrentUserId(request));
                    myImagesPageService.addImage(addedImage);
                } else {
                    request.setAttribute("errorMessage",
                            "File's extension is not valid. Valid extensions are : " + stringExtensions);

                }

            }
        }

    }

    private Integer getCurrentUserId(HttpServletRequest request) {

        return (Integer) request.getSession().getAttribute("userId");
    }

    private String generateUniqueFileName() {
        String filename = "";
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyhhmmssSSS");
        String datetime = simpleDateFormat.format(new Date());
        datetime = datetime.replace(" ", "");
        datetime = datetime.replace(":", "");
        filename = datetime + "_" + millis;
        return filename;
    }
}
