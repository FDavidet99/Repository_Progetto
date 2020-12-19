package InterfacceDAO;

import java.util.List;

import Entità.*;

public interface ProvinciaDAO {
	
	public Provincia getByNomeProvincia(String nome);
	public List<Comune> getComuni(Provincia provincia);
}
