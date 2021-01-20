package InterfacesDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Entity.Atleta;
import Entity.Contratto;
import Entity.Ingaggio;
import Entity.ProcuratoreSportivo;
import MyExceptions.EccezioneCF;

public interface ContrattoDAO {
	
	public void InsertContratto(Contratto contratto) throws SQLException, EccezioneCF;
	public List<Contratto> GetContratti() throws SQLException, EccezioneCF;
	public List<Contratto> GetContrattiAttivi() throws SQLException, EccezioneCF;
	public List<Contratto> GetMaxContrattiAtleta(Atleta atleta, Date datainizio, Date dataFine) throws EccezioneCF, SQLException;
	public List<Contratto> GetMaxContrattiProcuratori(ProcuratoreSportivo proc, Date datainizio, Date dataFine) throws EccezioneCF, SQLException;
	public List<Ingaggio> GetIngaggiMigliori(ProcuratoreSportivo proc, Date datainizio, Date dataFine) throws EccezioneCF, SQLException;
}
