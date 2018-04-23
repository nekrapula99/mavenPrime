package com.mycompany.controller;


import Entities.Usuario;
import ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped //Actualizaciones con ajax
public class IndexController implements Serializable{

    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
    
        usuario = new Usuario();
    
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){ //Regla de navegacion
        String redireccion= null;
        Usuario us;
        //System.out.println(EJBUsuario.iniciarSesion(usuario));
        
        try{
            
            us = EJBUsuario.iniciarSesion(usuario);
            
            //System.out.println(us);
                    
           if(null != us){
               //Almacenar en la sesion de JSF
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
               //redireccion ="/protegido/principal"; //Navegación implicita,porque no veo la ruta en el nav
               redireccion ="/protegido/principal?faces-redirect=true";//Navegación explicita
           }
           else{
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Datos Incorrectos!"));
           }
        
        }catch(Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","ERROR EN DB!"));
        
        }
            return redireccion;
    }
    
}
