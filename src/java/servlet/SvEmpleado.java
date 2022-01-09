package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Empleado;
import persistencia.exceptions.MiException;

public class SvEmpleado extends HttpServlet {
    
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empleado> listaEmpleados;
        try {
            listaEmpleados = control.traerEmpleados();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaEmpleados", listaEmpleados);
            response.sendRedirect("./Admin/lstemployees.jsp");
        } catch (MiException ex) {
            System.out.println(ex);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //throws ServletException, IOException {
        try {
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("name");
            String apellido = request.getParameter("last_name");
            String fec_nac = request.getParameter("date");  
            String cargo = request.getParameter("cargo");
            String nacionalidad = request.getParameter("nationality");
            String direccion = request.getParameter("address");
            String telefono= request.getParameter("phone");
            String puesto = request.getParameter("job");
            Double sueldo = Double.parseDouble(request.getParameter("salary"));
            String usuario = request.getParameter("user");
            String correo = request.getParameter("email");
            String contrasenia = request.getParameter("pass");
            String contra2= request.getParameter("pass2");

            //control.crearEmpleado(nombre, apellido, cargo, sueldo, usuario, contrasenia);
            control.crearEmpleado(dni, nombre, apellido, fec_nac, cargo, nacionalidad, direccion, telefono, puesto, sueldo, usuario, correo, contrasenia, contra2);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e);
            System.out.println("\n\n"+e);
        }
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
