package ejb;

import Entities.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless // sin estado
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_mavenproject2_war_1.0-SNAPSHOTPU") // invoca mi unidad de persistencia
    private EntityManager em; // me permite manejar mis operaciones CRUD a mi DB 

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    //Constructor a traves del super se manda el tipo de dato
    public CategoriaFacade() {
        super(Categoria.class); 
    }
    
}
