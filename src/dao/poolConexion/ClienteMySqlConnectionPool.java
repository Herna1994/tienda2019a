package dao.poolConexion;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteMySqlConnectionPool {
    private  final String url = "jdbc:mysql://localhost/tienda2019vista?useInformationSchema=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private  final String user;
    private  final String password;
    private  final int conexionesIniciales;
    private  final int conexionesMaximas;

   public  ClienteMySqlConnectionPool(String user, String password,int conexionesIniciales,int conexionesMaximas){
       this.user = user;
       this.password = password;
       this.conexionesIniciales = conexionesIniciales;
       this.conexionesMaximas = conexionesMaximas;
   }

    MysqlConnectionPool mysqlConnectionPool = null;

    public void useConnection() throws SQLException, ClassNotFoundException {
        mysqlConnectionPool = new MysqlConnectionPool(url,user,password,conexionesIniciales,conexionesMaximas);
        mysqlConnectionPool.useConnection();
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return   mysqlConnectionPool.getConnection();
    }

    synchronized public int update(String nif, String newUser) throws SQLException {
       String sqlSentence = "UPDATE `login_cliente` SET `usuario`= '" + newUser +"'  WHERE `dni` = '" + nif + "'";
       return  mysqlConnectionPool.executeUpdate(sqlSentence);
    }

    synchronized public int insertUpdateDelete(String sql) throws SQLException {

        return mysqlConnectionPool.executeUpdate(sql);
    }

    synchronized public ResultSet getCursor(String sql) throws SQLException {

        ResultSet cursor = mysqlConnectionPool.executeQuery(sql);
        return cursor;
    }

    synchronized public void iniciarTransaccion() throws SQLException{
        mysqlConnectionPool.iniciarTransaccion();
    }

    synchronized public void aceptarTransaccion() throws SQLException{
        mysqlConnectionPool.aceptarTransaccion();
    }

    synchronized public void cancelarTransaccion() throws SQLException{
        mysqlConnectionPool.cancelarTransaccion();
    }
}
