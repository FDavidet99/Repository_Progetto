package Entita;

import java.util.ArrayList;

public class Nazione {
	private String CodiceAt;
	private String NomeNazione;
	private ArrayList<Provincia> Province;
	
	public Nazione(String codiceAt, String nomeNazione, ArrayList<Provincia> province ) {
		super();
		CodiceAt = codiceAt;
		NomeNazione = nomeNazione;
		Province=province;
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
	
	
}
