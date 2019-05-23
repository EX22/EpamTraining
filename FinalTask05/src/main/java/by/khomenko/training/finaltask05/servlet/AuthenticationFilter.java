package by.khomenko.training.finaltask05.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(description = "Redirect to login if not authorised",
        urlPatterns = {"/adminpage.html", "/category.html", "/myimages.html",
                "/profile.html", "/profilesettings.html"})
public class AuthenticationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        if (req.getSession().getAttribute("userId") == null) {
            String s = req.getRequestURI() + req.getQueryString();
            req.getSession().setAttribute("requestedURL", s);
            res.sendRedirect("login.html");
        } else {
            super.doFilter(req, res, chain);
        }

    }

}
