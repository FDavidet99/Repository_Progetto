package Entità;

import java.util.ArrayList;

public class Provincia {
	private String Nome;
	private String Sigla;
	private ArrayList<Comune> Comuni;
	private Nazione Nazione;
	
	public Provincia(String nome, String sigla,ArrayList<Comune> comuni, Nazione nazione) {
		super();
		Nome = nome;
		Sigla = sigla;
		Comuni = comuni;
		Nazione = nazione;
	}
	
	public Nazione getNazione() {
		return Nazione;
	}
	
	public void setNazione(Nazione nazione) {
		Nazione = nazione;
	}
	
	public ArrayList<Comune> getComuni() {
		return Comuni;
	}
	
	public void setComuni(ArrayList<Comune> comuni) {
		Comuni = comuni;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public String getSigla() {
		return Sigla;
	}
	public void setSigla(String sigla) {
		Sigla = sigla;
	}
	

}
