package validate;

import error.Error;

public class ValidacionLetrasConEspacio extends ValidacionRegularExpression implements IValidacion {

    private static final String patron = "^([a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]*)$";

    private String value;

    public ValidacionLetrasConEspacio(String value) {
        this.value = value;
    }

    @Override
    public Error exec(){

        if(super.validar(value,patron)) return null;
            else return Error.ERROR_LETTER_SPACE;
    }

}

