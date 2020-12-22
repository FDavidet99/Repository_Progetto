package DatabaseUtility;

import java.sql.SQLException;

import InterfaceDAO.*;

public abstract class ImplementationClass implements ComuneDAO {

	@Override
	public abstract String getCodiceCatastale(String nomecomune) ;
	
	

}
