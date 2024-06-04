package models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Date created_at;

    public CategoryModel(Long id)
    {
        this.id = id;
    }

    public  CategoryModel(String name, String description){
        this.name = name;
        this.description = description;
    }
    public  CategoryModel(Long id, String name, String description){
        this(name, description);
        this.id=id;
    }
}
