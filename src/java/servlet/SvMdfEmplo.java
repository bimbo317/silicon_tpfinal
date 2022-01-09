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

public class SvMdfEmplo extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //traigo ID
        Long id = Long.parseLong(request.getParameter("id"));
        try {
            //
            control.modificarEstadoEmpleado(id, false);
            //Vuelvo a cargar la lista de empleados con las modificaciones realizadas
            List<Empleado> listaEmpleados;
            listaEmpleados = control.traerEmpleados();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaEmpleados", listaEmpleados);
            response.sendRedirect("./Admin/lstemployees.jsp");
        } catch (MiException ex) {
            System.out.println("\n\n" + ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //throws ServletException, IOException {
        //Método para modificar Empleado
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("name");
            String apellido = request.getParameter("last_name");
            String fec_nac = request.getParameter("date");
            String cargo = request.getParameter("cargo");
            String nacionalidad = request.getParameter("nationality");
            String direccion = request.getParameter("address");
            String telefono = request.getParameter("phone");
            String puesto = request.getParameter("job");
            Double sueldo = Double.parseDouble(request.getParameter("salary"));
            String usuario = request.getParameter("user");
            String correo = request.getParameter("email");
            String contrasenia = request.getParameter("pass");
            String contra2 = request.getParameter("pass2");

            control.modificarEmpleado(id, dni, nombre, apellido, fec_nac, cargo, nacionalidad, direccion, telefono, puesto, sueldo, usuario, correo, contrasenia, contra2);

            //Vuelvo a cargar la lista de empleados con las modificaciones realizadas
            List<Empleado> listaEmpleados;
            listaEmpleados = control.traerEmpleados();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaEmpleados", listaEmpleados);
            response.sendRedirect("./Admin/lstemployees.jsp");

        } catch (Exception e) {
            System.out.println("\n\n" + e);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
