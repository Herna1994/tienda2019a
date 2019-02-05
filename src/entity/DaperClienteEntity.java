package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "tienda2019vista")
public class DaperClienteEntity {

    private String nifCliente;
    private String apellidosCliente;
    private String nombreCliente;
    private String domicilioCliente;
    private String codigoPostalCliente;
    private String fechaNacimiento;
    private String telefonoCliente;
    private String movilCliente;
    private String sexoCliente;
    private String emailCliente;
    private String imagenCliente;



    @Basic
    @Column(name = "NifCliente")
    public String getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }

    @Basic
    @Column(name = "ApellidosCliente")
    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    @Basic
    @Column(name = "NombreCliente")
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Basic
    @Column(name = "CodigoPostalCliente")
    public String getCodigoPostalCliente() {
        return codigoPostalCliente;
    }

    public void setCodigoPostalCliente(String codigoPostalCliente) {
        this.codigoPostalCliente = codigoPostalCliente;
    }

    @Basic
    @Column(name = "DomicilioCliente")
    public String getDomicilioCliente() {
        return domicilioCliente;
    }

    public void setDomicilioCliente(String domicilioCliente) {
        this.domicilioCliente = domicilioCliente;
    }

    @Basic
    @Column(name = "FechaNacimiento")
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Basic
    @Column(name = "TelefonoCliente")
    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    @Basic
    @Column(name = "MovilCliente")
    public String getMovilCliente() {
        return movilCliente;
    }

    public void setMovilCliente(String movilCliente) {
        this.movilCliente = movilCliente;
    }

    @Basic
    @Column(name = "SexoCliente")
    public String getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    @Basic
    @Column(name = "EmailCliente")
    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    @Basic
    @Column(name = "ImagenCliente")
    public String getImagenCliente() {
        return imagenCliente;
    }

    public void setImagenCliente(String imagenCliente) {
        this.imagenCliente = imagenCliente;
    }




    @Override
    public int hashCode() {
        return Objects.hash( nifCliente, apellidosCliente, nombreCliente, domicilioCliente, fechaNacimiento, telefonoCliente, movilCliente, sexoCliente, emailCliente, imagenCliente);
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
