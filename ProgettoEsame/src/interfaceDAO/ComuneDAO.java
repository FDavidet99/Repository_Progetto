package interfaceDAO;

import java.util.List;
import Entita.*;

public interface ComuneDAO {
	
	public List<Comune> getNomeComune(String nome);
	public Comune getcodCatastale(String codCatastale);
}
