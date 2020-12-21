package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entità.*;

public interface ProvinciaDAO {
	
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException;
}
