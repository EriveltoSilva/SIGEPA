package models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private Date created_at;

    public UserModel(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public UserModel(String fullName, String email, String username, String password)
    {
        this(email,password);
        this.fullName = fullName;
        this.username = username;
    }

    public UserModel(Long id, String fullName, String email, String username, String password)
    {
        this(fullName,email, username, password);
        this.id = id;
    }
}
