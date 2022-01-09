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
import logica.Servicio;
import persistencia.exceptions.MiException;

public class SvLstPkg extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Método para visualizar los paquetes cargados
        List<Paquete> listaPaquetes;
        try {
            HttpSession misession = request.getSession();
            listaPaquetes = control.traerPaquetes();
            misession.setAttribute("listaPaquetes", listaPaquetes);
            response.sendRedirect("./Package/lstpackage.jsp");
        } catch (MiException ex) {
            System.out.println(ex);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("action");
        Long id = Long.parseLong(request.getParameter("id"));
        List<Servicio> listaServicios;
        HttpSession misession = request.getSession();
        List<Paquete> listaPaquetes;
        if (accion.equalsIgnoreCase("editar")) {
            //System.out.println("Ingresa a Editar Paquete");
            try {
                //Buscar paquete
                Paquete paquete = control.buscarPaquete(id);
                listaServicios = control.traerServicios();
                misession.setAttribute("paquete", paquete);
                misession.setAttribute("listaServicios", listaServicios);
                response.sendRedirect("./Package/editpackage.jsp");
            } catch (MiException ex) {
                System.out.println(ex);
                response.sendRedirect("./Package/lstpackage.jsp");
            }
        } else if (accion.equalsIgnoreCase("eliminar")) {
            //System.out.println("Ingresa a Eliminar Paquete");
            try {
                control.eliminarPaquete(id);
                //Vuelvo a cargar la lista de paquetes cargados
                listaPaquetes = control.traerPaquetes();
                misession.setAttribute("listaPaquetes", listaPaquetes);
                response.sendRedirect("./Package/lstpackage.jsp");
            } catch (MiException ex) {
                System.out.println(ex);
                response.sendRedirect("./Package/lstpackage.jsp");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
