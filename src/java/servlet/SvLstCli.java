package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Cliente;
import logica.Controladora;

public class SvLstCli extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            //busca el empleado
            Cliente client=control.buscarCliente(id);
            
            HttpSession misession=request.getSession();
            misession.setAttribute("cliente", client);
            response.sendRedirect("./Clients/editclient.jsp");
        } catch (Exception e) {
            System.out.println("\n\n"+e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
