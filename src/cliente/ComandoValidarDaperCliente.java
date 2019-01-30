package cliente;

import entity.DaperClienteEntity;
import error.Error;
import validate.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ComandoValidarDaperCliente implements ComandoValidarCliente {

    DaperClienteEntity daperClienteEntity = null;

    public ComandoValidarDaperCliente(DaperClienteEntity daperClienteEntity){
        this.daperClienteEntity = daperClienteEntity;
    }

    @Override
    public HashMap<String,Error> getCommands() throws SQLException, ClassNotFoundException {
        HashMap<String,Error> errors = new HashMap<>();

        errors.put("nif",new ValidacionDNINIECIF(daperClienteEntity.getNifCliente()).exec());
        errors.put("firstname",new ValidacionLetrasConEspacio(daperClienteEntity.getNombreCliente()).exec());
        errors.put("firstname",new ValidacionLongitud(daperClienteEntity.getNombreCliente(), 3, 50).exec());
        errors.put("lastname",new ValidacionLetrasConEspacio(daperClienteEntity.getApellidosCliente()).exec());
        errors.put("lastname",new ValidacionLongitud(daperClienteEntity.getApellidosCliente(), 15, 100).exec());
        errors.put("postalCode",new ValidacionCodigoPostal(daperClienteEntity.getCodigoPostalCliente()).exec());
        errors.put("address",new ValidarDomicilio(daperClienteEntity.getDomicilioCliente()).exec());
        errors.put("address",new ValidacionLongitud(daperClienteEntity.getDomicilioCliente(), 2, 100).exec());
        errors.put("phone",new ValidacionTelefonoSpain(daperClienteEntity.getTelefonoCliente()).exec());
        errors.put("mobile",new ValidacionTelefonoSpain(daperClienteEntity.getMovilCliente()).exec());
        errors.put("birthdate",new ValidacionFecha(daperClienteEntity.getFechaNacimiento().toString()).exec());
        errors.put("sex",new ValidacionSexo(daperClienteEntity.getSexoCliente()).exec());
        errors.put("email",new ValidacionEmail(daperClienteEntity.getEmailCliente()).exec());
        errors.put("postalCode",new VerificacionCodigoPostal(daperClienteEntity.getCodigoPostalCliente()).exec());
        //errors.removeIf(Objects::isNull);
        errors.entrySet().removeIf(entries->entries.getValue() == null);
        /*
        for(Map.Entry<String, Error> entry : errors.entrySet()) {
            if(entry.getValue()== null) {
                errors.remove(entry.getKey());
            }
        }*/
        return errors;
    }
}
