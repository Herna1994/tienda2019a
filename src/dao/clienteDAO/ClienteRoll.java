package dao.clienteDAO;

import dao.poolConexion.ClienteMySqlConnectionPool;
import entity.*;
import reflection.RsTransferArrayList;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.ParseException;

public class ClienteRoll {

    private final String usuario = "root";

    private final String pass = "";

    private final int conexionesIniciales = 3;

    private final int conexionesMaximas = 5;

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }

    ClienteMySqlConnectionPool clienteConnectionPool = null;

    public ClienteRoll() throws SQLException, ClassNotFoundException {

        clienteConnectionPool = new ClienteMySqlConnectionPool(usuario, pass, conexionesIniciales, conexionesMaximas);
        clienteConnectionPool.useConnection(); // Se crea el pool
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

    public boolean lockedClient(int id, String clave) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call locked_client( ?, ?, ?)}");
        cstmt.setInt(1,id);
        cstmt.setString(2,clave);
        cstmt.registerOutParameter(3, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(3);
    }

    public String getEmailClient(int id) throws SQLException {
        try{
            CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call get_email_client(?, ?)}");
            try{
                cstmt.setInt(1,id);
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

    public String getClaveBloqueo(int id) throws SQLException {
        try{
            CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call get_clave_bloqueo(?, ?)}");
            try{
                cstmt.setInt(1,id);
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

    public boolean 	unlock_user( String claveBloqueo, String email) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call unlock_user( ?, ?, ?)}");
        cstmt.setString(1,claveBloqueo);
        cstmt.setString(2,email);
        cstmt.registerOutParameter(3, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(3);
    }

    public boolean  update_login(LoginEntity login) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call update_login( ?, ?, ?, ?)}");
        cstmt.setInt(1, login.getIdCliente());
        cstmt.setString(2,login.getUsuarioCliente());
        cstmt.setString(3,login.getPasswordCliente());
        cstmt.registerOutParameter(4, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(4);
    }

    public boolean 	can_Is_New_User( LoginEntity login) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call canIsNewUser( ?, ?, ?)}");
        cstmt.setString(1,login.getUsuarioCliente());
        cstmt.setInt(2,login.getIdCliente());
        cstmt.registerOutParameter(3, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(3);
    }

    public boolean delete_user(int id)throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call delete_user(?,  ?)}");
        cstmt.setInt(1,id);
        cstmt.registerOutParameter(2, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(2);

    }

    public ClientDataEntity getDataClient(int id) {

        String clase = ClientDataEntity.class.getName();
        try {
            Connection conexion = clienteConnectionPool.getConnection();
            CallableStatement cstmt = conexion.prepareCall("{call get_data_client(?)}");
            cstmt.setInt(1, id);
            return (ClientDataEntity) new RsTransferArrayList().getGenericObject(cstmt, clase);
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ClientDataEntity getDataClient2(int id) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        ClientDataEntity clientDataEntity = new ClientDataEntity();
        String sql = "SELECT * FROM `client_data` WHERE `id` =" + id;
        System.out.println(sql);
         rs =  clienteConnectionPool.getCursor(sql);
        while(rs.next()) {
            clientDataEntity.setId(rs.getInt("Id"));
            clientDataEntity.setFirstname(rs.getString("firstname"));
            clientDataEntity.setLastname(rs.getString("lastname"));
            clientDataEntity.setBirthdate(rs.getDate("birthdate"));
            clientDataEntity.setSex(rs.getString("sex"));
            clientDataEntity.setNif(rs.getString("nif"));
            clientDataEntity.setPostalCode(rs.getString("postalCode"));
            clientDataEntity.setAddress(rs.getString("address"));
            clientDataEntity.setPhone(rs.getString("phone"));
            clientDataEntity.setMobile(rs.getString("mobile"));
            clientDataEntity.setEmail(rs.getString("email"));
        }
        return clientDataEntity;
    }

    public boolean updateDataClient(ClientDataEntity clientDataEntity) throws SQLException, ClassNotFoundException {

        CallableStatement cstmt = (CallableStatement) clienteConnectionPool.getConnection().prepareCall("{call updateDataClient( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        cstmt.setInt(1, clientDataEntity.getId());
        cstmt.setString(2,clientDataEntity.getFirstname());
        cstmt.setString(3,clientDataEntity.getLastname());
        cstmt.setString(4,clientDataEntity.getSbirthdate());
        cstmt.setString(5,clientDataEntity.getSex());
        cstmt.setString(6,clientDataEntity.getNif());
        cstmt.setString(7,clientDataEntity.getPostalCode());
        cstmt.setString(8,clientDataEntity.getAddress());
        cstmt.setString(9,clientDataEntity.getPhone());
        cstmt.setString(10,clientDataEntity.getMobile());
        cstmt.setString(11,clientDataEntity.getEmail());

        cstmt.registerOutParameter(12, Types.BOOLEAN);
        cstmt.execute();
        return  cstmt.getBoolean(12);

    }
}

