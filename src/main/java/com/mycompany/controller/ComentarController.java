
package com.mycompany.controller;

import Entities.Nota;
import ejb.NotaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ComentarController implements Serializable{
    
    
    @EJB
    private NotaFacadeLocal EJBNota;
    
    private List<Nota> notas;
    private Nota nota;
    
    @PostConstruct
    public void init(){
            
      notas = EJBNota.findAll(); 
    
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    

    public void asignar(Nota nota){
        System.out.println("Asignar??");
        this.nota=nota;
    
    }

}
