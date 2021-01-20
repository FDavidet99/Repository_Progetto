package InterfacesDAO;

import java.sql.SQLException;
import java.util.List;
import Entity.Sponsor;

public interface SponsorDAO {
	public List<Sponsor> GetSponsor() throws SQLException;
	public Sponsor GetSponsorById(int IdSponsor) throws SQLException;
}
