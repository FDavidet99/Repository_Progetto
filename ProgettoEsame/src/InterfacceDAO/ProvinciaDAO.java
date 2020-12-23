package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entità.*;

public interface ProvinciaDAO {
	public Provincia getProvinciaByNome(String nome) throws SQLException;
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException;
}
