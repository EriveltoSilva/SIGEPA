package services;

import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CategoryModel;
import validators.CategoryValidator;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;
    
    public CategoryService(Connection connection){
        this.categoryDAO = new CategoryDAO(connection);
    }

    public void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY NEW...");
        req.getRequestDispatcher( "/pages/category/category-register.jsp").forward(req, resp);
    }

    public void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY LIST...");
        List<CategoryModel> list = categoryDAO.findAll();
        req.setAttribute("categories", list);
        req.getRequestDispatcher("/pages/category/category-list.jsp").forward(req, resp);
    }
    
    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("POSTTING CATEGORY REGISTER...");
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        String errorMessage = CategoryValidator.validate(name,description);

        if(errorMessage!=null){
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/pages/catagory/category-register.jsp").forward(req, resp);
            return ;
        }

        if(categoryDAO.save(new CategoryModel(name.trim(), description.trim()))==0)
            req.setAttribute("errorMessage", "Algo deu errado, categoria não gravada! Tente mais tarde!");
        else
            req.setAttribute("successMessage", "Categoria gravada com sucesso!");
        req.getRequestDispatcher("/pages/category/category-register.jsp").forward(req, resp);

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
