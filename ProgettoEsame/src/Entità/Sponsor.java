package Entità;

import java.util.List;

public class Sponsor {
	private int IdSponsor;
	private String NomeSponsor;
	private String DescrizioneAttivita;
	private Nazione SedeLegale;
	
	public Sponsor(String nomeSponsor, String descrizioneAttivita, Nazione sedeLegale) {
		super();
		NomeSponsor = nomeSponsor;
		DescrizioneAttivita = descrizioneAttivita;
		SedeLegale = sedeLegale;
	}

	public int getIdSponsor() {
		return IdSponsor;
	}

	public void setIdSponsor(int idSponsor) {
		IdSponsor = idSponsor;
	}

	public String getNomeSponsor() {
		return NomeSponsor;
	}

	public void setNomeSponsor(String nomeSponsor) {
		NomeSponsor = nomeSponsor;
	}

	public String getDescrizioneAttivita() {
		return DescrizioneAttivita;
	}

	public void setDescrizioneAttivita(String descrizioneAttivita) {
		DescrizioneAttivita = descrizioneAttivita;
	}

	public Nazione getSedeLegale() {
		return SedeLegale;
	}

	public void setSedeLegale(Nazione sedeLegale) {
		SedeLegale = sedeLegale;
	}
	
	
	
	

}
