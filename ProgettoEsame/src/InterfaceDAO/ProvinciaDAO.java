package InterfaceDAO;

import java.util.List;
import Entita.*;

public interface ProvinciaDAO {
	
	public Provincia getByNomeProvincia(String nome);
	public List<Comune> getComuni(Provincia provincia);
}
