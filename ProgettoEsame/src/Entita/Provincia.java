package Entita;

import java.util.ArrayList;

public class Provincia {
	private String nome;
	private String sigla;
	private ArrayList<Comune> comuni;
	private Nazione nazione;
	public Provincia(String nome, String sigla,ArrayList<Comune> comuni,Nazione nazione) {
		super();
		this.nome = nome;
		this.sigla = sigla;
		this.comuni = comuni;
		this.nazione = nazione;
	}
	public Nazione getNazione() {
		return nazione;
	}
	public void setNazione(Nazione nazione) {
		this.nazione = nazione;
	}
	public ArrayList<Comune> getComuni() {
		return comuni;
	}
	public void setComuni(ArrayList<Comune> comuni) {
		this.comuni = comuni;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	

}
