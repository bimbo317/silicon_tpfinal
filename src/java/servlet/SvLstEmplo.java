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

public class SvLstEmplo extends HttpServlet {
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
        System.out.println("\n\nProbando Eliminar empleado ID: "+id);
        try {
            //
            control.eliminarEmpleado(id);
            //Vuelvo a cargar la lista de empleados con las modificaciones realizadas
            List<Empleado> listaEmpleados;
            listaEmpleados = control.traerEmpleados();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaEmpleados", listaEmpleados);
            response.sendRedirect("./Admin/lstemployees.jsp");
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
            Empleado emple=control.buscarEmpleado(id);
            
            HttpSession misession=request.getSession();
            misession.setAttribute("empleado", emple);
            response.sendRedirect("./Admin/editemployee.jsp");
        } catch (Exception e) {
            System.out.println("\n\n"+e);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
