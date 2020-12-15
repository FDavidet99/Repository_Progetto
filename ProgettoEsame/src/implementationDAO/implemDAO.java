package implementationDAO;

import java.util.List;

import Entita.Comune;
import Entita.Nazione;
import Entita.Provincia;
import interfaceDAO.*;

public class implemDAO implements ComuneDAO,ProvinciaDAO,NazioneDAO{

	public implemDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Nazione> getNomeNazione(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nazione getCodiceAt(String codAt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Provincia> getNomeProvincia(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provincia getSigla(String sigla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comune> getNomeComune(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comune getCodCatastale(String codCatastale) {
		// TODO Auto-generated method stub
		return null;
	}

}
