package Entità;

import java.time.LocalDate;

public class Presenza {
	private Atleta Atleta;
	private Squadra Squadra;
	private double GettonePresenzaNazionale;
	private LocalDate DataInizio;
	private LocalDate DataFine;
	
	public Presenza(Entità.Atleta atleta, Entità.Squadra squadra, double gettonePresenzaNazionale, LocalDate dataInizio,
			LocalDate dataFine) {
		super();
		Atleta = atleta;
		Squadra = squadra;
		GettonePresenzaNazionale = gettonePresenzaNazionale;
		DataInizio = dataInizio;
		DataFine = dataFine;
	}

	public Atleta getAtleta() {
		return Atleta;
	}

	public void setAtleta(Atleta atleta) {
		Atleta = atleta;
	}

	public Squadra getSquadra() {
		return Squadra;
	}

	public void setSquadra(Squadra squadra) {
		Squadra = squadra;
	}

	public double getGettonePresenzaNazionale() {
		return GettonePresenzaNazionale;
	}

	public void setGettonePresenzaNazionale(double gettonePresenzaNazionale) {
		GettonePresenzaNazionale = gettonePresenzaNazionale;
	}

	public LocalDate getDataInizio() {
		return DataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		DataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return DataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		DataFine = dataFine;
	}
	
	
	

}
