package validate;

import dao.clienteDAO.ClienteRoll;
import dao.cp.CPDAO;
import error.Error;

import java.sql.SQLException;

public class VerificacionCodigoPostal implements IValidacion {

    private String value;

    public VerificacionCodigoPostal(String value) {
        this.value = value;
    }

    @Override
    public Error exec() throws SQLException, ClassNotFoundException {

        ClienteRoll clienteRoll = new ClienteRoll();

        CPDAO cpdao = new CPDAO(clienteRoll.getUsuario(), clienteRoll.getPass());

        try {
            if (!cpdao.check_cp(value)) {

                return Error.ERROR_CODIGOPOSTAL_INCORRECTO;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Error.ERROR_NULL;
    }
}
