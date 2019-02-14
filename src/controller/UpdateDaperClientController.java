package controller;

import dao.clienteDAO.ClienteDAO;
import entity.ClientDataEntity;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

@WebServlet("/updateDaper")
public class UpdateDaperClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;
      HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String dataClient;
        dataClient = request.getParameter("json");

        ClientDataEntity clientDataEntity = new ClientDataEntity();
        JSONParser parser = new JSONParser();
        Object objet = null;
        try {
            objet = parser.parse(dataClient);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) objet;

        clientDataEntity.setId((Integer) session.getAttribute("idCliente"));
        clientDataEntity.setFirstname((String) jsonObject.get("firstname"));
        clientDataEntity.setLastname((String) jsonObject.get("lastname"));
        clientDataEntity.setSbirthdate((String) jsonObject.get("birthdate"));
        clientDataEntity.setSex((String) jsonObject.get("sex"));
        clientDataEntity.setNif((String) jsonObject.get("nif"));
        clientDataEntity.setPostalCode((String) jsonObject.get("postalCode"));
        clientDataEntity.setAddress((String) jsonObject.get("address"));
        clientDataEntity.setPhone((String) jsonObject.get("phone"));
        clientDataEntity.setMobile((String) jsonObject.get("mobile"));
        clientDataEntity.setEmail((String) jsonObject.get("email"));

        System.out.println("Cargo clientDataEntity pe birthdate" + clientDataEntity.getSbirthdate() );
        ClienteDAO clienteDAO;
        try {
             clienteDAO = new ClienteDAO();
            response.setCharacterEncoding("UTF-8");
            if (clienteDAO.updateDataClient(clientDataEntity)) {
                System.out.println("Que si update daper");
                response.getWriter().write(String.valueOf("OK"));
            }
            else {
                System.out.println("Que no");
                response.getWriter().write(String.valueOf("INVALID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
        }
    }

