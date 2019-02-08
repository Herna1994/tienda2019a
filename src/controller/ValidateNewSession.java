package controller;

import cliente.ComandValidateLogin;
import cliente.DataLoginCliente;
import dao.clienteDAO.ClienteDAO;
import entity.LoginClienteHarnina;
import error.Error;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/valiNewSession")
public class ValidateNewSession extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;
    String  miUsuario, user, password;
    String mensaje = "";
    JSONObject oneJson = new JSONObject();
    JSONArray arrayJson = new JSONArray();
    ClienteDAO  clienteDAO;
    DataLoginCliente  dataLoginCliente;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        miUsuario = request.getParameter("json");
        response.setCharacterEncoding("UTF-8");
        JSONParser parser = new JSONParser();
        try {
            Object objet = parser.parse(miUsuario);
            JSONObject jsonObject = (JSONObject) objet;
            user = (String) jsonObject.get("user");
            password = (String) jsonObject.get("password");

        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        LoginClienteHarnina loginClienteHarnina = new LoginClienteHarnina((Integer) session.getAttribute("idCliente"),user,password);

        ComandValidateLogin comandoValidarLoginCliente = new ComandValidateLogin(loginClienteHarnina);

        HashMap<String,Error> listaErrores = comandoValidarLoginCliente.getCommands();

        if (listaErrores.isEmpty()) {

            try {
                clienteDAO = new ClienteDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {

                if (clienteDAO.can_Is_New_User(loginClienteHarnina)){

                   response.getWriter().write("OK");
                }
                else
                {
                   response.getWriter().write("INVALID");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            for(Map.Entry<String, Error> entry : listaErrores.entrySet()) {
                oneJson.put("control" ,entry.getKey());
                oneJson.put("mensajeError" , entry.getValue().getMsg());
                arrayJson.add(oneJson);
            }

            response.getWriter().write(arrayJson.toJSONString());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
