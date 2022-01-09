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

public class SvMdfSrv extends HttpServlet {
    Controladora control = new Controladora();

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String nombre = request.getParameter("name_service");
            String destino= request.getParameter("destiny");
            Double costo = Double.parseDouble(request.getParameter("price"));
            String fecha_serv = request.getParameter("date");
            String descripcion = request.getParameter("description");

            //control.crearEmpleado(nombre, apellido, cargo, sueldo, usuario, contrasenia);
            control.modificarServicio(id, nombre, destino, costo, fecha_serv, descripcion);

            //Vuelvo a cargar la lista de empleados con las modificaciones realizadas
            List<Servicio> listaServicios;
            listaServicios = control.traerServicios();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaServicios", listaServicios);
            response.sendRedirect("./Services/services.jsp");

        } catch (Exception e) {
            System.out.println("\n\n" + e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
