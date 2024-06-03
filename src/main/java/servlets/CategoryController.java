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

@WebServlet(urlPatterns = {"/category-delete"})
public class CategoryController extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();
    private final CategoryService categoryService;

    public CategoryController()
    {
        this.categoryService = new CategoryService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getServletPath();
        switch (command){
            case "/nothing": System.out.println("----> Nothing");break;
            case "/nothing2": System.out.println("------> Nothing2");break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getServletPath();

        switch (command){
            case "/category-delete": categoryService.delete(req, resp); break;
        }
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
}
