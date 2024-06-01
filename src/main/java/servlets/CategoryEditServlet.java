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

@WebServlet(name = "CategoryEdit", urlPatterns = {"/category-edit"})
public class CategoryEditServlet extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY EDIT...");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            CategoryModel category = categoryDAO.findById(id);
            if(category==null)
                req.setAttribute("errorMessage", "Erro: Categoria de edição não existe!");

            req.setAttribute("category", category);
            req.getRequestDispatcher("/pages/category/category-edit.jsp").forward(req,resp);
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erro: Falha ao converter o id da category em int:"+e.getMessage());
            req.setAttribute("errorMessage", "Erro editando a categoria, tente mais tarde!");
            this.doGet(req, resp);
        }
        req.getRequestDispatcher("/pages/category/category-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING CATEGORY EDIT...");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        String errorMessage = CategoryValidator.validateEdit(id, name, description);

        if(errorMessage!=null){
            req.setAttribute("errorMessage", errorMessage);
            this.doGet(req, resp);
            //req.getRequestDispatcher("/category-edit.jsp").forward(req, resp);
            return ;
        }
        int idNumber = Integer.parseInt(id);

        CategoryDAO categoryDAO = new CategoryDAO(connection);
        if(categoryDAO.update(new CategoryModel(idNumber, name, description))==0)
            req.setAttribute("errorMessage", "Algo deu errado, categoria não gravada! Tente mais tarde!");
        else
            req.setAttribute("successMessage", "Categoria gravada com sucesso!");
        req.getRequestDispatcher("/category-list").forward(req, resp);
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
