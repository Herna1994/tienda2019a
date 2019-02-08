package controller;

import cliente.DataLoginCliente;
import dao.clienteDAO.ClienteDAO;
import entity.LoginClienteHarnina;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import java.util.Properties;

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
 //-------------------   // Enviamos un email  ---------------------

                final String username = "lucianoluqui55@gmail.com";
                final String password = "luciano2018";
                HttpSession session = request.getSession();
                int id_client = (Integer) session.getAttribute("idCliente");
                String email = "";
                String clave = "";
                ClienteDAO clienteDAO = null;
                try {
                    clienteDAO = new ClienteDAO();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("id client" + id_client);

                try {
                    // Recupero el email para su envio
                    email = (String) clienteDAO.getEmailClient(id_client);
                    System.out.println("email in MailServlet" + email);
                }catch (Exception e) {
                    mensaje = e.getMessage();
                }
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session2 = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                                return new javax.mail.PasswordAuthentication(username, password);
                            }
                        });
                try {
                    clave = new ClienteDAO().getClaveBloqueo(id_client);
                    System.out.println("clave in MailServlet" + clave);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (clave != null) {
                    Message message = new MimeMessage(session2);
                    try {
                        message.setFrom(new InternetAddress("from-email@gmail.com"));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(email));
                        message.setSubject("Recuperar contraseña");
                        message.setText("Tu clave es: " + clave + " O sigue este enlace para desboquear: http://localhost:8080/miDesbloquear?id=" + clave +"&email=" +email);
                        Transport.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    mensaje =  "Lock Client: bloqueado, email enviado";
                } else {
                    mensaje =  "Lock Client: bloqueado, email NO enviado";

                }
                session.setAttribute("paginaActiva", "index");
                System.out.println(mensaje );
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write( mensaje);

            }
       else {
                oneJson.put("maxIntento", session.getAttribute("maxIntento"));
                oneJson.put("intento", dataLoginCliente.getIntento());

                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(oneJson.toJSONString());
            }
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }

}
