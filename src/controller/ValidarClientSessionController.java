package controller;

import cliente.ComandoValidarLoginCliente;
import cliente.DataLoginCliente;
import dao.clienteDAO.ClienteDAO;
import entity.LoginClienteHarnina;
import error.Error;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import reflexion.ObjectTransferSession;
import util.ErrorManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/valiCliSesion")
public class ValidarClientSessionController extends HttpServlet {

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
        System.out.println("Hola Servidor");
        // leer el envio
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

        ComandoValidarLoginCliente comandoValidarLoginCliente = new ComandoValidarLoginCliente(loginClienteHarnina);

        HashMap<String,Error> listaErrores = comandoValidarLoginCliente.getCommands();

        if (listaErrores.isEmpty()) {

            try {
                  clienteDAO = new ClienteDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int id = clienteDAO.getIdLogin(user,password);


            if (id > 0) {
                session.setAttribute("paginaActiva", "client");
                session.setAttribute("idCliente", id);

                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(String.valueOf(id));
            }
            else
            {
                try {
                    dataLoginCliente = new DataLoginCliente(request);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                dataLoginCliente.incrementarIntento();

                if (!dataLoginCliente.disponibilidadIntento()) {
                    // Hay que bloquearlo un tiempo
                    session.setAttribute("horaBloqueo", new Date());
                 //  oneJson.put("horaBloqueo",session.getAttribute("horaBloqueo"));
                   oneJson.put("tiempoMaximoBloqueo",session.getAttribute("tiempoMaximoBloqueo"));
                }

                oneJson.put("maxIntento", session.getAttribute("maxIntento"));
                oneJson.put("intento", dataLoginCliente.getIntento());

                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(oneJson.toJSONString());
            }
        } else {
            for(Map.Entry<String, Error> entry : listaErrores.entrySet()) {
                oneJson.put("control" ,entry.getKey());
                oneJson.put("mensajeError" , entry.getValue().getMsg());
                arrayJson.add(oneJson);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(arrayJson.toJSONString());
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }


}
