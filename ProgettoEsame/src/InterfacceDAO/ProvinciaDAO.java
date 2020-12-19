package InterfacceDAO;

import java.util.List;

import Entità.*;

public interface ProvinciaDAO {
	
	public List<Comune> getComuni(Provincia provincia);
}
