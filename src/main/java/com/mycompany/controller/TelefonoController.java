
package com.mycompany.controller;

import Entities.Telefono;
import Entities.Usuario;
import ejb.TelefonoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class TelefonoController implements Serializable{
    
    @EJB
    private TelefonoFacadeLocal EJBTelefono;
    
    @Inject
    private Telefono telefono;
    private List<Telefono> listaTelefonos;
    
    private String action;
    
    
    @PostConstruct
    public void init(){
    
        listaTelefonos = EJBTelefono.findAll();
    
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public List<Telefono> getListaTelefonos() {
        return listaTelefonos;
    }

    public void setListaTelefonos(List<Telefono> listaTelefonos) {
        this.listaTelefonos = listaTelefonos;
    }
    
    public void registrarTelefono(){
         Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         telefono.setPersona(us.getCodigo()); //Obtengo un codigo tipo persona
         EJBTelefono.create(telefono); //Persistencia
         listaTelefonos= EJBTelefono.findAll(); //Listar todos los telefonos
 
    }
    
    public void leer(Telefono telSeleccion){
    
        telefono=telSeleccion;
        this.setAction("M");
    
    }
    
    public void modificar(){
    EJBTelefono.edit(telefono); // le paso telefono por que tiene la ultima referencia en el dialogo
    
    }
    
    //Mostrar Dialogo desde el Controller
    public void mostrarDialogo(){
        //Logica
        this.setAction("R");
        RequestContext req = RequestContext.getCurrentInstance();
        req.execute("PF('wdialogo').show();");
    
    }
    

}
