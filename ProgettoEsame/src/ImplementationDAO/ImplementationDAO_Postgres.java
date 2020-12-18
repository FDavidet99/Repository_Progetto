package ImplementationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementationDAO_Postgres extends ImplementationDAO {
	
	public ImplementationDAO_Postgres(Connection connection) throws SQLException {
		super();
		Connection = connection;
		StmGetCodiceCatastaleComune = Connection.prepareStatement("SELECT CodiceCatastale from Comune Where lower(NomeComune) = lower(?)");
		StmGetNomiComuni = Connection.prepareStatement("SELECT NomeComune FROM Comune");
		StmGetCodiceAt=Connection.prepareStatement("SELECT CodiceAt FROM Nazione where lower(NomeNazione)= lower(?)");
	}

	@Override
	public String getCodiceCatastale(String nomecomune) throws SQLException {
		StmGetCodiceCatastaleComune.setString(1, nomecomune);
		ResultSet rs=StmGetCodiceCatastaleComune.executeQuery();
		String CodiceCatastale=null;
		while(rs.next()) {
			 CodiceCatastale= rs.getString("codicecatastale");
		}
		rs.close();
		return CodiceCatastale;

	}

	@Override
	public List<String> getNomiComuni() throws SQLException {
		ResultSet rs=StmGetNomiComuni.executeQuery();
		ArrayList ElencoComuni=new ArrayList<>();
		
		while(rs.next()) {
			
			 ElencoComuni.add(rs.getString("nomecomune"));
		}
		return ElencoComuni;
	}

	@Override
	public String getCodiceAt(String NomeNazione) throws SQLException {
		StmGetCodiceAt.setString(1, NomeNazione);
		ResultSet rs=StmGetCodiceAt.executeQuery();
		String CodiceAt=null;
		while(rs.next()) {
			 CodiceAt= rs.getString("codiceat");
		}
		rs.close();
		return CodiceAt;
		
	}
	
	
}
