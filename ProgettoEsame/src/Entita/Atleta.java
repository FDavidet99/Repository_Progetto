package Entita;

import java.time.LocalDate;

import EccezioniPersona.EccezioneCF;

public class Atleta extends Persona {
	private boolean HasProcuratore;

	public Atleta(String codiceFiscale, String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			String nazioneNascita, String provinciaNascita, String comuneNascita, boolean hasProcuratore) {
		super(nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		HasProcuratore = hasProcuratore;
	}
	


}
