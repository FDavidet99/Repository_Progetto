package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.ProcuratoreSportivo;

public interface AtletaDAO {
	public void InsertAtleta(Atleta atleta) throws SQLException, EccezioneCF;
	public List<Atleta> GetAtleti() throws SQLException, EccezioneCF;
	public Atleta GetAtletaByCodiceFiscale (String CodiceFiscaleAtleta) throws SQLException, EccezioneCF;
	public ProcuratoreSportivo GetProcuratoreAttivo(Atleta atleta) throws SQLException, EccezioneCF;
}
