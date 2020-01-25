package by.khomenko.training.finaltask05.servlet;

import by.khomenko.training.finaltask05.entity.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(description = "Check user's role",
        urlPatterns = {"/adminpage.html"})
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (!(request.getSession().getAttribute("userRole")).equals(Role.ADMINISTRATOR)) {

            response.sendRedirect("error.html");
        } else {
            chain.doFilter(request, response);
        }

    }

}
