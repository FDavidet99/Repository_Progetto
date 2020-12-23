package ImplementationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import InterfacceDAO.*;

public abstract class ImplementationDAO implements NazioneDAO,ProvinciaDAO,ComuneDAO,AtletaDAO,ProcuratoreSportivoDAO {
	
	protected Connection Connection;
    protected PreparedStatement 
	    StmGetNazioni,StmGetProvinceByNazione,StmGetNazioneByCodiceAt,
	    StmGetComuniByProvincia,StmGetProvinciaByNome,
	    StmGetComuneByCodiceCatastale,
	    StmInsertAtleta,StmGetAtleti,
	    StmInsertProcuratoreSportivo;
    
	
	
    
    
	
	
	

}
