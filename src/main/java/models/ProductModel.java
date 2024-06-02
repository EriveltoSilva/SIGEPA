package models;

import jakarta.servlet.http.HttpServlet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel extends HttpServlet {
    private Integer id;
    private String name;
    private String description;
    private Long quantity;
    private Double price;
    private CategoryModel category;


    public ProductModel(String name, String description, Long quantity, Double price, CategoryModel category) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }
}
