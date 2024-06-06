package filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import db.PostgresConnectionFactory;

@WebFilter(filterName = "DataBaseConnectionFilter", urlPatterns = {"/database/*"})
public class DataBaseConnectionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Connection connection;

        try{
            connection = new PostgresConnectionFactory().getConnection();
            req.setAttribute("connection", connection);
            chain.doFilter(req, resp);
        }
        catch (SQLException e) {
            request.getRequestDispatcher("/error/errro-500.jsp").forward(request, response);
            throw new RuntimeException(e);
        }

        try {
            connection.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: ERRO AO FECHAR A CONEXÃO: " + request.getServletPath());
            throw new RuntimeException(e);
        }

    }
}
