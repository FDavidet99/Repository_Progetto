package interfaceDAO;
import java.util.List;

import Entita.*;
public interface ComuneDAO {
	public List<Comune> getByNomeComune(String nome);
	public Comune getBycodCatastale(String codCatastale);
}
