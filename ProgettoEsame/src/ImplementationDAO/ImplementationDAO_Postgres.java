package ImplementationDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entità.*;


public class ImplementationDAO_Postgres extends ImplementationDAO {
	
	public ImplementationDAO_Postgres(Connection connection) throws SQLException {
		super();
		Connection = connection;
		StmGetNazioni=Connection.prepareStatement("Select * from Nazione");
		StmGetProvinceByNazione=Connection.prepareStatement("Select * from Provincia Where lower(Codicenazione)= lower(?) ");
		StmGetComuniByProvincia = Connection.prepareStatement("SELECT * FROM Comune Where lower(NomeProvincia)= lower(?)");
		//StmGetCodiceAtByNazione=Connection.prepareStatement("SELECT CodiceAt FROM Nazione where lower(NomeNazione)= lower(?)");
		//StmGetCodiceCatastaleComune = Connection.prepareStatement("SELECT CodiceCatastale from Comune Where lower(NomeComune) = lower(?)");
		
	}

//	@Override
//	public String getCodiceCatastale(String nomecomune) throws SQLException {
//		StmGetCodiceCatastaleComune.setString(1, nomecomune);
//		ResultSet rs=StmGetCodiceCatastaleComune.executeQuery();
//		String CodiceCatastale=null;
//		while(rs.next()) {
//			 CodiceCatastale= rs.getString("codicecatastale");
//		}
//		rs.close();
//		return CodiceCatastale;
//
//	}

//	@Override
//	public List<String> getNomiComuni() throws SQLException {
//		ResultSet rs=StmGetNomiComuni.executeQuery();
//		ArrayList ElencoComuni=new ArrayList<>();
//		
//		while(rs.next()) {
//			
//			 ElencoComuni.add(rs.getString("nomecomune"));
//		}
//		return ElencoComuni;
//	}

//	@Override
//	public String GetCodiceAt(String NomeNazione) throws SQLException {
//		StmGetCodiceAtByNazione.setString(1, NomeNazione);
//		ResultSet rs=StmGetCodiceAtByNazione.executeQuery();
//		String CodiceAt=null;
//		while(rs.next()) {
//			 CodiceAt= rs.getString("codiceat");
//		}
//		rs.close();
//		return CodiceAt;
//		
//	}

	@Override
	public List<Nazione> GetNazioni() throws SQLException {
		ResultSet rs=StmGetNazioni.executeQuery();
		ArrayList<Nazione> ListaNazioni=new ArrayList<Nazione>();
		while(rs.next()) {
			Nazione Temp=new Nazione(rs.getString("codiceat"), rs.getString("nomenazione"));
			Temp.setProvince(GetProvinceByNazione(Temp));
			ListaNazioni.add(Temp);
		}
		rs.close();
		return ListaNazioni;
	}

	@Override
	public List<Provincia> GetProvinceByNazione(Nazione nazione) throws SQLException {
		StmGetProvinceByNazione.setString(1, nazione.getCodiceAt());
		ResultSet rs=StmGetProvinceByNazione.executeQuery();
		ArrayList<Provincia> ListaProvince=new ArrayList<Provincia>();
		while(rs.next()) {
			Provincia Temp=new Provincia(rs.getString("nomeprovincia"), rs.getString("siglaprovincia"),nazione);
			Temp.setComuni(GetComuniByProvincia(Temp));
			ListaProvince.add(Temp);
		}
		rs.close();
		return ListaProvince;
	}

	@Override
	public List<Comune> GetComuniByProvincia(Provincia provincia) throws SQLException {
		StmGetComuniByProvincia.setString(1,provincia.getNome());
		ResultSet rs=StmGetComuniByProvincia.executeQuery();
		ArrayList<Comune> ListaComuni=new ArrayList<Comune>();
		while(rs.next()) {
			Comune Temp=new Comune(rs.getString("codicecatastale"), rs.getString("nomecomune"),provincia);
			ListaComuni.add(Temp);
		}
		rs.close();
		return ListaComuni;
	}
	
	
	
	
}
