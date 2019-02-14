package controller;

import cliente.ComandValidateDataClient;
import cliente.ComandValidateDataUser;
import dao.clienteDAO.ClienteDAO;
import entity.ClientEntity;
import entity.UserEntity;
import error.Error;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

import java.sql.SQLException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/valiCliAdd")
public class ValidarClientInsertController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;
    String  mensaje, miUsuario, nif, firstname, lastname, birthdate,sex, postalCode, address, phone, mobile, email, user, password;
    JSONObject oneJson = new JSONObject();
    JSONArray arrayJson = new JSONArray();
    ClienteDAO  clienteDAO;
    UserEntity userEntity;
    ClientEntity clientEntity;
    HashMap<String,Error> listaErrores;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        miUsuario = request.getParameter("json");

        JSONParser parser = new JSONParser();

        try {
            Object objet = parser.parse(miUsuario);
            JSONObject jsonObject = (JSONObject) objet;
            firstname = (String) jsonObject.get("firstname");
            lastname = (String) jsonObject.get("lastname");
            birthdate = (String) jsonObject.get("birthdate");
            sex = (String) jsonObject.get("sex");
            nif = (String) jsonObject.get("nif");
            postalCode = (String) jsonObject.get("postalCode");
            address = (String) jsonObject.get("address");
            phone = (String) jsonObject.get("phone");
            mobile = (String) jsonObject.get("mobile");
            email = (String) jsonObject.get("email");
            user = (String) jsonObject.get("user");
            password = (String) jsonObject.get("password");

        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        userEntity = new UserEntity(nif, postalCode,address, phone, mobile,  email,  user, password);
        ComandValidateDataUser comandValidateDataUser = new ComandValidateDataUser(this.userEntity);
        clientEntity = new ClientEntity(firstname,lastname,birthdate,sex);
        ComandValidateDataClient comandValidateDataClient = new  ComandValidateDataClient(this.clientEntity);
        try {
             listaErrores = comandValidateDataUser.getCommands();
             listaErrores.putAll(comandValidateDataClient.getCommands());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
if (listaErrores.isEmpty()) {
           System.out.println("No hay errores Vamos a Insertar");
            try {
                clienteDAO = new ClienteDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int idInsert = clienteDAO.add_client(userEntity,clientEntity );

            System.out.println("idInsert:" + idInsert);

            if (idInsert > 0){
                session.setAttribute("paginaActiva", "client");
                session.setAttribute("idCliente", idInsert);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf(idInsert));
         }
        else{
            for (Map.Entry<String, Error> entry : listaErrores.entrySet()) {
                System.out.println("JSON Errores");
                oneJson.put("control", entry.getKey());
                oneJson.put("mensajeError", entry.getValue().getMsg());
                arrayJson.add(oneJson);
            }

            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(arrayJson.toJSONString());
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}

