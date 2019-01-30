package validate;


import error.Error;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidacionImagenNombre extends ValidacionMultiValidation implements IValidacionList{

    private String value;
    private String [] lista = {"jpg" , "png"};

    public ValidacionImagenNombre(String value) {
        this.value = value;
    }


    public ArrayList<Error> exec() throws SQLException, ClassNotFoundException {

        assertTrue(value.contains("."));

        String nombreImagen = new String(value.substring(0 , value.lastIndexOf('.')));

        String extension = new String(value.substring(value.lastIndexOf('.') + 1));

        IValidacion[] validadores = {new ValidacionExtensionFile(extension,this.lista) ,new ValidacionLetrasSinEspacio(nombreImagen)};

        return super.validar(validadores);
    }
}
