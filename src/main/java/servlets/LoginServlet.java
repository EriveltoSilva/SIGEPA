package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validators.LoginValidator;
import java.io.IOException;


@WebServlet(name="LoginServlet", urlPatterns = {"/login", "/accounts/login"})
public class LoginServlet extends HttpServlet {

    //-----------------> resp.sendRedirect("https:google.com");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println("GETTING...");
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        System.out.println("POSTTING...");
        String error = LoginValidator.validate(req.getParameter("email"), req.getParameter("password"));
        if(error != null) {
            req.setAttribute("errorMessage", error);
            this.doGet(req, resp);
        }
        // processar login
        req.getRequestDispatcher("/pages/administrator/dashboard.jsp").forward(req, resp);
    }
}
