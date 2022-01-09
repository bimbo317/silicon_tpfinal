package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idVenta;
    @Column(name = "num_venta", columnDefinition = "BIGINT(20) NOT NULL UNIQUE KEY auto_increment")
    private Integer num_venta;
    @Temporal (TemporalType.DATE)
    private Date fecha_venta;
    @Basic
    private String medio_pago;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Paquete paquete;
    @ManyToOne
    private Servicio servicio;

    public Venta() {
    }

    public Venta(Long idVenta, Integer num_venta, Date fecha_venta, String medio_pago, Cliente cliente, Usuario usuario, Paquete paquete, Servicio servicio) {
        this.idVenta = idVenta;
        this.num_venta = num_venta;
        this.fecha_venta = fecha_venta;
        this.medio_pago = medio_pago;
        this.cliente = cliente;
        this.usuario = usuario;
        this.paquete = paquete;
        this.servicio = servicio;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getNum_venta() {
        return num_venta;
    }

    public void setNum_venta(Integer num_venta) {
        this.num_venta = num_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    
}
