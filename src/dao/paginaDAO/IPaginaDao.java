package dao.paginaDAO;

import java.util.List;

public interface IPaginaDao<T> {
   int getIdPage();
      String getTitle();
      List<String> getMetaAll();
      List<String> getLinkAll();
      List<String> getCssAll();
      String getBody();
      List<String> getJsAll();

}
