package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entit�.ClubSportivo;
import Entit�.Sponsor;

public interface SponsorDAO {
	public List<Sponsor> GetSponsor() throws SQLException;
	public Sponsor GetSponsorById(int IdSponsor) throws SQLException;
}
