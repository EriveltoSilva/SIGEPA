package servlets;

import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.UserModel;
import validators.LoginValidator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name="LoginServlet", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();

    @Override
    public void destroy() {
        super.destroy();
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Erro fechando a conexão de LoginController");
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println("GETTING LOGIN...");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("POSTTING LOGIN...");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String error = LoginValidator.validate(email, password);
        if(error != null) {
            req.setAttribute("errorMessage", error);
            this.doGet(req, resp);
        }

        UserModel user = new UserModel(email, password);
        if(!(user.getEmail().equals("eriveltoclenio@gmail.com") && user.getPassword().equals("a")))
        {
            req.setAttribute("errorMessage", "USUÁRIO INVÁLIDO!");
            this.doGet(req, resp);
            return;
        }
        req.getSession().setAttribute("user", user);
        req.setAttribute("successMessage", "SEJA BEM VINDO");
        req.getRequestDispatcher("/dashboard").forward(req, resp);
    }
}
