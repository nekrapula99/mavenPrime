
package com.mycompany.controller;

import Entities.Categoria;
import Entities.Nota;
import Entities.Usuario;
import ejb.CategoriaFacadeLocal;
import ejb.NotaFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class BuscarController implements Serializable{
    
    private List<Categoria> listaCate;
    private List<Nota> listaNotas;
      
    @EJB
    private CategoriaFacadeLocal EJBCategoria;
    
    @EJB
    private NotaFacadeLocal EJBNota;
    
    private int codigoCategoria;
    private Date fechaConsulta;
    
    
    @PostConstruct
    public void init(){
        
        listaCate = EJBCategoria.findAll();
    
    
    }

    public List<Categoria> getListaCate() {
        return listaCate;
    }

    public void setListaCate(List<Categoria> listaCate) {
        this.listaCate = listaCate;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public List<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }    
    
    
    public void buscar(){
        try{
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listaNotas = EJBNota.buscar(us.getCodigo().getCodigo(), codigoCategoria, fechaConsulta);
        }catch(Exception e){
            System.out.println(e.getMessage());
        
        }
    }
    
    
    
    
}

