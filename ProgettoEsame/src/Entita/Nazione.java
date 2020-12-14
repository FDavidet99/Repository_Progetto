package Entita;

public class Nazione {
	private String codiceAt;
	private String nome;
	public Nazione(String codiceAt, String nome) {
		super();
		this.codiceAt = codiceAt;
		this.nome = nome;
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
