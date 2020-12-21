package Entità;

public class Comune {

	private String CodiceCatastale;
	private String NomeComune;
	private Provincia Provincia;
	
	public Comune(String codiceCatastale, String nome,Provincia provincia) {
		super();
		CodiceCatastale = codiceCatastale;
		NomeComune = nome;
		Provincia = provincia;
	}
	
	public Provincia getProvincia() {
		return Provincia;
	}
	
	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}
	
	public String getCodiceCatastale() {
		return CodiceCatastale;
	}
	
	public void setCodiceCatastale(String codiceCatastale) {
		CodiceCatastale = codiceCatastale;
	}
	
	public String getNome() {
		return NomeComune;
	}
	
	public void setNome(String nome) {
		NomeComune = nome;
	}

	@Override
	public String toString() {
		return NomeComune;
	}
	
}
