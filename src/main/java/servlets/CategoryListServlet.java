package servlets;

import dao.CategoryDAO;
import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CategoryModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "CategoryList", urlPatterns = {"/category-list"})
public class CategoryListServlet extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY LIST...");
        CategoryDAO categoryDAO = new CategoryDAO(connection);
        List<CategoryModel> list = categoryDAO.findAll();
        req.setAttribute("categories", list);
        req.getRequestDispatcher("/pages/category/category-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
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
