package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Cliente;
import logica.Controladora;

public class SvMdfCli extends HttpServlet {
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
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("name");
            String apellido = request.getParameter("last_name");
            String fec_nac = request.getParameter("date");
            String nacionalidad = request.getParameter("nationality");
            String direccion = request.getParameter("address");
            String telefono= request.getParameter("phone");
            String correo = request.getParameter("email");

            //control.crearEmpleado(nombre, apellido, cargo, sueldo, usuario, contrasenia);
            control.modificarEmpleado(id, dni, nombre, apellido, fec_nac, nacionalidad, direccion, telefono, correo);

            //Vuelvo a cargar la lista de empleados con las modificaciones realizadas
            List<Cliente> listaClientes;
            listaClientes = control.traerClientes();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaClientes", listaClientes);
            response.sendRedirect("./Clients/lstclients.jsp");

        } catch (Exception e) {
            System.out.println("\n\n" + e);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
