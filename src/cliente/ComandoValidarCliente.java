package cliente;

import error.Error;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface ComandoValidarCliente {
    HashMap<String,Error> getCommands() throws SQLException, ClassNotFoundException;
}
