package interfaceDAO;

import java.util.List;

import Entita.Nazione;

public interface NazioneDAO {
	public List<Nazione> getByNomeNazione(String nome);
	public Nazione getBycodiceAt(String codAt);
}
