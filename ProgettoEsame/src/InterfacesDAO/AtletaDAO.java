package InterfacesDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Entity.Atleta;
import Entity.ProcuratoreSportivo;
import MyExceptions.EccezioneCF;

public interface AtletaDAO {
	public void InsertAtleta(Atleta atleta) throws SQLException, EccezioneCF;
	public List<Atleta> GetAtleti() throws SQLException, EccezioneCF;
	public Atleta GetAtletaByCodiceFiscale (String CodiceFiscaleAtleta) throws SQLException, EccezioneCF;
	public ProcuratoreSportivo GetProcuratoreAttivo(Atleta atleta,Date DataInizio,Date DataFine) throws SQLException, EccezioneCF;
}
