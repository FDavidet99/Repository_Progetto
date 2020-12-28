package Entità;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import Eccezioni.EccezioneCF;

public class ProcuratoreSportivo extends Persona {
	private List<Contratto> Contratti;
	private List<Ingaggio> IngaggioDaAtleta;

	public ProcuratoreSportivo(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita, List<Contratto> contratti) throws SQLException {
		super(nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		Contratti = contratti;
	}
	
	public String toString() {
		try {
			return "("+this.getCF()+this.getNome()+this.getCognome()+this.getSessoPersona()+this.getDataNascita()+
					this.getNazioneNascita()+this.getProvinciaNascita()+this.getComuneNascita()+")";
		} catch (EccezioneCF e) {
			return "errore";
			
		} catch (SQLException e) {
			return "errore";
		}
	}
	
	public void AddIngaggioDaAtleta(Ingaggio ingaggio) {
		ingaggio.setProcuratore(this); //non serve
		IngaggioDaAtleta.add(ingaggio);
	}

}
