package reflexion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class RequestTransferSession {


    public void guardarDatosSesion(HttpServletRequest request, HttpSession session) {

        Enumeration<String> nombreParametros = request.getParameterNames();

        while(nombreParametros.hasMoreElements()){

            String nombreAtributo = nombreParametros.nextElement();

            session.setAttribute(nombreAtributo,request.getParameter(nombreAtributo));
        }
    }
}
