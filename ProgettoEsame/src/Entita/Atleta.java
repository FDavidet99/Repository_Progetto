package Entita;

import java.time.LocalDate;

import EccezioniPersona.EccezioneCF;

public class Atleta extends Persona {
	
	public Atleta(String nome, String cognome, LocalDate dataNascita, String comuneNascita, Sesso sesso)
			throws EccezioneCF {
		super(nome, cognome, dataNascita, comuneNascita, sesso);
	}

	public Atleta(String nome, String cognome, LocalDate dataNascita, Sesso sesso, String nazioneNascita)
			throws EccezioneCF {
		super(nome, cognome, dataNascita, sesso, nazioneNascita);
	}

}
