package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.ControladoraPersistencia;
import persistencia.exceptions.MiException;

public class Controladora {

    ControladoraPersistencia controlPersist = new ControladoraPersistencia();

    public void crearEmpleado(String nombre, String apellido, String cargo, Double sueldo, String usuario, String contrasenia) throws MiException {
        Empleado emple = new Empleado();
        Usuario usu = new Usuario();
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);

        //asigno valores a usuario
        usu.setNombreUsuario(usuario);
        usu.setContrasenia(contrasenia);
        usu.setAlta(true);

        //asigno usuario a empleado
        emple.setUsuario(usu);
        controlPersist.crearEmpleado(emple, usu);
    }

    public boolean verificarUsuario(String usuario, String contra) throws MiException {
        List<Usuario> listaUsuarios = controlPersist.traerUsuarios();

        if (listaUsuarios != null) {
            for (Usuario usuarioUnitario : listaUsuarios) {
                if (usuarioUnitario.getNombreUsuario().equals(usuario) && usuarioUnitario.getContrasenia().equals(contra)) {
                    if (usuarioUnitario.getAlta()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Empleado> traerEmpleados() throws MiException {
        return controlPersist.traerEmpleados();
    }

    private Date fechaAdate(String fec) throws MiException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date resultado;
        try {
            resultado = formato.parse(fec);
        } catch (ParseException ex) {
            throw new MiException("Error al convertir una fecha");
        }
        return resultado;
    }

    public void crearEmpleado(String dni, String nombre, String apellido, String fec_nac, String cargo, String nacionalidad, String direccion, String telefono, String puesto, Double sueldo, String usuario, String correo, String contrasenia, String contra2) throws MiException {
        Date fecha_nac = fechaAdate(fec_nac);

        Empleado emple = new Empleado();
        Usuario usu = new Usuario();
        emple.setDni(dni);
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setFechaNac(fecha_nac);
        emple.setCargo(cargo);
        emple.setNacionalidad(nacionalidad);
        emple.setDireccion(direccion);
        emple.setCelular(telefono);
        emple.setCargo(puesto);
        emple.setSueldo(sueldo);
        emple.setEmail(correo);

        //asigno valores a usuario
        usu.setNombreUsuario(usuario);
        usu.setContrasenia(contrasenia);
        usu.setAlta(true);

        //asigno usuario a empleado
        emple.setUsuario(usu);
        controlPersist.crearEmpleado(emple, usu);
    }

    public void crearCliente(String dni, String nombre, String apellido, String fec_nac, String nacionalidad, String direccion, String telefono, String correo) throws MiException {
        Date fecha_nac = fechaAdate(fec_nac);
        Cliente nuevo_cliente = new Cliente();
        nuevo_cliente.setDni(dni);
        nuevo_cliente.setNombre(nombre);
        nuevo_cliente.setApellido(apellido);
        nuevo_cliente.setFechaNac(fecha_nac);
        nuevo_cliente.setNacionalidad(nacionalidad);
        nuevo_cliente.setDireccion(direccion);
        nuevo_cliente.setCelular(telefono);
        nuevo_cliente.setEmail(correo);
        nuevo_cliente.setAlta(true);

        //Guardar cliente
        controlPersist.crearCliente(nuevo_cliente);
    }

    public void crearServicio(String nombre, String destino, Double costo, String fecha_serv, String descripcion) throws MiException {
        Servicio nuevo_servicio = new Servicio();
        nuevo_servicio.setNombre(nombre);
        nuevo_servicio.setDestino(destino);
        nuevo_servicio.setCosto_servicio(costo);
        nuevo_servicio.setDescripcion(descripcion);
        if (!fecha_serv.equals("")) {
            Date fecha_servicio = fechaAdate(fecha_serv);
            nuevo_servicio.setFecha_servicio(fecha_servicio);
        }
        //Guardar servicio
        controlPersist.crearServicio(nuevo_servicio);
    }

    public Empleado buscarEmpleado(Long id) throws MiException {
        return controlPersist.buscarEmpleado(id);
    }

    public void modificarEmpleado(Long id, String dni, String nombre, String apellido, String fec_nac, String cargo, String nacionalidad, String direccion, String telefono, String puesto, Double sueldo, String usuario, String correo, String contrasenia, String contra2) throws MiException {
        Empleado empleadoParaModificar = buscarEmpleado(id);
        Date fecha_nac = fechaAdate(fec_nac);
        empleadoParaModificar.setDni(dni);
        empleadoParaModificar.setNombre(nombre);
        empleadoParaModificar.setApellido(apellido);
        empleadoParaModificar.setFechaNac(fecha_nac);
        empleadoParaModificar.setCargo(cargo);
        empleadoParaModificar.setNacionalidad(nacionalidad);
        empleadoParaModificar.setDireccion(direccion);
        empleadoParaModificar.setCelular(telefono);
        empleadoParaModificar.setCargo(puesto);
        empleadoParaModificar.setSueldo(sueldo);
        empleadoParaModificar.setEmail(correo);

        //asigno valores a usuario
        Usuario usu = empleadoParaModificar.getUsuario();
        usu.setNombreUsuario(usuario);
        usu.setContrasenia(contrasenia);
        usu.setAlta(true);

        //guardo datos de empleado y usuario
        controlPersist.modificarEmpleado(empleadoParaModificar, usu);
    }

    public void modificarEstadoEmpleado(Long id, boolean estado) throws MiException {
        Empleado empleado = buscarEmpleado(id);
        Usuario usu = empleado.getUsuario();
        usu.setAlta(estado);

        //guardo datos de empleado y usuario
        controlPersist.modificarEmpleado(empleado, usu);
    }

    public void eliminarEmpleado(Long id) throws MiException {
        Empleado empleado = buscarEmpleado(id);
        Usuario usu = empleado.getUsuario();

        //elimino usuario y empleado
        controlPersist.eliminarEmpleado(empleado, usu);
    }

    public List<Cliente> traerClientes() throws MiException {
        return controlPersist.traerClientes();
    }

    public Cliente buscarCliente(Long id) throws MiException {
        return controlPersist.buscarCliente(id);
    }

    public void modificarEmpleado(Long id, String dni, String nombre, String apellido, String fec_nac, String nacionalidad, String direccion, String telefono, String correo) throws MiException {
        Cliente clienteParaModificar = buscarCliente(id);
        Date fecha_nac = fechaAdate(fec_nac);
        clienteParaModificar.setDni(dni);
        clienteParaModificar.setNombre(nombre);
        clienteParaModificar.setApellido(apellido);
        clienteParaModificar.setFechaNac(fecha_nac);
        clienteParaModificar.setNacionalidad(nacionalidad);
        clienteParaModificar.setDireccion(direccion);
        clienteParaModificar.setCelular(telefono);
        clienteParaModificar.setEmail(correo);

        //guardo datos el cliente modificado
        controlPersist.modificarCliente(clienteParaModificar);
    }

    public List<Servicio> traerServicios() throws MiException {
        return controlPersist.traerServicios();
    }

    public Servicio buscarServicio(Long id) throws MiException {
        return controlPersist.buscarServicio(id);
    }

    public void eliminarServicio(Long id) throws MiException {
        controlPersist.eliminarServicio(id);
    }

    public void modificarServicio(Long id, String nombre, String destino, Double costo, String fecha_serv, String descripcion) throws MiException {
        Servicio servParaModificar = buscarServicio(id);
        servParaModificar.setNombre(nombre);
        servParaModificar.setDestino(destino);
        servParaModificar.setCosto_servicio(costo);
        servParaModificar.setDescripcion(descripcion);
        if (!fecha_serv.equals("")) {
            Date fecha_servicio = fechaAdate(fecha_serv);
            servParaModificar.setFecha_servicio(fecha_servicio);
        }
        //Guardar servicio

        controlPersist.modificarServicio(servParaModificar);
    }

    public void crearPaquete(String[] serviciosElegidos, Double valor) throws MiException {
        Paquete nuevoPaquete = new Paquete();
        List<Servicio> listaServicios = new ArrayList();
        if (!serviciosElegidos.equals("")) {
            for (String idServicioElegido : serviciosElegidos) {
                Long idSrvElegido = Long.parseLong(idServicioElegido);
                listaServicios.add(controlPersist.buscarServicio(idSrvElegido));
            }
            //agrego la lista al paquete
            nuevoPaquete.setLista_servicios_incluidos(listaServicios);
            nuevoPaquete.setCosto_paquete(valor);
            //Guardar Paquete
            controlPersist.crearPaquete(nuevoPaquete);
        }
    }

    public List<Paquete> traerPaquetes() throws MiException {
        return controlPersist.traerPaquetes();
    }

    public Paquete buscarPaquete(Long id) throws MiException {
        return controlPersist.buscarPaquete(id);
    }

    public void modificarPaquete(Long id, String[] serviciosElegidos, Double valor) throws MiException {
        Paquete packModificar = buscarPaquete(id);
        List<Servicio> listaServicios = new ArrayList();
        if (!serviciosElegidos.equals("")) {
            for (String idServicioElegido : serviciosElegidos) {
                Long idSrvElegido = Long.parseLong(idServicioElegido);
                listaServicios.add(controlPersist.buscarServicio(idSrvElegido));
            }
            //agrego la lista al paquete
            packModificar.setLista_servicios_incluidos(listaServicios);
            packModificar.setCosto_paquete(valor);
            //Guardar Paquete
            controlPersist.modificarPaquete(packModificar);
        }
    }

    public void eliminarPaquete(Long id) throws MiException {
        Paquete paquete = buscarPaquete(id);
        List<Venta> listaVentas = paquete.getListaVenta();
        if (listaVentas.size() > 0) {
            for (Venta unaVenta : listaVentas) {
                eliminarVenta(unaVenta.getIdVenta());
            }
        }
        controlPersist.eliminarPaquete(id);
    }

    public void crearVenta(String idCliente, String paqueteOservicio, String idElegido, String usuario, String medioPago) throws MiException {
        Cliente cliente = buscarCliente(Long.valueOf(idCliente));

        //creo la venta con los datos que ya tengo
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFecha_venta(new Date());
        nuevaVenta.setMedio_pago(medioPago);
        nuevaVenta.setCliente(cliente);

        List<Empleado> listaEmpleados = traerEmpleados();

        for (Empleado unEmpleado : listaEmpleados) {
            System.out.println("ingresa a for");
            //busco al empleado que le corresponde el usuario obtenido del JSP
            if (unEmpleado.getUsuario().getNombreUsuario().equalsIgnoreCase(usuario)) {
                nuevaVenta.setUsuario(unEmpleado.getUsuario());
                System.out.println("ingresa if");

                //verifico si lo que se eligió fue un Servicio o un Paquete
                if (paqueteOservicio.equalsIgnoreCase("S")) {
                    System.out.println("es un servicio");
                    try {
                        Servicio servi = buscarServicio(Long.valueOf(idElegido));
                        nuevaVenta.setServicio(servi);
                        unEmpleado.getUsuario().getListaVentas().add(nuevaVenta);
                        cliente.getListaventas().add(nuevaVenta);
                        servi.getListaVenta().add(nuevaVenta);
                        controlPersist.crearVentaServicio(nuevaVenta, unEmpleado, cliente, servi);
                        break;
                    } catch (Exception e) {
                        throw new MiException("no era un Servicio");
                    }

                } else {
                    System.out.println("es un paquete");
                    try {
                        Paquete paque = buscarPaquete(Long.valueOf(idElegido));
                        nuevaVenta.setPaquete(paque);
                        unEmpleado.getUsuario().getListaVentas().add(nuevaVenta);
                        cliente.getListaventas().add(nuevaVenta);
                        paque.getListaVenta().add(nuevaVenta);
                        controlPersist.crearVentaPaquete(nuevaVenta, unEmpleado, cliente, paque);
                        break;
                    } catch (Exception e) {
                        throw new MiException("no era un Paquete");
                    }
                }
            }
        }

    }

    public List<Venta> traerVentas() throws MiException {
        return controlPersist.traerVentas();
    }

    public Venta buscarVenta(Long id) throws MiException {
        return controlPersist.buscarVenta(id);
    }

    public void eliminarVenta(Long idVenta) throws MiException {
        Venta ventaParaEliminar = buscarVenta(idVenta);
        Cliente cliente = ventaParaEliminar.getCliente();
        Usuario usu = ventaParaEliminar.getUsuario();
        Paquete paq = ventaParaEliminar.getPaquete();
        Servicio serv = ventaParaEliminar.getServicio();
        cliente.getListaventas().remove(ventaParaEliminar);
        usu.getListaVentas().remove(ventaParaEliminar);
        if (paq != null) {
            paq.getListaVenta().remove(ventaParaEliminar);
            controlPersist.eliminarVentaPaquete(cliente, usu, paq, ventaParaEliminar);
        }
        if (serv != null) {
            serv.getListaVenta().remove(ventaParaEliminar);
            controlPersist.eliminarVentaServicio(cliente, usu, serv, ventaParaEliminar);
        }
    }

    public void modificarVenta(String idVenta, String idCliente, String paqueteOservicio, String idElegido, String usuario, String medioPago) throws MiException {
        Venta ventaMod = buscarVenta(Long.parseLong(idVenta));
        Cliente cliente = buscarCliente(Long.valueOf(idCliente));
        //al cliente anterior le quito de su lista de ventas la venta actual para asignarsela al nuevo cliente (en caso de que hubiera cambiado)
        Cliente cliViejo = ventaMod.getCliente();
        cliViejo.getListaventas().remove(ventaMod);
        ventaMod.setFecha_venta(new Date());
        ventaMod.setMedio_pago(medioPago);
        ventaMod.setCliente(cliente);

        List<Empleado> listaEmpleados = traerEmpleados();

        //Elimino del paquete o servicio la venta por si fue cambiada
        Servicio serviViejo = ventaMod.getServicio();
        if (serviViejo != null) {
            serviViejo.getListaVenta().remove(ventaMod);
        }
        Paquete paqueViejo = ventaMod.getPaquete();
        if (paqueViejo != null) {
            paqueViejo.getListaVenta().remove(ventaMod);
        }

        for (Empleado unEmpleado : listaEmpleados) {
            //busco al empleado que le corresponde el usuario obtenido del JSP
            if (unEmpleado.getUsuario().getNombreUsuario().equalsIgnoreCase(usuario)) {
                //borro de la lista de ventas del usuario anterior para poder asignar la venta a otro usuario (si fuera otro usuario el que la modifica)
                Usuario usuViejo = ventaMod.getUsuario();
                usuViejo.getListaVentas().remove(ventaMod);
                Usuario usuNue=unEmpleado.getUsuario();
                //Guardo los datos del nuevo usuario
                ventaMod.setUsuario(usuNue);

                //verifico si lo que se eligió fue un Servicio o un Paquete
                if (paqueteOservicio.equalsIgnoreCase("S")) {
                    try {
                        Servicio servi = buscarServicio(Long.valueOf(idElegido));
                        ventaMod.setServicio(servi);
                        unEmpleado.getUsuario().getListaVentas().add(ventaMod);
                        cliente.getListaventas().add(ventaMod);
                        servi.getListaVenta().add(ventaMod);
                        if (serviViejo != null) {
                            controlPersist.modificarVentaServicio(ventaMod, cliViejo, cliente, serviViejo, usuViejo, usuNue, servi);
                        }
                        if (paqueViejo != null) {
                            controlPersist.modificarVentaServicio(ventaMod, cliViejo, cliente, paqueViejo, usuViejo, usuNue, servi);
                        }

                        break;
                    } catch (Exception e) {
                        throw new MiException("no era un Servicio");
                    }

                } else {
                    try {
                        Paquete paque = buscarPaquete(Long.valueOf(idElegido));
                        ventaMod.setPaquete(paque);
                        unEmpleado.getUsuario().getListaVentas().add(ventaMod);
                        cliente.getListaventas().add(ventaMod);
                        paque.getListaVenta().add(ventaMod);
                        //controlPersist.crearVentaPaquete(ventaMod, unEmpleado, cliente, paque);
                        if (serviViejo != null) {
                            controlPersist.modificarVentaPaquete(ventaMod, cliViejo, cliente, serviViejo, usuViejo, usuNue, paque);
                        }
                        if (paqueViejo != null) {
                            controlPersist.modificarVentaPaquete(ventaMod, cliViejo, cliente, paqueViejo, usuViejo, usuNue, paque);
                        }
                        break;
                    } catch (Exception e) {
                        throw new MiException("no era un Paquete");
                    }
                }
            }
        }

    }

}
