package ImplementationDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import EccezioniPersona.EccezioneCF;
import Entità.*;


public class ImplementationDAO_Postgres extends ImplementationDAO {
	
	public ImplementationDAO_Postgres(Connection connection) throws SQLException {
		super();
		Connection = connection;
		StmGetNazioni=Connection.prepareStatement("Select * from Nazione");
		StmGetProvinceByNazione=Connection.prepareStatement("Select * from Provincia Where lower(Codicenazione)= lower(?)");
		StmGetComuniByProvincia = Connection.prepareStatement("SELECT * FROM Comune Where lower(NomeProvincia)= lower(?)");
		StmInsertProcuratoreSportivo=Connection.prepareStatement("Insert into ProcuratoreSportivo values (?,?,?,?,?,?,?,?);");
		StmInsertAtleta=Connection.prepareStatement("Insert into atleta values (?,?,?,?,?,?,?,?,?);");
		
	
	}

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


	@Override
	public void InsertAtleta(Atleta atleta) throws SQLException, EccezioneCF {
		StmInsertAtleta.setString(1,atleta.getCF());
		StmInsertAtleta.setString(2,atleta.getNome());
		StmInsertAtleta.setString(3,atleta.getCognome());
		StmInsertAtleta.setObject(4,atleta.getSessoPersona(),Types.OTHER);
		StmInsertAtleta.setObject(5,atleta.getDataNascita());
		StmInsertAtleta.setString(6,atleta.getNazioneNascita().getCodiceAt());
		if(atleta.getNazioneNascita().getCodiceAt().equals("Z000")) {
			StmInsertAtleta.setString(7,atleta.getProvinciaNascita().getNome()); 
			StmInsertAtleta.setString(8,atleta.getComuneNascita().getCodiceCatastale());
		}
		else {
			StmInsertAtleta.setString(7,null); 
			StmInsertAtleta.setString(8,null);
		}
		StmInsertAtleta.setBoolean(9,atleta.isHasProcuratore());	
		int RigheAggiunte=StmInsertAtleta.executeUpdate();
		
	}

	@Override
	public void InsertProcuratoreSportivo(ProcuratoreSportivo procuratore) throws SQLException, EccezioneCF {
		StmInsertProcuratoreSportivo.setString(1,procuratore.getCF());
		StmInsertProcuratoreSportivo.setString(2,procuratore.getNome());
		StmInsertProcuratoreSportivo.setString(3,procuratore.getCognome());
		StmInsertProcuratoreSportivo.setObject(4,procuratore.getSessoPersona(),Types.OTHER);
		StmInsertProcuratoreSportivo.setObject(5,procuratore.getDataNascita());
		StmInsertProcuratoreSportivo.setString(6,procuratore.getNazioneNascita().getCodiceAt());
		if(procuratore.getNazioneNascita().getCodiceAt().equals("Z000")) {
			StmInsertProcuratoreSportivo.setString(7,procuratore.getProvinciaNascita().getNome()); 
			StmInsertProcuratoreSportivo.setString(8,procuratore.getComuneNascita().getCodiceCatastale());
		}
		else {
			StmInsertProcuratoreSportivo.setString(7,null); 
			StmInsertProcuratoreSportivo.setString(8,null);
		}
			
		int RigheAggiunte=StmInsertProcuratoreSportivo.executeUpdate();
		}

	
	
	
	
	
}
