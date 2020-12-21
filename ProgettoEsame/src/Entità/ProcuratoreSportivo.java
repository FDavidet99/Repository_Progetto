package Entit�;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import EccezioniPersona.EccezioneCF;

public class ProcuratoreSportivo extends Persona {
	private List<Contratto> Contratti;

	public ProcuratoreSportivo(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita, List<Contratto> contratti)
			throws SQLException {
		super(nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		Contratti = contratti;
	}
	
	@Override
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
	
	
	

}
