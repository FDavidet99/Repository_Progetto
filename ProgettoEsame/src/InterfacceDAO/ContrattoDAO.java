package InterfacceDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Contratto;

public interface ContrattoDAO {
	
	public void InsertContratto(Contratto contratto) throws SQLException, EccezioneCF;
	public List<Contratto> GetContratti() throws SQLException, EccezioneCF;
	public List<Contratto> GetContrattiAttivi() throws SQLException, EccezioneCF;
	public List GetMaxContrattiAtleta(Atleta atleta, Date datainizio, Date dataFine) throws EccezioneCF, SQLException;

}
