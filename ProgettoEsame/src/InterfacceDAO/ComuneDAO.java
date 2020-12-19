package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entità.*;

public interface ComuneDAO {
	
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException; //andra in combobox
	//public String getCodiceCatastale(String nomecomune) throws SQLException;
}
