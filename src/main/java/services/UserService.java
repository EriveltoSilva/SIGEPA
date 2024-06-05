package services;

import dao.UserDAO;
import interfaces.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.UserModel;
import validators.UserValidator;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserService implements Service<UserModel> {
    private final UserDAO userDAO;

    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("successMessage", "Sucesso no logout. Volte Sempre!");
        req.getRequestDispatcher("/login").forward(req, resp);
    }

    public void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING USER REGISTER...");
        req.getRequestDispatcher("/pages/accounts/register.jsp").forward(req, resp);
    }

    public void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING USER LIST...");
        List<UserModel> list = userDAO.findAll();
        req.setAttribute("users", list);
        req.getRequestDispatcher("/pages/accounts/list.jsp").forward(req, resp);
    }

    public void getEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING USER EDIT...");
        try {
            long id = Long.parseLong(req.getParameter("id"));
            UserModel user = userDAO.findById(id);
            if(user==null) {
                req.setAttribute("errorMessage", "Erro: Usuário de edição não existe!");
                req.getRequestDispatcher("/accounts/list").forward(req,resp);
                return;
            }

            req.setAttribute("user", user);
            req.getRequestDispatcher("/pages/accounts/user-edit.jsp").forward(req,resp);
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erro: Falha ao converter o id da user em int:"+e.getMessage());
            req.setAttribute("errorMessage", "Erro editando a usuário, tente mais tarde!");
            req.getRequestDispatcher("/accounts/user-edit").forward(req, resp);
            //resp.sendRedirect(req.getContextPath()+"/user-list");
        }
        //req.getRequestDispatcher("/pages/user/user-list.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath()+"/accounts/list");
    }

    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING USER REGISTER...");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmationPassword = req.getParameter("confirmationPassword");

        String errorMessage = UserValidator.registerValidate(fullName,email, username, password, confirmationPassword);

        if(errorMessage!=null){
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/pages/accounts/register.jsp").forward(req, resp);
            return ;
        }

        if(userDAO.save(new UserModel(fullName.trim(), email.trim(), username.trim(), password))==0)
            req.setAttribute("errorMessage", "Algo deu errado, usuário não gravada! Tente mais tarde!");
        else
            req.setAttribute("successMessage", "Usuário gravada com sucesso!");
        req.getRequestDispatcher("/pages/accounts/register.jsp").forward(req, resp);

    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING USER EDIT...");
        String id = req.getParameter("id");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmationPassword = req.getParameter("confirmationPassword");

        String errorMessage = UserValidator.validateEdit(id, fullName, email, username, password, confirmationPassword);

        if(errorMessage!=null){
            req.setAttribute("errorMessage", errorMessage);
            if(id!=null)
                resp.sendRedirect(req.getContextPath()+"/accounts/user-edit?id="+id);
            else
                resp.sendRedirect(req.getContextPath()+"/accounts/list");
            return ;
        }

        long idNumber = Long.parseLong(id);
        if(userDAO.update(new UserModel(idNumber, fullName.trim(), email.trim(), username.trim(), password))==0)
            req.setAttribute("errorMessage", "Algo deu errado, usuário não gravada! Tente mais tarde!");
        else
            req.setAttribute("successMessage", "Usuário gravado com sucesso!");
        resp.sendRedirect(req.getContextPath() + "/accounts/list");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING USER DELETE...");
        try {
            long id = Integer.parseInt(req.getParameter("id"));
            if(userDAO.delete(id)==0)
                req.setAttribute("errorMessage", "Erro deletando a usuário, tente mais tarde!");
            else
                req.setAttribute("successMessage", "Usuário Deletada com Sucesso!");
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erro: Falha ao converter o id da user em int:"+e.getMessage());
            req.setAttribute("errorMessage", "Erro deletando a usuário, tente mais tarde!");
        }
        resp.sendRedirect(req.getContextPath() + "/accounts/list");
    }

    public UserModel authenticateUser(UserModel user){
        return userDAO.find(user);
    }

    public int countUsers(){
        return userDAO.countUsers();
    }

    public Map<Date, Integer> getUsersCountsByDate(){
        return userDAO.getUsersCountsByDate();
    }
}
