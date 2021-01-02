package Entità;

import java.util.List;

import Eccezioni.EccezioneNazionale;

public class ClubSportivo {
	private int IdClubSportivo;
	private String NomeClub;
	private Nazione SedeLegale;
	private boolean IsNazionale;
	private List<Atleta> AtlentiComponenti;
	
	public ClubSportivo(int idClubSportivo, String nomeClub, Nazione sedeLegale,boolean isNazionale) {
		super();
		IdClubSportivo = idClubSportivo;
		NomeClub = nomeClub;
		SedeLegale = sedeLegale;
		IsNazionale=isNazionale;
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
	
	public Nazione getSedeLegale() {
		return SedeLegale;
	}
	
	public void setSedeLegale(Nazione sedeLegale) {
		SedeLegale = sedeLegale;
	}

	public boolean isIsNazionale() {
		return IsNazionale;
	}

	public void setIsNazionale(boolean isNazionale) {
		IsNazionale = isNazionale;
	}
	
	
	
	
	

}
