package InterfaceDAO;

import java.sql.SQLException;
import java.util.List;
import Entita.*;

public interface ComuneDAO {
	
	public List<String> getNomiComuni() throws SQLException;
	public String getCodiceCatastale(String nomecomune) throws SQLException;
}
