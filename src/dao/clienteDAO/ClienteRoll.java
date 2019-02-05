package dao.clienteDAO;

import com.mysql.jdbc.CallableStatement;
import dao.poolConexion.ClienteMySqlConnectionPool;
import entity.*;
import reflexion.RsTransferArraylist;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;

public class ClienteRoll {

    private final  String usuario = "root";

    private final  String pass = "";

    private final int conexionesIniciales = 3;

    private final int conexionesMaximas = 5;

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }

    ClienteMySqlConnectionPool clienteConnectionPool = null;

      public  ClienteRoll() throws SQLException, ClassNotFoundException {

        clienteConnectionPool = new ClienteMySqlConnectionPool(usuario, pass, conexionesIniciales, conexionesMaximas);
        clienteConnectionPool.useConnection(); // Se crea el pool
    }

     //    ----------------   ver anteriores

      public Boolean deleteClient (String nif) throws SQLException {

        CallableStatement cstmt = null;
        try {
            cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call delete_client(?, ?)}");
            try {
                cstmt.setString(1, nif);
                cstmt.registerOutParameter(2, Types.BOOLEAN);
                cstmt.execute();

            } finally {
                if (cstmt != null) {
                    cstmt.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cstmt.getBoolean(2);
    }

      public String getClaveBloqueo(String nif) throws SQLException {
        try{
            CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call get_clave_bloqueo(?, ?)}");
            try{
                cstmt.setString(1,nif);
                cstmt.registerOutParameter(2, Types.VARCHAR);
                cstmt.execute();
                return  cstmt.getString(2);
            }
            finally {
                if (cstmt != null) {
                    cstmt.close();
                }
            }
        }
        catch (Exception ignore) {
        }

        return "null";

    }

      public DaperClienteEntity getCliente(String dni) {

        String clase = DaperClienteEntity.class.getName();
        try {
            CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call get_daper_cliente(?)}");
            cstmt.setString(1, dni);
            return (DaperClienteEntity) new RsTransferArraylist().getGenericObject(cstmt, clase);
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

      public ResultSet getCursor(String sql) throws SQLException {

        ResultSet cursor = clienteConnectionPool.getCursor(sql);
        return cursor;
    }

      public String getEmailClient(String nif) throws SQLException {
        try{
            CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call get_email_client(?, ?)}");
            try{
                cstmt.setString(1,nif);
                cstmt.registerOutParameter(2, Types.VARCHAR);
                cstmt.execute();
                return  cstmt.getString(2);
            }
            finally {
                if (cstmt != null) {
                    cstmt.close();
                }
            }
        }
        catch (Exception ignore) {
        }

        return "null";

    }

      public ArrayList<?> getListaClientes() {

        String clase = ClienteEntity.class.getName();

        try {

            return new RsTransferArraylist().getListGenericObject((CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call getListaClientes()}"), clase);

        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

      public String get_nif_login(String user, String password) throws SQLException, ClassNotFoundException {

          System.out.println("Mi conexión" + clienteConnectionPool.getConnection());

        try{
            CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call get_nif_login(?, ?, ?)}");
            try{
                cstmt.setString(1,user);
                cstmt.setString(2,password);
                cstmt.registerOutParameter(3, Types.VARCHAR);
                cstmt.execute();
                return  cstmt.getString(3);
            }
            finally {
                if (cstmt != null) {
                    cstmt.close();
                }
            }
        }
        catch (Exception ignore) {
        }

        return "error:ClienteRoll.get_nif_login()";
    }

      public int insertUpdateDelete(String sql) throws SQLException {

        return clienteConnectionPool.insertUpdateDelete(sql);
    }

      public boolean lockedClient(String user, String clave) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call locked_client( ?, ?, ?)}");
        cstmt.setString(1,user);
        cstmt.setString(2,clave);
        cstmt.registerOutParameter(3, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(3);
    }

      public boolean update_client_daper (DaperClienteEntity cliente, String usuario) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call update_client_daper( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        cstmt.setString(1,usuario);
        System.out.println("usuarioCliente ROLL:"+ usuario);
        cstmt.setString(2,cliente.getNifCliente());
        cstmt.setString(3,cliente.getApellidosCliente());
        cstmt.setString(4,cliente.getNombreCliente());
        cstmt.setString(5,cliente.getCodigoPostalCliente());
        cstmt.setString(6,cliente.getDomicilioCliente());
        System.out.println("domicilioCliente ROLL:"+ cliente.getDomicilioCliente());
        cstmt.setString(7,cliente.getFechaNacimiento());

        cstmt.setString(8,cliente.getTelefonoCliente());
        cstmt.setString(9,cliente.getMovilCliente());
        cstmt.setString(10,cliente.getSexoCliente());
        cstmt.setString(11,cliente.getEmailCliente());
        cstmt.registerOutParameter(12, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(12);
    }

      public boolean  update_client_login(LoginClienteHarnina cliente) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call update_client_login( ?, ?, ?, ?)}");
        cstmt.setString(3,cliente.getNifCliente());
        cstmt.setString(1,cliente.getUsuarioCliente());
        cstmt.setString(2,cliente.getPasswordCliente());
        cstmt.registerOutParameter(4, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(4);
    }

// ---------------- version    2  0  1  9   -----------------------

    public int getIdLogin(String user, String password){
        try{
            CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call check_login(?, ?, ?)}");
            try{
                cstmt.setString(1,user);
                cstmt.setString(2,password);
                cstmt.registerOutParameter(3, Types.INTEGER);
                cstmt.execute();
                return  cstmt.getInt(3);
            }
            finally {
                if (cstmt != null) {
                    cstmt.close();
                }
            }
        }
        catch (Exception ignore) {
        }

        return 0;
    }

    public int  add_client(UserEntity userEntity, ClientEntity clientEntity) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call add_client(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        cstmt.setString(1,userEntity.getNif());
        cstmt.setString(2,userEntity.getPostalCode());
        cstmt.setString(3,userEntity.getAddress());
        cstmt.setString(4,userEntity.getPhone());
        cstmt.setString(5,userEntity.getMobile());
        cstmt.setString(6,userEntity.getEmail());
        cstmt.setString(7,userEntity.getUser());
        cstmt.setString(8,userEntity.getPassword());
        cstmt.setString(9,clientEntity.getLastname());
        cstmt.setString(10,clientEntity.getFirstname());
        cstmt.setString(11,clientEntity.getBirthdate());
        cstmt.setString(12,clientEntity.getSex());
        cstmt.registerOutParameter(13, Types.INTEGER);
        cstmt.execute();
        return  cstmt.getInt(13);
    }

}

