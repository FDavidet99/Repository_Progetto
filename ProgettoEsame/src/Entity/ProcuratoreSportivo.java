package Entity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import MyExceptions.EccezioneCF;

public class ProcuratoreSportivo extends Persona {
	
	public ProcuratoreSportivo(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita) throws SQLException, EccezioneCF {
		super(nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		
	}
	
	public ProcuratoreSportivo(String codiceFiscale,String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita) throws SQLException, EccezioneCF {
		super(codiceFiscale,nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		
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
	
}
