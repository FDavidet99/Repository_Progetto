package Entita;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ListIterator;

import DatabaseUtility.ControllerQuery;
import DatabaseUtility.DatabaseConnection;
import ImplementationDAO.ImplementationClass;
import ImplementationDAO.ImplementationClassPostgres;


public class MainClass {

	public static void main(String[] args) throws SQLException {
		
		Persona p2 = new Persona("Davide", "Ferreri",Sesso.M, LocalDate.of(1999, 2, 5),"Italia","Napoli","Lacco Ameno");

		System.out.println(p2);
		
		//solo per ora metto qua la connessione che andra nel main alla fine
	    DatabaseConnection dbconn = null;
        Connection connection = null;
        dbconn=DatabaseConnection.getInstance();
        connection=dbconn.getConnection();
	//finisce la connessione
        
		ImplementationClass obj = ControllerQuery.getInstance(connection).getDAO();
		
		
//		ImplementationClassPostgres a=new ImplementationClassPostgres(connection);
//		a.getNomiComuni();
//		Iterator i=a.getNomiComuni().iterator() ;
		
		
		Iterator i=obj.getNomiComuni().iterator() ;
		while(i.hasNext())
			System.out.println(i.next());
		
	}

}
