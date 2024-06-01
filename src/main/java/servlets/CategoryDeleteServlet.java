package servlets;

import dao.CategoryDAO;
import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "CategoryDelete", urlPatterns = {"/category-delete"})
public class CategoryDeleteServlet extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING CATEGORY DELETE...");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            if(categoryDAO.delete(id)==0)
                req.setAttribute("errorMessage", "Erro deletando a categoria, tente mais tarde!");
            else
                req.setAttribute("successMessage", "Categoria Deletada com Sucesso!");
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erro: Falha ao converter o id da category em int:"+e.getMessage());
            req.setAttribute("errorMessage", "Erro deletando a categoria, tente mais tarde!");
        }
        resp.sendRedirect(req.getContextPath() + "/category-list");
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
