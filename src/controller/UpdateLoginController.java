package controller;

import dao.clienteDAO.ClienteDAO;
import entity.LoginEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateLogin")
public class UpdateLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;

    String  miUsuario, user, password;
    String mensaje = "";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();

        miUsuario = request.getParameter("json");

        JSONParser parser = new JSONParser();
        try {
            Object objet = parser.parse(miUsuario);
            JSONObject jsonObject = (JSONObject) objet;
            user = (String) jsonObject.get("user");
            password = (String) jsonObject.get("password");

        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        LoginEntity loginEntity = new LoginEntity((Integer) session.getAttribute("idCliente"),user,password);
        ClienteDAO clienteDAO = null;
        try {
             clienteDAO = new ClienteDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            response.setCharacterEncoding("UTF-8");

            if(clienteDAO.can_Is_New_User(loginEntity)) {

                if (clienteDAO.update_login(loginEntity)) {
                      response.getWriter().write(String.valueOf("OK"));
                } else {
                      response.getWriter().write(String.valueOf("INVALID"));
                }
            }
            else{
                System.out.println("Usuario Ocupado");
                response.getWriter().write(String.valueOf("BUSY"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }
}
