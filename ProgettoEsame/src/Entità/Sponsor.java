package Entità;

import java.util.List;

public class Sponsor {
	private int IdSponsor;
	private String NomeSponsor;
	private String DescrizioneAttivita;
	private Provincia SedeLegale;
	
	public Sponsor(int idSponsor, String nomeSponsor, String descrizioneAttivita, Provincia sedeLegale) {
		super();
		IdSponsor = idSponsor;
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

	public Provincia getSedeLegale() {
		return SedeLegale;
	}

	public void setSedeLegale(Provincia sedeLegale) {
		SedeLegale = sedeLegale;
	}
	
	
	
	

}
