package validate;

import error.Error;

public class ValidacionLetrasSinEspacio extends ValidacionRegularExpression implements IValidacion {

    private String patron =  "^([a-zA-ZñÑáéíóúÁÉÍÓÚ\\S]*)$";

    private String value;

    public ValidacionLetrasSinEspacio(String value) {
        this.value = value;
    }

    @Override
    public Error exec() {

        if(super.validar(value, patron)) return null;
            else return Error.ERROR_LETTER_ONLY;
    }



}