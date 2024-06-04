package services;

import dao.CategoryDAO;
import dao.ProductDAO;
import interfaces.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CategoryModel;
import models.ProductModel;
import validators.ProductValidator;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProductService implements Service<ProductModel> {
    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    public ProductService(Connection connection) {
        this.productDAO = new ProductDAO(connection);
        this.categoryDAO = new CategoryDAO(connection);
    }


    public void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING PRODUCT REGISTER...");
        List<CategoryModel> list = categoryDAO.findAll();
        req.setAttribute("categories", list);
        req.getRequestDispatcher("/pages/product/product-register.jsp").forward(req, resp);
    }

    public void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING PRODUCT LIST...");
        List<ProductModel> list = productDAO.findAll();
        req.setAttribute("products", list);
        req.getRequestDispatcher("/pages/product/product-list.jsp").forward(req, resp);
    }

    public void getEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GETTING PRODUCT EDIT...");
        try {
            long id = Long.parseLong(req.getParameter("id"));
            ProductModel product = productDAO.findById(id);
            List<CategoryModel> categories = categoryDAO.findAll();

            if(product==null) {
                req.setAttribute("errorMessage", "Erro: Este Producto não existe!");
            }

            req.setAttribute("categories", categories);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/pages/product/product-edit.jsp").forward(req,resp);
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erro: Falha ao converter o id da product em int:"+e.getMessage());
            req.setAttribute("errorMessage", "Erro editando a producto, tente mais tarde!");
            req.getRequestDispatcher("/product-edit").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath()+"/product-list");
    }

    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING PRODUCT REGISTER...");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String quantity = req.getParameter("quantity");
        String price = req.getParameter("price");
        String categoryId = req.getParameter("categoryId");

        String errorMessage = ProductValidator.registerValidate(name,description, quantity, price, categoryId);

        if(errorMessage!=null){
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/pages/product/product-register.jsp").forward(req, resp);
            return ;
        }
        
        if(productDAO.save(new ProductModel(name.trim(), description.trim(),Long.parseLong(quantity), Double.parseDouble(price), new CategoryModel(Long.parseLong(categoryId))))==0)
        req.setAttribute("errorMessage", "Algo deu errado, produto não gravada! Tente mais tarde!");
        else
            req.setAttribute("successMessage", "Produto gravado com sucesso!");
        req.getRequestDispatcher("/pages/product/product-register.jsp").forward(req, resp);

    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING PRODUCT EDIT...");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String quantity = req.getParameter("quantity");
        String price = req.getParameter("price");
        String categoryId = req.getParameter("categoryId");

        String errorMessage = ProductValidator.editValidate(id, name, description, quantity, price, categoryId);

        if(errorMessage!=null){
            req.setAttribute("errorMessage", errorMessage);
            System.err.println(errorMessage);
            if(id!=null)
                resp.sendRedirect(req.getContextPath()+"/product-edit?id="+id);
            else
                resp.sendRedirect(req.getContextPath()+"/product-list");
            return ;
        }
        long idNumber = Long.parseLong(id);
        if(productDAO.update(new ProductModel(idNumber,name.trim(), description.trim(),Long.parseLong(quantity), Double.parseDouble(price), new Date(),new CategoryModel(Long.parseLong(categoryId))))==0)
            req.setAttribute("errorMessage", "Algo deu errado, produto não gravada! Tente mais tarde!");
        else
            req.setAttribute("successMessage", "Produto gravada com sucesso!");
        resp.sendRedirect(req.getContextPath() + "/product-list");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSTTING PRODUCT DELETE...");
        try {
            long id = Long.parseLong(req.getParameter("id"));
            if(productDAO.delete(id)==0)
                req.setAttribute("errorMessage", "Erro deletando a produto, tente mais tarde!");
            else
                req.setAttribute("successMessage", "Produto Deletada com Sucesso!");
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erro: Falha ao converter o id da product em int:"+e.getMessage());
            req.setAttribute("errorMessage", "Erro deletando a produto, tente mais tarde!");
        }
        resp.sendRedirect(req.getContextPath() + "/product-list");
    }

    public int countProducts(){
        int totalProducts = productDAO.countProduct();
        System.out.println("NUM PRODUCTOS:"+totalProducts);
        return totalProducts;
    }

    public Map<Date, Integer> getProductCountsByDate(){
        return productDAO.getProductCountsByDate();
    }
}
