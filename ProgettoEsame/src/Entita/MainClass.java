package Entita;

import java.sql.SQLException;
import java.time.LocalDate;


public class MainClass {

	public static void main(String[] args) throws SQLException {
		
		Persona p2 = new Persona("Davide", "Ferreri",Sesso.M, LocalDate.of(1999, 2, 5),"Italia","Napoli","Lacco Ameno");

		System.out.println(p2);

	}

}
