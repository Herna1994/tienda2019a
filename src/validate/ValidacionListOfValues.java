package validate;

public class ValidacionListOfValues {

    public boolean validar(String value, String [] valores){

        for (int i = 0; i < valores.length ; i++) {
            if(valores[i].toLowerCase().equals(value.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
