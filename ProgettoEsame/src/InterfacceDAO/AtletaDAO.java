package InterfacceDAO;

import java.sql.SQLException;

import EccezioniPersona.EccezioneCF;
import Entità.Atleta;

public interface AtletaDAO {
	public void InsertAtleta(Atleta atleta) throws SQLException, EccezioneCF;
}
