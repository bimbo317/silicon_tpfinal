package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import persistencia.exceptions.MiException;

public class SvUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("username");
        String contra = request.getParameter("password");
        Controladora control = new Controladora();
        boolean autorizado;
        try {
            autorizado = control.verificarUsuario(usuario, contra);
            if (autorizado) {
                //obtengo la session y le asigno el usuario y contraseña para validar
                HttpSession misession = request.getSession(true);
                misession.setAttribute("usuario", usuario);
                misession.setAttribute("contra", contra);

                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (MiException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
