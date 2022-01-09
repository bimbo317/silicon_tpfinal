package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Paquete;
import persistencia.exceptions.MiException;

public class SvMdfPkg extends HttpServlet {
    
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Método para guardar el paquete modificado
        //Obtengo el id del paquete y los id de los servicios elegidos en el jsp
        List<Paquete> listaPaquetes;
        try {
            String[] serviciosElegidos = request.getParameterValues("idcheck");
            Double valor = Double.parseDouble(request.getParameter("precioPackage"));
            Long id= Long.parseLong(request.getParameter("idPackage"));
            control.modificarPaquete(id, serviciosElegidos, valor);
            listaPaquetes = control.traerPaquetes();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaPaquetes", listaPaquetes);
            response.sendRedirect("./Package/lstpackage.jsp");
        } catch (MiException ex) {
            System.out.println(ex);
            response.sendRedirect("./Package/editpackage.jsp");
        } catch (Exception e){
            response.sendRedirect("./Package/editpackage.jsp");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
