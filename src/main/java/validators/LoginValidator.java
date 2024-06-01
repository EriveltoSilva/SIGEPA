package validators;

public class LoginValidator {

    public static String validate(String email, String password){
        StringBuilder error = new StringBuilder();

        if(email==null)
            error.append("Campo e-mail não enviado!<br/>");
        else if(email.isEmpty())
            error.append("Campo e-mail está vazio!<br/>");

        if(password==null)
            error.append("Campo password não enviado<br/>");
        else if (password.isEmpty())
            error.append("Campo password está vázio!<br/>");

        return (error.length()>0)? error.toString() : null;
    }
}
