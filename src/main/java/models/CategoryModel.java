package models;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    private Integer id;
    private String name;
    private String description;
    private Date created_at;

    public  CategoryModel(String name, String description){
        this.name = name;
        this.description = description;
    }
    public  CategoryModel(Integer id, String name, String description){
        this(name, description);
        this.id=id;
    }
}
