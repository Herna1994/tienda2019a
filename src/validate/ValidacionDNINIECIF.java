package validate;

import error.Error;

/**
 * Clase que calcula o valida un documento de identificación del reino de España. (DNI,NIE,CIF).
 *
 * @author yo
 * El antecedente del NIF es el CIF, utilizado en personas jurídicas
 */
public class ValidacionDNINIECIF implements IValidacion {

    private String documento;

    private static final String mensajeError = "Documento Incorrecto";


    public ValidacionDNINIECIF(String documento) {
        this.documento = documento;
    }

    @Override
    public Error exec() {
        ValidacionDNINIF validacionDNINIF = new ValidacionDNINIF(documento);
        if (validacionDNINIF.exec() != null) {
            ValidacionNIE validacionNIE = new ValidacionNIE(documento);
            if (validacionNIE.exec() != null) {
                ValidacionNIFCIF validacionNIFCIF = new ValidacionNIFCIF(documento);
                if (validacionNIFCIF.exec() != null) {
                    return Error.ERROR_NIF_8DIGIT_LETTER;
                }
            }
        }
        return null;
    }


}
