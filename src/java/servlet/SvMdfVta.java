package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Venta;

public class SvMdfVta extends HttpServlet {

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
        //Método para modificar una Venta
        //Obtengo los datos del JSP
        HttpSession misession = request.getSession();
        List<Venta> listaVentas;
        String idVenta= request.getParameter("idVenta");
        String idCliente = request.getParameter("idCliente");
        String cambio = request.getParameter("cambio");
        
        String usuario = request.getParameter("usuario");
        //Esta variable me permite distinguir si fue elegido un Servicio o un Paquete
        //Al value del input le antepongo una "S" a los servicios y una "P" para los paquetes
        String idElegido = request.getParameter("srvpkg");
        String medioPago = request.getParameter("medioPago");
        
        
        
        System.out.println("Cliente: "+idCliente);
        System.out.println("Venta: "+idVenta);
        System.out.println("Cambio: "+cambio);
        System.out.println("usuario: "+usuario);
        System.out.println("srvpkg: "+idElegido);
        System.out.println("Empieza con: "+idElegido.substring(0, 1));
        System.out.println("elegido: "+idElegido.substring(1));
        System.out.println("medio de pago: "+medioPago);
        String paqueteOservicio = idElegido.substring(0, 1);
        idElegido=idElegido.substring(1);
        try {
            control.modificarVenta(idVenta, idCliente, paqueteOservicio, idElegido, usuario, medioPago);
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
