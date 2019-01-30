package validate;

import error.Error;

public class ValidarDomicilio extends ValidacionRegularExpression implements IValidacion {

    private static final String patron = "^([0-9ºª.:,/a-zA-ZñÑáéíóúÜüÁÉÍÓÚ\\s]*)$";

    private String value;

    public ValidarDomicilio(String value){
        this.value = value;
    }

    @Override
    public Error exec() {

        if(super.validar(value,patron)) return null;
           else return Error.ERROR_DOMICILIO_BAD;
    }


}
