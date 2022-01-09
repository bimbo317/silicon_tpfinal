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

public class SvPackage extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Método para ingresar a la pagina de crear paquete 
        //(es necesario obtener la lista de servicios disponibles)
        List<Servicio> listaServicios;
        try {
            HttpSession misession = request.getSession();
            listaServicios = control.traerServicios();
            misession.setAttribute("listaServicios", listaServicios);
            response.sendRedirect("./Package/addpackage.jsp");
        } catch (MiException ex) {
            System.out.println(ex);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Método para guardar el paquete creado
        //Obtengo los id de los servicios elegidos en el jsp
        try {
            String[] serviciosElegidos = request.getParameterValues("idcheck");
            Double valor = Double.parseDouble(request.getParameter("precioPackage"));
            control.crearPaquete(serviciosElegidos, valor);
            response.sendRedirect("index.jsp");
        } catch (MiException ex) {
            System.out.println(ex);
            response.sendRedirect("index.jsp");
        } catch (Exception e){
            System.out.println(e.fillInStackTrace());
            response.sendRedirect("./Package/addpackage.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
