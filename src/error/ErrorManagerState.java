package error;

import cliente.DataLoginCliente;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ErrorManagerState {

    protected Error error;

    void setEstado(Error error) {
        this.error = error;
    }

    public abstract void consecuencia(DataLoginCliente dataBundleCliente, HttpServletResponse response) throws IOException;
}
