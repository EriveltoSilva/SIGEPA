package servlets;

import dao.CategoryDAO;
import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CategoryModel;
import validators.CategoryValidator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "CategoryRegister", urlPatterns = {"/category-new"})
public class CategoryRegisterServlet extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY NEW...");
        req.getRequestDispatcher("/pages/category/category-new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING CATEGORY NEW...");
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        String errorMessage = CategoryValidator.validate(name,description);

        if(errorMessage!=null){
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/pages/catagory/category-new.jsp").forward(req, resp);
            return ;
        }

        CategoryDAO categoryDAO = new CategoryDAO(connection);
        if(categoryDAO.save(new CategoryModel(name.trim(), description.trim()))==0)
            req.setAttribute("errorMessage", "Algo deu errado, categoria não gravada! Tente mais tarde!");
        else
            req.setAttribute("successMessage", "Categoria gravada com sucesso!");
        req.getRequestDispatcher("/pages/category/category-new.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
