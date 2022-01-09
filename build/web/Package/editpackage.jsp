<%@page import="logica.Paquete"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="logica.Servicio"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Trabajo Final (Silicon Misiones)</title>
        <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css?h=781fb6de6e6c5e4ad71f8f3786a9f480">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Kaushan+Script">
        <link rel="stylesheet" href="../assets/fonts/fontawesome-all.min.css?h=220695d33937f0c1d9977599320bf21d">
        <link rel="stylesheet" href="../assets/fonts/font-awesome.min.css?h=220695d33937f0c1d9977599320bf21d">
        <link rel="stylesheet" href="../assets/fonts/ionicons.min.css?h=220695d33937f0c1d9977599320bf21d">
        <link rel="stylesheet" href="../assets/fonts/material-icons.min.css?h=220695d33937f0c1d9977599320bf21d">
        <link rel="stylesheet" href="../assets/fonts/fontawesome5-overrides.min.css?h=220695d33937f0c1d9977599320bf21d">
        <link rel="stylesheet" href="../assets/css/styles.min.css?h=bba6c2735f5d24a882e68f15efabe94d">
        <link rel="icon" href="../assets/img/favicon.png">
        <script language="javascript" type="text/javascript">
            function sumar() {
                obj = document.frmpackage['idcheck'];
                objConValor = document.frmpackage['servicioElegido'];
                totalChecks = obj.length;
                totalSumado = 0;
                valorPaquete = 0;
                cont = 0;
                for (i = 0; i < totalChecks; i++) {
                    if (obj[i].checked == true) {
                        valor = objConValor[i].value;
                        cont = cont + 1;
                        totalSumado = totalSumado + parseInt(valor);
                        if (cont > 1) {
                            valorPaquete = totalSumado * 0.9;
                        }
                    }
                }
                document.getElementById('sumaServicio').innerHTML = 'Suma de Servicios: $ ' + totalSumado;
                document.getElementById('valorPackage').innerHTML = 'Precio Paquete: $ ' + valorPaquete;
                document.getElementById("totalP").setAttribute('value', valorPaquete);
            }
        </script>
    </head>

    <body style="background: rgb(34,34,34);min-height: 750px;">
        <%
            HttpSession misession = request.getSession();
            String user = (String) misession.getAttribute("usuario");
            if (user == null) {
                response.sendRedirect("../login.jsp");
            } else {
        %>
        <nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-dark" id="mainNav" style="height: 70px;background-color: #8f3c10!important;position: relative;">
            <div class="container"><a class="navbar-brand" href="#page-top" style="/*background-color: #8f3c10;*/margin: 0!important;padding: 0!important;">Travel Agency</a><button data-bs-toggle="collapse" data-bs-target="#navbarResponsive" class="navbar-toggler navbar-toggler-right" type="button" data-toogle="collapse" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto text-uppercase" style="background-color: rgba(143,60,16,0.75);align-items: center;">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="../index.jsp">Inicio</a></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item dropdown">
                            <a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#">clientes</a>
                            <div class="dropdown-menu" style="background: rgb(187,99,53);">
                                <form action="../SvCliente" method="GET">
                                    <a href="../SvCliente" class="SUBMIT dropdown-item">Listado</a>
                                </form>
                                <a class="dropdown-item" href="../Clients/addclient.jsp">Agregar</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#">Ventas</a>
                            <div class="dropdown-menu" style="background: rgb(187,99,53);">
                                <form action="../SvLstVta" method="GET">
                                    <a href="../SvLstVta" class="SUBMIT dropdown-item">Listado</a>
                                </form>
                                <form action="../SvVenta" method="GET">
                                    <a class="dropdown-item" href="../SvVenta" style="text-decoration: none;">Agregar</a>
                                </form>
                            </div>
                        </li>
                        <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#">Servicios</a>
                            <div class="dropdown-menu" style="background: rgb(187,99,53);">
                                <form action="../SvServicio" method="GET">
                                    <a class="dropdown-item" href="../SvServicio" style="text-decoration: none;">Listado</a>
                                </form>
                                <a class="dropdown-item" href="../Services/addservice.jsp">Agregar</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#">paquetes</a>
                            <div class="dropdown-menu" style="background: rgb(187,99,53);">
                                <form action="../SvLstPkg" method="GET">
                                    <a class="dropdown-item" href="../SvLstPkg" style="text-decoration: none;">Listado</a>
                                </form>
                                <form action="../SvServicio" method="GET">
                                    <a class="dropdown-item" href="../SvPackage" style="text-decoration: none;">Agregar</a>
                                </form>
                            </div>
                        </li>
                        <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#">empleados</a>
                            <div class="dropdown-menu" style="background: rgb(187,99,53);">
                                <form action="../SvEmpleado" method="GET">
                                    <a href="../SvEmpleado" class="SUBMIT dropdown-item">Listado</a>
                                </form>
                                <a class="dropdown-item" href="../Admin/addemployee.jsp">agregar</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><%=user%></a>
                            <div class="dropdown-menu" style="background: rgb(187,99,53);">
                                <a class="dropdown-item" href="#" data-bs-target="#editProfile" data-bs-toggle="modal">Profile</a>
                                <a class="dropdown-item" href="../logout.jsp">Logout</a>
                            </div>
                        </li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul>
                </div>
            </div>
        </nav>
        <section id="services" style="/*position: 50% 50%;*/display: block;box-sizing: border-box;position: relative;vertical-align: middle;overflow: visible;object-fit: cover;clear: both;content: '';background-image: url(&quot;../assets/img/80695701-0.jpeg?h=ba907fe0be1b39a84cd0d15e4724b0e6&quot;);background-size: cover;background-repeat: no-repeat;min-height: 750px;padding: 110px 0;">
            <!-- Start: user settings -->
            <div class="col-lg-12 col-xl-12 offset-xl-0 d-lg-flex justify-content-lg-center">
                <div class="card shadow mb-3" style="background: rgba(255,255,255,0.77);">
                    <div class="card-header py-3">
                        <p class="text-center m-0 fw-bold" style="font-size: 32px;text-shadow: 5px 4px 7px rgb(150,129,40);color: rgb(143,60,16);">Modificar Paquete</p>
                    </div>
                    <div class="card-body">
                        <p>Seleccione los servicios que desea incluir o quitar en el paquete</p>
                        <form name="frmpackage" id="frmpackage" action="../SvMdfPkg" method="POST">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col" class="border-0 text-uppercase font-medium pl-4">Cod</th>
                                        <th class="border-0 text-uppercase font-medium" scope="col">Nombre/Destino</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Costo/F.Serv</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Descripción</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Elegir</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        Paquete paqueteMod = (Paquete) request.getSession().getAttribute("paquete");
                                        Long idpkg=paqueteMod.getId();
                                        List<Servicio> serviciosDelPaquete = paqueteMod.getLista_servicios_incluidos();
                                        List<Servicio> listaServicios = (List) request.getSession().getAttribute("listaServicios");
                                        for (Servicio unServicio : listaServicios) {
                                            Long id = unServicio.getId();
                                            Integer cod = unServicio.getCod_servicio();
                                            String nombre = unServicio.getNombre();
                                            String dest = unServicio.getDestino();
                                            String desc = unServicio.getDescripcion();
                                            Double costo = unServicio.getCosto_servicio();
                                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                            sdf.setTimeZone(TimeZone.getTimeZone("America/Argentina"));
                                            Date fecha = unServicio.getFecha_servicio();
                                            String fecsv = "";
                                            String checked = "";
                                            if (fecha != null) {
                                                fecsv = sdf.format(unServicio.getFecha_servicio());
                                            }
                                            for (Servicio srvPkg : serviciosDelPaquete) {
                                                if (srvPkg.getCod_servicio() == cod) {
                                                    checked = "checked";                                                    
                                                    break;
                                                }
                                            }
                                    %>
                                    <tr>
                                        <td style="width: 2%;"><%=cod%></td>
                                        <td class="col-2">
                                            <h5 class="font-medium mb-0"><%=nombre%></h5>
                                            <span class="text-muted"><%=dest%></span>
                                        </td>
                                        <td class="col-2">
                                            <span class="text-muted">$ <%=costo%></span><br>
                                            <span class="text-muted"><%=fecsv%></span></td>
                                        <td class="col-5">
                                            <span class="text-muted"><%=desc%></span><br>
                                        <td class="col-2">
                                            <input type="hidden" name="servicioElegido" value="<%=costo%>">
                                            <input type="checkbox" name="idcheck" class="btn-check" id="<%=id%>" value="<%=id%>" autocomplete="off" onclick="sumar()" <%=checked%>>
                                            <label class="btn btn-outline-primary" for="<%=id%>">Agregar</label>
                                        </td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                            <div id="sumaServicio">Suma de Servicios: $ 0</div>
                            <div id="valorPackage">Precio Paquete: $ 0</div>
                            <div class="d-xl-flex justify-content-xl-center form-group mb-3">
                                <input type="hidden" value="<%=idpkg%>" name="idPackage">
                                <input type="hidden" id="totalP" value="" name="precioPackage">
                                <button class="btn btn-primary btn-sm" type="submit">Guardar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div><!-- End: user settings -->
        </section><!-- Start: Footer Basic -->
        <footer class="footer-basic" style="background: rgb(34,34,34);border-color: #333333;border-top-color: rgb(75,;border-right-color: 76,;border-bottom-color: 77);border-left-color: 76,;padding: 0px;">
            <!-- Start: Social Icons -->
            <div class="social" style="padding: 10px;color: rgb(255,255,255);"><a href="#"><i class="icon ion-social-instagram"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-facebook"></i></a></div><!-- End: Social Icons -->
            <!-- Start: Links -->
            <ul class="list-inline" style="color: rgb(255,255,255);">
                <li class="list-inline-item"><a href="../index.jsp">Inicio</a></li>
                <li class="list-inline-item"><a href="../Services/services.jsp">Servicios</a></li>
            </ul><!-- End: Links -->
            <!-- Start: Copyright -->
            <p class="copyright">Silicon Misiones&nbsp; © 2021</p><!-- End: Copyright -->
        <footer class="footer-basic" style="background: rgb(34,34,34);border-color: #333333;border-top-color: rgb(75,;border-right-color: 76,;border-bottom-color: 77);border-left-color: 76,;padding: 0px;">
            <!-- Start: Social Icons -->
            <div class="social" style="padding: 10px;color: rgb(255,255,255);"><a href="#"><i class="icon ion-social-instagram"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-facebook"></i></a></div><!-- End: Social Icons -->
            <!-- Start: Links -->
            <ul class="list-inline" style="color: rgb(255,255,255);">
                <li class="list-inline-item">
                    <a href="#">Inicio</a>
                </li>
                <li class="list-inline-item">
                    <form action="../SvServicio" method="GET">
                        <a href="../SvServicio" style="text-decoration: none;">Servicios</a>
                    </form>
                </li>
            </ul><!-- End: Links -->
            <!-- Start: Copyright -->
            <p class="copyright">Ariel Guerrero&nbsp; © 2021</p><!-- End: Copyright -->
        </footer><!-- End: Footer Basic -->
        <% }%>
        <script src="../assets/js/jquery.min.js?h=83e266cb1712b47c265f77a8f9e18451"></script>
        <script src="../assets/bootstrap/js/bootstrap.min.js?h=5488c86a1260428f0c13c0f8fb06bf6a"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="../assets/js/script.min.js?h=99e4877f501cd30e2301247e12adfe46"></script>
    </body>
</html>
