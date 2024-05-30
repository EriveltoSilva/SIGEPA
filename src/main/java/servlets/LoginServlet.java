package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="LoginServlet", urlPatterns = {"/login", "/accounts/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING...");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        req.setAttribute("email", email);
        this.doGet(req,resp);
    }

    //-----------------> resp.sendRedirect("https:google.com");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        System.out.println("GETTING...");


        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
