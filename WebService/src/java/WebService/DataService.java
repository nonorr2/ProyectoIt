package WebService;

import DAO.TematicaDAO;
import POJOs.Tematica;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "DataService")
public class DataService {

    @WebMethod(operationName = "getTematicas")
    public List<Tematica> getTematicas() {
        TematicaDAO tematicaDao = new TematicaDAO();
        return tematicaDao.getTematicas();
    }

    @WebMethod(operationName = "getTematica")
    public Tematica getTematica(@WebParam(name = "id") int id) {
        TematicaDAO tematicaDao = new TematicaDAO();
        return tematicaDao.getTematica(id);
    }
}
