package Entit�;

import java.util.Date;

public class Atleta 
{
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private Sesso Sesso;
	private Date DataNascita;
	private String Provincia;
	
	public Atleta(String nome, String cognome, String codiceFiscale, String provincia, Date dataNascita, Sesso sesso) {
		super();
		Nome = nome;
		Cognome = cognome;
		CodiceFiscale = codiceFiscale;
		Provincia = provincia;
		DataNascita = dataNascita;
		Sesso = sesso;
	
	}

	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public Date getDataNascita() {
		return DataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		DataNascita = dataNascita;
	}
	public Sesso getSessoAtleta() {
		return SessoAtleta;
	}
	public void setSessoAtleta(Sesso sessoAtleta) {
		SessoAtleta = sessoAtleta;
	}
	
}
