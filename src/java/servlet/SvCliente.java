package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Cliente;
import logica.Controladora;
import persistencia.exceptions.MiException;

@WebServlet(name = "SvCliente", urlPatterns = {"/SvCliente"})
public class SvCliente extends HttpServlet {
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Método para listar los clientes
        List<Cliente> listaClientes;
        try {
            HttpSession misession = request.getSession();
            listaClientes=control.traerClientes();
            misession.setAttribute("listaClientes", listaClientes);
            response.sendRedirect("./Clients/lstclients.jsp");
        } catch (MiException ex) {
            System.out.println(ex);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //throws ServletException, IOException {
        //Método para crear Cliente nuevo
        try {
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("name");
            String apellido = request.getParameter("last_name");
            String fec_nac = request.getParameter("date");
            String nacionalidad = request.getParameter("nationality");
            String direccion = request.getParameter("address");
            String telefono= request.getParameter("phone");
            String correo = request.getParameter("email");

            control.crearCliente(dni, nombre, apellido, fec_nac, nacionalidad, direccion, telefono, correo);
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
