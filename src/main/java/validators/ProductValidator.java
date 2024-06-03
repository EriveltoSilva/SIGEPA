package validators;

public class ProductValidator {

    public static String registerValidate(String name, String description, String quantity, String price, String categoryId)
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

        if(quantity==null)
            error.append("O Campo quantidade não foi enviado!<br/>");
        else if(quantity.isEmpty())
            error.append("O Campo quantidade está em branco!<br/>");
        else {
            try {
                Long.parseLong(quantity);
            }catch (NumberFormatException e)
            {
                System.err.println("ERROR: ERRO CONVERTENDO O VALOR DE QUANTITY DE PRODUCTO!");
                error.append("O Campo quantidade não tem um valor numérico!");
            }
        }

        if(price==null)
            error.append("O Campo preço não foi enviado!<br/>");
        else if(price.isEmpty())
            error.append("O Campo preço está em branco!<br/>");
        else {
            try {
                Double.parseDouble(price);
            }catch (NumberFormatException e)
            {
                System.err.println("ERROR: ERRO CONVERTENDO O VALOR DE PRICE DE PRODUCTO!");
                error.append("O Campo preço não tem um valor numérico!");
            }
        }

        if(categoryId==null)
            error.append("O Campo categoria não foi selecionado!<br/>");
        else if(categoryId.isEmpty())
            error.append("O Campo categoria está em branco!<br/>");
        else {
            try {
                Long.parseLong(categoryId);
            }catch (NumberFormatException e)
            {
                System.err.println("ERROR: ERRO CONVERTENDO O VALOR DE CATEGORY_ID DE PRODUCTO!");
                error.append("O Campo categoria tem um valor inválido!");
            }
        }

        return (error.length()>0)? error.toString():null;
    }

    public static String editValidate(String id, String name, String description, String quantity, String price, String categoryId)
    {
        StringBuilder error = new StringBuilder();

        if(id == null)
            error.append("O Campo id não foi enviado!<br/>");
        else if(name.isEmpty())
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

        String errorValue = ProductValidator.registerValidate(name,description, quantity, price, categoryId);
        if (errorValue!=null)
            error.append(errorValue);

        return (error.length()>0)? error.toString():null;
    }
}
