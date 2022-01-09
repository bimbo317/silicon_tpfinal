package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Persona implements Serializable{
    @Column(name = "cod_cliente", columnDefinition = "BIGINT(20) NOT NULL UNIQUE KEY auto_increment")
    private Integer cod_cliente;
    @OneToMany
    private List<Venta> listaventas;
    @Basic
    private Boolean alta;

    public Cliente() {
    }

    public Cliente(List<Venta> listaventas, Boolean alta, String nombre, String apellido, String direccion, String dni, Date fechaNac, String nacionalidad, String celular, String email) {
        super(nombre, apellido, direccion, dni, fechaNac, nacionalidad, celular, email);
        this.listaventas = listaventas;
        this.alta = alta;
    }

    public Integer getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public List<Venta> getListaventas() {
        return listaventas;
    }

    public void setListaventas(List<Venta> listaventas) {
        this.listaventas = listaventas;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    
}
