
package com.mycompany.controller;

import Entities.Persona;
import Entities.Telefono;
import ejb.PersonaFacadeLocal;
import ejb.TelefonoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class ConsultarController implements Serializable{
    
    @EJB
    private PersonaFacadeLocal EJBPersona;
    @EJB
    private TelefonoFacadeLocal EJBTelefono;
    
    private List<Persona> listaPersonas;
    private List<Telefono> listaTelefonos;

    public List<Telefono> getListaTelefonos() {
        return listaTelefonos;
    }

    public void setListaTelefonos(List<Telefono> listaTelefonos) {
        this.listaTelefonos = listaTelefonos;
    }
    
    
    
    private int codigoPersona;

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    @PostConstruct
    public void init(){
        
       listaPersonas = EJBPersona.findAll();
    
    
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
    
    public void buscarTelefonos() throws Exception{
        try{
        
            listaTelefonos = EJBTelefono.buscarTelefono(codigoPersona);
        
        }catch(Exception e){
            throw e;
        
        }
    
    }
    
    
    
    
    
}
