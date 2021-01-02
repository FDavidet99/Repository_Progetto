package ImplementationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import InterfacceDAO.*;

public abstract class ImplementationDAO implements NazioneDAO,ProvinciaDAO,ComuneDAO,AtletaDAO,ProcuratoreSportivoDAO,IngaggioDAO,ClubSportivoDAO,SponsorDAO {
	
	protected Connection Connection;
    protected PreparedStatement StmGetNazioni,StmGetComuniByProvincia,StmGetProvinceByNazione,StmGetNazioniByCodiceAt,StmGetProvinciaByNome,StmGetComuneByCodiceCatastale,
                                StmInsertAtleta,StmInsertProcuratoreSportivo,StmGetAtleti,StmGetProcuratori,StmGetProcuratoreByCodiceFiscale,StmGetAtletaByCodiceFiscale,
                                StmInsertIngaggio,StmGetIngaggiByAtleta,StmGetIngaggiByProcuratore,
                                StmGetClubSportivi,StmGetSponsor;
    
    
    
	
	
    
    
	
	
	

}
