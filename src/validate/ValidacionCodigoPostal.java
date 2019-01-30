package validate;


import error.Error;

public class ValidacionCodigoPostal  extends ValidacionRegularExpression implements IValidacion {

    private static final String patron ="^(?:0[1-9][0-9]{3}|[1-4][0-9]{4}|5[0-2][0-9]{3})$";

    private String value;

    public ValidacionCodigoPostal(String value){

        this.value = value;

    }
    @Override
    public Error exec(){
        if(!super.validar(value,patron)) return Error.ERROR_CODIGOPOSTAL_INCORRECTO;
       return null;
    }


}
