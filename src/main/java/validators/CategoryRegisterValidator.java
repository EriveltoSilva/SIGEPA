package validators;

public class CategoryRegisterValidator {

    public static String validate(String name, String description)
    {
        StringBuilder error = new StringBuilder();
        if(name==null)
            error.append("O Campo nome não foi enviado!<br/>");
        else if(name.isEmpty())
            error.append("O Campo nome está em branco!<br/>");

        if(description==null)
            error.append("O Campo descrição não foi enviado!<br/>");
        else if(description.isEmpty())
            error.append("O Campo descrição está em branco!<br/>");


        return (error.length()>0)? error.toString():null;
    }
}
