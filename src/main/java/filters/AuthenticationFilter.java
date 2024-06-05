package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.UserModel;

import java.io.IOException;

@WebFilter(filterName = "/AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        String route = request.getServletPath();

        if (route.equals("/login")) {
            chain.doFilter(req, resp);
            return ;
        }

        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("errorMessage", "Você não se encontra logado! Faça login por favor!-");
            request.setAttribute("previousURL", route);
            request.getRequestDispatcher("/login").forward(request,response);
            //request.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        chain.doFilter(req, resp);
    }

}
