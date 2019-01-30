package dao.poolConexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {

    private final String url;
    private final String user;
    private final String password;
    private final String driver;
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();
    private  final int INITIAL_POOL_SIZE;
    private final int MAX_POOL_SIZE;

    public static BasicConnectionPool create(String driver, String url, String user, String password, int conexionesIniciales, int conexionesMaximas) throws SQLException, ClassNotFoundException {
        List<Connection> pool = new ArrayList<>(conexionesIniciales);
        Class.forName(driver);
        for (int i = 0; i < conexionesIniciales; i++) {
            pool.add(createConnection(url, user, password));
           // System.out.println("Conectado a BD " + url);
            System.out.println("Connection: " + pool.get(i));
        }
        System.out.println("Fin conexión");
        return new BasicConnectionPool(driver,url, user, password, pool,conexionesIniciales,conexionesMaximas);
    }

    private BasicConnectionPool(String driver, String url, String user, String password, List<Connection> connectionPool,int conexionesIniciales, int conexionesMaximas) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
        this.INITIAL_POOL_SIZE = conexionesIniciales;
        this.MAX_POOL_SIZE = conexionesMaximas;
    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {

        //singleton

        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection(url, user, password));
            } else {
                throw new RuntimeException("Se alcanzó el tamaño máximo del pool, no hay conexiones disponibles!");
            }
        }
        //Elimina el elemento en la posición especificada en esta lista (operación opcional).
        // Desplaza cualquier elemento posterior a la izquierda (resta uno de sus índices).
        // Devuelve el elemento que fue eliminado de la lista.
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection( String url, String user, String password) throws SQLException, ClassNotFoundException {

        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void shutdown() throws SQLException {
      //  List<String> names = Arrays.asList("Larry", "Steve", "James");
      //  names.forEach(System.out::println);
        // Cada conexion usada la libera

        usedConnections.forEach(this::releaseConnection);

        // Cierra todas las conexiones
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
    }


}