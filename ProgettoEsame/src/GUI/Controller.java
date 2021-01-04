package GUI;


import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.*;
import ImplementationDAO.ImplementationDAO;

public class Controller {
	
	HomePage HomePage;
	InsertAtleta PageInsertAtleta;
	InsertProcuratoreSportivo PageInsertProcuratore;
	VisualizzaAtleti PageViewAtleti;
	FrameForJDialog DialogErrori;
	InsertIngaggio PageInsertIngaggio;
	VisualizzaProcuratori PageViewProcuratori;
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

		try {
			HomePage.setVisible(false);
			PageInsertAtleta=new InsertAtleta(this);
			PageInsertAtleta.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		}
	}
	
	public void InsertAtletaInDB(Atleta atleta) {	
			try {
				ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();
				OggettoConnessione.InsertAtleta(atleta);
				JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
	            JLabel LabelJDialog= new JLabel("L'atleta è stato inserito con successo"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400,250, 250, 200);
	            Dialog.setVisible(true); 
	            PageInsertAtleta.SvuotaCampi();		
			} catch (EccezioneCF e) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errori di inserimento dati"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 250, 250, 200);
	            Dialog.setVisible(true); 	
			} catch (SQLException e1) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 350, 250, 200);
	            Dialog.setVisible(true); 
			} 
	}
	
	public void GotoHomePageFromInsertAtleta() {
		PageInsertAtleta.setVisible(false);
		HomePage.setVisible(true);
	}
	
	public void GotoFrameInsertProcuratore() {
		try {
			HomePage.setVisible(false);
			PageInsertProcuratore=new InsertProcuratoreSportivo(this);
			PageInsertProcuratore.setVisible(true);		
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		}
		
	}
	
	public void InsertProcuratoreInDB(ProcuratoreSportivo procuratore) {
			try {
				ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();
				OggettoConnessione.InsertProcuratoreSportivo(procuratore);
				JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
	            JLabel LabelJDialog= new JLabel("Il procuratore è stato inserito con successo"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400,250, 270, 200);
	            Dialog.setVisible(true); 
	            PageInsertProcuratore.SvuotaCampi();		
			} catch (EccezioneCF e) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errori di inserimento dati"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 250, 250, 200);
	            Dialog.setVisible(true); 	
			} catch (SQLException e1) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 350, 250, 200);
	            Dialog.setVisible(true); 
			}
	}
	
	public void GotoHomePageFromInsertProcuratoreSportivo() {
		PageInsertProcuratore.setVisible(false);
		HomePage.setVisible(true);
	}
	
	public void GoToPageViewAtleti() {
		HomePage.setVisible(false);
		PageViewAtleti=new VisualizzaAtleti(this);
		PageViewAtleti.setVisible(true);
	}
	
	public void GotoHomePageFromPageViewAtleti() {
		PageViewAtleti.setVisible(false);
		HomePage.setVisible(true);
	}
	
	public void InsertIngaggio(Ingaggio ingaggio) {
		try {			
			ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();		
			OggettoConnessione.InsertIngaggio(ingaggio);
			JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
            JLabel LabelJDialog= new JLabel("Il procuratore è stato ingaggiato"); 
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
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		}  
	}

	public void GoToViewProcuratori () {
		HomePage.setVisible(false);
		PageViewProcuratori= new VisualizzaProcuratori(this);
		PageViewProcuratori.setVisible(true);
		
	}
	
	public void GotoHomePageFromViewProcuratori() {
		PageViewProcuratori.setVisible(false);
		HomePage.setVisible(true);
	}
	
	public void GoToInsertIngaggio() {
		try {
			HomePage.setVisible(false);
			PageInsertIngaggio=new InsertIngaggio(this);
			PageInsertIngaggio.setVisible(true);
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errori di inserimento dati"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 250, 250, 200);
            Dialog.setVisible(true); 			
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		} 
	}

	public void GotoHomePageFromInsertIngaggio() {
		PageInsertIngaggio.setVisible(false);
		HomePage.setVisible(true);
	}
	
	public void GotoInfoAtletaFromVisualizzaAtleta(Atleta atletaSelezionato) {
		PageViewAtleti.setVisible(false);
		PageInfoAtleta = new InfoAtleta(this,atletaSelezionato);
		PageInfoAtleta.setVisible(true);
	}
	
	public void GotoHomeFromInfoAtleta() {
		PageInfoAtleta.setVisible(false);
		HomePage.setVisible(true);
	}
	
	public void GotoInfoProcuratoreFromVisualizzaProcuratore(ProcuratoreSportivo proc) {
		PageViewProcuratori.setVisible(false);
		PageInfoProcuratore = new InfoProcuratore(this,proc);
		PageInfoProcuratore.setVisible(true);
	}
	
	public void GotoHomeFromInfoProcuratore() {
		PageInfoProcuratore.setVisible(false);
		HomePage.setVisible(true);
	}
	
	public void GoToPageInsertContratto() {
		try {
			HomePage.setVisible(false);
			PageInsertContratto=new InsertContratto(this);
			PageInsertContratto.setVisible(true);
		} catch (EccezioneCF e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore inserimento dati"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		}
	}
	
	public void GotoHomeFromPageInsertContratto() {
		PageInsertContratto.setVisible(false);
		HomePage.setVisible(true);
	}

	public void InsertContratto(Contratto contratto) {
		try {			
			ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();		
			OggettoConnessione.InsertContratto(contratto);
			JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
            JLabel LabelJDialog= new JLabel("Il Contratto è stato registrato"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400,250, 270, 200);
            Dialog.setVisible(true); 
			PageInsertContratto.SvuotaCampi();
		} catch (EccezioneCF e1) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errori di inserimento dati"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 250, 250, 200);
            Dialog.setVisible(true);
		} catch (SQLException e2) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 350, 250, 200);
            Dialog.setVisible(true); 
		}  
	}
}


