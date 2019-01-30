package cliente;

import entity.DaperClienteEntity;
import reflexion.SessionTransferObject;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class DataPersonCliente extends DataLoginCliente {

    private DaperClienteEntity daperClienteEntity = new DaperClienteEntity();

    public DataPersonCliente(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        super(request);

        new SessionTransferObject(session,daperClienteEntity);
    }

    public DaperClienteEntity getDaperClienteEntity() {

        return daperClienteEntity;
    }



    public String getNifCliente() {
        return daperClienteEntity.getNifCliente();
    }


    public String getApellidosCliente() {
        return daperClienteEntity.getApellidosCliente();
    }


    public String getNombreCliente() {
        return daperClienteEntity.getNombreCliente();
    }


    public String getDomicilioCliente() {
        return daperClienteEntity.getDomicilioCliente();
    }


    public String getFechaNacimiento() {
        return daperClienteEntity.getFechaNacimiento();
    }


    public String getTelefonoCliente() {
        return daperClienteEntity.getTelefonoCliente();
    }


    public String getMovilCliente() {
        return daperClienteEntity.getMovilCliente();
    }


    public String getSexoCliente() {
        return daperClienteEntity.getSexoCliente();
    }


    public String getEmailCliente() {
        return daperClienteEntity.getEmailCliente();
    }


    public String getImagenCliente() {
        return daperClienteEntity.getImagenCliente();
    }

   public void setImagenCliente(String imagenCliente) {
       daperClienteEntity.setImagenCliente(imagenCliente);
    }


    public String getCodigoPostalCliente() {
        return daperClienteEntity.getCodigoPostalCliente();
    }

}
