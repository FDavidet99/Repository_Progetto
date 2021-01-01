package Entità;

import java.util.List;

import Eccezioni.EccezioneNazionale;

public class ClubSportivo {
	private int IdClubSportivo;
	private String NomeClub;
	private Provincia SedeLegale;
	private List<Atleta> AtlentiComponenti;
	
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
	
	
	

}
