package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Eccezioni.EccezioneCF;
import Entit�.Atleta;
import Entit�.ProcuratoreSportivo;

public interface ProcuratoreSportivoDAO {
	
	public void InsertProcuratoreSportivo(ProcuratoreSportivo procuratore) throws SQLException, EccezioneCF;
	public List<ProcuratoreSportivo> GetProcuratori() throws SQLException, EccezioneCF;
	public ProcuratoreSportivo GetProcuratoreByCodiceFiscale(String CodiceFiscaleProcuratore) throws SQLException, EccezioneCF;

}
