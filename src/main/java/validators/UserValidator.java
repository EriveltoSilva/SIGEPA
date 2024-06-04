package validators;

public class UserValidator {


    public static String registerValidate(String fullName, String email, String username, String password, String confirmationPassword)
    {
        StringBuilder error = new StringBuilder();
        if(fullName==null)
            error.append("O Campo nome completo não foi enviado!<br/>");
        else if(fullName.isEmpty())
            error.append("O Campo nome completo está em branco!<br/>");

        if(email==null)
            error.append("O Campo email não foi enviado!<br/>");
        else if(email.isEmpty())
            error.append("O Campo email está em branco!<br/>");

        if(username==null)
            error.append("O Campo username não foi enviado!<br/>");
        else if(username.isEmpty())
            error.append("O Campo username está em branco!<br/>");

        if(password==null)
            error.append("O Campo password não foi enviado!<br/>");
        else if(password.isEmpty())
            error.append("O Campo password está em branco!<br/>");

        if(confirmationPassword==null)
            error.append("O Campo confirmação de palavra-passe  não foi enviado!<br/>");
        else if(confirmationPassword.isEmpty())
            error.append("O Campo confirmação de palavra-passe está em branco!<br/>");

        if(password!=null && confirmationPassword!=null)
        {
            if(!confirmationPassword.equals(password)) {
                error.append("Os Campos palavra-passe e confirmação de palavra-passe não são iguais!<br/>");
            }
        }

        return (error.length()>0)? error.toString():null;
    }

    public static String validateEdit(String id, String fullName, String email, String username, String password, String confirmationPassword)
    {
        StringBuilder error = new StringBuilder();

        if(id == null)
            error.append("O Campo id não foi enviado!<br/>");
        else if(id.isEmpty())
            error.append("O Campo id está em branco!<br/>");
        else{
            try {
                Long.parseLong(id);
            }catch (NumberFormatException e)
            {
                System.err.println("ERRO: NUMERO DE ID INVALIDO!");
                error.append("ERRO: NUMERO DE ID INVALIDO!");
            }
        }

        String errorValue = UserValidator.registerValidate(fullName, email, username, password, confirmationPassword);
        if(errorValue!=null)
            error.append(errorValue);

        return (error.length()>0)? error.toString():null;
    }
}
