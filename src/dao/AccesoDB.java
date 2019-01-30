package dao;
import java.sql.*;

public class AccesoDB {
    private static AccesoDB miConexion = null;
    private Connection conexion = null;
    private String driver = null;
    private String dbName= null;
    private String user = null;
    private String password= null;

    synchronized public static AccesoDB getMiConexion(){

        if (miConexion == null) miConexion = new AccesoDB();
        return miConexion;
    }

    synchronized public void conectar(String driver, String dbName,String user,String password )
            throws SQLException, ClassNotFoundException{
        if(conexion != null) {
            if (!this.driver.equals(driver) ||
                    !this.dbName.equals(dbName) ||
                    (this.user != null && !this.user.equals(user)) ||
                    (this.password != null && !this.password.equals(password))

            ) {
                conexion.close();
            } else return;
        }

        Class.forName(driver);

        if(user == null || password == null) conexion = (Connection) DriverManager.getConnection(dbName);
            else conexion = (Connection) DriverManager.getConnection(dbName,user,password);
        System.out.println("Conectado a BD " + dbName);
        this.driver = driver;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }
    synchronized public void cerrar() throws SQLException {
        if (conexion != null) conexion.close();
    }

    synchronized public ResultSet executeQuery(String sqlSentence) throws SQLException{
        if(conexion == null) return null;
        Statement sentence = (Statement) conexion.createStatement();
        return sentence.executeQuery(sqlSentence);
    }

    synchronized public int executeUpdate(String sqlSentence) throws SQLException{
        if(conexion == null) return -1;
        Statement sentence = (Statement) conexion.createStatement();
        return sentence.executeUpdate(sqlSentence);
    }


    public Connection getConexion(){
        return this.conexion;

    }
}
