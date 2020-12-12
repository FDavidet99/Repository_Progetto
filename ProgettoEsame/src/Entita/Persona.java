package Entita;

import java.time.LocalDate;

import EccezioniPersona.*;

public class Persona 
{
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String comuneNascita;
	private String nazioneNascita;
	private Sesso sesso;
	public Persona(String nome, String cognome, LocalDate dataNascita, String comuneNascita,
			Sesso sesso) throws EccezioneCF 
	{
		super();
		setNome(nome);
		setCognome(cognome);
		setDataNascita(dataNascita);
		setComuneNascita(comuneNascita);
		setSesso(sesso);
		codiceFiscale = getCF();
	}
	public Persona(String nome, String cognome, LocalDate dataNascita, Sesso sesso,
			String nazioneNascita) throws EccezioneCF 
	{
		super();
		setNome(nome);
		setCognome(cognome);
		setDataNascita(dataNascita);
		setNazioneNascita(nazioneNascita);
		setSesso(sesso);
		codiceFiscale = getCF();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getComuneNascita() throws TentativoLetturaStringaVuota{
		if(!hasComuneNascita())throw new TentativoLetturaStringaVuota();
		return comuneNascita;
	}
	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}
	public String getNazioneNascita() throws TentativoLetturaStringaVuota{
		if(!hasNazioneNascita())throw new TentativoLetturaStringaVuota();
		return nazioneNascita;
	}
	public String getLuogoNascita() throws EccezionePersona
	{
		if(hasComuneNascita())return comuneNascita;
		else if(hasNazioneNascita())return nazioneNascita;
		else throw new PersonaSenzaLuogoNascita();
	}
	public void setNazioneNascita(String nazioneNascita) {
		this.nazioneNascita = nazioneNascita;
	}
	public Sesso getSesso() {
		return sesso;
	}
	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}
	
	public boolean hasComuneNascita()
	{
		if(comuneNascita!=null)return true;
		else return false;
	}
	public boolean hasNazioneNascita()
	{
		if(nazioneNascita!=null)return true;
		else return false;
	}
	@Override
	public String toString() {
		String out = "[";
		try {
			out+="CF: "+getCF()+", ";
		} catch (EccezioneCF e) {}
		out+="Nome: "+nome+", ";
		out+="Cognome: "+cognome+", ";
		out+="DataNascita: "+dataNascita+", ";
		if(hasComuneNascita())out+="ComuneNascita: "+comuneNascita+", ";
		else out+= "NazioneNascita"+nazioneNascita+", ";
		out+="Sesso: "+sesso+"]";
		return out;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == Persona.class)
		{
			Persona p = (Persona)obj;
			try {
				if(p.getCF() == getCF())return true;
				else return false;
			} catch (EccezioneCF e) {
				return false;
			}
		}
		else return false;
	}
	//METODI CODICE FISCALE//////////////////////////////////////
	public String getCF() throws EccezioneCF
	{
		if(codiceFiscale!=null)return codiceFiscale;
		else
		{
			if(hasComuneNascita())
				return calcolaCF(nome, cognome, dataNascita, comuneNascita, sesso);
			else return calcolaCFestero(nome, cognome, dataNascita,nazioneNascita, sesso);
		}
	}
	
	private String calcolaCF(String nome,String cognome,LocalDate dataNascita,
			String nomeComuneNascita,Sesso sesso) throws EccezioneCF
	{
		nome = nome.toLowerCase();
		cognome = cognome.toLowerCase();
		nomeComuneNascita = nomeComuneNascita.toUpperCase();
		if(!(isAlpha(nome) && isAlpha(cognome) && isAlpha(nomeComuneNascita)))
					throw new EccezioneCF();
		
		String CodiceFiscale="";
		
		CodiceFiscale += calcolaCaratteriPerCognome(cognome);
		CodiceFiscale += calcolaCaratteriPerNome(nome);
		
		CodiceFiscale += Integer.toString(dataNascita.getYear()).substring(2);
		
		CodiceFiscale += caratterePerIlMese(dataNascita);
		
		int giornoNascita = dataNascita.getDayOfMonth();
		
		if(sesso == Sesso.F) giornoNascita+=40;
		String strGiornoNascita = Integer.toString(giornoNascita);
		if (strGiornoNascita.length()==1) strGiornoNascita = "0"+strGiornoNascita;
		CodiceFiscale += strGiornoNascita;
		
		CodiceFiscale += getCodiceCatastaleComune(nomeComuneNascita);
		
		return CodiceFiscale;
	}
	private String calcolaCFestero(String nome,String cognome,LocalDate dataNascita,
			String nomeNazione,Sesso sesso) throws EccezioneCF
	{
		nome = nome.toLowerCase();
		cognome = cognome.toLowerCase();
		nomeNazione = nomeNazione.toUpperCase();
		if(!(isAlpha(nome) && isAlpha(cognome) && isAlpha(nomeNazione)))
			throw new EccezioneCF();
		
		String CodiceFiscale="";
		
		CodiceFiscale += calcolaCaratteriPerCognome(cognome);
		CodiceFiscale += calcolaCaratteriPerNome(nome);
		
		CodiceFiscale += Integer.toString(dataNascita.getYear()).substring(2);
		
		CodiceFiscale += caratterePerIlMese(dataNascita);
		
		int giornoNascita = dataNascita.getDayOfMonth();
		if(sesso == Sesso.F) giornoNascita+=40;
		
		String strGiornoNascita = Integer.toString(giornoNascita);
		if (strGiornoNascita.length()==1) strGiornoNascita = "0"+strGiornoNascita;
		CodiceFiscale += strGiornoNascita;
		
		CodiceFiscale += getCodiceNazione(nomeNazione);
		
		return CodiceFiscale;
	}
	
	
	private String getCodiceCatastaleComune(String nomeComune)
	{
		// TODO
		// cerca il codice del comune nel database, se non lo trovi lancia eccezione
		return "";
	}
	private String getCodiceNazione(String nomeNazione)
	{
		// TODO
		// cerca il codice della nazione nel database, se non lo trovi lancia eccezione
		return "";
	}
	private char caratterePerIlMese(LocalDate dataNascita)
	{
		switch (dataNascita.getMonthValue())
		{
			case 1:return 'A';
			case 2:return 'B';
			case 3:return 'C';
			case 4:return 'D';
			case 5:return 'E';
			case 6:return 'H';
			case 7:return 'L';
			case 8:return 'M';
			case 9:return 'P';
			case 10:return 'R';
			case 11:return 'S';
			case 12:return 'T';
			default:return 'A';
		}
	}
	private String calcolaCaratteriPerCognome(String cognome) throws EccezioneCF
	{
		String consonantiCognome = getConsonanti(cognome);
		int dimConsonantiCognome = consonantiCognome.length();
		
		if(dimConsonantiCognome<3)
		{
			String vocaliCognome = getVocali(cognome);
			int dimVocaliCognome = vocaliCognome.length();
			
			if(dimVocaliCognome + dimConsonantiCognome < 3)
				throw new EccezioneCF();
			
			return (consonantiCognome+vocaliCognome).toUpperCase().substring(0,3);
		}
		consonantiCognome = consonantiCognome.substring(0,3);
		return consonantiCognome.toUpperCase();
		
	}
	private String calcolaCaratteriPerNome(String nome) throws EccezioneCF
	{
		String consonantiNome = getConsonanti(nome);
		int dimConsonantiNome = consonantiNome.length();
		
		if(dimConsonantiNome<3)
		{
			String vocaliNome = getVocali(nome);
			int dimVocaliNome = vocaliNome.length();
			
			if(dimVocaliNome + dimConsonantiNome < 3)
				throw new EccezioneCF();
			
			return (consonantiNome+vocaliNome).toUpperCase().substring(0,3);
		}
		else if(dimConsonantiNome>=4)
		{
			String s = "";
			s +=consonantiNome.charAt(0);
			s +=consonantiNome.charAt(2);
			s +=consonantiNome.charAt(3);
			consonantiNome =s;
		}
		else consonantiNome = consonantiNome.substring(0,3);
		return consonantiNome.toUpperCase();
	}
	
	private boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }
	private boolean isConsonante(char c)
	{
		return (c!='a' && c!= 'i' && c!='e' && c!='o' && c!='u');
	}
	private boolean isVocale(char c)
	{
		return (c=='a' || c=='i' || c=='e' || c=='o' || c=='u');
	}
	private String getConsonanti(String s)
	{
		String consonanti = "";
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(isConsonante(c)) consonanti+=c;
		}
		return consonanti;
	}
	private String getVocali(String s)
	{
		String vocali = "";
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(isVocale(c)) vocali+=c;
		}
		return vocali;
	}
	
	
}
