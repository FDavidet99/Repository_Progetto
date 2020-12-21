package Entità;

import java.sql.Connection;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ListIterator;

import Controller.ControllerQuery;
import DatabaseUtility.DatabaseConnection;
import ImplementationDAO.ImplementationDAO;
import ImplementationDAO.ImplementationDAO_Postgres;


public class MainClass {

	public static void main(String[] args) throws SQLException {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		//Persona p2 = new Persona("Davide", "Ferreri",Sesso.M, LocalDate.of(1985,12,17),null,null,null);
		//System.out.println(p2);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		System.out.print(LocalDate.of(1985,12,17).format(formatter).toString());
		
		//System.out.println(p2);
		
		//solo per ora metto qua la connessione che andra nel main alla fine
	    DatabaseConnection dbconn = null;
        Connection connection = null;
        dbconn=DatabaseConnection.getInstance();
        connection=dbconn.getConnection();
       
        //	finisce la connessione
        
//		ImplementationDAO obj = ControllerQuery.getInstance().getDAO();
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
