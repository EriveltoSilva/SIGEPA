package servlets;

import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CategoryService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/category-register", "/category-list", "/category-edit", "/category-delete"})
public class CategoryController extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();
    private final CategoryService categoryService;

    public CategoryController()
    {
        this.categoryService = new CategoryService(connection);
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Erro Fechando ao conexão ao destruir o CategoryController!");
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getServletPath();
        switch (command){
            case "/category-register": categoryService.getRegister(req, resp);
            case "/category-list": categoryService.getList(req, resp); break;
            case "/category-edit": categoryService.getEdit(req, resp); break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String command = req.getServletPath();
        switch (command){
            case "/category-register": categoryService.save(req, resp); break;
            case "/category-edit": categoryService.update(req, resp); break;
            case "/category-delete": categoryService.delete(req, resp); break;
        }
    }

}
