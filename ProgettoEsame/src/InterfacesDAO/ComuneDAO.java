package InterfacesDAO;

import java.sql.SQLException;
import java.util.List;

import Entity.*;

public interface ComuneDAO {
	
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException;
	public Comune GetComuneByCodiceCatastale(String codiceCatastale) throws SQLException;
}
