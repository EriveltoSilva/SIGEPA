package models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private Double price;
    private Date created_at;
    private CategoryModel category;

    public ProductModel(String name, String description, Long quantity, Double price, CategoryModel category) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }
}
