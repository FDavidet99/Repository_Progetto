package Entity;

import java.util.*;


public class Nazione {
	private String CodiceAt;
	private String NomeNazione;
	private List<Provincia> Province;
	
	public Nazione(String codiceAt, String nomeNazione, List<Provincia> province) {
		super();
		CodiceAt = codiceAt;
		NomeNazione = nomeNazione;
		Province = province;
	}
	
	public Nazione(String codiceAt, String nomeNazione) {
		super();
		CodiceAt = codiceAt;
		NomeNazione = nomeNazione;
	}

	public String getCodiceAt() {
		return CodiceAt;
	}

	public void setCodiceAt(String codiceAt) {
		CodiceAt = codiceAt;
	}

	public String getNomeNazione() {
		return NomeNazione;
	}

	public void setNomeNazione(String nomeNazione) {
		NomeNazione = nomeNazione;
	}

	public List<Provincia> getProvince() {
		return Province;
	}

	public void setProvince(List<Provincia> province) {
		Province = province;
	}

	@Override
	public String toString() {
		return "Nazione [" + (CodiceAt != null ? "CodiceAt=" + CodiceAt + ", " : "")
				+ (NomeNazione != null ? "NomeNazione=" + NomeNazione + ", " : "")
				+ (Province != null ? "Province=" + Province : "") + "]";
	}

	

	
}
