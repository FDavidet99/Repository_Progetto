package ImplementationDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entity.*;
import MyExceptions.EccezioneCF;

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
		StmInsertContratto=Connection.prepareStatement("INSERT INTO Contratto (datainizio, datafine,compenso,"
				+ "tipocontratto,guadagnoprocuratore,codicefiscaleprocuratore,codicefiscaleatleta,sponsor,"
				+ "club,gettonepresenzanazionale) values (?,?,?,?,?,?,?,?,?,?);");
		StmGetProcuratoreAttivo = Connection.prepareStatement("SELECT * FROM Ingaggio where CodiceFiscaleAtleta= ? and  "
				+ " ?::date>=datainizio and ?::date <=datafine ;");
		StmGetSponsorById=Connection.prepareStatement("SELECT * FROM Sponsor Where Idsponsor=?;");
		StmGetClubById=Connection.prepareStatement("SELECT * FROM ClubSportivo Where IdClub=?;");
		StmGetContratti = Connection.prepareStatement("SELECT * From contratto;");
		StmGetContrattiAttivi = Connection.prepareStatement("SELECT * FROM Contratto where ?::date>=datainizio and ?::date <datafine ;");
		StmGetIngaggiByProcuratoreAttivi=Connection.prepareStatement("SELECT * FROM Ingaggio WHERE CodiceFiscaleProcuratore=? AND ?::date>=datainizio and ?::date <=datafine;");
		
		StmGetMaxContrattiAtleta=Connection.prepareStatement("(Select IdContratto,tipocontratto,club as Entita,compenso,datainizio,datafine,GettonepresenzaNazionale From Contratto "+
				" where codicefiscaleatleta=? AND tipocontratto= 'Club' and compenso = ( "+
				"Select max(compenso) From Contratto  where tipocontratto= 'Club' "+
				"And ((datainizio>= ?::date and datafine <= ?::date) OR (?::date>=datainizio AND  ?::date <= datafine)) AND codicefiscaleatleta=? )) "+
				"union "+
				"(Select IdContratto,tipocontratto,sponsor as Entita,compenso,datainizio,datafine,GettonepresenzaNazionale From Contratto "+
				"where codicefiscaleatleta=? AND tipocontratto= 'Sponsor' and compenso = ( "+
				"Select max(compenso) From Contratto  where tipocontratto= 'Sponsor'  "+
				"And ((datainizio>= ?::date and datafine <= ?::date) OR (?::date>=datainizio AND  ?::date <= datafine)) AND codicefiscaleatleta=? )) "+
				"union "+
				"(Select IdContratto,tipocontratto,club as Entita,compenso,datainizio,datafine,GettonepresenzaNazionale From Contratto "+
			    "where codicefiscaleatleta=? AND tipocontratto= 'Club' and GettonepresenzaNazionale=( "+
			    "Select max(GettonepresenzaNazionale) From Contratto  where tipocontratto= 'Club' "+	
			    "And ((datainizio>= ?::date and datafine <= ?::date) OR (?::date>=datainizio AND  ?::date <= datafine)) AND codicefiscaleatleta=? ))");
		
		
		StmGetMaxContrattiProcuratori = Connection.prepareStatement("(Select IdContratto,tipocontratto,club as Entita,guadagnoprocuratore From Contratto "+
				" where codicefiscaleprocuratore=? AND  tipocontratto= 'Club' and guadagnoprocuratore = ( "+
				"Select max(guadagnoprocuratore) From Contratto  where tipocontratto= 'Club' "+
				"AND codicefiscaleprocuratore=? And ((datainizio>= ?::date and datafine <= ?::date) OR (?::date>=datainizio AND  ?::date <= datafine)) ))"+
				"union "+
				"(Select IdContratto,tipocontratto,sponsor as Entita,guadagnoprocuratore From Contratto "+
				"where codicefiscaleprocuratore=? AND  tipocontratto= 'Sponsor' and guadagnoprocuratore = ( "+
				"Select max(guadagnoprocuratore) From Contratto  where tipocontratto= 'Sponsor'  "+
				" AND codicefiscaleprocuratore=? AND ((datainizio>= ?::date and datafine <= ?::date) OR (?::date>=datainizio AND  ?::date <= datafine)) ))");
					
		StmGetInaggiMigliori = Connection.prepareStatement("select datainizio,datafine,codicefiscaleatleta,stipendioprocuratore  "+
				"from ingaggio where codicefiscaleprocuratore = ? "+
			    "AND datainizio>= ?::date and datafine <= ?::date "+
				"AND stipendioprocuratore*greatest(1,(DATE_PART('month', AGE(datainizio, datafine)))) = "+
				"(SELECT max(stipendioprocuratore*greatest(1,(DATE_PART('month', AGE(datainizio, datafine))))) "+
				"FROM ingaggio where codicefiscaleprocuratore = ? "+
				"AND datainizio>= ?::date and datafine <= ?::date);");
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
		StmInsertAtleta.setBoolean(9,atleta.HasProcuratore());	
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
		ArrayList<ClubSportivo> ClubList=new ArrayList<ClubSportivo>();
		while(rs.next()) {
			int IdClub = rs.getInt("idclub");
			String Nome=rs.getString("nome");
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("sedelegale"));
			boolean IsNazionale =  rs.getBoolean("isnazionale");
			ClubSportivo TmpClub=new ClubSportivo(Nome,nazione,IsNazionale);
			TmpClub.setIdClubSportivo(IdClub);
			ClubList.add(TmpClub);	
		}
		rs.close();
		return ClubList;
	}

	@Override
	public List<Sponsor> GetSponsor() throws SQLException {
		ResultSet rs=StmGetSponsor.executeQuery();
		ArrayList<Sponsor> SponsorList=new ArrayList<Sponsor>();
		while(rs.next()) {
			int idSponsor =rs.getInt("idsponsor");
			String nome = rs.getString("nome");
			String descrizione = rs.getString("descrizione");	
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("sedelegale"));
			Sponsor TmpSponsor=new Sponsor(nome,descrizione,nazione);
			TmpSponsor.setIdSponsor(idSponsor);
			SponsorList.add(TmpSponsor);
		}
		rs.close();
		return SponsorList;
	}

	@Override
	public void InsertContratto(Contratto contratto) throws SQLException, EccezioneCF {
		StmInsertContratto.setObject(1,contratto.getDataInizio());
		StmInsertContratto.setObject(2,contratto.getDataFine());
		StmInsertContratto.setDouble(3,contratto.getCompensoAtleta());
		StmInsertContratto.setObject(4,contratto.getTipo(),Types.OTHER);
		StmInsertContratto.setDouble(5,contratto.getCompensoProcuratore());
		
		if(contratto.getProcuratoreInteressato() != null)
		StmInsertContratto.setString(6,contratto.getProcuratoreInteressato().getCF());
		else
			StmInsertContratto.setString(6, null);
		
		StmInsertContratto.setString(7,contratto.getAtletaSottosritto().getCF());
		
		if(contratto.getClub() == null) {
			StmInsertContratto.setInt(8,contratto.getSponsor().getIdSponsor());
		    StmInsertContratto.setObject(9, null);
		}
		else {
			StmInsertContratto.setObject(8, null);
			StmInsertContratto.setInt(9,contratto.getClub().getIdClubSportivo());   
		}
		StmInsertContratto.setDouble(10,contratto.getGettonePresenzaNazionale());
		StmInsertContratto.executeUpdate();
	}

	@Override
	public ProcuratoreSportivo GetProcuratoreAttivo(Atleta atleta,Date DataInizio,Date DataFine) throws SQLException, EccezioneCF {
		StmGetProcuratoreAttivo.setString(1,atleta.getCF());
		StmGetProcuratoreAttivo.setDate(2,DataInizio);
		StmGetProcuratoreAttivo.setDate(3,DataFine);
		ResultSet rs=StmGetProcuratoreAttivo.executeQuery();
		ProcuratoreSportivo procuratore=null;
		while(rs.next()) {
			procuratore = GetProcuratoreByCodiceFiscale(rs.getString("codicefiscaleprocuratore"));
		}
		rs.close();
		return procuratore;
	}

	@Override
	public ClubSportivo GetClubById(int IdClub) throws SQLException {
		StmGetClubById.setInt(1, IdClub);
		ResultSet rs=StmGetClubById.executeQuery();
		ClubSportivo Club=null;
		while(rs.next()) {
			String Nome=rs.getString("nome");
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("sedelegale"));
			boolean IsNazionale =  rs.getBoolean("isnazionale");
			Club=new ClubSportivo(Nome,nazione,IsNazionale);
			Club.setIdClubSportivo(IdClub);
		}
		rs.close();
		return Club;
	}

	@Override
	public Sponsor GetSponsorById(int IdSponsor) throws SQLException {
		StmGetSponsorById.setInt(1, IdSponsor);
		ResultSet rs=StmGetSponsorById.executeQuery();
		Sponsor sponsor=null;
		while(rs.next()) {
			String nome = rs.getString("nome");
			String descrizione = rs.getString("descrizione");	
			Nazione nazione = GetNazioneByCodiceAt(rs.getString("sedelegale"));
			sponsor=new Sponsor(nome,descrizione,nazione);		
			sponsor.setIdSponsor(IdSponsor);
		}
		rs.close();
		return sponsor;
	}	
	
	@Override
	public List<Contratto> GetContratti() throws SQLException, EccezioneCF {
		ResultSet rs=StmGetContratti.executeQuery();
		ArrayList<Contratto> contratti=new ArrayList<Contratto>();
		while(rs.next()) {
			int id =rs.getInt("idcontratto");
			LocalDate dataInizio =  LocalDate.parse(rs.getString("datainizio"));
			LocalDate dataFine =  LocalDate.parse(rs.getString("datafine"));
			double CompensoAtleta = rs.getDouble("compenso");
			TipoContratto tipoContratto = TipoContratto.Club;
			if(rs.getString("tipocontratto").equals("Sponsor"))
				tipoContratto = tipoContratto.Sponsor;
			double GuadagnoProcuratore = rs.getDouble("guadagnoprocuratore");
			ProcuratoreSportivo Procuratore = GetProcuratoreByCodiceFiscale(rs.getString("codicefiscaleprocuratore"));
			Atleta atleta = GetAtletaByCodiceFiscale(rs.getString("codicefiscaleatleta"));
			int idClub=rs.getInt("club");
			int idSponsor=rs.getInt("sponsor");
			ClubSportivo club = null;
			Sponsor sponsor=null;
			if(idClub!=0)
				club = GetClubById(idClub);
			if(idSponsor!=0)
				sponsor = GetSponsorById(idSponsor);
			double Gettone =rs.getDouble("gettonepresenzanazionale");
			Contratto contratto = new Contratto (Procuratore, atleta, dataInizio, dataFine, 
						tipoContratto, club, sponsor, CompensoAtleta, GuadagnoProcuratore,Gettone);
			contratto.setIdContratto(id);
			contratti.add(contratto);
		}
		rs.close();
		return contratti;
	}
	
	@Override
	public List<Contratto> GetContrattiAttivi() throws SQLException, EccezioneCF {		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = formatter.format(new Date(System.currentTimeMillis()));
		StmGetContrattiAttivi.setString(1,curDate);
		StmGetContrattiAttivi.setString(2,curDate);
		ResultSet rs=StmGetContrattiAttivi.executeQuery();
		ArrayList<Contratto> contrattiAttivi=new ArrayList<Contratto>();
		while(rs.next()) {
			int id =rs.getInt("idcontratto");
			LocalDate dataInizio =  LocalDate.parse(rs.getString("datainizio"));
			LocalDate dataFine =  LocalDate.parse(rs.getString("datafine"));
			double CompensoAtleta = rs.getDouble("compenso");
			TipoContratto tipoContratto = TipoContratto.Club;
			if(rs.getString("tipocontratto").equals("Sponsor"))
				tipoContratto = tipoContratto.Sponsor;
			double GuadagnoProcuratore = rs.getDouble("guadagnoprocuratore");
			ProcuratoreSportivo Procuratore = GetProcuratoreByCodiceFiscale(rs.getString("codicefiscaleprocuratore"));
			Atleta atleta = GetAtletaByCodiceFiscale(rs.getString("codicefiscaleatleta"));
			int idClub=rs.getInt("club");
			int idSponsor=rs.getInt("sponsor");
			ClubSportivo club = null;
			Sponsor sponsor=null;
			if(idClub!=0)
				club = GetClubById(idClub);
			if(idSponsor!=0)
				sponsor = GetSponsorById(idSponsor);
			double gettone =rs.getDouble("gettonepresenzanazionale");
			Contratto contratto = new Contratto (Procuratore, atleta, dataInizio, dataFine, 
						tipoContratto, club, sponsor, CompensoAtleta, GuadagnoProcuratore,gettone);
			contratto.setIdContratto(id);
			contrattiAttivi.add(contratto);
		}
		rs.close();
		return contrattiAttivi;
	}

	@Override
	public List<Contratto> GetMaxContrattiAtleta(Atleta atleta,Date DataInizio,Date DataFine) throws EccezioneCF, SQLException {
		ArrayList<Contratto> ValoriMassimi= new ArrayList<Contratto>();
		StmGetMaxContrattiAtleta.setString(1, atleta.getCF());
		StmGetMaxContrattiAtleta.setDate(2,DataInizio);
		StmGetMaxContrattiAtleta.setDate(3,DataFine);
		StmGetMaxContrattiAtleta.setDate(4,DataInizio);
		StmGetMaxContrattiAtleta.setDate(5,DataFine);
		StmGetMaxContrattiAtleta.setString(6, atleta.getCF());
		StmGetMaxContrattiAtleta.setString(7, atleta.getCF());
		StmGetMaxContrattiAtleta.setDate(8,DataInizio);
		StmGetMaxContrattiAtleta.setDate(9,DataFine);
		StmGetMaxContrattiAtleta.setDate(10,DataInizio);
		StmGetMaxContrattiAtleta.setDate(11,DataFine);
		StmGetMaxContrattiAtleta.setString(12,atleta.getCF());
		StmGetMaxContrattiAtleta.setString(13, atleta.getCF());
		StmGetMaxContrattiAtleta.setDate(14,DataInizio);
		StmGetMaxContrattiAtleta.setDate(15,DataFine);
		StmGetMaxContrattiAtleta.setDate(16,DataInizio);
		StmGetMaxContrattiAtleta.setDate(17,DataFine);
		StmGetMaxContrattiAtleta.setString(18,atleta.getCF());
		
		ResultSet rs= StmGetMaxContrattiAtleta.executeQuery();
		while(rs.next()) {
			int IdClub_Sponsor=rs.getInt("Entita");
			LocalDate dataInizio =  LocalDate.parse(rs.getString("datainizio"));
			LocalDate dataFine =  LocalDate.parse(rs.getString("datafine"));
			ClubSportivo club = null;
			Sponsor sponsor=null;
			double CompensoAtleta = rs.getDouble("compenso");
			double Gettone=rs.getDouble("gettonepresenzanazionale");
			TipoContratto TipoC=null;
			if(rs.getString("tipocontratto").equals("Club")) {
				TipoC = TipoContratto.Club;
				club = GetClubById(IdClub_Sponsor);
			}
			if(rs.getString("tipocontratto").equals("Sponsor")) {
				TipoC = TipoContratto.Sponsor;
				sponsor = GetSponsorById(IdClub_Sponsor);
			}
			Contratto contratto = new Contratto (null, atleta,dataInizio,dataFine, TipoC, club, sponsor, CompensoAtleta, 0,Gettone);

			contratto.setIdContratto(rs.getInt("idcontratto"));
			ValoriMassimi.add(contratto);	
		}
		return ValoriMassimi;	
	}
	
	@Override
	public List<Contratto> GetMaxContrattiProcuratori(ProcuratoreSportivo proc,Date DataInizio,Date DataFine) throws EccezioneCF, SQLException {
		ArrayList<Contratto> ValoriMassimi= new ArrayList<Contratto>();		
		StmGetMaxContrattiProcuratori.setString(1,proc.getCF());
		StmGetMaxContrattiProcuratori.setString(2,proc.getCF());
		StmGetMaxContrattiProcuratori.setDate(3,DataInizio);
		StmGetMaxContrattiProcuratori.setDate(4,DataFine);
		StmGetMaxContrattiProcuratori.setDate(5,DataInizio);
		StmGetMaxContrattiProcuratori.setDate(6,DataFine);
		StmGetMaxContrattiProcuratori.setString(7,proc.getCF());
		StmGetMaxContrattiProcuratori.setString(8,proc.getCF());
		StmGetMaxContrattiProcuratori.setDate(9,DataInizio);
		StmGetMaxContrattiProcuratori.setDate(10,DataFine);	
		StmGetMaxContrattiProcuratori.setDate(11,DataInizio);
		StmGetMaxContrattiProcuratori.setDate(12,DataFine);
		ResultSet rs= StmGetMaxContrattiProcuratori.executeQuery();
		while(rs.next()) {
			int IdClub_Sponsor=rs.getInt("Entita");
			ClubSportivo club = null;
			Sponsor sponsor=null;
			double compenso = rs.getDouble("guadagnoprocuratore");
			TipoContratto TipoC=null;
			if(rs.getString("tipocontratto").equals("Club")) {
				TipoC = TipoContratto.Club;
				club = GetClubById(IdClub_Sponsor);
			}
			if(rs.getString("tipocontratto").equals("Sponsor")) {
				TipoC = TipoContratto.Sponsor;
				sponsor = GetSponsorById(IdClub_Sponsor);
			}
			
			Contratto contratto = new Contratto (proc, null, null, null, TipoC, club, sponsor, 0, compenso,0);
			contratto.setIdContratto(rs.getInt("idcontratto"));
			ValoriMassimi.add(contratto);	
		}
		return ValoriMassimi;	
	}
	
	@Override
	public List<Ingaggio> GetIngaggiByProcuratoreAttivi(ProcuratoreSportivo procuratore) throws EccezioneCF, SQLException {
		StmGetIngaggiByProcuratoreAttivi.setString(1,procuratore.getCF());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = formatter.format(new Date(System.currentTimeMillis()));
		StmGetIngaggiByProcuratoreAttivi.setString(2,curDate);
		StmGetIngaggiByProcuratoreAttivi.setString(3,curDate);
		ResultSet rs= StmGetIngaggiByProcuratoreAttivi.executeQuery();
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
	public List<Ingaggio> GetIngaggiMigliori(ProcuratoreSportivo proc,Date DataInizio,Date DataFine) throws EccezioneCF, SQLException {
		ArrayList<Ingaggio> ingaggiVantaggiosi= new ArrayList<Ingaggio>();
		StmGetInaggiMigliori.setString(1,proc.getCF());
		StmGetInaggiMigliori.setDate(2,DataInizio);
		StmGetInaggiMigliori.setDate(3,DataFine);
		StmGetInaggiMigliori.setString(1,proc.getCF());
		StmGetInaggiMigliori.setString(4,proc.getCF());
		StmGetInaggiMigliori.setDate(5,DataInizio);
		StmGetInaggiMigliori.setDate(6,DataFine);
		ResultSet rs= StmGetInaggiMigliori.executeQuery();
		while(rs.next()) {
			String CfAtleta=rs.getString("codicefiscaleatleta");
			double stipendioProc = rs.getDouble("stipendioprocuratore");
			LocalDate DataInizioIng = LocalDate.parse(rs.getDate("datainizio").toString());
			LocalDate DataFineIng = LocalDate.parse(rs.getDate("datafine").toString());
			Ingaggio Ingaggio = new Ingaggio (null,GetAtletaByCodiceFiscale(CfAtleta),DataInizioIng,DataFineIng,stipendioProc);
			ingaggiVantaggiosi.add(Ingaggio);	
		}
		return ingaggiVantaggiosi;	
	}
}
	

