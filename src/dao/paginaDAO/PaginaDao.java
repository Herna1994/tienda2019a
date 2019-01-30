package dao.paginaDAO;

import com.mysql.jdbc.CallableStatement;
import dao.AccesoDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PaginaDao implements  IPaginaDao{

    private AccesoDB acceso = null;
    private String driver;
    private String dbName;
    private String user;
    private String password;
    private String pagina;
    private int idPage;
    private String title;
    private List<String> metaAll = new ArrayList<String>();
    private List<String> metaLink = new ArrayList<String>();
    private List<ObjectPathName> cssAll = new ArrayList<ObjectPathName>();
    private List<ObjectPathName> jsAll = new ArrayList<ObjectPathName>();

    private String body;
    private String sql;
    private ResultSet mirs;

    public PaginaDao(String pagina, String driver, String dbName, String user, String password ) throws SQLException {
        this.pagina = pagina;
        this.driver = driver;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        loadPartsPage();
    }

    private void conectar() {
        acceso = AccesoDB.getMiConexion();
        try {
            acceso.conectar(this.driver, this.dbName, this.user, this.password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void loadPartsPage() throws SQLException {

        this.conectar();
        loadTitle();
        loadMeta();
        loadLinkHref();
        loadCssAll();
        loadBody();
        loadJsAll();
    }

    private void loadTitle() throws SQLException {
        sql = "SELECT code,title FROM page WHERE name = '" + this.pagina + "'";
        mirs = acceso.executeQuery(sql);
        if (mirs.next()) {
            this.idPage = mirs.getInt("code");
            this.title = mirs.getString("title");
        }
    }

    private void loadMeta() throws SQLException {

        sql = "SELECT `meta` FROM `pagemetadata` WHERE `page` = " + this.idPage;
        mirs = acceso.executeQuery(sql);
        while (mirs.next()) {
            this.metaAll.add(mirs.getString("meta"));
        }
    }

    private void loadLinkHref() throws SQLException {

        sql = "SELECT `link`,`orden` FROM `pagelinkhref` WHERE `page`=" +
                + this.idPage +  " ORDER by 2";
        mirs = acceso.executeQuery(sql);
        while (mirs.next()) {
            this.metaLink.add(mirs.getString("link"));
        }
    }

    private void loadCssAll() throws SQLException {

        sql = "SELECT `path`,`name`,`orden`" +
                " FROM `pagecss` WHERE `page` = " +
                + this.idPage +  " ORDER by 3";
        mirs = acceso.executeQuery(sql);
        while (mirs.next()) {

            this.cssAll.add(new ObjectPathName(mirs.getString("path"),mirs.getString("name")));
        }
    }

    private void loadBody() throws SQLException {
        CallableStatement cstmt = (CallableStatement) acceso.getConexion().prepareCall("{call getBody(?, ?)}");
        cstmt.registerOutParameter(2, Types.VARCHAR);
        cstmt.setInt(1,this.getIdPage());    //this.idPagina);
        cstmt.execute();
        this.body =  cstmt.getString(2);
    }

    private void loadJsAll() throws SQLException {

        sql = "SELECT `path`,`name`,`orden`" +
                " FROM `pagejs` WHERE `page` = " +
                + this.idPage +  " ORDER by 3";
        mirs = acceso.executeQuery(sql);
        while (mirs.next()) {

            this.jsAll.add(new ObjectPathName(mirs.getString("path"),mirs.getString("name")));
        }
    }

    public int getIdPage() {
        return idPage;
    }

    public String getTitle() {
        return title;
    }

    public List getMetaAll() {return metaAll;}

    public List getLinkAll(){return metaLink;}

    public List<ObjectPathName> getCssAll() {
        return cssAll;
    }

    public String getBody() {
        return body;
    }

    public List<ObjectPathName> getJsAll() {
        return jsAll;
    }
}
