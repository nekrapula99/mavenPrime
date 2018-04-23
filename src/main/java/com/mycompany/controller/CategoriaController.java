package com.mycompany.controller;

import Entities.Categoria;
import ejb.CategoriaFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


//Anotaciones para reconocer el Framework JSF
@Named  //inyecciones de dependndias
@ViewScoped
public class CategoriaController implements Serializable{
    
    //Inyección de EJB  (LAS INTERFACES NO SE INSTANCIAN, SIMPLEMENTE LAS CLASES SOBRE ESCRIBEN LOS EMTODOS)
    @EJB //para evitame la palabra new tales 
    private CategoriaFacadeLocal categoriaEJB;
   
    @Inject
    private Categoria categoria; //para poder guardar los objetos.

    public Categoria getCategoria() {
        return categoria;
    } 

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    //(Un constructor se define automaticamente en java si no se define)
    @PostConstruct
    public void init(){
       // categoria = new Categoria(); //instanciando objeto
    }
    
    public void registrar(){
    
      try{
          categoriaEJB.create(categoria);
      
      }catch(Exception e){
      //Mensaje prime grwol
      }
    
    }
    
}
