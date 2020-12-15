package Entita;

import java.sql.SQLException;
import java.time.LocalDate;

import EccezioniPersona.EccezioneCF;

public class MainClass {

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//Persona p1 = new Persona("Alessandro", "Bonomo", LocalDate.of(2001, 3, 28),"Caserta");
		Persona p2 = new Persona("Davide", "Ferreri",Sesso.M, LocalDate.of(1999, 2, 5),"Italia","Napoli","Forio");
		System.out.println(p2);
		//System.out.println(p2.getCF());
	}

}
