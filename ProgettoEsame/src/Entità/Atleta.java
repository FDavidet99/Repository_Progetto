package Entità;

import java.sql.SQLException;
import java.time.LocalDate;


import EccezioniPersona.EccezioneCF;

public class Atleta extends Persona {

	private boolean HasProcuratore;

	public Atleta(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita, boolean hasProcuratore) throws SQLException {
		super(nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		setHasProcuratore(hasProcuratore);
	}

	public boolean isHasProcuratore() {
		return HasProcuratore;
	}

	public void setHasProcuratore(boolean hasProcuratore) {
		HasProcuratore = hasProcuratore;
	}

	@Override
	public String toString() {
		try {
			return "("+this.getCF()+this.getNome()+this.getCognome()+this.getSessoPersona()+this.getDataNascita()+
					this.getNazioneNascita()+this.getProvinciaNascita()+this.getComuneNascita()+this.isHasProcuratore()+")";
		} catch (EccezioneCF e) {
			return "errore";
			
		} catch (SQLException e) {
			return "errore";
		}
	}
	
	


}
