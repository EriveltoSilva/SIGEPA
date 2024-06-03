package services;

import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

public class CategoryService {

    private final CategoryDAO categoryDAO;
    
    public CategoryService(Connection connection){
        this.categoryDAO = new CategoryDAO(connection);
    }


    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING CATEGORY DELETE...");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
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

}
