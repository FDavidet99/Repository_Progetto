package Entità;
import java.util.Date; 

public class ProcuratoreSportivo {
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private Sesso SessoProcuratore;
	private Date DataNascita;
	private String Provincia;
	
	public ProcuratoreSportivo(String codiceFiscale, String nome, String cognome, Sesso sesso, Date dataNascita,
			String provincia) {
		super();
		CodiceFiscale = codiceFiscale;
		Nome = nome;
		Cognome = cognome;
		SessoProcuratore = sesso;
		DataNascita = dataNascita;
		Provincia = provincia;
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

	public Sesso getSesso() {
		return SessoProcuratore;
	}

	public void setSesso(Sesso sesso) {
		SessoProcuratore = sesso;
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

	


}
