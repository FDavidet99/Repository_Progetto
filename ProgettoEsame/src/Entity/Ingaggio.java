package Entity;

import java.time.LocalDate;

public class Ingaggio {
	private ProcuratoreSportivo Procuratore;
	private Atleta Atleta;
	private LocalDate DataInizio;
	private LocalDate DataFine;
	private double StipendioProcuratore;
	
	public Ingaggio(ProcuratoreSportivo procuratore,Atleta atleta, LocalDate dataInizio, LocalDate dataFine, double stipendioProcuratore) {
		super();
		Procuratore = procuratore;
		Atleta = atleta;
		DataInizio = dataInizio;
		DataFine = dataFine;
		StipendioProcuratore = stipendioProcuratore;
	}

	public ProcuratoreSportivo getProcuratore() {
		return Procuratore;
	}

	public void setProcuratore(ProcuratoreSportivo procuratore) {
		Procuratore = procuratore;
	}

	public Atleta getAtleta() {
		return Atleta;
	}

	public void setAtleta(Atleta atleta) {
		Atleta = atleta;
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

	public double getStipendioProcuratore() {
		return StipendioProcuratore;
	}

	public void setStipendioProcuratore(double stipendioProcuratore) {
		StipendioProcuratore = stipendioProcuratore;
	}
	
//	public void RegistraIngaggio() {
//		Procuratore.AddIngaggioDaAtleta(this);
//		Atleta.AddIngaggioProcuratore(this);
//	}
	

}
