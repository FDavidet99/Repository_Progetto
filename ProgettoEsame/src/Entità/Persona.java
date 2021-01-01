package Entità;

import java.sql.Connection;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import ImplementationDAO.*;
import DatabaseUtility.*;
import Eccezioni.*;

public class Persona {
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private Sesso SessoPersona;
	private LocalDate DataNascita;
	private Nazione NazioneNascita;
	private Provincia ProvinciaNascita;
	private Comune ComuneNascita;
	
	public Persona(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita) throws SQLException, EccezioneCF{
		super();
		CodiceFiscale = calcolaCF(nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		Nome = nome;
		Cognome = cognome;
		SessoPersona = sessoPersona;
		DataNascita = dataNascita;
		NazioneNascita = nazioneNascita;
		ProvinciaNascita = provinciaNascita;
		ComuneNascita = comuneNascita;
	}
	
	public Persona(String codiceFiscale,String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita) throws SQLException, EccezioneCF{
		super();
		CodiceFiscale=codiceFiscale;
		Nome = nome;
		Cognome = cognome;
		SessoPersona = sessoPersona;
		DataNascita = dataNascita;
		NazioneNascita = nazioneNascita;
		ProvinciaNascita = provinciaNascita;
		ComuneNascita = comuneNascita;
	}
	
	public String getCF() throws EccezioneCF, SQLException {
		if(CodiceFiscale!=null)
			return CodiceFiscale;
		else
			return calcolaCF(Nome, Cognome, SessoPersona, DataNascita, NazioneNascita, ProvinciaNascita, ComuneNascita);
	}
	
	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public Sesso getSessoPersona() {
		return SessoPersona;
	}

	public void setSessoPersona(Sesso sessoPersona) {
		SessoPersona = sessoPersona;
	}

	public LocalDate getDataNascita() {
		return DataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		DataNascita = dataNascita;
	}

	public Nazione getNazioneNascita() {
		return NazioneNascita;
	}

	public void setNazioneNascita(Nazione nazioneNascita) {
		NazioneNascita = nazioneNascita;
	}

	public Provincia getProvinciaNascita() {
		return ProvinciaNascita;
	}

	public void setProvinciaNascita(Provincia provinciaNascita) {
		ProvinciaNascita = provinciaNascita;
	}

	public Comune getComuneNascita() {
		return ComuneNascita;
	}

	public void setComuneNascita(Comune comuneNascita) {
		ComuneNascita = comuneNascita;
	}

	public boolean hasNazioneNascita() {
		if(NazioneNascita!=null)
			return true;
		else
			return false;
	}
	
	public boolean hasProviciaNascita() {
		if(ProvinciaNascita!=null)
			return true;
		else 
			return false;
	}
	
	public boolean hasComuneNascita() {
		if(ComuneNascita!=null)
			return true;
		else 
			return false;
	}
	
	@Override
	public String toString() {
		String out = "[";
		out+="Nome: "+ Nome +", ";
		out+="Cognome: "+ Cognome +", ";
		out+="Sesso: "+ SessoPersona+", ";
		out+="DataNascita: "+ DataNascita +", ";
		out+="Nazione: "+ NazioneNascita.getNomeNazione();
		if(hasNazioneNascita() && NazioneNascita.getNomeNazione().equalsIgnoreCase("Italia")) {
			out+=", ";
			if(hasProviciaNascita()) {
				out+="Provincia: "+ ProvinciaNascita +", ";
				if(hasComuneNascita())
					out+="Comune:"+ ComuneNascita ;
			}
		}
		out+="]";
		return out;
	}
	
	private String calcolaCF(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			Nazione nazioneNascita, Provincia provinciaNascita, Comune comuneNascita) throws EccezioneCF, SQLException {
	        
		nome = nome.toLowerCase();
		cognome = cognome.toLowerCase();
		if(!(isAlpha(nome) && isAlpha(cognome)))
					throw new EccezioneCF();
		
		String CodiceFiscale="";
		
		CodiceFiscale += calcolaCaratteriPerCognome(cognome);
		CodiceFiscale += calcolaCaratteriPerNome(nome);
		
		CodiceFiscale += Integer.toString(dataNascita.getYear()).substring(2);
		
		CodiceFiscale += caratterePerIlMese(dataNascita);
		
		int giornoNascita = dataNascita.getDayOfMonth();
		
		if(sessoPersona == Sesso.F) giornoNascita+=40;
		String strGiornoNascita = Integer.toString(giornoNascita);
		if (strGiornoNascita.length()==1) strGiornoNascita = "0"+strGiornoNascita;
		CodiceFiscale += strGiornoNascita;
    	if(nazioneNascita.getCodiceAt().equalsIgnoreCase("Z000"))
    		CodiceFiscale += comuneNascita.getCodiceCatastale();
    	else
    		CodiceFiscale += nazioneNascita.getCodiceAt();
		CodiceFiscale += carControllo(CodiceFiscale);
		return CodiceFiscale;
	}
	
	
	
	private char caratterePerIlMese(LocalDate dataNascita) {
		switch (dataNascita.getMonthValue()) {
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
	
	private String calcolaCaratteriPerCognome(String cognome) throws EccezioneCF {
		String consonantiCognome = getConsonanti(cognome);
		int dimConsonantiCognome = consonantiCognome.length();
		
		if(dimConsonantiCognome<3) {
			String vocaliCognome = getVocali(cognome);
			int dimVocaliCognome = vocaliCognome.length();
			
			if(dimVocaliCognome + dimConsonantiCognome < 3)
				throw new EccezioneCF();
			
			return (consonantiCognome+vocaliCognome).toUpperCase().substring(0,3);
		}
		consonantiCognome = consonantiCognome.substring(0,3);
		return consonantiCognome.toUpperCase();
	}
	
	private String calcolaCaratteriPerNome(String nome) throws EccezioneCF {
		String consonantiNome = getConsonanti(nome);
		int dimConsonantiNome = consonantiNome.length();
		
		if(dimConsonantiNome<3) {
			String vocaliNome = getVocali(nome);
			int dimVocaliNome = vocaliNome.length();
			
			if(dimVocaliNome + dimConsonantiNome < 3)
				throw new EccezioneCF();
			
			return (consonantiNome+vocaliNome).toUpperCase().substring(0,3);
		}
		else if(dimConsonantiNome>=4) {
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
        return s != null && s.matches("^[a-zA-Z' ]*$");
    }
	
	private boolean isConsonante(char c) {
		return (c!='a' && c!= 'e' && c!='i' && c!='o' && c!='u');
	}
	
	private boolean isVocale(char c) {
		return (c=='a' || c=='e' || c=='i' || c=='o' || c=='u');
	}
	
	private String getConsonanti(String s) {
		String consonanti = "";
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if(isConsonante(c)) consonanti+=c;
		}
		return consonanti;
	}
	
	private String getVocali(String s) {
		String vocali = "";
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if(isVocale(c)) vocali+=c;
		}
		return vocali;
	}
	public char carControllo(String cf15) throws EccezioneCF
	{
		if(cf15.length()!=15)throw new EccezioneCF();
		String carPari = getCarPostoPari(cf15);
		String carDispari = getCarPostoDispari(cf15);
		int somma = 0;
		for(int i=0;i<carPari.length();i++)
			somma += getValAssociatoAcarDiStringaPari(carPari.charAt(i));
		
		for(int i=0;i<carDispari.length();i++)
			somma += getValAssociatoAcarDiStringaDispari(carDispari.charAt(i));
		
		int resto = somma % 26;
		
		char carAssociatoAresto = (char)(resto + 65);
		return carAssociatoAresto;
	}
	
	private int getValAssociatoAcarDiStringaPari(char carStrPari) throws EccezioneCF {
		int asciiCode = (int)carStrPari;
		// per i caratteri 0..9 ritorna 0..9
		if( asciiCode >=48 && asciiCode<=57)
			return asciiCode - 48;
		else
			return asciiCode - 65; // enumera A..Z
	}
	
	private int getValAssociatoAcarDiStringaDispari(char carStrDispari) throws EccezioneCF {
		switch(carStrDispari) {
			case '0':return 1;
			case '1':return 0;
			case '2':return 5;
			case '3':return 7;
			case '4':return 9;
			case '5':return 13;
			case '6':return 15;
			case '7':return 17;
			case '8':return 19;
			case '9':return 21;
			case 'A':return 1;
			case 'B':return 0;
			case 'C':return 5;
			case 'D':return 7;
			case 'E':return 9;
			case 'F':return 13;
			case 'G':return 15;
			case 'H':return 17;
			case 'I':return 19;
			case 'J':return 21;
			case 'K':return 2;
			case 'L':return 4;
			case 'M':return 18;
			case 'N':return 20;
			case 'O':return 11;
			case 'P':return 3;
			case 'Q':return 6;
			case 'R':return 8;
			case 'S':return 12;
			case 'T':return 14;
			case 'U':return 16;
			case 'V':return 10;
			case 'W':return 22;
			case 'X':return 25;
			case 'Y':return 24;
			case 'Z':return 23;
		}
		throw new EccezioneCF();
	}
	
	private String getCarPostoPari(String s) {
		String res ="";
		for(int i=0;i<s.length();i++)
			if((i+1)%2==0)res+=s.charAt(i);
		return res;
	}
	
	private String getCarPostoDispari(String s) {
		String res ="";
		for(int i=0;i<s.length();i++)
			if((i+1)%2!=0)
				res+=s.charAt(i);
	return res;
	}
	
}
