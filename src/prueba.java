import dao.clienteDAO.ClienteDAO;
import entity.ClientDataEntity;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class prueba {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
       // ClienteDAO clienteDAO = new ClienteDAO();
       // ClientDataEntity clientDataEntity = clienteDAO.getDataClient2(13);
        //System.out.println(clientDataEntity.getFirstname());
/*
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "1954-02-13";

        try {

            Date date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

*/
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        System.out.println(sDate1+"\t"+date1);
    }
}
