package Entita;

import java.time.LocalDate;

import EccezioniPersona.EccezioneCF;

public class MainClass {

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		//Persona p1 = new Persona("Alessandro", "Bonomo", LocalDate.of(2001, 3, 28),"Caserta");
		Persona p2 = new Persona("Davide", "Ferreri",Sesso.M, LocalDate.of(1999, 2, 5),"Italia","boh","boh");
		System.out.println(p2);
		//System.out.println(p2.getCF());
	}

}
