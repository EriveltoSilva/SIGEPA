package validators;

public class CategoryValidator {

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

    public static String validateEdit(String id, String name, String description)
    {
        StringBuilder error = new StringBuilder();

        if(id == null)
            error.append("O Campo id não foi enviado!<br/>");
        else if(id.isEmpty())
            error.append("O Campo  está em branco!<br/>");
        else{
            try {
                Long.parseLong(id);
            }catch (NumberFormatException e)
            {
                System.err.println("ERRO: NUMERO DE ID INVALIDO!");
                error.append("ERRO: NUMERO DE ID INVALIDO!");
            }
        }
        String errorValue = CategoryValidator.validate(name, description);
        if(errorValue!=null)
            error.append(errorValue);

        return (error.length()>0)? error.toString():null;
    }
}
