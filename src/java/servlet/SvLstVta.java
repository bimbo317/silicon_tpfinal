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
import logica.Paquete;
import logica.Servicio;
import logica.Venta;
import persistencia.exceptions.MiException;

public class SvLstVta extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Método para visualizar los paquetes cargados
        List<Venta> listaVentas;
        try {
            HttpSession misession = request.getSession();
            listaVentas = control.traerVentas();
            misession.setAttribute("listaVentas", listaVentas);
            response.sendRedirect("./Sales/lstsales.jsp");
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
        List<Venta> listaVentas;
        HttpSession misession = request.getSession();
        List<Paquete> listaPaquetes;
        List<Servicio> listaServicios;
        List<Cliente> listaClientes;
        if (accion.equalsIgnoreCase("editar")) {
            System.out.println("Ingresa a Editar la venta");
            try {
                //Buscar Venta
                Venta venta = control.buscarVenta(id);
                misession.setAttribute("venta", venta);
                listaPaquetes = control.traerPaquetes();
                listaServicios = control.traerServicios();
                listaClientes = control.traerClientes();
                misession.setAttribute("listaPaquetes", listaPaquetes);
                misession.setAttribute("listaServicios", listaServicios);
                misession.setAttribute("listaClientes", listaClientes);
                response.sendRedirect("./Sales/editsales.jsp");
            } catch (MiException ex) {
                System.out.println(ex);
                response.sendRedirect("./Sales/lstsales.jsp");
            }
        } else if (accion.equalsIgnoreCase("eliminar")) {
            //System.out.println("Ingresa a Eliminar Venta");
            try {
                control.eliminarVenta(id);
                //Vuelvo a cargar la lista de ventas cargadas
                listaVentas = control.traerVentas();
                misession.setAttribute("listaVentas", listaVentas);
                response.sendRedirect("./Sales/lstsales.jsp");
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
