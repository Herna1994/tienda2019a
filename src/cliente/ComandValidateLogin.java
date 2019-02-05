package cliente;

import entity.LoginClienteHarnina;
import error.Error;
import validate.ValidacionPassword;
import validate.ValidacionUsuario;

import java.util.HashMap;

public class ComandValidateLogin implements ComandValidate {

    LoginClienteHarnina loginClienteHarnina = null;

   public ComandValidateLogin(LoginClienteHarnina loginClienteHarnina){
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
