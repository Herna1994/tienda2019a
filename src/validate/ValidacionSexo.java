package validate;

import error.Error;

public class ValidacionSexo extends ValidacionListOfValues implements IValidacion {
    private static final String [] valores  = {"m" , "f"};

    private static final String error = "El  valor no esta en la lista";

    private String value;

    public ValidacionSexo(String value) {
        this.value = value;
    }

    @Override
    public Error exec(){
        if (super.validar(value,valores)) return null;
           else return Error.ERROR_NOTIN;    }



}
