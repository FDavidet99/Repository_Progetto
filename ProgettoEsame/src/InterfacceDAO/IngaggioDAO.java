package InterfacceDAO;

import java.sql.SQLException;
import Eccezioni.EccezioneCF;
import Entit�.Ingaggio;

public interface IngaggioDAO {
	
	public void InsertIngaggio(Ingaggio ingaggio) throws SQLException, EccezioneCF;

}
