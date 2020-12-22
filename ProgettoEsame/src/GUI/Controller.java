package GUI;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JDialog;

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
	FrameForJDialog DialogCF;
	
	public static void main(String[] args) throws SQLException {
		Controller controller=new Controller();
	}

	public Controller() {
		super();
		HomePage=new Demo_Menu_Principale(this);
		HomePage.setVisible(true);
		
	}
	
	public void GotoFrameInsertAtleta() throws SQLException {

		HomePage.setVisible(false);
		F1=new Insert_Atleta(this);
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
					
			} catch (Exception e1) {
				DialogCF=new FrameForJDialog(this);
				DialogCF.setVisible(true);	
				try {
					F1.SvuotaCampi();
				} catch (Exception e) {
					System.out.println("inserire un valore nelle combobox");
				}
				
			}
	}
	
	public void GotoHomePageFromInsertAtleta() {
		F1.setVisible(false);
		HomePage.setVisible(true);
	}
	

}
