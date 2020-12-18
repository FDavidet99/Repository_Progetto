package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;
import Entita.*;

public interface ComuneDAO {
	
	public List<String> getNomiComuni() throws SQLException; //andra in combobox
	public String getCodiceCatastale(String nomecomune) throws SQLException;
}
