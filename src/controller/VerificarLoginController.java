package controller;

import cliente.DataLoginCliente;
import dao.clienteDAO.ClienteDAO;
import entity.LoginClienteHarnina;
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

@WebServlet("/veriLogin")
public class VerificarLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;
    String  mensaje, miUsuario, user, password;
    JSONObject oneJson = new JSONObject();
    ClienteDAO  clienteDAO;
    DataLoginCliente  dataLoginCliente;

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
        LoginClienteHarnina loginClienteHarnina = new LoginClienteHarnina(user,password);

        try {
            clienteDAO = new ClienteDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int id = clienteDAO.getIdLogin(user,password);

        if (id > 0) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf(id));
        }
        else {
            try {
                dataLoginCliente = new DataLoginCliente(request);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            dataLoginCliente.incrementarIntento();

            if (!dataLoginCliente.disponibilidadIntento()) {
                // Bloqueamos a nivel de la BB DD
                try {
                    clienteDAO.locked_client((Integer) session.getAttribute("idCliente"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // Enviamos un email
            }

            oneJson.put("maxIntento", session.getAttribute("maxIntento"));
            oneJson.put("intento", dataLoginCliente.getIntento());

            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(oneJson.toJSONString());
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }

}
