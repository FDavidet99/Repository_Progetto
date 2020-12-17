package ImplementationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementationClassPostgres extends ImplementationClass {
	
	public ImplementationClassPostgres(Connection connection) throws SQLException {
		super();
		Connection = connection;
		St_getCodiceCatastaleComune = Connection.prepareStatement("SELECT CodiceCatastale from Comune Where NomeComune = ?");
		St_getNomiComuni = Connection.prepareStatement("SELECT NomeComune FROM Comune");
	}

	@Override
	public String getCodiceCatastale(String nomecomune) throws SQLException {
		St_getCodiceCatastaleComune.setString(1, nomecomune);
		ResultSet rs=St_getCodiceCatastaleComune.executeQuery();
		String CodiceCatastale=null;
		while(rs.next()) {
			 CodiceCatastale= rs.getString("codicecatastale");
		}
		rs.close();
		return CodiceCatastale;

	}

	@Override
	public List<String> getNomiComuni() throws SQLException {
		ResultSet rs=St_getNomiComuni.executeQuery();
		ArrayList ElencoComuni=new ArrayList<>();
		
		while(rs.next()) {
			
			 ElencoComuni.add(rs.getString("nomecomune"));
		}
		return ElencoComuni;
	}
	
	
	
}
