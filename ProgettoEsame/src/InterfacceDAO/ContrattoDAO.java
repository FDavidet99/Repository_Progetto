package InterfacceDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Eccezioni.EccezioneCF;
import Entit�.Atleta;
import Entit�.Contratto;
import Entit�.Ingaggio;
import Entit�.ProcuratoreSportivo;

public interface ContrattoDAO {
	
	public void InsertContratto(Contratto contratto) throws SQLException, EccezioneCF;
	public List<Contratto> GetContratti() throws SQLException, EccezioneCF;
	public List<Contratto> GetContrattiAttivi() throws SQLException, EccezioneCF;
	public List<Contratto> GetMaxContrattiAtleta(Atleta atleta, Date datainizio, Date dataFine) throws EccezioneCF, SQLException;
	public List<Contratto> GetMaxContrattiProc(ProcuratoreSportivo proc, Date datainizio, Date dataFine) throws EccezioneCF, SQLException;
	public List<Ingaggio> GetIngaggiVantaggiosi(ProcuratoreSportivo proc, Date datainizio, Date dataFine) throws EccezioneCF, SQLException;
}
