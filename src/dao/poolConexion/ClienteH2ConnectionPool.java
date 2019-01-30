package dao.poolConexion;

import java.sql.SQLException;

public class ClienteH2ConnectionPool {
    private  final String url = "jdbc:h2:~/tienda189"; //jdbc:h2:mem:db
    private  final String user = "SA";
    private  final String password = "";
    private  final int conexionesIniciales;
    private  final int conexionesMaximas;


    H2ConnectionPool  h2ConnectionPool = null;

   public ClienteH2ConnectionPool (int conexionesIniciales, int conexionesMaximas){
       this.conexionesIniciales = conexionesIniciales;
       this.conexionesMaximas = conexionesMaximas;

   }

   public void useConnection() throws SQLException, ClassNotFoundException {

        h2ConnectionPool = new H2ConnectionPool(url,user,password,conexionesIniciales,conexionesMaximas);
        h2ConnectionPool.useConnection();
    }

   synchronized public void createTables(){

        try {
            h2ConnectionPool.executeUpdate("create table CLIENTE (ID VARCHAR(3), NAME VARCHAR(45), DESIGNATION VARCHAR(15))");
            h2ConnectionPool.executeUpdate("create table CLIENTE_ADDRESS (ID VARCHAR(3), EMP_ID VARCHAR(36), ADDRESS VARCHAR(45))");
            System.out.println("Tables Created!!!");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

   synchronized public void addTables() throws SQLException {

      h2ConnectionPool.iniciarTransaccion();

      String newEmployeeSQL = "INSERT INTO CLIENTE(ID, NAME, DESIGNATION) VALUES ('4','Luis','Programador');";

      String newEmployeeAddrSQL = "INSERT INTO CLIENTE_ADDRESS(ID, EMP_ID, ADDRESS) VALUES ('4','4','Lusitania s/n');";

      if (h2ConnectionPool.executeUpdate(newEmployeeSQL)>0 &&  h2ConnectionPool.executeUpdate(newEmployeeAddrSQL)>0){
         h2ConnectionPool.aceptarTransaccion();
      }else h2ConnectionPool.cancelarTransaccion();

   }
}
