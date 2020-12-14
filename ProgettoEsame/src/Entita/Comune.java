package Entita;

public class Comune {

	private String codiceCatastale;
	private String nome;
	private Provincia provincia;
	public Comune(String codiceCatastale, String nome,Provincia provincia) {
		super();
		this.codiceCatastale = codiceCatastale;
		this.nome = nome;
		this.provincia = provincia;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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
