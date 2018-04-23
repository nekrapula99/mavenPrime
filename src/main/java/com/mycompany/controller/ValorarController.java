
package com.mycompany.controller;

import Entities.Nota;
import ejb.NotaFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ValorarController implements Serializable{
    
    @EJB
    private NotaFacadeLocal EJBNota;
    
    @Inject
    private ComentarController comentarController;   
    private Nota nota;
    
    @PostConstruct
    public void init(){
        this.nota = comentarController.getNota();

    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    
    public void registrar(){
        
        System.out.println("Entro a Registrar");
        try{
            EJBNota.edit(nota);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Valoración exitosa!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error","No se pudo!"));
        }finally{
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true); //Para que me guarde los mensajes
        }
    
    }
    
    
}
