package controller;

import dao.clienteDAO.ClienteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUser")
public class DeleteUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        try {
            if (new ClienteDAO().delete_user((Integer) session.getAttribute("idCliente"))){
                session.setAttribute("idCliente","");
                session.setAttribute("paginaActiva", "index");
                response.getWriter().write(String.valueOf("OK"));
            }
            else{
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
