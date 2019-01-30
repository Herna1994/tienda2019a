package controller;


import dao.paginaDAO.BuilderPage;
import dao.paginaDAO.ObjectPathName;
import dao.paginaDAO.PaginaDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Luciano on 16/1/2019.
 */
public class PaginaController {

    private String pagina;
    private String dom = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String dbName = "jdbc:mysql://localhost/tienda2019vista?useInformationSchema=true";
    private String user = "root";
    private String password = "";
    PaginaDao paginaDao;

    public PaginaController(String pagina){
        this.pagina = pagina;
    }

    public String getPagina() throws ClassNotFoundException, SQLException {

        if (this.pagina.equals("index")) {
            paginaDao = new PaginaDao("index", this.driver, this.dbName, this.user, this.password);
        }
        return (new BuilderPage(paginaDao).getPage());
    }
}
