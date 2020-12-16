package implementationDAO;

import java.util.List;

import Entita.Comune;
import Entita.Nazione;
import Entita.Provincia;
import interfaceDAO.*;

public class ImplemDAO implements ComuneDAO,ProvinciaDAO,NazioneDAO{

	public ImplemDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Nazione> getByNomeNazione(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nazione getBycodiceAt(String codAt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Provincia> getByNomeProvincia(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provincia getBySigla(String sigla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comune> getByNomeComune(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comune getBycodCatastale(String codCatastale) {
		// TODO Auto-generated method stub
		return null;
	}

}
