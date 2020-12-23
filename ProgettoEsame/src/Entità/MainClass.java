package Entità;

import java.sql.Connection;
//
import java.sql.Date;
//
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import Controller.ControllerQuery;
import DatabaseUtility.DatabaseConnection;
import EccezioniPersona.EccezioneCF;
import ImplementationDAO.ImplementationDAO;
import ImplementationDAO.ImplementationDAO_Postgres;


public class MainClass {

	public static void main(String[] args) throws SQLException, EccezioneCF {
		Nazione a=new Nazione("Z100","Albania");
		Atleta p2 = new Atleta("Davide", "Ferreri",Sesso.M, LocalDate.of(1985,12,17),a,null,null,false);
		

		
	
		
		System.out.println(p2);
		
		//solo per ora metto qua la connessione che andra nel main alla fine
	    DatabaseConnection dbconn = null;
        Connection connection = null;
        dbconn=DatabaseConnection.getInstance();
        connection=dbconn.getConnection();
       
        //	finisce la connessione
        ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
        
        System.out.println("com "+dao.getNazioneByCodiceAt("Z000"));
        System.out.println("com "+dao.getProvinciaByNome("Napoli"));
        System.out.println("com "+dao.getComuneByCodiceCatastale("A019"));
        
        
        ArrayList<Atleta> atleti = (ArrayList<Atleta>) dao.getAtleti();
        for(Atleta i : atleti)
        	System.out.println(i);
//		
//		Iterator i=obj.GetNazioni().iterator() ;
//		for(Nazione a:obj.GetNazioni()) {
//			for(Provincia p:obj.GetProvinceByNazione(a)){
//				for(Comune c:obj.GetComuniByProvincia(p))
//					System.out.println(c);
//			}
//		}
			
		
//		while(i.hasNext())
//			System.out.println(i.next());
		
	}

}
