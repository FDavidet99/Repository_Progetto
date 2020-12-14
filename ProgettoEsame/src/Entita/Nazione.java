package Entita;

import java.util.ArrayList;

public class Nazione {
	private String codiceAt;
	public ArrayList<Provincia> getProvince() {
		return province;
	}
	public void setProvince(ArrayList<Provincia> province) {
		this.province = province;
	}
	private String nome;
	private ArrayList<Provincia> province;
	
	public Nazione(String codiceAt, String nome, ArrayList<Provincia> province) {
		super();
		this.codiceAt = codiceAt;
		this.nome = nome;
		this.province = province;
	}
	public String getCodiceAt() {
		return codiceAt;
	}
	public void setCodiceAt(String codiceAt) {
		this.codiceAt = codiceAt;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
