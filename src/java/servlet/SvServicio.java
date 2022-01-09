package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Servicio;
import persistencia.exceptions.MiException;

public class SvServicio extends HttpServlet {
    
    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Servicio> listaServicios;
        try {
            HttpSession misession = request.getSession();
            listaServicios=control.traerServicios();
            misession.setAttribute("listaServicios", listaServicios);
            response.sendRedirect("./Services/services.jsp");
        } catch (MiException ex) {
            System.out.println(ex);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //throws ServletException, IOException {
        try {
            String nombre = request.getParameter("name_service");
            String destino= request.getParameter("destiny");
            Double costo = Double.parseDouble(request.getParameter("price"));
            String fecha_serv = request.getParameter("date");
            String descripcion = request.getParameter("description");

            control.crearServicio(nombre, destino, costo, fecha_serv, descripcion);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            System.out.println("\n\n"+e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
