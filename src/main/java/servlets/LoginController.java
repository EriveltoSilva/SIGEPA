package servlets;

import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.UserModel;
import services.UserService;
import validators.LoginValidator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name="LoginServlet", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();
    private final UserService userService;

    public LoginController()
    {
        this.userService = new UserService(connection);
    }

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
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String error = LoginValidator.validate(email, password);
        if(error != null) {
            req.setAttribute("errorMessage", error);
            this.doGet(req, resp);
        }

        UserModel user = new UserModel(email, password);
        if((user = userService.authenticateUser(user))==null)
        {
            req.setAttribute("errorMessage", "CREDENCIAIS INVÁLIDAS!");
            this.doGet(req, resp);
            return;
        }
        req.getSession().setAttribute("user", user);
        req.setAttribute("successMessage", "BEM VINDO DE VOLTA \""+ user.getFullName()+"\"");
        String previousURL = req.getParameter("previousURL");
        if(previousURL!=null && !previousURL.isEmpty() && !previousURL.equals("null")) {
            resp.sendRedirect(req.getContextPath()+previousURL);
            return;
        }
        req.getRequestDispatcher("/dashboard").forward(req, resp);
    }
}
