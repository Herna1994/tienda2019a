package error;

public enum Error {

    ERROR_NULL(0,"No hay Error","Lorem Ipsum...", "NoError"),
    ERROR_MISSING(25,"Error Desconocido","","ErrorMissing"),
    ERROR_NOPATTERN(50,"No cumple el patrón","","NoPattern"),
    ERROR_NOTIN(70,"El valor no está en la lista","","NotIn"),
    ERROR_CODIGOPOSTAL_INCORRECTO (100,"Codigo Postal Incorrecto","","CodigoPostalIncorrecto"),
    ERROR_NIF_LENGTH(110,"Cadena distinta a 9 caracteres y/o  el último caracter no es una letra","","NifIncorrecto"),
    ERROR_NIF_8DIGIT_LETTER(112,"8 dígitos entre los 8 primeros caracteres y una letra al final que corresponda para esos digitos","","NifIncorrecto"),
    ERROR_NIE_BAD(114,"NIE Incorrecto","","NieIncorrecto"),
    ERROR_CIF_BAD(116,"CIF Incorrecto","","CifIncorrecto"),
    ERROR_EMAIL_BAD (120,"Email Incorrecto","","EmailIncorrecto"),
    ERROR_EXTENSION_BAD (130,"Extension No válida","","ExtensionInvalida"),
    ERROR_FECHA_BAD (140,"Fecha No válida","","FechaInvalida"),
    ERROR_LETTER_SPACE(150,"Solo puede contener letras y espacio","","LetraSpace"),
    ERROR_LETTER_ONLY(160,"Solo puede contener letras","","LetraNoSpace"),
    ERROR_INTERVALO(170,"Intervalo Incorrecto ","","IntervalBad"),
    ERROR_PASSWORD_BAD(180, "Esta password es Incorrecta", "Lorem Ipsum...", "Passwordbad"),
    ERROR_PHONE_BAD(190, "Telefono Incorrecto", "Lorem Ipsum...", "PhoneBad"),
    ERROR_USUARIO_BAD(200, "Usuario Incorrecto", "Lorem Ipsum...","UsuarioBad"),
    ERROR_DOMICILIO_BAD(210,"Domicilio: solo letras, números y espacio","","DomicilioBad"),
    ERROR_DOCUMENT_BAD(220,"Documento Incorrecto" ,"","DocumentBad");


    private final int id;
    private final String msg;
    private final String descrip;
    private final String className;

    Error(int id, String msg, String descrip, String className) {
        this.id = id;
        this.msg = msg;
        this.descrip = descrip;
        this.className = className;
    }

    public int getId() {
        return this.id;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getDescrip() {
        return this.descrip;
    }

    public String getClassName() {
        return this.className;
    }
}


