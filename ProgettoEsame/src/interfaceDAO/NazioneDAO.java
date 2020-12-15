package interfaceDAO;

import java.util.List;
import Entita.Nazione;

public interface NazioneDAO {
	
	public List<Nazione> getNomeNazione(String nome);
	public Nazione getCodiceAt(String codAt);
}
