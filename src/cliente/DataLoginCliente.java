package cliente;

import entity.LoginEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

public  class DataLoginCliente {

    LoginEntity loginClienteEntity = new LoginEntity();
    HttpSession session;



    public DataLoginCliente(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {

        session = request.getSession();

       // new RequestTransferSession().guardarDatosSesion(request,session);

       // new SessionTransferObject(session,loginClienteEntity);
    }

    public LoginEntity getLoginClienteEntity() {
        return loginClienteEntity;
    }

    public void setUsuarioCliente(String usuarioCliente) {

        loginClienteEntity.setUsuarioCliente(usuarioCliente);
    }

    public void setNifCliente(String nifCliente) {

       // loginClienteEntity.setNifCliente(nifCliente);
    }

    public String getNifCliente() {
        return ""; //loginClienteEntity.getNifCliente();
    }

    public String getUsuarioCliente() {
        return loginClienteEntity.getUsuarioCliente();
    }

    public String getPasswordCliente() {
        return loginClienteEntity.getPasswordCliente();
    }

   // Nuevo para contador de intentos

    private void setSessionAttribute(String s, Object o) {
        session.setAttribute(s, o);
    }

    private void reiniciarIntentos() {

        this.setIntento(0);
    }

    private void setIntento(int intento) {

        this.setSessionAttribute("intento", intento);
    }

    public int getIntento() {

        return (Integer) session.getAttribute("intento");
    }

    public void incrementarIntento() {

        this.setIntento( this.getIntento() + 1);
    }

    private int getMaxIntento() {

        return (Integer) session.getAttribute("maxIntento");
    }

    public boolean  disponibilidadIntento(){
        return (getIntento() < getMaxIntento());
    }

}
