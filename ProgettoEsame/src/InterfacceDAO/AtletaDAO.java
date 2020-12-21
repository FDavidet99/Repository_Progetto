package InterfacceDAO;

import java.sql.SQLException;

import Entità.Atleta;

public interface AtletaDAO {
	public void InsertAtleta(Atleta atleta) throws SQLException;
}
