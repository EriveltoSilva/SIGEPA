package servlets;

import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "UserController", urlPatterns = {"/accounts/register", "/accounts/list", "/accounts/delete", "/accounts/user-edit"})
public class UserController extends HttpServlet {
    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();
    private final UserService userService;

    public UserController(){
        this.userService = new UserService(connection);
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Erro fechando a conexão de UserController");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getServletPath();
        switch (command){
            case "/accounts/register": userService.getRegister(req, resp);break;
            case "/accounts/list": userService.getList(req, resp);break;
            case "/accounts/user-edit": userService.getEdit(req, resp);break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getServletPath();
        switch (command){
            case "/accounts/register": userService.save(req, resp);break;
            case "/accounts/user-edit": userService.update(req, resp);break;
            case "/accounts/delete": userService.delete(req, resp);break;
        }
    }
}
