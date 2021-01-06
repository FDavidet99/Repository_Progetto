package Entità;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Contratto {
	private int IdContratto;
	private ProcuratoreSportivo ProcuratoreInteressato;
	private Atleta AtletaSottosritto;
	private LocalDate DataInizio;
	private LocalDate DataFine;
	private TipoContratto Tipo;
	private ClubSportivo Club;
	private Sponsor Sponsor;
	private double CompensoAtleta;
	private double CompensoProcuratore;
	private double  GettonePresenzaNazionale;
	
	public Contratto(ProcuratoreSportivo procuratoreInteressato, Atleta atletaSottosritto,
			LocalDate dataInizio, LocalDate dataFine, TipoContratto tipo, ClubSportivo club, Entità.Sponsor sponsor,
			double compensoAtleta, double compensoProcuratore,double gettonePresenzaNazionale) {
		super();
		
		ProcuratoreInteressato = procuratoreInteressato;
		AtletaSottosritto = atletaSottosritto;
		DataInizio = dataInizio;
		DataFine = dataFine;
		Tipo = tipo;
		Club = club;
		Sponsor = sponsor;
		CompensoAtleta = compensoAtleta;
		CompensoProcuratore = compensoProcuratore;
		GettonePresenzaNazionale=gettonePresenzaNazionale;
	}
	
	public int getIdContratto() {
		return IdContratto;
	}

	public void setIdContratto(int idContratto) {
		IdContratto = idContratto;
	}
	
	public ProcuratoreSportivo getProcuratoreInteressato() {
		return ProcuratoreInteressato;
	}
	
	public void setProcuratoreInteressato(ProcuratoreSportivo procuratoreInteressato) {
		ProcuratoreInteressato = procuratoreInteressato;
	}
	
	public Atleta getAtletaSottosritto() {
		return AtletaSottosritto;
	}
	
	public void setAtletaSottosritto(Atleta atletaSottosritto) {
		AtletaSottosritto = atletaSottosritto;
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
	
	public TipoContratto getTipo() {
		return Tipo;
	}
	
	public void setTipo(TipoContratto tipo) {
		Tipo = tipo;
	}
	
	public ClubSportivo getClub() {
		return Club;
	}
	
	public void setClub(ClubSportivo club) {
		Club = club;
	}
	
	public Sponsor getSponsor() {
		return Sponsor;
	}
	
	public void setSponsor(Sponsor sponsor) {
		Sponsor = sponsor;
	}
	
	public double getCompensoAtleta() {
		return CompensoAtleta;
	}
	
	public void setCompensoAtleta(double compensoAtleta) {
		CompensoAtleta = compensoAtleta;
	}
	
	public double getCompensoProcuratore() {
		return CompensoProcuratore;
	}
	
	public void setCompensoProcuratore(double compensoProcuratore) {
		CompensoProcuratore = compensoProcuratore;
	}

	public double getGettonePresenzaNazionale() {
		return GettonePresenzaNazionale;
	}

	public void setGettonePresenzaNazionale(double gettonePresenzaNazionale) {
		GettonePresenzaNazionale = gettonePresenzaNazionale;
	}
	
}
