package cliente;

import entity.UserEntity;
import error.Error;
import validate.*;

import java.sql.SQLException;
import java.util.HashMap;

public class ComandValidateDataUser implements ComandValidate {
     UserEntity userEntity;

    public ComandValidateDataUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public HashMap<String, Error> getCommands() throws SQLException, ClassNotFoundException {
        HashMap<String,Error> errors = new HashMap<>();

        errors.put("nif",new ValidacionDNINIECIF(this.userEntity.getNif()).exec());
        errors.put("postalCode",new VerificacionCodigoPostal(this.userEntity.getPostalCode()).exec());
        errors.put("address",new ValidarDomicilio(this.userEntity.getAddress()).exec());
        errors.put("phone",new ValidacionTelefonoSpain(this.userEntity.getPhone()).exec());
        errors.put("mobile",new ValidacionTelefonoSpain(this.userEntity.getMobile()).exec());
        errors.put("email",new ValidacionEmail(this.userEntity.getEmail()).exec());
        errors.put("user",new ValidacionUsuario(this.userEntity.getUser()).exec());
        errors.put("password",new ValidacionPassword(this.userEntity.getPassword()).exec());
        System.out.println("fin validar User");
        errors.entrySet().removeIf(entries->entries.getValue() == null);

        return errors;
    }
}
