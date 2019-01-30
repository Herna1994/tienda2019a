package dao.paginaDAO;

import java.util.List;

public class BuilderPage {

    private PaginaDao paginaDao = null;
    private String dom = "";
    public BuilderPage(PaginaDao paginaDao) {
        this.paginaDao = paginaDao;
    }
    public String getPage(){
            dom ="<HTML>";
            dom +=  getHead();
            dom +=  getBody();
            dom +=  "</HTML>";
            dom +=  getJs();
                return dom;
}

   private String getHead(){

        String head = "<head>";

        head +=  "<title>" + paginaDao.getTitle() + "</title>";

        List<String> metaAll = paginaDao.getMetaAll();
        for(String meta : metaAll){
            head +=   meta;
        }
        List<String> metaLink = paginaDao.getLinkAll();
        for(String meta : metaLink){
            head +=   meta;
        }
        head +=   "</head>";

        List<ObjectPathName> cssAll = paginaDao.getCssAll();
        for (ObjectPathName css :cssAll){
            head +=  "<link rel='stylesheet' href='" +css.getPath() + "/" + css.getName() + ".css'>";
        }

        return  head;

    }

   private String getBody(){
        return paginaDao.getBody();
    }

   private String getJs(){

        String js = "";
        List<ObjectPathName> jsAll = paginaDao.getJsAll();
        for (ObjectPathName jss :jsAll){
            js +=  "<script src ='" + "../" +  jss.getPath() + "/" + jss.getName() + ".js'></script>";
        }
        return  js;

    }
}
