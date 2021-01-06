package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entità.ClubSportivo;
import Entità.Sponsor;

public interface SponsorDAO {
	public List<Sponsor> GetSponsor() throws SQLException;
	public Sponsor GetSponsorById(int IdSponsor) throws SQLException;
}
