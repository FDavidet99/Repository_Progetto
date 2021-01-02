package Entità;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Contratto {
	private int IdContratto;
	private Atleta AtletaSottosritto;
	private ProcuratoreSportivo ProcuratoreInteressato;
	private LocalDate DataInizio;
	private LocalDate DataFine;
	private TipoContratto Tipo;
	private ClubSportivo Club=null;
	private Sponsor Sponsor=null;
	
	
	
	public Contratto(Atleta atletaSottosritto, ProcuratoreSportivo procuratoreInteressato, LocalDate dataInizio,
			LocalDate dataFine) {
		super();
		AtletaSottosritto = atletaSottosritto;
		ProcuratoreInteressato = procuratoreInteressato;
		DataInizio = dataInizio;
		DataFine = dataFine;
	}

	public Atleta getAtletaSottosritto() {
		return AtletaSottosritto;
	}

	public void setAtletaSottosritto(Atleta atletaSottosritto) {
		AtletaSottosritto = atletaSottosritto;
	}

	public ProcuratoreSportivo getProcuratoreInteressato() {
		return ProcuratoreInteressato;
	}

	public void setProcuratoreInteressato(ProcuratoreSportivo procuratoreInteressato) {
		ProcuratoreInteressato = procuratoreInteressato;
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
