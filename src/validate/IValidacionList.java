package validate;

import error.Error;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IValidacionList {
    public abstract ArrayList<Error> exec() throws SQLException, ClassNotFoundException;
}
