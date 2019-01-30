package validate;

import error.Error;

public class ValidacionUsuario extends ValidacionRegularExpression implements IValidacion {

    // Los usuarios tienen 7 dígitos.
    //El primer dígito es una letra.
    //Los dígitos 2 y 3 pueden ser letras o números.
    //Los 4 últimos son números.

    private static final String patron = "^[A-Za-z]{1}([A-Za-z]{2}|[0-9]{2}|[A-Za-z][0-9]{2})([0-9]{4})$";

    private static final String mensajeError = "Usuario Incorrecto";

    private String value;

    public ValidacionUsuario(String value){

        this.value = value;

    }
    @Override
    public Error exec(){
        if (!super.validar(value,patron)){
            return  Error.ERROR_USUARIO_BAD;
        }

        return null;
    }


}
