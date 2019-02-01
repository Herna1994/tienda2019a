<%@ page import="dao.paginaDAO.PaginaDao" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.PaginaController" %>
<%--
  Created by IntelliJ IDEA.
  User: Luciano
  Date: 09/01/2019
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String miPagina="";
  session = request.getSession();

  if(session.getAttribute("paginaActiva") == null){

    session.setAttribute("paginaActiva", "index");

    session.setAttribute("idSesion", session.getId());

    session.setAttribute("intento",0);

    session.setAttribute("maxIntento",3);

    session.setAttribute("tiempoMaximoBloqueo",30);

  }

  if(session.getAttribute("idSesion") == session.getId())
  {
    miPagina = new PaginaController((String) session.getAttribute("paginaActiva")).getPagina();
  }
%>
<%=miPagina%>

