package Entità;
import java.util.Date; 

public class ProcuratoreSportivo {
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private String Sesso;
	private Date DataNascita;
	private String Provincia;
	private String CAP;
	public ProcuratoreSportivo(String codiceFiscale, String nome, String cognome, String sesso, Date dataNascita,
			String provincia, String cAP) {
		super();
		CodiceFiscale = codiceFiscale;
		Nome = nome;
		Cognome = cognome;
		Sesso = sesso;
		DataNascita = dataNascita;
		Provincia = provincia;
		CAP = cAP;
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
	public String getSesso() {
		return Sesso;
	}
	
	public void setSesso(String sesso) {
		Sesso = sesso;
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
	public String getCAP() {
		return CAP;
	}
	public void setCAP(String cAP) {
		CAP = cAP;
	}

}
