
package ejb;

import Entities.Telefono;
import java.util.List;
import javax.ejb.Local;


@Local
public interface TelefonoFacadeLocal {
    
    
    void create(Telefono telefono);

    void edit(Telefono telefono);

    void remove(Telefono telefono);

    Telefono find(Object id);

    List<Telefono> findAll();

    List<Telefono> findRange(int[] range);

    int count();
    
    List<Telefono> buscarTelefono(int codigoPersona) throws Exception;
    
}
