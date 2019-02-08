package entity;


public class LoginClienteHarnina {

    private int idCliente;
    private String usuarioCliente;
    private String passwordCliente;

    public LoginClienteHarnina() {

    }

    public LoginClienteHarnina(int idCliente, String usuarioCliente, String passwordCliente) {
        this.idCliente = idCliente;
        this.usuarioCliente = usuarioCliente;
        this.passwordCliente = passwordCliente;
    }

    public LoginClienteHarnina(String usuario, String password) {
        this.usuarioCliente = usuario;
        this.passwordCliente = password;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(String usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public String getPasswordCliente() {
        return passwordCliente;
    }

    public void setPasswordCliente(String passwordCliente) {
        this.passwordCliente = passwordCliente;
    }
}

