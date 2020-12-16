package Entita;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import EccezioniPersona.*;
import implementationDAO.*;
import DatabaseUtility.*;

public class Persona {
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private Sesso SessoPersona;
	private LocalDate DataNascita;
	private String NazioneNascita;
	private String ProvinciaNascita=new String("");
	private String ComuneNascita=new String("");
	
	public Persona(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			String nazioneNascita, String provinciaNascita, String comuneNascita) throws SQLException{
		super();
		
		try {
			CodiceFiscale = calcolaCF(nome, cognome, sessoPersona, dataNascita, nazioneNascita, provinciaNascita, comuneNascita);
		} catch (EccezioneCF e) {
			System.out.println("Dati non compatibili con il sistema");
		}
		
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

	public String getNazioneNascita() {
		return NazioneNascita;
	}

	public void setNazioneNascita(String nazioneNascita) {
		NazioneNascita = nazioneNascita;
	}

	public String getProvinciaNascita() {
		return ProvinciaNascita;
	}

	public void setProvinciaNascita(String provinciaNascita) {
		ProvinciaNascita = provinciaNascita;
	}

	public String getComuneNascita() {
		return ComuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
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
		try {
			out+="CF: "+getCF()+", ";
		} catch (EccezioneCF e) {} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out+="Nome: "+ Nome +", ";
		out+="Cognome: "+ Cognome +", ";
		out+="Sesso: "+ SessoPersona+", ";
		out+="DataNascita: "+ DataNascita +", ";
		out+="Nazione: "+ NazioneNascita;
		if(hasNazioneNascita() && NazioneNascita.equals("Italia")) {
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
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj.getClass() == Persona.class) {
//			Persona p = (Persona)obj;
//			try {
//				if(p.getCF() == getCF())
//					return true;
//				else
//					return false;
//			} catch (EccezioneCF e) {
//				return false;
//			}
//		}
//		else
//			return false;
//	}
//	
	
	//METODI CODICE FISCALE//////////////////////////////////////
	
	private String calcolaCF(String nome, String cognome, Sesso sessoPersona, LocalDate dataNascita,
			String nazioneNascita, String provinciaNascita, String comuneNascita) throws EccezioneCF, SQLException {
		
		
		//solo per ora metto qua la connessione che andra nel main alla fine
		    DatabaseConnection dbconn = null;
	        Connection connection = null;
	        dbconn=DatabaseConnection.getInstance();
	        connection=dbconn.getConnection();
		//finisce la connessione
	        
	        
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
		
		ComuneDaoPostgresImplementation tempo= new ComuneDaoPostgresImplementation(connection);
		
		//if(nazioneNascita.equals("Italia"))
		CodiceFiscale += tempo.getCodiceCatastale(comuneNascita);
//		else
//			CodiceFiscale += getCodiceNazione(nazioneNascita);
//		
//		aggiungere il carattere di controllo
		return CodiceFiscale;
	}
	
	
	
	private String getCodiceNazione(String nomeNazione)
	{
		// TODO
		// cerca il codice della nazione nel database, se non lo trovi lancia eccezione
		return "";
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
        return s != null && s.matches("^[a-zA-Z']*$");
    }
	
	private boolean isConsonante(char c) {
		return (c!='a' && c!= 'i' && c!='e' && c!='o' && c!='u');
	}
	
	private boolean isVocale(char c) {
		return (c=='a' || c=='i' || c=='e' || c=='o' || c=='u');
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
	
}
