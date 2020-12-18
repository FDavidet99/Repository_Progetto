package ImplementationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import InterfacceDAO.*;

public abstract class ImplementationDAO implements ComuneDAO,NazioneDAO {
	
	protected Connection Connection;
    protected PreparedStatement StmGetCodiceCatastaleComune, StmGetNomiComuni,StmGetCodiceAt;
    
	
	
    
    
	
	
	

}
