package interfaceDAO;

import java.util.List;

import Entita.Provincia;

public interface ProvinciaDAO {
	public List<Provincia> getByNomeProvincia(String nome);
	public Provincia getBySigla(String sigla);
}
