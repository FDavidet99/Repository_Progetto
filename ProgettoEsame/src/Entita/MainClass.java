package Entita;

import java.time.LocalDate;

import EccezioniPersona.EccezioneCF;

public class MainClass {

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws EccezioneCF {
		// TODO Auto-generated method stub
		Persona p1 = new Persona("Alessandro", "Bonomo", LocalDate.of(2001, 3, 28),"Caserta",Sesso.M);
		Persona p2 = new Persona("Davide", "Ferreri", LocalDate.of(1999, 2, 5),"Napoli",Sesso.M);
		System.out.println(p1);
		System.out.println(p2.getCF());
	}

}
