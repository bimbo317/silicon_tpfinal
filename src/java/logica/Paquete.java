package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Paquete implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "cod_paquete", columnDefinition = "BIGINT(20) NOT NULL UNIQUE KEY auto_increment")
    private Integer cod_paquete;
    @ManyToMany
    private List<Servicio> lista_servicios_incluidos;
    @OneToMany
    private List<Venta> listaVenta;
    @Basic
    private Double costo_paquete;

    public Paquete() {
    }

    public Paquete(Long id, Integer cod_paquete, List<Servicio> lista_servicios_incluidos, List<Venta> listaVenta, Double costo_paquete) {
        this.id = id;
        this.cod_paquete = cod_paquete;
        this.lista_servicios_incluidos = lista_servicios_incluidos;
        this.listaVenta = listaVenta;
        this.costo_paquete = costo_paquete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCod_paquete() {
        return cod_paquete;
    }

    public void setCod_paquete(Integer cod_paquete) {
        this.cod_paquete = cod_paquete;
    }

    public List<Servicio> getLista_servicios_incluidos() {
        return lista_servicios_incluidos;
    }

    public void setLista_servicios_incluidos(List<Servicio> lista_servicios_incluidos) {
        this.lista_servicios_incluidos = lista_servicios_incluidos;
    }

    public List<Venta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public Double getCosto_paquete() {
        return costo_paquete;
    }

    public void setCosto_paquete(Double costo_paquete) {
        this.costo_paquete = costo_paquete;
    }

    
}
