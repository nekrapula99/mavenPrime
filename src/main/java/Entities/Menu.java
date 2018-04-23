package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "menu")
public class Menu implements Serializable {

   // private static final long serialVersionUID = 1L;
    
    @Id
    private int codigo;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "url")
    private String url;
    
    
    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "tipoUsuario")
    private String tipoUsuario;
    

    @Column(name = "estado")
    private boolean estado = true;
    
    @ManyToOne
    @JoinColumn(name = "codigo_Submenu")
    private Menu Submenu;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Menu getSubmenu() {
        return Submenu;
    }

    public void setSubmenu(Menu Submenu) {
        this.Submenu = Submenu;
    }

  

 
}