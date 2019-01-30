package validate;

import error.Error;

public class ValidacionExtensionFile extends ValidacionListOfValues implements IValidacion {


    private String value;
    private String[] lista_extension;

    public ValidacionExtensionFile(String value, String[] listaExtension) {
        this.value = value;
        this.lista_extension = listaExtension;
    }

    @Override
    public Error exec() {
        if (!super.validar(value, lista_extension)) {
            return Error.ERROR_EXTENSION_BAD;
        }
        return null;


    }


}
