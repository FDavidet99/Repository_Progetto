package implementationDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import implementationDAO.*;
import DatabaseUtility.*;
import Entita.Comune;
import InterfaceDAO.ComuneDAO;

public class ComuneDaoPostgresImplementation implements ComuneDAO {
	private Connection Connection;
    private PreparedStatement getCodiceCastaleComune;
    
	public ComuneDaoPostgresImplementation(Connection connection) throws SQLException {
		super();
		Connection = connection;
		getCodiceCastaleComune=Connection.prepareStatement("SELECT codicecatastale from comune Where nomecomune = ?");
	}

	@Override
	public String getCodiceCatastale(String nomecomune) throws SQLException {
		getCodiceCastaleComune.setString(1, nomecomune);
		ResultSet rs=getCodiceCastaleComune.executeQuery();
		String CodiceCatastale=null;
		while(rs.next()) {
			 CodiceCatastale= rs.getString("codicecatastale");
		}
		return CodiceCatastale;

	}
    
    

}
