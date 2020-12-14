package Entita;

public class Comune {

	private String codiceCatastale;
	private String nome;
	public Comune(String codiceCatastale, String nome) {
		super();
		this.codiceCatastale = codiceCatastale;
		this.nome = nome;
	}
	public String getCodiceCatastale() {
		return codiceCatastale;
	}
	public void setCodiceCatastale(String codiceCatastale) {
		this.codiceCatastale = codiceCatastale;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
