package validate;

import error.Error;

public class ValidacionLongitud implements IValidacion {

    private String cadena;

    private int minimo;

    private int maximo;

   public ValidacionLongitud(String cadena,int minimo,int maximo ){

        assert maximo >= minimo;

        this.cadena = cadena;

        this.minimo = minimo;

        this.maximo = maximo;
    }


    @Override
    public Error exec() {

       if (this.minimo <= this.cadena.length() && this.cadena.length()<= this.maximo){

           return null;
       }
        return Error.ERROR_INTERVALO;
    }


}
