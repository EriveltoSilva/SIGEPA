package servlets;

import db.PostgresConnectionSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "ProductController", urlPatterns = {"/product-register", "/product-list","/product-edit","/product-delete" })
public class ProductController extends HttpServlet {

    private final Connection connection = PostgresConnectionSingleton.getInstance().getConnection();;
    private final ProductService productService;

    public ProductController()
    {
        this.productService = new ProductService(this.connection);
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
        switch(command) {
            case "/product-register": productService.getRegister(req, resp); break;
            case "/product-list": productService.getList(req, resp);         break;
            case "/product-edit": productService.getEdit(req, resp);         break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getServletPath();
        switch(command) {
            case "/product-register": productService.save(req, resp);   break;
            case "/product-edit": productService.update(req, resp);     break;
            case "/product-delete": productService.delete(req, resp);   break;
        }
    }
}
