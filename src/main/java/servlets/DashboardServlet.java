package servlets;

import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CategoryService;
import services.ProductService;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name ="DashboardServlet", urlPatterns = {"/home","/dashboard", "/admin"})
public class DashboardServlet extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();
    private final ProductService productService;
    private final CategoryService categoryService;

    public DashboardServlet(){
        this.productService = new ProductService(this.connection);
        this.categoryService = new CategoryService(this.connection);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println("GETTING DASHBOARD...");
        req.setAttribute("totalProducts", productService.countProducts());
        req.setAttribute("totalCategories", categoryService.countCategories());
        getServletContext().getRequestDispatcher("/pages/administrator/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        System.out.println("POSTTING DASHBOARD...");
        this.doGet(req, resp);
    }
}
