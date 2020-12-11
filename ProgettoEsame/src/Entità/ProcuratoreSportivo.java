package Entità;
import java.util.Date; 

public class ProcuratoreSportivo {
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private Sesso SessoProcuratore;
	private Date DataNascita;
	private String Provincia;
	private String LuogoNascita;
	public ProcuratoreSportivo(String codiceFiscale, String nome, String cognome, Sesso sessoProcuratore,
			Date dataNascita, String provincia, String luogoNascita) {
		super();
		CodiceFiscale = codiceFiscale;
		Nome = nome;
		Cognome = cognome;
		SessoProcuratore = sessoProcuratore;
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
	public Sesso getSessoProcuratore() {
		return SessoProcuratore;
	}
	public void setSessoProcuratore(Sesso sessoProcuratore) {
		SessoProcuratore = sessoProcuratore;
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
