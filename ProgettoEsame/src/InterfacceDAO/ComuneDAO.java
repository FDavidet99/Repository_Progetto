package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entit�.*;

public interface ComuneDAO {
	
	public List<String> getNomiComuni() throws SQLException; //andra in combobox
	public String getCodiceCatastale(String nomecomune) throws SQLException;
}
