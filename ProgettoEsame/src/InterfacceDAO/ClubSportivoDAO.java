package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entit�.ClubSportivo;

public interface ClubSportivoDAO {
	public List<ClubSportivo> GetClubSportivi() throws SQLException;

}
