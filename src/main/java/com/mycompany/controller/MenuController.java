
package com.mycompany.controller;

import Entities.Menu;
import Entities.Usuario;
import ejb.MenuFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped; // temas de CDI
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class MenuController implements Serializable{
    
    @EJB
    private MenuFacadeLocal EJBMenu;
    private List<Menu> lista;
    
    private MenuModel model;
    
    @PostConstruct
    public void init(){
        this.listarMenus();
        model = new DefaultMenuModel();
        this.establecerPermisos();
    }
    
    public void listarMenus(){
    
        try{
            System.out.println("Entro a ListarMenu");

            lista = EJBMenu.findAll();
            System.out.println(lista);

                    
        }catch(Exception e){
            //Mensaje JSF
        }
    
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
    public void establecerPermisos(){
        
      Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        for(Menu m: lista){
            if(m.getTipo().equals("S") && m.getTipoUsuario().equals(us.getTipo())){
                DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
                for(Menu i : lista){
                
                    Menu suMenu = i.getSubmenu();
                    if(suMenu!=null){
                        if(suMenu.getCodigo()==m.getCodigo()){
                            
                            DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
                            firstSubmenu.addElement(item);
                            item.setUrl(i.getUrl());
                        
                        }
                    }
                }
                model.addElement(firstSubmenu); 
            }else{
                if(m.getSubmenu()==null && m.getTipoUsuario().equals(us.getTipo())){
                    DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                    model.addElement(item); // menu principal
                     item.setUrl(m.getUrl());
                }
            }
        }
    }
    
    public void cerrarSesion(){
    
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    
    }
    
    public String mostrarUsuario(){
        
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getUsuario();

    }
    
    
}
