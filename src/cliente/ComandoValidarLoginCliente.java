package cliente;

import entity.LoginClienteHarnina;
import error.Error;
import validate.ValidacionPassword;
import validate.ValidacionUsuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ComandoValidarLoginCliente implements ComandoValidarCliente {

    LoginClienteHarnina loginClienteHarnina = null;

   public ComandoValidarLoginCliente(LoginClienteHarnina loginClienteHarnina){
      this.loginClienteHarnina = loginClienteHarnina;
   }
    public HashMap<String,Error> getCommands(){

        HashMap<String,Error> errors = new HashMap<>();
        errors.put("user",new ValidacionUsuario(loginClienteHarnina.getUsuarioCliente()).exec());
        errors.put("password",new ValidacionPassword(loginClienteHarnina.getPasswordCliente()).exec());
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
