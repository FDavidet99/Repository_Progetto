package interfaceDAO;

import java.sql.SQLException;
import java.util.List;
import Entita.*;

public interface ComuneDAO {
	
	
	public String getCodiceCatastale(String nomecomune) throws SQLException;
}
