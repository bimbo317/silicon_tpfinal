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

public class SvVenta extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Método para cargar la pantalla de nueva venta
        List<Paquete> listaPaquetes;
        List<Servicio> listaServicios;
        List<Cliente> listaClientes;
        HttpSession misession = request.getSession();
        try {
            listaPaquetes = control.traerPaquetes();
            listaServicios = control.traerServicios();
            listaClientes = control.traerClientes();
            misession.setAttribute("listaPaquetes", listaPaquetes);
            misession.setAttribute("listaServicios", listaServicios);
            misession.setAttribute("listaClientes", listaClientes);
            response.sendRedirect("./Sales/addsale.jsp");
        } catch (MiException ex) {
            System.out.println(ex);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //Método para guardar una Nueva Venta
        //Obtengo los datos del JSP
        String idCliente = request.getParameter("idCliente");
        //Esta variable me permite distinguir si fue elegido un Servicio o un Paquete
        String usuario = request.getParameter("usuario");
        String idElegido = request.getParameter("srvpkg");
        String medioPago = request.getParameter("medioPago");
        //Al value del input le antepongo una "S" a los servicios y una "P" para los paquetes
        String paqueteOservicio = idElegido.substring(0, 1);
        idElegido=idElegido.substring(1);
        
//        System.out.println("Cliente: "+idCliente);
//        System.out.println("usuario: "+usuario);
//        System.out.println("srvpkg: "+idElegido);
//        System.out.println("Empieza con: "+idElegido.substring(0, 1));
//        System.out.println("elegido: "+idElegido.substring(1));
//        System.out.println("medio de pago: "+medioPago);
        try {
            control.crearVenta(idCliente, paqueteOservicio, idElegido, usuario, medioPago);
            List<Venta> listaVentas;
            HttpSession misession = request.getSession();
            listaVentas = control.traerVentas();
            misession.setAttribute("listaVentas", listaVentas);
            response.sendRedirect("./Sales/lstsales.jsp");
        } catch (Exception e) {
            System.out.println("\n\n" + e.fillInStackTrace());
            response.sendRedirect("./index.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
