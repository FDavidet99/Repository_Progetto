package Entità;

import java.util.List;

import Eccezioni.EccezioneNazionale;

public class ClubSportivo {
	private int IdClubSportivo;
	private String NomeClub;
	private Provincia SedeLegale;
	private List<Squadra> ElencoSquadre;
	
	public ClubSportivo(int idClubSportivo, String nomeClub, Provincia sedeLegale) {
		super();
		IdClubSportivo = idClubSportivo;
		NomeClub = nomeClub;
		SedeLegale = sedeLegale;
	}
	
	public int getIdClubSportivo() {
		return IdClubSportivo;
	}
	
	public void setIdClubSportivo(int idClubSportivo) {
		IdClubSportivo = idClubSportivo;
	}
	
	public String getNomeClub() {
		return NomeClub;
	}
	
	public void setNomeClub(String nomeClub) {
		NomeClub = nomeClub;
	}
	
	public Provincia getSedeLegale() {
		return SedeLegale;
	}
	
	public void setSedeLegale(Provincia sedeLegale) {
		SedeLegale = sedeLegale;
	}
	
	public void AddSquadra(Squadra squadra) throws EccezioneNazionale {
		squadra.setClubAppartenenza(this);
		ElencoSquadre.add(squadra);
	}
	
	public void RemoveSquadra(Squadra squadra) throws EccezioneNazionale {
		ElencoSquadre.remove(ElencoSquadre.indexOf(squadra));
	}
	
	

}
