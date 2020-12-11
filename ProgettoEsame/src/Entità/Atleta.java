package Entità;

import java.util.Date;

public class Atleta 
{
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private Sesso SessoAtleta;
	private Date DataNascita;
	private String LuogoNascita;
	
	public Atleta(String nome, String cognome, String codiceFiscale, String luogoNascita, Date dataNascita, Sesso sesso) {
		super();
		CodiceFiscale = codiceFiscale;
		Nome = nome;
		Cognome = cognome;
		SessoAtleta = sesso;
		DataNascita = dataNascita;
		LuogoNascita = luogoNascita;
	
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
	public String getLuogoNascita() {
		return LuogoNascita;
	}
	public void setLuogoNascita(String luogoNascita) {
		LuogoNascita = luogoNascita;
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
