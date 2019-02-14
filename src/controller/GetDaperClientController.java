package controller;

import com.google.gson.JsonObject;
import dao.clienteDAO.ClienteDAO;
import entity.ClientDataEntity;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/getDaperClient")
public class GetDaperClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;
    JSONObject oneJson = new JSONObject();
    ClientDataEntity clientDataEntity;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        response.setCharacterEncoding("UTF-8");
        try {
            clientDataEntity = (new ClienteDAO()).getDataClient2((Integer) session.getAttribute("idCliente"));


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        oneJson.put("id",clientDataEntity.getId());
        oneJson.put("firstname",clientDataEntity.getFirstname());
        oneJson.put("lastname",clientDataEntity.getLastname());
        oneJson.put("birthdate", clientDataEntity.getBirthdate().toString());
        oneJson.put("sex",clientDataEntity.getSex());
        oneJson.put("nif",clientDataEntity.getNif());
        oneJson.put("postalCode",clientDataEntity.getPostalCode());
        oneJson.put("address",clientDataEntity.getAddress());
        oneJson.put("phone",clientDataEntity.getPhone());
        oneJson.put("mobile",clientDataEntity.getMobile());
        oneJson.put("email",clientDataEntity.getEmail());

        response.getWriter().write(oneJson.toJSONString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
