package InterfacesDAO;

import java.sql.SQLException;
import java.util.List;

import Entity.Atleta;
import Entity.Ingaggio;
import Entity.ProcuratoreSportivo;
import MyExceptions.EccezioneCF;

public interface IngaggioDAO {
	
	public void InsertIngaggio(Ingaggio ingaggio) throws SQLException, EccezioneCF;
	public List<Ingaggio> GetIngaggiByAtleta (Atleta atleta ) throws SQLException,EccezioneCF;
	public List<Ingaggio> GetIngaggiByProcuratore (ProcuratoreSportivo procuratore)  throws SQLException, EccezioneCF;
	public List<Ingaggio> GetIngaggiByProcuratoreAttivi(ProcuratoreSportivo procuratore) throws EccezioneCF, SQLException;

}
