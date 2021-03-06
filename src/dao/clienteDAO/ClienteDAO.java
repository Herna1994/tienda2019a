package dao.clienteDAO;

import entity.*;
import java.sql.SQLException;
import java.util.UUID;

public class ClienteDAO {

    ClienteRoll clienteRoll = new ClienteRoll();

    public ClienteDAO() throws SQLException, ClassNotFoundException {
    }
/*
    public int add_cliente(ClienteEntity cliente) {
       String sql = "INSERT INTO `cliente`(`NifCliente`, `ApellidosCliente`, `NombreCliente`, `CodigoPostalCliente`, `DomicilioCliente`, `FechaNacimiento`, `TelefonoCliente`, `MovilCliente`, `SexoCliente`, `EmailCliente`, `ImagenCliente`, `UsuarioCliente`, `PasswordCliente`) " + " VALUES ('" + cliente.getNifCliente() + "','" +
               cliente.getApellidosCliente()+ "','" +
               cliente.getNombreCliente()+ "','" +
               cliente.getCodigoPostalCliente()+ "','" +
               cliente.getDomicilioCliente()+ "','" +
               cliente.getFechaNacimiento()+ "','" +
               cliente.getTelefonoCliente()+ "','" +
               cliente.getMovilCliente()+ "','" +
               cliente.getSexoCliente()+ "','" +
               cliente.getEmailCliente()+ "','" +
               cliente.getImagenCliente()+ "','" +
               cliente.getUsuarioCliente()+ "','" +
               cliente.getPasswordCliente()+ "')";

       System.out.println(sql);
        try {

            return clienteRoll.insertUpdateDelete(sql);

        } catch (SQLException e) {

            return 0;
        }
    }



    public Boolean deleteClient(String nif) throws SQLException {
        return clienteRoll.deleteClient(nif);
    }



    public DaperClienteEntity getCliente(String nif){
        return (DaperClienteEntity) clienteRoll.getCliente(nif);
    }



    public ArrayList getListaClientes(){
        return clienteRoll.getListaClientes();
    }

    public String  get_nif_login(LoginEntity clientloginEntity) {

      try
       {
           return clienteRoll.get_nif_login(clientloginEntity.getUsuarioCliente(),clientloginEntity.getPasswordCliente());
       }
       catch (Exception ignore)
       {
           // do something appropriate with the exception, *at least*:
           //e.printStackTrace();
       }
       return "null";
    }



    public boolean update_client_daper(DaperClienteEntity cliente, String usuario) throws SQLException, ClassNotFoundException {

        return clienteRoll.update_client_daper(cliente,usuario);
    }


*/

// -------------------- version  2  0  1  9   ---------------------

    public int getIdLogin(String user, String password){

        return clienteRoll.getIdLogin(user,password);
    }

    public int add_client (UserEntity userEntity, ClientEntity clientEntity) {
        try {
            return clienteRoll.add_client(userEntity, clientEntity);
        } catch (SQLException e) {
            System.out.println("DAO false");
            return 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean locked_client(int id) throws SQLException, ClassNotFoundException {
        String uuid = UUID.randomUUID().toString();
        String clave = uuid.substring(0, Math.min(uuid.length(), 50));
        return clienteRoll.lockedClient(id, clave);
    }

    public String getEmailClient(int id) throws SQLException {
        return clienteRoll.getEmailClient(id);
    }

    public String getClaveBloqueo(int id) throws SQLException {
        return clienteRoll.getClaveBloqueo(id);
    }

    public boolean 	unlock_user( String claveBloqueo, String email) throws SQLException, ClassNotFoundException {
        return clienteRoll.unlock_user(claveBloqueo, email);
    }

    public boolean update_login(LoginEntity login) throws SQLException, ClassNotFoundException {
         return clienteRoll.update_login(login);
    }

    public boolean 	can_Is_New_User( LoginEntity login)throws SQLException, ClassNotFoundException {
        return clienteRoll.can_Is_New_User(login);
    }

    public boolean delete_user(int id) throws SQLException, ClassNotFoundException {
        return clienteRoll.delete_user(id);
    }

    public ClientDataEntity getDataClient(int id) {

        return clienteRoll.getDataClient(id);
    }
    public ClientDataEntity getDataClient2(int id) throws SQLException, ClassNotFoundException {

        return clienteRoll.getDataClient2(id);
    }
    public boolean updateDataClient(ClientDataEntity clientDataEntity)throws SQLException, ClassNotFoundException {

        return clienteRoll.updateDataClient(clientDataEntity);
    }
}
