package validate;

import error.Error;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValidacionMultiValidation {

    private String error;

    ArrayList<Error> listaErrores = new ArrayList<>();

    protected ArrayList<Error> validar(IValidacion validadores[]) throws SQLException, ClassNotFoundException {

        for (int i = 0; i < validadores.length; i++) {

            listaErrores.add(validadores[i].exec());

        }
        return listaErrores;
    }
    public static ArrayList<Error> validar(List<IValidacion> validador ) throws SQLException, ClassNotFoundException {

        ArrayList<Error> errors = new ArrayList<>();

        for(IValidacion vali:validador){

            errors.add(vali.exec());

        }
        return errors;
    }

}




