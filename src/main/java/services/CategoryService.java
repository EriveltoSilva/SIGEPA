package services;

import dao.CategoryDAO;
import interfaces.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CategoryModel;
import validators.CategoryValidator;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class CategoryService implements Service<CategoryModel> {
    private final CategoryDAO categoryDAO;

    public CategoryService(Connection connection) {
        this.categoryDAO = new CategoryDAO(connection);
    }

    public void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY NEW...");
        req.getRequestDispatcher("/pages/category/category-register.jsp").forward(req, resp);
    }

    public void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY LIST...");
        List<CategoryModel> list = categoryDAO.findAll();
        req.setAttribute("categories", list);
        req.getRequestDispatcher("/pages/category/category-list.jsp").forward(req, resp);
    }

    public void getEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING CATEGORY EDIT...");
        try {
            long id = Long.parseLong(req.getParameter("id"));
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
            req.getRequestDispatcher("/category-edit").forward(req, resp);
            //resp.sendRedirect(req.getContextPath()+"/category-list");
        }
        //req.getRequestDispatcher("/pages/category/category-list.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath()+"/category-list");
    }

    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING CATEGORY EDIT...");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        String errorMessage = CategoryValidator.validateEdit(id, name, description);

        if(errorMessage!=null){
            req.setAttribute("errorMessage", errorMessage);
            if(id!=null)
                resp.sendRedirect(req.getContextPath()+"/category-edit?id="+id);
            else
                resp.sendRedirect(req.getContextPath()+"/category-list");
            return ;
        }

        long idNumber = Long.parseLong(id);
        if(categoryDAO.update(new CategoryModel(idNumber, name.trim(), description.trim()))==0)
            req.setAttribute("errorMessage", "Algo deu errado, categoria não gravada! Tente mais tarde!");
        else
            req.setAttribute("successMessage", "Categoria gravada com sucesso!");
        resp.sendRedirect(req.getContextPath() + "/category-list");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING CATEGORY DELETE...");
        try {
            long id = Integer.parseInt(req.getParameter("id"));
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
