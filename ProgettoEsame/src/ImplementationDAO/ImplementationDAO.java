package ImplementationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import InterfacesDAO.*;

public abstract class ImplementationDAO implements NazioneDAO,ProvinciaDAO,ComuneDAO,AtletaDAO,ProcuratoreSportivoDAO,IngaggioDAO,ClubSportivoDAO,SponsorDAO,ContrattoDAO {
	
	protected Connection Connection;
    protected PreparedStatement StmGetNazioni,StmGetComuniByProvincia,StmGetProvinceByNazione,StmGetNazioniByCodiceAt,StmGetProvinciaByNome,StmGetComuneByCodiceCatastale,
                                StmInsertAtleta,StmInsertProcuratoreSportivo,StmGetAtleti,StmGetProcuratori,StmGetProcuratoreByCodiceFiscale,StmGetAtletaByCodiceFiscale,
                                StmInsertIngaggio,StmGetIngaggiByAtleta,StmGetIngaggiByProcuratore,StmGetIngaggiByProcuratoreAttivi,
                                StmGetClubSportivi,StmGetSponsor,StmGetProcuratoreAttivo,StmGetSponsorById,StmGetClubById,
                                StmInsertContratto,StmGetContratti,StmGetContrattiAttivi,StmGetMaxContrattiAtleta,StmGetMaxContrattiProcuratori,StmGetInaggiMigliori;

	
	

}
