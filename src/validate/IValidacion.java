package validate;

import error.Error;

import java.sql.SQLException;

public interface IValidacion {
    Error exec() throws SQLException, ClassNotFoundException; //si es 0 es que no hay error
}


