package validate;

import error.Error;

public class ValidacionTelefonoSpain extends ValidacionRegularExpression implements IValidacion {
    private static final String patron = "^(\\+34|0034|34)?[6789]\\d{8}$";//"^(\+34|0034|34)?[6789]\d{8}$";

    private String value;
    public ValidacionTelefonoSpain(String value){

        this.value = value;

    }
    @Override
    public Error exec(){
        if( super.validar(value,patron)) return null;
          else return Error.ERROR_PHONE_BAD;
    }


}
