/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Entities.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_mavenproject2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario iniciarSesion(Usuario us){ //Valor que viene del controlador
        Usuario usuario= null;
        String consulta;
        
        
        ResultSet rs = null;
        
        
        try{
            consulta  = "FROM usuario A WHERE A.usuario = ?1 AND A.clave = ?2";
            Query query = em.createQuery(consulta);
            
            query.setParameter(1,us.getUsuario());
            query.setParameter(2,us.getClave());
            
            List<Usuario> lista = query.getResultList();
             //System.out.println("la lista: "+lista);
             
          //Iterator iter = lista.iterator();
            //while (iter.hasNext()){
              //      System.out.println(iter.next());
            //}
            
            if(!lista.isEmpty()){
                usuario=lista.get(0);         
            }
    //
        
        }catch(Exception e){
            throw e;
        }
        //finally{
            //em.close();
        //}
        return usuario;  
    }
    
}
