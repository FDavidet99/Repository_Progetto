package Entity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import MyExceptions.EccezioneCF;

public class Atleta extends Persona {
	
	private boolean HasProcuratore;

	public Atleta(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita, boolean hasProcuratore) throws SQLException, EccezioneCF {
		super(nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		HasProcuratore=hasProcuratore;
	}
	
	public Atleta(String codiceFiscale,String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita, boolean hasProcuratore) throws SQLException, EccezioneCF {
		super(codiceFiscale,nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		HasProcuratore=hasProcuratore;
	}

	public boolean HasProcuratore() {
		return HasProcuratore;
	}

	public void setHasProcuratore(boolean hasProcuratore) {
		HasProcuratore = hasProcuratore;
	}
	
	@Override
	public String toString()  {
		try {
			return  (getCF() != null ? getCF() + ", " : "")
					+ (getNome() != null ?  getNome() + ", " : "")
					+ (getCognome() != null ? getCognome() + ", " : "")
					+ (getSessoPersona() != null ? getSessoPersona() + ", " : "")
					+ (getDataNascita() != null ? getDataNascita() + ", " : "")
					+ (getNazioneNascita() != null ? getNazioneNascita().getNomeNazione() + ", " : "")
					+ (getProvinciaNascita() != null ? getProvinciaNascita().getNome() + ", " : "")
					+ (getComuneNascita() != null ? getComuneNascita().getNome() : "") +", "
			        + HasProcuratore;
		} catch (EccezioneCF | SQLException e) {
			return "errore";
		}
		
	}


	
	
	


}
