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
import EccezioniPersona.EccezioneCF;
import Entità.Atleta;
import Entità.Comune;
import Entità.Nazione;
import Entità.Provincia;
import Entità.Sesso;
import ImplementationDAO.ImplementationDAO;

public class Controller {
	
	Demo_Menu_Principale HomePage;
	Insert_Atleta F1;
	FrameForJDialog DialogErrori;
	
	public static void main(String[] args) throws SQLException {
		Controller controller=new Controller();
	}

	public Controller() {
		super();
		HomePage=new Demo_Menu_Principale(this);
		HomePage.setVisible(true);
		
	}
	
	public void GotoFrameInsertAtleta()  {

		HomePage.setVisible(false);
		try {
			F1=new Insert_Atleta(this);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
            JLabel LabelJDialog= new JLabel("Errori ndi connessioe"); 
            Dialog.add(LabelJDialog); 
            Dialog.setBounds(400, 200, 250, 200);
            Dialog.setVisible(true); 
		}
		F1.setVisible(true);
	}
	
	public void InsertAtletaDB(String TempNome, String TempCognome, Sesso TempSesso , Date DataScelta,
			Nazione TempNazione, Provincia TempProvincia, Comune TempComune, boolean hasProcuratore) {
		
			try {
				LocalDate TempDate=LocalDate.ofInstant(DataScelta.toInstant(), ZoneId.systemDefault());
				Atleta TempAtleta;
				TempAtleta = new Atleta(TempNome,TempCognome,TempSesso,TempDate,TempNazione,TempProvincia,TempComune,false);
				ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();
				OggettoConnessione.InsertAtleta(TempAtleta);
				F1.SvuotaCampi();
				JDialog Dialog = new JDialog(DialogErrori, "Successo"); 
	            JLabel LabelJDialog= new JLabel("L'atleta è stato inserito con successo"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 200, 250, 200);
	            Dialog.setVisible(true); 	
	            
			} catch (EccezioneCF e) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errori di inserimento dati"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 200, 250, 200);
	            Dialog.setVisible(true); 	
			} catch (SQLException e1) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Errore di connessioe"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 200, 250, 200);
	            Dialog.setVisible(true); 
			} catch (NullPointerException e) {
				JDialog Dialog = new JDialog(DialogErrori, "Attenzione"); 
	            JLabel LabelJDialog= new JLabel("Tutti i campi devono essere compilati"); 
                Dialog.add(LabelJDialog); 
                Dialog.setBounds(400, 56, 250, 200);
	            Dialog.setVisible(true); 
	        }
			    
	}
	
	public void GotoHomePageFromInsertAtleta() {
		F1.setVisible(false);
		HomePage.setVisible(true);
	}
	

}
