package Entità;

import java.util.Date;

public class Atleta 
{
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private Sesso SessoAtleta;
	private Date DataNascita;
	private String Provincia;
	private String LuogoNascita;
	public Atleta(String codiceFiscale, String nome, String cognome, Sesso sessoAtleta, Date dataNascita,
			String provincia, String luogoNascita) {
		super();
		CodiceFiscale = codiceFiscale;
		Nome = nome;
		Cognome = cognome;
		SessoAtleta = sessoAtleta;
		DataNascita = dataNascita;
		Provincia = provincia;
		LuogoNascita = luogoNascita;
	}
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
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
	public Sesso getSessoAtleta() {
		return SessoAtleta;
	}
	public void setSessoAtleta(Sesso sessoAtleta) {
		SessoAtleta = sessoAtleta;
	}
	public Date getDataNascita() {
		return DataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		DataNascita = dataNascita;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public String getLuogoNascita() {
		return LuogoNascita;
	}
	public void setLuogoNascita(String luogoNascita) {
		LuogoNascita = luogoNascita;
	}
	
	
	
}
