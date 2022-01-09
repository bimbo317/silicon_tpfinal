package logica;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Empleado extends Persona{
    @Column(name = "cod_empleado", columnDefinition = "BIGINT(20) NOT NULL UNIQUE KEY auto_increment")
    private Integer cod_empleado;
    @OneToOne
    private Usuario usuario;
    @Basic
    private String cargo;
    private Double sueldo;
    
    public Empleado() {
    }

    public Empleado(Usuario usuario, String cargo, Double sueldo, String nombre, String apellido, String direccion, String dni, Date fechaNac, String nacionalidad, String celular, String email) {
        super(nombre, apellido, direccion, dni, fechaNac, nacionalidad, celular, email);
        
        this.usuario = usuario;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }
    

    public Integer getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(Integer cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
