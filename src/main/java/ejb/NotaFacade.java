
package ejb;

import Entities.Nota;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;



@Stateless
public class NotaFacade extends AbstractFacade<Nota> implements NotaFacadeLocal{
    
    @PersistenceContext(unitName = "com.mycompany_mavenproject2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotaFacade() {
        super(Nota.class);
    }

    @Override
    public List<Nota> buscar(int codigoPersona, int codigoCategoria, Date fechaConsulta) throws Exception{
        List<Nota> lista;
        try{
        
            String jpql = "FROM Nota n WHERE n.persona.codigo = ?1 AND n.categoria.codigo = ?2 AND n.fecha BETWEEN ?3 AND ?4";
            Query query = em.createQuery(jpql);
            query.setParameter(1, codigoPersona);
            query.setParameter(2, codigoCategoria);
            query.setParameter(3, fechaConsulta,TemporalType.DATE);
            Calendar cal = Calendar.getInstance(); //obtengo el calendario
            cal.setTime(fechaConsulta);// se le establece el calenfario a la variable
            cal.add(Calendar.DATE,1); //Agrego un dia al calendario
            query.setParameter(4, cal,TemporalType.DATE);
            
            lista = query.getResultList();
            
            
        }catch(Exception e){
            throw e;
        }
        return lista;
    }
    
}
