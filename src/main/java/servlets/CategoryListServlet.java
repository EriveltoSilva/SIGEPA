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


@WebServlet(name = "CategoryList", urlPatterns = {"/category-list", "/category-delete"})
public class CategoryListServlet extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY LIST...");
        CategoryDAO categoryDAO = new CategoryDAO(connection);
        List<CategoryModel> list = categoryDAO.findAll();
        //System.out.println(list.toString());
        req.setAttribute("categories", list);
        req.getRequestDispatcher("/pages/category/category-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            if(categoryDAO.delete(id)==0)
                req.setAttribute("errorMessage", "Erro deletando a categoria, tente mais tarde!");
            else
                req.setAttribute("successMessage", "Categoria Deletada com Sucesso!");
            this.doGet(req, resp);
            //req.getRequestDispatcher("/pages/category/category-list.jsp").forward(req,resp);
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erro: Falha ao converter o id da category em int:"+e.getMessage());
            req.setAttribute("errorMessage", "Erro deletando a categoria, tente mais tarde!");
            this.doGet(req, resp);
        }
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
