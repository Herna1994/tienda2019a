package cliente;

import entity.LoginEntity;
import error.Error;
import validate.ValidacionPassword;
import validate.ValidacionUsuario;

import java.util.HashMap;

public class ComandValidateLogin implements ComandValidate {

    LoginEntity loginEntity = null;

   public ComandValidateLogin(LoginEntity loginEntity){
      this.loginEntity = loginEntity;
   }
    public HashMap<String,Error> getCommands(){

        HashMap<String,Error> errors = new HashMap<>();
        errors.put("user",new ValidacionUsuario(loginEntity.getUsuarioCliente()).exec());
        errors.put("password",new ValidacionPassword(loginEntity.getPasswordCliente()).exec());
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
