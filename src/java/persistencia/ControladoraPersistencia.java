package persistencia;

import java.util.List;
import logica.Cliente;
import logica.Empleado;
import logica.Paquete;
import logica.Servicio;
import logica.Usuario;
import logica.Venta;
import persistencia.exceptions.MiException;

public class ControladoraPersistencia {
    ClienteJpaController clienteJPA = new ClienteJpaController();
    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    UsuarioJpaController usuarioJPA=new UsuarioJpaController();
    PaqueteJpaController paqueteJPA=new PaqueteJpaController();
    ServicioJpaController servicioJPA=new ServicioJpaController();
    VentaJpaController ventaJPA=new VentaJpaController();
    
    
     public void crearEmpleado(Empleado emple, Usuario usu) throws MiException {
        try {
            usuarioJPA.create(usu);
            empleadoJPA.create(emple);
        } catch (Exception e) {
            throw new MiException("Error al guardar el Empleado");
        }
    }

    public List<Usuario> traerUsuarios() throws MiException {
        try {
            return usuarioJPA.findUsuarioEntities();
        } catch (Exception e) {
            throw new MiException("No se encontraron usuarios");
        }
    }

    public List<Empleado> traerEmpleados() throws MiException {
        try {
            return empleadoJPA.findEmpleadoEntities();
        } catch (Exception e) {
            throw new MiException("No se encontraron Empleados");
        }
    }

    public void crearCliente(Cliente nuevo_cliente) throws MiException {
        try {
            clienteJPA.create(nuevo_cliente);
        } catch (Exception e) {
            throw new MiException("No se puede guardar el cliente");
        }
    }

    public void crearServicio(Servicio nuevo_servicio) throws MiException {
        try {
            servicioJPA.create(nuevo_servicio);
        } catch (Exception e) {
            throw new MiException("No se puede guardar el servicio");
        }
    }

    public Empleado buscarEmpleado(Long id) throws MiException {
        
        try {
            return empleadoJPA.findEmpleado(id);
        } catch (Exception e) {
            throw new MiException("No se encontró Empleado con ese id");
        }
    }

    public void modificarEmpleado(Empleado empleadoParaModificar, Usuario usu) throws MiException {
        try {
            usuarioJPA.edit(usu);
            empleadoJPA.edit(empleadoParaModificar);
        } catch (Exception e) {
            throw new MiException("Error al modificar el Empleado");
        }
    }

    public void eliminarEmpleado(Empleado empleado, Usuario usu) throws MiException {
        try {
            //usuarioJPA.destroy(usu.getId_usuario());
            Long id=empleado.getId();
            System.out.println("String id: "+id);
            Long idUser= empleado.getUsuario().getId_usuario();
            empleadoJPA.destroy(id);
            usuarioJPA.destroy(idUser);
        } catch (Exception e) {
            System.out.println(e);
            throw new MiException("Error al eliminar el Empleado");
        }
    }

    public List<Cliente> traerClientes() throws MiException {
        try {
            return clienteJPA.findClienteEntities();
        } catch (Exception e) {
            throw new MiException("No se encontraron Clientes");
        }
    }

    public Cliente buscarCliente(Long id) throws MiException {
        try {
            return clienteJPA.findCliente(id);
        } catch (Exception e) {
            throw new MiException("No se encontró Cliente con ese id");
        }
    }

    public void modificarCliente(Cliente clienteParaModificar) throws MiException {
        try {
            clienteJPA.edit(clienteParaModificar);
        } catch (Exception e) {
            throw new MiException("Error al modificar el Cliente");
        }
    }

    public List<Servicio> traerServicios() throws MiException {
        try {
            return servicioJPA.findServicioEntities();
        } catch (Exception e) {
            throw new MiException("No se encontraron Servicios");
        }
    }

    public Servicio buscarServicio(Long id) throws MiException {
        try {
            return servicioJPA.findServicio(id);
        } catch (Exception e) {
            throw new MiException("No se encontró Servicio con ese id");
        }
    }

    public void eliminarServicio(Long id) throws MiException {
        try {
            servicioJPA.destroy(id);
        } catch (Exception e) {
            System.out.println(e);
            throw new MiException("Error al eliminar el Servicio");
        }
    }

    public void modificarServicio(Servicio servParaModificar) throws MiException {
        try {
            servicioJPA.edit(servParaModificar);
        } catch (Exception e) {
            throw new MiException("Error al modificar el Servicio");
        }
    }

    public void crearPaquete(Paquete nuevoPaquete) throws MiException {
        try {
            paqueteJPA.create(nuevoPaquete);
        } catch (Exception e) {
            throw new MiException("No se puede guardar el paquete");
        }
    }

    public List<Paquete> traerPaquetes() throws MiException {
        try {
            return paqueteJPA.findPaqueteEntities();
        } catch (Exception e) {
            throw new MiException("No se encontraron Paquetes");
        }
    }

    public Paquete buscarPaquete(Long id) throws MiException {
        try {
            return paqueteJPA.findPaquete(id);
        } catch (Exception e) {
            throw new MiException("No se encontró Paquete con ese id");
        }
    }

    public void modificarPaquete(Paquete packModificar) throws MiException {
        try {
            paqueteJPA.edit(packModificar);
        } catch (Exception e) {
            throw new MiException("Error al modificar el Paquete");
        }
    }

    public void eliminarPaquete(Long id) throws MiException {
        try {
            paqueteJPA.destroy(id);
        } catch (Exception e) {
            throw new MiException("Error al eliminar el Paquete");
        }
    }

    public void crearVentaServicio(Venta nuevaVenta, Empleado unEmpleado, Cliente cliente, Servicio servi) throws MiException {
        try {
            ventaJPA.create(nuevaVenta);
            usuarioJPA.edit(unEmpleado.getUsuario());
            empleadoJPA.edit(unEmpleado);
            clienteJPA.edit(cliente);
            servicioJPA.edit(servi);
        } catch (Exception e) {
            throw new MiException("No se puede guardar la nueva venta");
        }
    }

    public void crearVentaPaquete(Venta nuevaVenta, Empleado unEmpleado, Cliente cliente, Paquete paque) throws MiException {
        try {
            ventaJPA.create(nuevaVenta);
            usuarioJPA.edit(unEmpleado.getUsuario());
            empleadoJPA.edit(unEmpleado);
            clienteJPA.edit(cliente);
            paqueteJPA.edit(paque);
        } catch (Exception e) {
            throw new MiException("No se puede guardar la nueva venta");
        }
    }

    public List<Venta> traerVentas() throws MiException {
        try {
            return ventaJPA.findVentaEntities();
        } catch (Exception e) {
            throw new MiException("No se encontraron Paquetes");
        }
    }

    public Venta buscarVenta(Long id) throws MiException {
        try {
            return ventaJPA.findVenta(id);
        } catch (Exception e) {
            throw new MiException("No se encontro una Venta para ese ID");
        }
    }

    public void eliminarVenta(Cliente cliente, Usuario usu, Paquete paq, Servicio serv, Venta ventaParaEliminar) throws MiException {
        try {
            clienteJPA.edit(cliente);
            usuarioJPA.edit(usu);
            paqueteJPA.edit(paq);
            servicioJPA.edit(serv);
            ventaJPA.destroy(ventaParaEliminar.getIdVenta());
        } catch (Exception e) {
            throw new MiException("No se pudo eliminar la Venta seleccionada");
        }
    }

    public void eliminarVentaPaquete(Cliente cliente, Usuario usu, Paquete paq, Venta ventaParaEliminar) throws MiException {
        try {
            clienteJPA.edit(cliente);
            usuarioJPA.edit(usu);
            paqueteJPA.edit(paq);
            ventaJPA.destroy(ventaParaEliminar.getIdVenta());
        } catch (Exception e) {
            throw new MiException("No se pudo eliminar la Venta seleccionada");
        }
    }

    public void eliminarVentaServicio(Cliente cliente, Usuario usu, Servicio serv, Venta ventaParaEliminar) throws MiException {
        try {
            clienteJPA.edit(cliente);
            usuarioJPA.edit(usu);
            servicioJPA.edit(serv);
            ventaJPA.destroy(ventaParaEliminar.getIdVenta());
        } catch (Exception e) {
            throw new MiException("No se pudo eliminar la Venta seleccionada");
        }
    }

    public void modificarVentaServicio(Venta ventaMod, Cliente cliViejo, Cliente cliente, Servicio serviViejo, Usuario usuViejo, Usuario usuNue, Servicio servi) throws MiException {
        try {
            usuarioJPA.edit(usuViejo);
            usuarioJPA.edit(usuNue);
            servicioJPA.edit(serviViejo);
            clienteJPA.edit(cliente);
            clienteJPA.edit(cliViejo);
            ventaJPA.edit(ventaMod);
            servicioJPA.edit(servi);
        } catch (Exception e) {
            throw new MiException("No se pudo modificar la Venta seleccionada");
        }
    }

    public void modificarVentaServicio(Venta ventaMod, Cliente cliViejo, Cliente cliente, Paquete paqueViejo, Usuario usuViejo, Usuario usuNue, Servicio servi) throws MiException {
        try {
            usuarioJPA.edit(usuViejo);
            usuarioJPA.edit(usuNue);
            paqueteJPA.edit(paqueViejo);
            clienteJPA.edit(cliente);
            clienteJPA.edit(cliViejo);
            ventaJPA.edit(ventaMod);
            servicioJPA.edit(servi);
        } catch (Exception e) {
            throw new MiException("No se pudo modificar la Venta seleccionada");
        }
    }

    public void modificarVentaPaquete(Venta ventaMod, Cliente cliViejo, Cliente cliente, Servicio serviViejo, Usuario usuViejo, Usuario usuNue, Paquete paque) throws MiException {
        try {
            usuarioJPA.edit(usuViejo);
            usuarioJPA.edit(usuNue);
            servicioJPA.edit(serviViejo);
            paqueteJPA.edit(paque);
            clienteJPA.edit(cliente);
            clienteJPA.edit(cliViejo);
            ventaJPA.edit(ventaMod);
        } catch (Exception e) {
            throw new MiException("No se pudo modificar la Venta seleccionada");
        }
    }

    public void modificarVentaPaquete(Venta ventaMod, Cliente cliViejo, Cliente cliente, Paquete paqueViejo, Usuario usuViejo, Usuario usuNue, Paquete paque) throws MiException {
        try {
            usuarioJPA.edit(usuViejo);
            usuarioJPA.edit(usuNue);
            paqueteJPA.edit(paqueViejo);
            paqueteJPA.edit(paque);
            clienteJPA.edit(cliente);
            clienteJPA.edit(cliViejo);
            ventaJPA.edit(ventaMod);
        } catch (Exception e) {
            throw new MiException("No se pudo modificar la Venta seleccionada");
        }
    }

    

    
}
