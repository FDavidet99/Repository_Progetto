package Entity;

import java.util.*;

public class Provincia {
	private String NomeProvincia;
	private String Sigla;
	private Nazione Nazione;
	private List<Comune> Comuni;
	
	public Provincia(String nome, String sigla,Nazione nazione,List<Comune> comune) {
		super();
		NomeProvincia = nome;
		Sigla = sigla;
		Nazione=nazione;
		Comuni=comune;
	}
	
	public Provincia(String nome, String sigla,Nazione nazione) {
		super();
		NomeProvincia = nome;
		Sigla = sigla;
		Nazione=nazione;
		
	}
	
	public Nazione getNazione() {
		return Nazione;
	}
	
	public void setNazione(Nazione nazione) {
		Nazione = nazione;
	}
	
	public List<Comune> getComuni() {
		return Comuni;
	}
	
	public void setComuni(List<Comune> comuni) {
		Comuni = comuni;
	}
	
	public String getNome() {
		return NomeProvincia;
	}
	
	public void setNome(String nome) {
		NomeProvincia = nome;
	}
	
	public String getSigla() {
		return Sigla;
	}
	public void setSigla(String sigla) {
		Sigla = sigla;
	}

	@Override
	public String toString() {
		return "Provincia [" + (NomeProvincia != null ? "NomeProvincia=" + NomeProvincia + ", " : "")
				+ (Sigla != null ? "Sigla=" + Sigla + ", " : "") + (Nazione != null ? "Nazione=" + Nazione + ", " : "")
				+ (Comuni != null ? "Comuni=" + Comuni : "") + "]";
	}

	
	

}
