package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entit�.Sponsor;

public interface SponsorDAO {
	public List<Sponsor> GetSponsor() throws SQLException;

}
