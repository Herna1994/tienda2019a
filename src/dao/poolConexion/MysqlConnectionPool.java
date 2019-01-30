package dao.poolConexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnectionPool {
    private  final String url;
    private  final String user;
    private  final String password;
    private final int conexionesIniciales;
    private final int conexionesMaximas;

    private  final String driver = "com.mysql.jdbc.Driver";

    UseConnectionPool useConnectionPool = new UseConnectionPool();

    public MysqlConnectionPool(String url, String user, String password, int conexionesIniciales, int conexionesMaximas) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.conexionesIniciales = conexionesIniciales;
        this.conexionesMaximas = conexionesMaximas;
    }

    public void useConnection() throws SQLException, ClassNotFoundException {

        useConnectionPool.conectar(driver, url, user, password,conexionesIniciales,conexionesMaximas);
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return   useConnectionPool.connection;
    }

    synchronized public int executeUpdate(String sqlSentence) throws SQLException {
       return  useConnectionPool.executeUpdate(sqlSentence);
    }

    synchronized public ResultSet executeQuery(String sqlSentence) throws SQLException{

        return useConnectionPool.executeQuery(sqlSentence);
    }

    synchronized public void iniciarTransaccion() throws SQLException{
        useConnectionPool.connection.setAutoCommit(false);
    }

    synchronized public void aceptarTransaccion() throws SQLException{
        useConnectionPool.connection.commit();
        useConnectionPool.connection.setAutoCommit(true);
    }

    synchronized public void cancelarTransaccion() throws SQLException{
        useConnectionPool.connection.rollback();
        useConnectionPool.connection.setAutoCommit(true);
    }
}
