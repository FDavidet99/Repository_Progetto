package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entità.*;

public interface ComuneDAO {
	public Comune getComuneByCodiceCatastale(String codiceCatastale) throws SQLException;
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException; //andra in combobox
}
