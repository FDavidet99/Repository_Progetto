package interfaceDAO;

import java.util.List;
import Entita.Provincia;

public interface ProvinciaDAO {
	
	public List<Provincia> getNomeProvincia(String nome);
	public Provincia getSigla(String sigla);
}
