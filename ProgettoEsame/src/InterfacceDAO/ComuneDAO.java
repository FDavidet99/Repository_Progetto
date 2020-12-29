package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entità.*;

public interface ComuneDAO {
	
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException;
	public Comune GetComuneByCodiceCatastale(String codiceCatastale) throws SQLException;
}
