package InterfacceDAO;

import java.sql.SQLException;

import EccezioniPersona.EccezioneCF;
import Entit�.Atleta;

public interface AtletaDAO {
	public void InsertAtleta(Atleta atleta) throws SQLException, EccezioneCF;
}
