package ImplementationDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Eccezioni.EccezioneCF;
import Entità.*;


public class ImplementationDAO_Postgres extends ImplementationDAO {
	
	public ImplementationDAO_Postgres(Connection connection) throws SQLException {
		super();
		Connection = connection;
		StmGetNazioni=Connection.prepareStatement("SELECT * FROM Nazione");
		StmGetProvinceByNazione=Connection.prepareStatement("SELECT * FROM Provincia Where lower(Codicenazione)= lower(?)");
		StmGetComuniByProvincia = Connection.prepareStatement("SELECT * FROM Comune Where lower(NomeProvincia)= lower(?)");
		StmGetComuneByCodiceCatastale = Connection.prepareStatement("SELECT * FROM comune where codicecatastale like ?");
		StmGetNazioniByCodiceAt = Connection.prepareStatement("SELECT * FROM nazione where codiceat like ?");
		StmGetProvinciaByNome = Connection.prepareStatement("SELECT * FROM provincia where nomeprovincia like ?");
		StmInsertProcuratoreSportivo=Connection.prepareStatement("Insert into ProcuratoreSportivo values (?,?,?,?,?,?,?,?)");
		StmInsertAtleta=Connection.prepareStatement("INSERT INTO atleta values (?,?,?,?,?,?,?,?,?)");
		StmGetAtleti = Connection.prepareStatement("SELECT * FROM atleta;");
		StmGetProcuratori = Connection.prepareStatement("SELECT * from ProcuratoreSportivo;");
		StmInsertIngaggio=Connection.prepareStatement("INSERT INTO Ingaggio values (?,?,?,?,?);");
		StmGetIngaggiByAtleta=Connection.prepareStatement("SELECT * FROM Ingaggio WHERE CodiceFiscaleAtleta= ?;");
		StmGetIngaggiByProcuratore=Connection.prepareStatement("SELECT * FROM Ingaggio WHERE CodiceFiscaleProcuratore=?;");
		StmGetProcuratoreByCodiceFiscale=Connection.prepareStatement("SELECT * FROM ProcuratoreSportivo WHERE CodiceFiscale= ?;");
		StmGetAtletaByCodiceFiscale=Connection.prepareStatement("SELECT * FROM Atleta WHERE CodiceFiscale= ?;");
		StmGetClubSportivi=Connection.prepareStatement("SELECT * FROM ClubSportivo;");
		StmGetSponsor=Connection.prepareStatement("SELECT * FROM Sponsor;");
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
	public Nazione GetNazioneByCodiceAt(String codiceAt) throws SQLException {
		StmGetNazioniByCodiceAt.setString(1, codiceAt);
		ResultSet rs=StmGetNazioniByCodiceAt.executeQuery();
		Nazione nazione=null;
		while(rs.next()) {
			nazione = new Nazione(rs.getString("codiceat"), rs.getString("nomenazione"));
		}
		rs.close();
		return nazione;
	}

	@Override
	public Provincia GetProvinciaByNome(String nome) throws SQLException {
		StmGetProvinciaByNome.setString(1, nome);
		ResultSet rs=StmGetProvinciaByNome.executeQuery();
		Provincia provincia=null;
		while(rs.next()) {
			provincia = new Provincia( rs.getString("nomeprovincia"), rs.getString("siglaprovincia"),
					GetNazioneByCodiceAt(rs.getString("codicenazione")));
		}
		
		rs.close();
		return provincia;
	}

	@Override
	public Comune GetComuneByCodiceCatastale(String codiceCatastale) throws SQLException {
		StmGetComuneByCodiceCatastale.setString(1, codiceCatastale);
		ResultSet rs=StmGetComuneByCodiceCatastale.executeQuery();
		Comune comune=null;
		while(rs.next()) {
			comune = new Comune(rs.getString("codicecatastale"), rs.getString("nomecomune"),
					GetProvinciaByNome((rs.getString("nomeprovincia"))));
		}
		rs.close();
		return comune;
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
		
		StmInsertProcuratoreSportivo.executeUpdate();
	}
	
	@Override
	public List<Atleta> GetAtleti() throws SQLException, EccezioneCF {
		ResultSet rs=StmGetAtleti.executeQuery();
		ArrayList<Atleta> atleti=new ArrayList<Atleta>();
		while(rs.next()) {
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("nazionenascita"));
			Provincia provincia = GetProvinciaByNome(rs.getString("provincianascita"));
			Comune comune = GetComuneByCodiceCatastale(rs.getString("comunenascita"));
			Sesso sesso = Sesso.F;
			if(rs.getString("sesso").equals("M"))
				sesso = Sesso.M;
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			LocalDate dataNascita =  LocalDate.parse(rs.getString("datanascita"));
			boolean hasProcuratore =  rs.getBoolean("hasprocuratore");
			Atleta TmpAtleta=new Atleta(nome,cognome,sesso,dataNascita,
						nazione,provincia,comune,hasProcuratore);
			atleti.add(TmpAtleta);
		}
		rs.close();
		return atleti;
	}

	@Override
	public List<ProcuratoreSportivo> GetProcuratori() throws SQLException, EccezioneCF {
		ResultSet rs=StmGetProcuratori.executeQuery();
		ArrayList<ProcuratoreSportivo> Procuratori=new ArrayList<ProcuratoreSportivo>();
		while(rs.next()) {
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			Sesso sesso = Sesso.F;
			if(rs.getString("sesso").equals("M"))
				sesso = Sesso.M;
			LocalDate dataNascita =  LocalDate.parse(rs.getString("datanascita"));
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("nazionenascita"));
			Provincia provincia = GetProvinciaByNome(rs.getString("provincianascita"));
			Comune comune = GetComuneByCodiceCatastale(rs.getString("comunenascita"));
			ProcuratoreSportivo TmpProcuratore=new ProcuratoreSportivo(nome,cognome,sesso,dataNascita,
						nazione,provincia,comune);
			Procuratori.add(TmpProcuratore);
		}
		rs.close();
		return Procuratori;
	}
	
	@Override
	public ProcuratoreSportivo GetProcuratoreByCodiceFiscale(String CodiceFiscaleProcuratore) throws SQLException, EccezioneCF {
		StmGetProcuratoreByCodiceFiscale.setString(1, CodiceFiscaleProcuratore);
		ResultSet rs=StmGetProcuratoreByCodiceFiscale.executeQuery();
		ProcuratoreSportivo Procuratore=null;
		while(rs.next()) {
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			Sesso sesso = Sesso.F;
			if(rs.getString("sesso").equals("M"))
				sesso = Sesso.M;
			LocalDate dataNascita =  LocalDate.parse(rs.getString("datanascita"));
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("nazionenascita"));
			Provincia provincia = GetProvinciaByNome(rs.getString("provincianascita"));
			Comune comune = GetComuneByCodiceCatastale(rs.getString("comunenascita"));
			
			Procuratore=new ProcuratoreSportivo(CodiceFiscaleProcuratore,nome,cognome,sesso,dataNascita,
						nazione,provincia,comune);
		}
		rs.close();
		return Procuratore;
	}
	
	public Atleta GetAtletaByCodiceFiscale (String CodiceFiscaleAtleta) throws SQLException, EccezioneCF {
		StmGetAtletaByCodiceFiscale.setString(1, CodiceFiscaleAtleta);
		ResultSet rs=StmGetAtletaByCodiceFiscale.executeQuery();
		Atleta Atleta=null;
		while(rs.next()) {
			String nome = rs.getString("nome");
			String cognome = rs.getString("cognome");
			Sesso sesso = Sesso.F;
			if(rs.getString("sesso").equals("M"))
				sesso = Sesso.M;
			LocalDate dataNascita =  LocalDate.parse(rs.getString("datanascita"));
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("nazionenascita"));
			Provincia provincia = GetProvinciaByNome(rs.getString("provincianascita"));
			Comune comune = GetComuneByCodiceCatastale(rs.getString("comunenascita"));
			Boolean hasprocuratore=rs.getBoolean("hasprocuratore");
			
			Atleta=new Atleta(CodiceFiscaleAtleta,nome,cognome,sesso,dataNascita,
						nazione,provincia,comune,hasprocuratore);
		}
		rs.close();
		return Atleta;
	}

	@Override
	public void InsertIngaggio(Ingaggio ingaggio) throws SQLException, EccezioneCF {
		StmInsertIngaggio.setString(1,ingaggio.getProcuratore().getCF());
		StmInsertIngaggio.setString(2,ingaggio.getAtleta().getCF());
		StmInsertIngaggio.setObject(3,ingaggio.getDataInizio());
		StmInsertIngaggio.setObject(4,ingaggio.getDataFine());
		StmInsertIngaggio.setObject(5,ingaggio.getStipendioProcuratore());
		StmInsertIngaggio.executeUpdate();
	}

	@Override
	public List<Ingaggio> GetIngaggiByAtleta(Atleta atleta) throws SQLException, EccezioneCF {
		StmGetIngaggiByAtleta.setString(1,atleta.getCF());
		ResultSet rs= StmGetIngaggiByAtleta.executeQuery();
		List<Ingaggio> IngaggiAtleta= new ArrayList<Ingaggio>();
		while(rs.next()) {
			String CodiceFiscaleProcuratore=rs.getString("codicefiscaleprocuratore");
			LocalDate DataInizio =  LocalDate.parse(rs.getString("datainizio"));
			LocalDate DataFine =  LocalDate.parse(rs.getString("datafine"));
			Ingaggio TmpIngaggio=new Ingaggio(GetProcuratoreByCodiceFiscale(CodiceFiscaleProcuratore), atleta, DataInizio, DataFine, rs.getDouble("stipendioprocuratore"));
			IngaggiAtleta.add(TmpIngaggio);
		}
		rs.close();
		return IngaggiAtleta;
	}

	@Override
	public List<Ingaggio> GetIngaggiByProcuratore(ProcuratoreSportivo procuratore) throws EccezioneCF, SQLException {
		StmGetIngaggiByProcuratore.setString(1,procuratore.getCF());
		ResultSet rs= StmGetIngaggiByProcuratore.executeQuery();
		List<Ingaggio> IngaggiProcuratore=new ArrayList<Ingaggio>();
		while(rs.next()) {
			String CodiceFiscaleAtleta=rs.getString("codicefiscaleatleta");
			LocalDate DataInizio =  LocalDate.parse(rs.getString("datainizio"));
			LocalDate DataFine =  LocalDate.parse(rs.getString("datafine"));
			Ingaggio TmpIngaggio=new Ingaggio(procuratore, GetAtletaByCodiceFiscale(CodiceFiscaleAtleta) , DataInizio, DataFine, rs.getDouble("stipendioprocuratore"));
			IngaggiProcuratore.add(TmpIngaggio);
		}
		rs.close();
		return IngaggiProcuratore;
	}

	@Override
	public List<ClubSportivo> GetClubSportivi() throws SQLException {
		ResultSet rs=StmGetClubSportivi.executeQuery();
		ArrayList<ClubSportivo> ClubSportivi=new ArrayList<ClubSportivo>();
		while(rs.next()) {
			int IdClub = rs.getInt("idclub");
			String Nome=rs.getString("nome");
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("sedelegale"));
			boolean IsNazionale =  rs.getBoolean("isnazionale");
			ClubSportivo TmpClub=new ClubSportivo(IdClub,Nome,nazione,IsNazionale);
			ClubSportivi.add(TmpClub);	
		}
		rs.close();
		return ClubSportivi;
	}

	@Override
	public List<Sponsor> GetSponsor() {
		// TODO Auto-generated method stub
		return null;
	}


	

	
}
	

