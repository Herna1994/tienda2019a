package dao.poolConexion;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2ConnectionPool {
    private   String url = null;
    private   String user = null;
    private   String password = null;
    private  final String driver = "org.h2.Driver";
    private final int conexionesIniciales;
    private final int conexionesMaximas;

    UseConnectionPool useConnectionPool = new UseConnectionPool();

    public H2ConnectionPool(String url, String user, String password, int conexionesIniciales, int conexionesMaximas) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.conexionesIniciales = conexionesIniciales;
        this.conexionesMaximas = conexionesMaximas;
    }

    public void useConnection() throws SQLException, ClassNotFoundException {

        useConnectionPool.conectar(driver, url, user, password,conexionesIniciales,conexionesMaximas);
    }

    synchronized public int executeUpdate(String sqlSentence) throws SQLException {
        return  useConnectionPool.executeUpdate(sqlSentence);
    }

    synchronized public ResultSet executeQuery(String sqlSentence) throws SQLException{
        if(useConnectionPool.connection == null) return null;
        Statement sentence = (Statement) useConnectionPool.connection.createStatement();
        return sentence.executeQuery(sqlSentence);
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
