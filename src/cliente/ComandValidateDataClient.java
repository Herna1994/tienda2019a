package cliente;

import entity.ClientEntity;
import error.Error;
import validate.*;

import java.sql.SQLException;
import java.util.HashMap;

public class ComandValidateDataClient implements ComandValidate {

    ClientEntity clientEntity = null;

    public ComandValidateDataClient(ClientEntity clientEntity){
        this.clientEntity = clientEntity;
    }

    @Override
    public HashMap<String,Error> getCommands() throws SQLException, ClassNotFoundException {
        HashMap<String,Error> errors = new HashMap<>();

        errors.put("firstname",new ValidacionLetrasConEspacio(this.clientEntity.getFirstname()).exec());
        errors.put("firstname",new ValidacionLongitud(this.clientEntity.getFirstname(), 3, 50).exec());
        errors.put("lastname",new ValidacionLetrasConEspacio(this.clientEntity.getLastname()).exec());
        errors.put("lastname",new ValidacionLongitud(this.clientEntity.getLastname(), 10, 100).exec());
        errors.put("birthdate",new ValidacionFecha(this.clientEntity.getBirthdate().toString()).exec());
        errors.put("sex",new ValidacionSexo(this.clientEntity.getSex()).exec());
        errors.entrySet().removeIf(entries->entries.getValue() == null);
        System.out.println("fin validar Client");
        return errors;
    }
}
