package Entita;

import java.sql.SQLException;
import java.time.LocalDate;

import EccezioniPersona.EccezioneCF;

public class MainClass {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//Persona p1 = new Persona("Alessandro", "Bonomo", LocalDate.of(2001, 3, 28),"Caserta");
<<<<<<< Updated upstream
		Persona p2 = new Persona("Davide", "Ferreri",Sesso.M, LocalDate.of(1999, 2, 5),"Italia","Napoli","Lacco Ameno");
=======
		Persona p2 = new Persona("Davide Manuele", "Ferreri",Sesso.M, LocalDate.of(1999, 2, 5),"Italia","Napoli","Lacco Ameno");
>>>>>>> Stashed changes
		System.out.println(p2);
		//System.out.println(p2.getCF());
	}

}
