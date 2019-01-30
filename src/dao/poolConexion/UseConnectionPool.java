package dao.poolConexion;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UseConnectionPool {


    Connection connection = null;

    synchronized public void conectar(String driver,String url, String user,String password, int conexionesIniciales, int conexionesMaximas ) throws SQLException, ClassNotFoundException {

       connection = BasicConnectionPool.create(driver,url,user,password,conexionesIniciales,conexionesMaximas).getConnection();
    }

    synchronized public int executeUpdate(String sqlSentence) throws SQLException {
        if(connection == null) return -1;
        Statement sentence = (Statement) connection.createStatement();
        return sentence.executeUpdate(sqlSentence);
    }

    synchronized public ResultSet executeQuery(String sqlSentence) throws SQLException{
        if(connection == null) return null;
        Statement sentence = (Statement) connection.createStatement();
        return sentence.executeQuery(sqlSentence);
    }

    synchronized public void iniciarTransaccion() throws SQLException{
        commitDefault(false);
    }

    synchronized public void aceptarTransaccion() throws SQLException{
        connection.commit();
        commitDefault(true);
 }

    synchronized public void cancelarTransaccion() throws SQLException{
        connection.rollback();
        commitDefault(true);
    }

    private void commitDefault(Boolean estado) throws SQLException {
        connection.setAutoCommit(estado);
    }
}
