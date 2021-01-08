package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Contratto;
import Entità.Ingaggio;
import Entità.Persona;
import Entità.ProcuratoreSportivo;
import ImplementationDAO.ImplementationDAO;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class InfoAtleta2 extends JFrame {

	private JPanel contentPane;
	private JTextField ProcuratoreAttivoField;
	private JTable TabellaContrattiAttivi;
	Controller controller;
	private JTextField textField;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InfoAtleta2 frame = new InfoAtleta2();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws EccezioneCF 
	 */
	public InfoAtleta2(Controller c,Atleta atleta) throws SQLException, EccezioneCF {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImplementationDAO DAO = ControllerQuery.getInstance().getDAO();
		
		JLabel NomeProcuratoreLabel = new JLabel("Procuratore Attuale:");
		NomeProcuratoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		NomeProcuratoreLabel.setBounds(10, 71, 293, 18);
		contentPane.add(NomeProcuratoreLabel);
		
		JButton InfoProcuratoreButton = new JButton("Info Procuratore");
		InfoProcuratoreButton.setBounds(341, 70, 144, 23);
		contentPane.add(InfoProcuratoreButton);
		
		JLabel IntroitiAttualiLabel = new JLabel("Contratti Attivi:");
		IntroitiAttualiLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		IntroitiAttualiLabel.setBounds(10, 111, 144, 18);
		contentPane.add(IntroitiAttualiLabel);
		
		ProcuratoreAttivoField = new JTextField();
		ProcuratoreAttivoField.setEditable(false);
		ProcuratoreAttivoField.setBounds(180, 71, 123, 20);
		contentPane.add(ProcuratoreAttivoField);
		ProcuratoreAttivoField.setColumns(10);
		ProcuratoreSportivo procuratore;
		procuratore = DAO.GetProcuratoreAttivo(atleta);
		if(procuratore!=null)
			ProcuratoreAttivoField.setText(procuratore.getNome()+" "+procuratore.getCognome());
		else {
			ProcuratoreAttivoField.setVisible(false);
			InfoProcuratoreButton.setVisible(false);
			NomeProcuratoreLabel.setText("Procuratore Attuale: Nessuno");
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 700, 87);
		contentPane.add(scrollPane);
		
		JButton StoriaContrattiButton = new JButton("Storia Contratti");
		StoriaContrattiButton.setBounds(341, 110, 144, 23);
		contentPane.add(StoriaContrattiButton);
		
		JButton StoriaProcuratoriButton = new JButton("Storia Procuratori");
		StoriaProcuratoriButton.setBounds(515, 70, 144, 23);
		contentPane.add(StoriaProcuratoriButton);
		
		JLabel InfoLabel = new JLabel("Informazioni dell'atleta:"+" "+atleta.getNome()+" "+atleta.getCognome());
		InfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		InfoLabel.setBounds(10, 33, 509, 21);
		contentPane.add(InfoLabel);
					
		Object[] Colonne= {"Id Contratto", "Club/Sponsor", "Entita Stipulante", "Data Fine", "Compenso"};
		TabellaContrattiAttivi = new JTable();
		scrollPane.setViewportView(TabellaContrattiAttivi);
		
		TabellaContrattiAttivi.setModel(new DefaultTableModel(
				PopolaTabellaContrattiAttivi(atleta),Colonne 
				) {private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {return false;}});
	
		
		
//		double SommaGuadagni=0.0;
//		tableprova.getRowCount();
//		for(int i=0;i<tableprova.getRowCount();i++) {
//			SommaGuadagni+=(Double)tableprova.getValueAt(i,4);
//		}
//		
//		textField = new JTextField();
//		textField.setBounds(525, 273, 86, 20);
//		contentPane.add(textField);
//		textField.setColumns(10);
//		textField.setText(String.valueOf(SommaGuadagni));
//		
		
	}
	
	public Object[][] PopolaTabellaContrattiAttivi(Atleta atleta) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContrattiAttivi();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if (!(contrattiAttivo.getAtletaSottosritto().getCF().equals(atleta.getCF()))) {
			        it.remove();
			    }
			}
			Contenutotab=new Object [ContrattiAttivi.size()][5];		
			for(int i=0;i<ContrattiAttivi.size();i++){
					Contratto TmpContratto=ContrattiAttivi.get(i);	
					String W;
					if(TmpContratto.getSponsor()!=null)
						W=TmpContratto.getSponsor().getNomeSponsor();
					else
						W=TmpContratto.getClub().getNomeClub();	
					
					double Guadagno=TmpContratto.getCompensoAtleta();
					if(TmpContratto.getCompensoAtleta()==0)
						Guadagno=TmpContratto.getGettonePresenzaNazionale();

						Contenutotab[i][0]=TmpContratto.getIdContratto();
						Contenutotab[i][1]=TmpContratto.getTipo();
						Contenutotab[i][2]=W;
						Contenutotab[i][3]=TmpContratto.getDataFine();
						Contenutotab[i][4]=TmpContratto.getCompensoAtleta();
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

	

