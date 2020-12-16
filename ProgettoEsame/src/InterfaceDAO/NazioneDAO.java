package InterfaceDAO;

import java.util.List;
import Entita.Nazione;
import Entita.Provincia;

public interface NazioneDAO {
	
	public String getCodiceAt(Nazione nazione);
	public Nazione getByCodiceAt(String codAt);
	public List<Provincia> getNomeProvince(String CodiceNazione);
}
