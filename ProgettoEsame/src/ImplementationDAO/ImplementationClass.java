package ImplementationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import InterfaceDAO.*;

public abstract class ImplementationClass implements ComuneDAO,NazioneDAO {
	
	protected Connection Connection;
    protected PreparedStatement StmGetCodiceCatastaleComune, StmGetNomiComuni,StmGetCodiceAt;
    
	
	
    
    
	
	
	

}
