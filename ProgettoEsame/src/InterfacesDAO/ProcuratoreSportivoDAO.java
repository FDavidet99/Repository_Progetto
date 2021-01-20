package InterfacesDAO;

import java.sql.SQLException;
import java.util.List;
import Entity.ProcuratoreSportivo;
import MyExceptions.EccezioneCF;

public interface ProcuratoreSportivoDAO {
	
	public void InsertProcuratoreSportivo(ProcuratoreSportivo procuratore) throws SQLException, EccezioneCF;
	public List<ProcuratoreSportivo> GetProcuratori() throws SQLException, EccezioneCF;
	public ProcuratoreSportivo GetProcuratoreByCodiceFiscale(String CodiceFiscaleProcuratore) throws SQLException, EccezioneCF;

}
