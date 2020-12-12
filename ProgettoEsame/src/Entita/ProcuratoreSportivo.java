package Entita;

import java.time.LocalDate;

import EccezioniPersona.EccezioneCF;

public class ProcuratoreSportivo extends Persona {

	public ProcuratoreSportivo(String nome, String cognome, LocalDate dataNascita, String comuneNascita, Sesso sesso)
			throws EccezioneCF {
		super(nome, cognome, dataNascita, comuneNascita, sesso);
	}

	public ProcuratoreSportivo(String nome, String cognome, LocalDate dataNascita, Sesso sesso, String nazioneNascita)
			throws EccezioneCF {
		super(nome, cognome, dataNascita, sesso, nazioneNascita);
	}

}
