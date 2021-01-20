package InterfacesDAO;

import java.sql.SQLException;
import java.util.List;

import Entity.*;

public interface ProvinciaDAO {
	
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException;
	public Provincia GetProvinciaByNome(String nome) throws SQLException;
	
}
