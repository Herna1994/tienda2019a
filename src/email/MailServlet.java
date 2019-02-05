package email;


/**
 * Created by Luciano on 12/12/2018.
 */

import dao.clienteDAO.ClienteDAO;

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
import java.sql.SQLException;
import java.util.Properties;


@WebServlet("/Mail")
public class MailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = "lucianoluqui55@gmail.com";
        final String password = "*******";
        HttpSession session = request.getSession();
        int id_client = (Integer) session.getAttribute("idCliente");
        String email = "";
        String mensaje = "";
        String clave = "";
        //int perfil = 0;

        ClienteDAO clienteDAO = null;
        try {
            clienteDAO = new ClienteDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("id client" + id_client);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        RequestDispatcher rd = null;

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
                message.setText("Tu clave es: " + clave + " O sigue este enlace para desboquear: http://localhost:8080/Desbloquear?id=" + clave +"&email=" +email);
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            request.setAttribute("mensaje", "Cliente: bloqueado, email enviado");
        } else {
            request.setAttribute("mensaje", "Cliente: bloqueado, email NO enviado");

        }
        rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }

}
