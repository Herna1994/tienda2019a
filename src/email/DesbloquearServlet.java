package email;


import dao.clienteDAO.ClienteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/miDesbloquear")
public class DesbloquearServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String claveBloqueo = request.getParameter("clave");
        String email = request.getParameter("email");
        String mensaje = "";

        if(claveBloqueo == null){
             mensaje = "Errror con CLAVE BLOQueo vacia";
        }
      else {

            try {
                if (new ClienteDAO().unlock_user(claveBloqueo, email)) {
                    session.setAttribute("intento",0);
                    mensaje = "Usuario desbloqueado";
                } else {
                    mensaje = "Error con unlock_user";

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

      }
             response.getWriter().write(mensaje);

    }
}
