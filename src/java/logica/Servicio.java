package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "cod_servicio", columnDefinition = "BIGINT(20) NOT NULL UNIQUE KEY auto_increment")
    private Integer cod_servicio;
    @Temporal (TemporalType.DATE)
    private Date fecha_servicio;
    @ManyToMany
    private List<Paquete> lista_paquetes;
    @OneToMany
    private List<Venta> listaVenta;
    @Basic
    private Double costo_servicio;
    private String nombre;
    private String descripcion;
    private String destino;

    public Servicio() {
    }

    public Servicio(Long id, Integer cod_servicio, Date fecha_servicio, List<Paquete> lista_paquetes, List<Venta> listaVenta, Double costo_servicio, String nombre, String descripcion, String destino) {
        this.id = id;
        this.cod_servicio = cod_servicio;
        this.fecha_servicio = fecha_servicio;
        this.lista_paquetes = lista_paquetes;
        this.listaVenta = listaVenta;
        this.costo_servicio = costo_servicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCod_servicio() {
        return cod_servicio;
    }

    public void setCod_servicio(Integer cod_servicio) {
        this.cod_servicio = cod_servicio;
    }

    public Date getFecha_servicio() {
        return fecha_servicio;
    }

    public void setFecha_servicio(Date fecha_servicio) {
        this.fecha_servicio = fecha_servicio;
    }

    public List<Paquete> getLista_paquetes() {
        return lista_paquetes;
    }

    public void setLista_paquetes(List<Paquete> lista_paquetes) {
        this.lista_paquetes = lista_paquetes;
    }

    public List<Venta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public Double getCosto_servicio() {
        return costo_servicio;
    }

    public void setCosto_servicio(Double costo_servicio) {
        this.costo_servicio = costo_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    
}
