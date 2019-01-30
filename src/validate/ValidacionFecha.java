package validate;

import error.Error;

public class ValidacionFecha extends ValidacionRegularExpression implements IValidacion {

    private static final String patron = "^(\\d{4})(\\-)(0[1-9]|1[012])(\\-)(0[1-9]|[1-2]\\d|3[01])$";

    private String value;

    public ValidacionFecha(String value){

        this.value = value;

    }
    @Override
    public Error exec(){

        if(super.validar(value,patron)) return null;

            else return Error.ERROR_FECHA_BAD;
    }


}

