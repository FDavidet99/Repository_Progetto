package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entit�.*;

public interface ProvinciaDAO {
	
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException;
}
