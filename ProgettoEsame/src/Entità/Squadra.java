package Entità;

import java.util.List;

import Eccezioni.EccezioneNazionale;

public class Squadra {
	private String NomeSquadra;
	private Nazione Nazione;
    private boolean IsNazionale;
	private ClubSportivo ClubAppartenenza;	
	private List<Presenza> AtletiInSquadra;
	
	public Squadra(String nomeSquadra, Entità.Nazione nazione, boolean isNazionale, ClubSportivo clubAppartenenza) {
		super();
		NomeSquadra = nomeSquadra;
		Nazione = nazione;
		IsNazionale = isNazionale;
		if(IsNazionale)
			ClubAppartenenza = null;
	}
	
	public Squadra(String nomeSquadra, Entità.Nazione nazione, boolean isNazionale) {
		super();
		NomeSquadra = nomeSquadra;
		Nazione = nazione;
		if(IsNazionale)
			ClubAppartenenza = null;
	}

	public String getNomeSquadra() {
		return NomeSquadra;
	}
	
	public void setNomeSquadra(String nomeSquadra) {
		NomeSquadra = nomeSquadra;
	}
	
	public Nazione getNazione() {
		return Nazione;
	}
	
	public void setNazione(Nazione nazione) {
		Nazione = nazione;
	}
	
	public boolean isIsNazionale() {
		return IsNazionale;
	}
	
	public void setIsNazionale(boolean isNazionale) {
		IsNazionale = isNazionale;
	}
	
	public ClubSportivo getClubAppartenenza() {
		return ClubAppartenenza;
	}
	
	public void setClubAppartenenza(ClubSportivo clubAppartenenza) throws EccezioneNazionale {
		if(!IsNazionale)
			ClubAppartenenza = clubAppartenenza;
		else 
			throw new EccezioneNazionale();
			
	}
	
	
}
