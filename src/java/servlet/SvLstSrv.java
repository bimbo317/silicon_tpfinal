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

public class SvLstSrv extends HttpServlet {
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Método para eliminar Servicio
        //traigo ID
        Long id = Long.parseLong(request.getParameter("id"));
        try {
            //
            control.eliminarServicio(id);
            //Vuelvo a cargar la lista de empleados con las modificaciones realizadas
            List<Servicio> listaServicios;
            listaServicios = control.traerServicios();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaServicios", listaServicios);
            response.sendRedirect("./Services/services.jsp");
        } catch (MiException ex) {
            System.out.println("\n\n"+ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            //busca el empleado
            Servicio service=control.buscarServicio(id);
            
            HttpSession misession=request.getSession();
            misession.setAttribute("servicio", service);
            response.sendRedirect("./Services/editsrv.jsp");
        } catch (Exception e) {
            System.out.println("\n\n"+e);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
