package servlets;

import db.PostgresConnectionSingleton;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.CategoryService;
import services.ProductService;
import services.UserService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name ="DashboardServlet", urlPatterns = {"/home","/dashboard", "/admin"})
public class DashboardController extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    public DashboardController() {
        this.userService = new UserService(this.connection);
        this.productService = new ProductService(this.connection);
        this.categoryService = new CategoryService(this.connection);
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Erro fechando a conexão de DashboardServlet");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println("GETTING DASHBOARD...");
        req.setAttribute("totalUsers", userService.countUsers());
        req.setAttribute("totalProducts", productService.countProducts());
        req.setAttribute("totalCategories", categoryService.countCategories());
        req.setAttribute("productsBar", productService.getProductCountsByDate());
        req.setAttribute("categoriesBar", categoryService.getCategoriesCountsByDate());
        getServletContext().getRequestDispatcher("/pages/administrator/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
