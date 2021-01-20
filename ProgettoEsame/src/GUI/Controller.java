package GUI;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import Entity.*;
import ImplementationDAO.ControllerDAO;
import ImplementationDAO.ImplementationDAO;
import MyExceptions.EccezioneCF;

public class Controller {
	
	HomePage HomePage;
	InsertAtleta PageInsertAtleta;
	InsertProcuratoreSportivo PageInsertProcuratore;
	ViewAtleti PageViewAtleti;
	FrameForJDialog DialogErrori;
	InsertIngaggio PageInsertIngaggio;
	ViewProcuratori PageViewProcuratori;
	InfoAtleta PageInfoAtleta;
	InfoProcuratore PageInfoProcuratore;
	InsertContratto PageInsertContratto;

	public static void main(String[] args) {
		Controller controller=new Controller();
	}

	public Controller() {
		super();
		HomePage=new HomePage(this);
		HomePage.setVisible(true);
		
	}
	
	public void GotoFrameInsertAtleta() {
		HomePage.setVisible(false);
		PageInsertAtleta=new InsertAtleta(this);
		PageInsertAtleta.setVisible(true);
	}
	
	public boolean NazioneIsItalia(int IndexNazione) {
		try {
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
			ArrayList <Nazione> QueryNazioni=(ArrayList) OggettoConnessione.GetNazioni();  
			if (QueryNazioni.get(IndexNazione).getCodiceAt().equals("Z000")) 
				return true;
			else
				return false;
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 200, 200);
            Dialog.setVisible(true); 
            return false;
		}
	}
	
	public List<Nazione> GetDatiComboboxNazioni() {
		ArrayList<Nazione> QueryNazioni=new ArrayList<>();
		try {
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
			QueryNazioni=(ArrayList) OggettoConnessione.GetNazioni();  
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(700, 150, 200, 200);
            Dialog.setVisible(true);
		}
		return QueryNazioni;
	}
	
	public List<Provincia> GetDatiComboBoxProvince(int IndexNazione) {
		ArrayList<Provincia> QueryProvince=new ArrayList<>();
		try {
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
			ArrayList <Nazione> QueryNazioni=(ArrayList) OggettoConnessione.GetNazioni();
			QueryProvince=(ArrayList) OggettoConnessione.GetProvinceByNazione(QueryNazioni.get(IndexNazione));	
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 200, 200);
            Dialog.setVisible(true);
		}
		return QueryProvince;
	}

	public List<Comune> GetDatiComboBoxComuni(int IndexNazione,int IndexProvincia) {
		ArrayList<Comune> QueryComuni=new ArrayList<>();
		try {
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
			ArrayList <Nazione> QueryNazioni=(ArrayList) OggettoConnessione.GetNazioni(); 
			ArrayList <Provincia> QueryProvince=(ArrayList) OggettoConnessione.GetProvinceByNazione(QueryNazioni.get(IndexNazione));
			QueryComuni=(ArrayList) OggettoConnessione.GetComuniByProvincia(QueryProvince.get(IndexProvincia));
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 200, 200);
            Dialog.setVisible(true);
		}
		return QueryComuni;
	}
	
	public void InsertAtletaInDB(Atleta atleta) {	
			try {
				ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
				OggettoConnessione.InsertAtleta(atleta);
				JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
	            JLabel LabelJDialog= new JLabel("L'atleta è stato inserito con successo",SwingConstants.CENTER); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400,250, 250, 200);
	            Dialog.setVisible(true); 
	            PageInsertAtleta.SvuotaCampi();		
			} catch (EccezioneCF e) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errori di inserimento dati",SwingConstants.CENTER); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 250, 250, 200);
	            Dialog.setVisible(true); 	
			} catch (SQLException e1) {	
				if(Integer.parseInt(e1.getSQLState().toString())== 23505){
					JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
		            JLabel LabelJDialog= new JLabel("Atleta già presente",SwingConstants.CENTER); 
	                Dialog.add(LabelJDialog); 
	                Dialog.setBounds(400, 350, 200, 200);
		            Dialog.setVisible(true); 
				}
				else {
					JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
		            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
	                Dialog.add(LabelJDialog); 
	                Dialog.setBounds(400, 350, 200, 200);
		            Dialog.setVisible(true); 
				}
			} 
	}
	
	public void GotoHomePageFromInsertAtleta() {
		PageInsertAtleta.dispose();
		HomePage.setVisible(true);
	}
	
	public void GotoFrameInsertProcuratore() {
		HomePage.setVisible(false);
		PageInsertProcuratore=new InsertProcuratoreSportivo(this);
		PageInsertProcuratore.setVisible(true);		
	}
	
	public void InsertProcuratoreInDB(ProcuratoreSportivo procuratore) {
			try {
				ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
				OggettoConnessione.InsertProcuratoreSportivo(procuratore);
				JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
	            JLabel LabelJDialog= new JLabel("Il procuratore è stato inserito con successo",SwingConstants.CENTER); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400,250, 270, 200);
	            Dialog.setVisible(true); 
	            PageInsertProcuratore.SvuotaCampi();		
			} catch (EccezioneCF e) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errori di inserimento dati",SwingConstants.CENTER); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 250, 250, 200);
	            Dialog.setVisible(true); 	
			} catch (SQLException e1) {	
				if(Integer.parseInt(e1.getSQLState().toString())== 23505){
					JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
		            JLabel LabelJDialog= new JLabel("Procuratore già presente",SwingConstants.CENTER); 
	                Dialog.add(LabelJDialog); 
	                Dialog.setBounds(400, 350, 200, 200);
		            Dialog.setVisible(true); 
				}
				else {
					JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
		            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
	                Dialog.add(LabelJDialog); 
	                Dialog.setBounds(400, 350, 200, 200);
		            Dialog.setVisible(true); 
				}
			} 
	}
	
	public void GotoHomePageFromInsertProcuratoreSportivo() {
		PageInsertProcuratore.dispose();
		HomePage.setVisible(true);
	}
	
	public void GoToPageViewAtleti() {
		HomePage.setVisible(false);
		PageViewAtleti=new ViewAtleti(this);
		PageViewAtleti.setVisible(true);
	}
	
	public void GotoHomePageFromPageViewAtleti() {
		PageViewAtleti.dispose();
		HomePage.setVisible(true);
	}
	
	public void InsertIngaggioInDB(Ingaggio ingaggio) {
		try {			
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();		
			OggettoConnessione.InsertIngaggio(ingaggio);
			JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
            JLabel LabelJDialog= new JLabel("Il procuratore è stato ingaggiato",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400,250, 270, 200);
            Dialog.setVisible(true); 
			PageInsertIngaggio.SvuotaCampi();
		} catch (EccezioneCF e1) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errori di inserimento dati"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 250, 250, 200);
            Dialog.setVisible(true);
		} catch (SQLException e2) {	
			if(Integer.parseInt(e2.getSQLState().toString())== 23505){
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Ingaggio già presente",SwingConstants.CENTER); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 350, 200, 200);
	            Dialog.setVisible(true); 
			}
			else {
				JDialog Dialog = new JDialog(DialogErrori,"Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 350, 200, 200);
	            Dialog.setVisible(true); 
			}
		} 
	}

	public void GoToViewProcuratori () {
		HomePage.setVisible(false);
		PageViewProcuratori= new ViewProcuratori(this);
		PageViewProcuratori.setVisible(true);
		
	}
	
	public void GotoHomePageFromViewProcuratori() {
		PageViewProcuratori.dispose();
		HomePage.setVisible(true);
	}
	
	public void GoToInsertIngaggio() {
		HomePage.setVisible(false);
		PageInsertIngaggio=new InsertIngaggio(this);
		PageInsertIngaggio.setVisible(true);
	}

	public List<ProcuratoreSportivo> GetProcuratori() {
		List<ProcuratoreSportivo> QueryProcuratori=new ArrayList<>();
		try {
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
			QueryProcuratori=(ArrayList) OggettoConnessione.GetProcuratori();
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
			JLabel LabelJDialog= new JLabel("Errori di inserimento dati",SwingConstants.CENTER); 
			Dialog.add(LabelJDialog); 
			Dialog.setBounds(400, 250, 250, 200);
			Dialog.setVisible(true); 			
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.add(LabelJDialog); 
			Dialog.setBounds(400, 350, 250, 200);
			Dialog.setVisible(true); 
		} 
		return QueryProcuratori;
	}
	
	public List<Atleta> GetAtleti() {
		List<Atleta> QueryAtleti=new ArrayList<>();
		try {
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
		    QueryAtleti=(ArrayList) OggettoConnessione.GetAtleti();
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
			JLabel LabelJDialog= new JLabel("Errori di inserimento dati",SwingConstants.CENTER); 
			Dialog.add(LabelJDialog); 
			Dialog.setBounds(400, 250, 250, 200);
			Dialog.setVisible(true); 			
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.add(LabelJDialog); 
			Dialog.setBounds(400, 350, 250, 200);
			Dialog.setVisible(true); 
		} 
		return QueryAtleti;
	}
	
	public void GotoHomePageFromInsertIngaggio() {
		PageInsertIngaggio.dispose();
		HomePage.setVisible(true);
	}
	
	public void GotoInfoAtletaFromVisualizzaAtleta(Atleta atletaSelezionato) {
		PageViewAtleti.setVisible(false);
		PageInfoAtleta = new InfoAtleta(this,atletaSelezionato);
		PageInfoAtleta.setVisible(true);	
	}
	
	public void GotoHomeFromInfoAtleta() {
		PageInfoAtleta.dispose();
		HomePage.setVisible(true);
	}
	
	public void GotoInfoProcuratoreFromVisualizzaProcuratore(ProcuratoreSportivo procuratore) {
		PageViewProcuratori.setVisible(false);
		PageInfoProcuratore = new InfoProcuratore(this,procuratore);
		PageInfoProcuratore.setVisible(true);
		
	}

	public void GotoInfoProcuratoreFromInfoAtleta(ProcuratoreSportivo proc) {
		PageInfoAtleta.setVisible(false);
		PageInfoProcuratore = new InfoProcuratore(this,proc);
		PageInfoProcuratore.setVisible(true);
	}
	
	public void GotoHomeFromInfoProcuratore() {
		PageInfoProcuratore.dispose();
		HomePage.setVisible(true);
	}
	
	public void GotoVisualizzaProcuratoreFromInfoProcuratore() {
		if(PageViewProcuratori == null)
			PageViewProcuratori=new ViewProcuratori(this);
		PageInfoProcuratore.dispose();
		PageViewProcuratori.setVisible(true);
	}
	
	public void GotoVisualizzaAtletiFromInfoAtleta() {
		PageInfoAtleta.dispose();
		PageViewAtleti.setVisible(true);
	}
	
	public void GoToPageInsertContrattoInDB() {
			HomePage.setVisible(false);
			PageInsertContratto=new InsertContratto(this);
			PageInsertContratto.setVisible(true);
	}
	
	public List<Sponsor> GetSponsorForContratto() {
		ArrayList<Sponsor> QuerySponsor=new ArrayList();
		try {
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
			QuerySponsor=(ArrayList) OggettoConnessione.GetSponsor();
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		}
		return QuerySponsor;
	}
	
	public List<ClubSportivo> GetClubForContratto() {
		ArrayList<ClubSportivo> QueryClub=new ArrayList();
		try {
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();
			QueryClub=(ArrayList) OggettoConnessione.GetClubSportivi();
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		}
		return QueryClub;
		
	}
	
	public void GotoHomeFromPageInsertContratto() {
		PageInsertContratto.dispose();
		HomePage.setVisible(true);
	}

	public void InsertContratto(Contratto contratto) {
		try {			
			ImplementationDAO OggettoConnessione = ControllerDAO.getInstance().getDAO();		
			OggettoConnessione.InsertContratto(contratto);
			JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
            JLabel LabelJDialog= new JLabel("Il Contratto è stato registrato",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400,250, 270, 200);
            Dialog.setVisible(true); 
			PageInsertContratto.SvuotaCampi();
		} catch (EccezioneCF e1) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errori di inserimento dati",SwingConstants.CENTER); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 250, 250, 200);
            Dialog.setVisible(true);
		} catch (SQLException e2) {	
			if(Integer.parseInt(e2.getSQLState().toString())== 23505){
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Contratto già presente",SwingConstants.CENTER); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 350, 200, 200);
	            Dialog.setVisible(true); 
			}
			else {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 350, 200, 200);
	            Dialog.setVisible(true); 
			}
		} 
	}

	public ProcuratoreSportivo GetProcuratoreAtletaAttivo(Atleta atleta) {
		ProcuratoreSportivo procuratore=null;
		try {
			ImplementationDAO DAO = ControllerDAO.getInstance().getDAO();
			procuratore = DAO.GetProcuratoreAttivo(atleta);
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
			JLabel LabelJDialog= new JLabel("Dati non compatibili con il sistema",SwingConstants.CENTER); 
			Dialog.add(LabelJDialog); 
			Dialog.setBounds(700, 150, 200, 200);
			Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.add(LabelJDialog); 
			Dialog.setBounds(700, 150, 200, 200);
			Dialog.setVisible(true);
		}
		return procuratore;
	}

	public Object[][] GetDatiTabContrattiAttiviAtleta(Atleta atleta,String[] ColonneTabContratti) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerDAO.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContrattiAttivi();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if (!(contrattiAttivo.getAtletaSottosritto().getCF().equals(atleta.getCF())) || ((contrattiAttivo.getClub()==null)&&(contrattiAttivo.getSponsor()==null)))
			        it.remove();
			    }
			Contenutotab=new Object [ContrattiAttivi.size()][ColonneTabContratti.length];		
			for(int i=0;i<ContrattiAttivi.size();i++){
					Contratto TmpContratto=ContrattiAttivi.get(i);	
					String NomeClub_Sponsor;
					if(TmpContratto.getSponsor()!=null)
						NomeClub_Sponsor=TmpContratto.getSponsor().getNomeSponsor();
					else
						NomeClub_Sponsor=TmpContratto.getClub().getNomeClub();	
					
					double Guadagno=TmpContratto.getCompensoAtleta();
					if(Guadagno==0)
						Guadagno=TmpContratto.getGettonePresenzaNazionale();			
					Contenutotab[i][0]=TmpContratto.getIdContratto();
					Contenutotab[i][1]=TmpContratto.getTipo();
					Contenutotab[i][2]=NomeClub_Sponsor;
					Contenutotab[i][3]=TmpContratto.getDataInizio();
					Contenutotab[i][4]=TmpContratto.getDataFine();
					Contenutotab[i][5]=Guadagno;
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}
	
	public Object[][] GetDatiTabContrattiAtleta(Atleta atleta,String[] ColonneTabContratti) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerDAO.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContratti();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if (!(contrattiAttivo.getAtletaSottosritto().getCF().equals(atleta.getCF())) || ((contrattiAttivo.getClub()==null)&&(contrattiAttivo.getSponsor()==null))) {
			        it.remove();
			    }
			}
			Contenutotab=new Object [ContrattiAttivi.size()][ColonneTabContratti.length];		
			for(int i=0;i<ContrattiAttivi.size();i++){
					Contratto TmpContratto=ContrattiAttivi.get(i);	
					String NomeClub_Sponsor;
					if(TmpContratto.getSponsor()!=null)
						NomeClub_Sponsor=TmpContratto.getSponsor().getNomeSponsor();
					else
						NomeClub_Sponsor=TmpContratto.getClub().getNomeClub();	
					
					double Guadagno=TmpContratto.getCompensoAtleta();
					if(Guadagno==0)
						Guadagno=TmpContratto.getGettonePresenzaNazionale();
					Contenutotab[i][0]=TmpContratto.getIdContratto();
					Contenutotab[i][1]=TmpContratto.getTipo();
					Contenutotab[i][2]=NomeClub_Sponsor;
					Contenutotab[i][3]=TmpContratto.getDataInizio();
					Contenutotab[i][4]=TmpContratto.getDataFine();
					Contenutotab[i][5]=Guadagno;	
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}

	public Object[][] GetDatiTabContrattiNazionaliAtleta(Atleta atleta,String[] ColonneContrattiNazionale ){
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerDAO.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContratti();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if (!(contrattiAttivo.getAtletaSottosritto().getCF().equals(atleta.getCF())) || (contrattiAttivo.getCompensoAtleta()!=0)
			    		 || ((contrattiAttivo.getClub()==null)&&(contrattiAttivo.getSponsor()==null))) {
			        it.remove();
			    }
			}
			Contenutotab=new Object [ContrattiAttivi.size()][ColonneContrattiNazionale.length];		
			for(int i=0;i<ContrattiAttivi.size();i++){
					Contratto TmpContratto=ContrattiAttivi.get(i);	
					Contenutotab[i][0]=TmpContratto.getIdContratto();
					Contenutotab[i][1]=TmpContratto.getClub().getNomeClub();
					Contenutotab[i][2]=TmpContratto.getDataInizio();
					Contenutotab[i][3]=TmpContratto.getDataFine();
					Contenutotab[i][4]=TmpContratto.getGettonePresenzaNazionale();	
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}

	public Object[][] GetDatiTabProcuratoriAtleta(Atleta atleta,String[] ColonneTabStoriaProcuratori) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerDAO.getInstance().getDAO();
			ArrayList<Ingaggio> Ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByAtleta(atleta);	
			Contenutotab=new Object [Ingaggi.size()][ColonneTabStoriaProcuratori.length];		
			for(int i=0;i<Ingaggi.size();i++){
					Ingaggio TmpIngaggio=Ingaggi.get(i);	

					Contenutotab[i][0]=TmpIngaggio.getProcuratore().getCF();
					Contenutotab[i][1]=TmpIngaggio.getProcuratore().getNome();
					Contenutotab[i][2]=TmpIngaggio.getProcuratore().getCognome();
					Contenutotab[i][3]=TmpIngaggio.getDataInizio();
					Contenutotab[i][4]=TmpIngaggio.getDataFine();
					Contenutotab[i][5]=TmpIngaggio.getStipendioProcuratore();
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}
	
	public Object[][] GetDatiTabContrattiMiglioriAtleta(Atleta atleta,String[] ColonneContrattiMigliori, LocalDate DataInizio,LocalDate DataFine) {
		Object[][] ContenutoTab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerDAO.getInstance().getDAO(); 
		    ArrayList<Contratto> ContrattiMigliori = (ArrayList) DAO.GetMaxContrattiAtleta(atleta,java.sql.Date.valueOf(DataInizio),java.sql.Date.valueOf(DataFine));  
		    for (Iterator it = ContrattiMigliori.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if (contrattiAttivo.getCompensoAtleta()==0 || ((contrattiAttivo.getClub()==null)&&(contrattiAttivo.getSponsor()==null))) {
			        it.remove();
			    }
			}  
			ContenutoTab = new Object[ContrattiMigliori.size()][ColonneContrattiMigliori.length];
			for(int i=0;i<ContrattiMigliori.size();i++){
				TipoContratto tipoContratto = ContrattiMigliori.get(i).getTipo();
				if(tipoContratto.equals(TipoContratto.Club)){
					ContenutoTab[i][0] = ContrattiMigliori.get(i).getIdContratto();
					ContenutoTab[i][1] = "Club";
					ContenutoTab[i][2] = ContrattiMigliori.get(i).getClub().getNomeClub();	
					ContenutoTab[i][3] = ContrattiMigliori.get(i).getDataInizio();
					ContenutoTab[i][4] = ContrattiMigliori.get(i).getDataFine();
					ContenutoTab[i][5] = ContrattiMigliori.get(i).getCompensoAtleta();
				}
				else {
					ContenutoTab[i][0] = ContrattiMigliori.get(i).getIdContratto();
					ContenutoTab[i][1] = "Sponsor";
					ContenutoTab[i][2] = ContrattiMigliori.get(i).getSponsor().getNomeSponsor();
					ContenutoTab[i][3] = ContrattiMigliori.get(i).getDataInizio();
					ContenutoTab[i][4] = ContrattiMigliori.get(i).getDataFine();
					ContenutoTab[i][5] = ContrattiMigliori.get(i).getCompensoAtleta();
				}	
			}
		} catch (EccezioneCF e1) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Elementi non visualizzabili",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		} catch (SQLException e1) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Tutti i campi devono essere inseriti",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}				
		return ContenutoTab;		
	}

	public Object[][] GetDatiTabContrattiNazionaleMiglioriAtleta(Atleta atleta,String[] ColonneContrattiNazionale,LocalDate DataInizio,LocalDate DataFine){
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerDAO.getInstance().getDAO();
		    ArrayList<Contratto> ContrattiMigliori = (ArrayList) DAO.GetMaxContrattiAtleta(atleta,java.sql.Date.valueOf(DataInizio),java.sql.Date.valueOf(DataFine));
			for (Iterator it = ContrattiMigliori.iterator(); it.hasNext();) {
			    Contratto ContrattoMigliore = (Contratto) it.next();
			    if (!(ContrattoMigliore.getAtletaSottosritto().getCF().equals(atleta.getCF())) || (ContrattoMigliore.getCompensoAtleta()!=0)
			    		|| ((ContrattoMigliore.getClub()==null)&&(ContrattoMigliore.getSponsor()==null))) {
			        it.remove();
			    }
			}
			Contenutotab=new Object [ContrattiMigliori.size()][ColonneContrattiNazionale.length];		
			for(int i=0;i<ContrattiMigliori.size();i++){
					Contratto TmpContratto=ContrattiMigliori.get(i);	
					Contenutotab[i][0]=TmpContratto.getIdContratto();
					Contenutotab[i][1]=TmpContratto.getClub().getNomeClub();
					Contenutotab[i][2]=TmpContratto.getDataInizio();
					Contenutotab[i][3]=TmpContratto.getDataFine();
					Contenutotab[i][4]=TmpContratto.getGettonePresenzaNazionale();	
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}
	
	public Object[][] GetDatiTabContrattiMiglioriProcuratore(ProcuratoreSportivo Procuratore,String[] ColonneContrattiMigliori,LocalDate DataInizio,LocalDate DataFine){
		Object[][] ContenutoTab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerDAO.getInstance().getDAO();
		    ArrayList<Contratto> ContrattiMigliori = (ArrayList) DAO.GetMaxContrattiProcuratori(Procuratore,java.sql.Date.valueOf(DataInizio),java.sql.Date.valueOf(DataFine));
		    ContenutoTab = new Object[ContrattiMigliori.size()][ColonneContrattiMigliori.length]; 
		    for (Iterator it = ContrattiMigliori.iterator(); it.hasNext();) {
			    Contratto ContrattiMigliore = (Contratto) it.next();
			    if ((ContrattiMigliore.getClub()==null)&&(ContrattiMigliore.getSponsor()==null))
			        it.remove();
			    }
			for(int i=0;i<ContrattiMigliori.size();i++) {
				TipoContratto tipoContratto = ContrattiMigliori.get(i).getTipo();
				if(tipoContratto.equals(TipoContratto.Club)){
					ContenutoTab[i][0] = ContrattiMigliori.get(i).getIdContratto();
					ContenutoTab[i][1] = ContrattiMigliori.get(i).getClub().getNomeClub();
					ContenutoTab[i][2] = "Club";
					ContenutoTab[i][3] = ContrattiMigliori.get(i).getCompensoProcuratore();
				}
				else {
					ContenutoTab[i][0] = ContrattiMigliori.get(i).getIdContratto();
					ContenutoTab[i][1] = ContrattiMigliori.get(i).getSponsor().getNomeSponsor();
					ContenutoTab[i][2] = "Sponsor";
					ContenutoTab[i][3] = ContrattiMigliori.get(i).getCompensoProcuratore();
				}
			}
		} catch (EccezioneCF e1) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Elementi non visualizzabili",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			e1.printStackTrace();
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Tutti i campi devono essere inseriti",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}	
		return ContenutoTab;
	}	
	
	public Object[][] GetDatiTabIngaggiMiglioriProcuratore(ProcuratoreSportivo Procuratore,String[] ColonneIngaggiMigliori,LocalDate DataInizio,LocalDate DataFine){
		Object[][] ContenutoTab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerDAO.getInstance().getDAO();
		    ArrayList<Ingaggio> IngaggiMigliori = new ArrayList<Ingaggio>();
		    IngaggiMigliori = (ArrayList) DAO.GetIngaggiMigliori(Procuratore,java.sql.Date.valueOf(DataInizio),java.sql.Date.valueOf(DataFine));
			ContenutoTab = new Object[IngaggiMigliori.size()][ColonneIngaggiMigliori.length];
			for(int i=0;i<IngaggiMigliori.size();i++){
				ContenutoTab[i][0] = IngaggiMigliori.get(i).getAtleta().getNome();
				ContenutoTab[i][1] = IngaggiMigliori.get(i).getAtleta().getCognome();
				ContenutoTab[i][2] = IngaggiMigliori.get(i).getAtleta().getCF();
				ContenutoTab[i][3] = IngaggiMigliori.get(i).getDataInizio();
				ContenutoTab[i][4] = IngaggiMigliori.get(i).getDataFine();
				ContenutoTab[i][5] = IngaggiMigliori.get(i).getStipendioProcuratore();
			}
		} catch (EccezioneCF e1) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Elementi non visualizzabili",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			e1.printStackTrace();
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Tutti i campi devono essere inseriti",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}	
		return ContenutoTab;
	}
	
	public Object[][] GetDatiTabContrattiAttiviProcuratore(ProcuratoreSportivo Procuratore,String[] ColonneTabContratti) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerDAO.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContrattiAttivi();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if(contrattiAttivo.getProcuratoreInteressato()==null) {
			    	it.remove();
			    	continue;
			    }	
			    if (!(contrattiAttivo.getProcuratoreInteressato().getCF().equals(Procuratore.getCF()))) {
			        it.remove();
			    }
			}
			Contenutotab=new Object [ContrattiAttivi.size()][ColonneTabContratti.length];		
			for(int i=0;i<ContrattiAttivi.size();i++){
					Contratto TmpContratto=ContrattiAttivi.get(i);	
					String NomeClub_Sponsor;
					if(TmpContratto.getSponsor()!=null)
						NomeClub_Sponsor=TmpContratto.getSponsor().getNomeSponsor();
					else
						NomeClub_Sponsor=TmpContratto.getClub().getNomeClub();	
					
					double Guadagno=TmpContratto.getCompensoAtleta();
					if(TmpContratto.getCompensoAtleta()==0)
						Guadagno=TmpContratto.getGettonePresenzaNazionale();

					Contenutotab[i][0]=TmpContratto.getIdContratto();
					Contenutotab[i][1]=TmpContratto.getTipo();
					Contenutotab[i][2]=NomeClub_Sponsor;
					Contenutotab[i][3]=TmpContratto.getDataInizio();
					Contenutotab[i][4]=TmpContratto.getDataFine();
					Contenutotab[i][5]=TmpContratto.getCompensoProcuratore();
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			e1.printStackTrace();
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}
	
	public Object[][] GetDatiTabContrattiProcuratore(ProcuratoreSportivo Procuratore,String[] ColonneTabContratti) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerDAO.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContratti();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if(contrattiAttivo.getProcuratoreInteressato()==null) {
			    	it.remove();
			    	continue;}
			    	if (!(contrattiAttivo.getProcuratoreInteressato().getCF().equals(Procuratore.getCF()))) {
			    		it.remove();
			    }	    	
			}
			Contenutotab=new Object [ContrattiAttivi.size()][ColonneTabContratti.length];		
			for(int i=0;i<ContrattiAttivi.size();i++){
					Contratto TmpContratto=ContrattiAttivi.get(i);	
					String NomeClub_Sponsor;
					if(TmpContratto.getSponsor()!=null)
						NomeClub_Sponsor=TmpContratto.getSponsor().getNomeSponsor();
					else
						NomeClub_Sponsor=TmpContratto.getClub().getNomeClub();	
					
					double Guadagno=TmpContratto.getCompensoAtleta();
					if(TmpContratto.getCompensoAtleta()==0)
						Guadagno=TmpContratto.getGettonePresenzaNazionale();

						Contenutotab[i][0]=TmpContratto.getIdContratto();
						Contenutotab[i][1]=TmpContratto.getTipo();
						Contenutotab[i][2]=NomeClub_Sponsor;
						Contenutotab[i][3]=TmpContratto.getDataInizio();
						Contenutotab[i][4]=TmpContratto.getDataFine();
						Contenutotab[i][5]=TmpContratto.getCompensoProcuratore();
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}
	
	public Object[][] GetDatiTabIngaggiAtletiAttiviProcuratore(ProcuratoreSportivo Procuratore,String[] ColonneTabStoriaAtleti) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerDAO.getInstance().getDAO();
			ArrayList<Ingaggio> Ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByProcuratoreAttivi(Procuratore);	
			Contenutotab=new Object [Ingaggi.size()][ColonneTabStoriaAtleti.length];		
			for(int i=0;i<Ingaggi.size();i++){
					Ingaggio TmpIngaggio=Ingaggi.get(i);	

					Contenutotab[i][0]=TmpIngaggio.getAtleta().getCF();
					Contenutotab[i][1]=TmpIngaggio.getAtleta().getNome();
					Contenutotab[i][2]=TmpIngaggio.getAtleta().getCognome();
					Contenutotab[i][3]=TmpIngaggio.getDataInizio();
					Contenutotab[i][4]=TmpIngaggio.getDataFine();
					Contenutotab[i][5]=TmpIngaggio.getStipendioProcuratore();
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}

	public Object[][] GetDatiTabIngaggiAtletiProcuratore(ProcuratoreSportivo Procuratore, String[] ColonneTabStoriaAtleti) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerDAO.getInstance().getDAO();
			ArrayList<Ingaggio> Ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByProcuratore(Procuratore);	
			Contenutotab=new Object [Ingaggi.size()][ColonneTabStoriaAtleti.length];		
			for(int i=0;i<Ingaggi.size();i++){
					Ingaggio TmpIngaggio=Ingaggi.get(i);	
					Contenutotab[i][0]=TmpIngaggio.getAtleta().getCF();
					Contenutotab[i][1]=TmpIngaggio.getAtleta().getNome();
					Contenutotab[i][2]=TmpIngaggio.getAtleta().getCognome();
					Contenutotab[i][3]=TmpIngaggio.getDataInizio();
					Contenutotab[i][4]=TmpIngaggio.getDataFine();
					Contenutotab[i][5]=TmpIngaggio.getStipendioProcuratore();
			}	
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (NullPointerException e1) {
			JDialog Dialog = new JDialog(); 
	        JLabel LabelJDialog= new JLabel("Non è stato trovato nulla",SwingConstants.CENTER); 
	        Dialog.getContentPane().add(LabelJDialog); 
            Dialog.setBounds(400, 150, 240, 150);
	        Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}


	
}


