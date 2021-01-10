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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class InfoProcuratore extends JFrame {

	private JPanel contentPane;
	private JTable TabellaStatistiche;
	Controller controller;

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
	public InfoProcuratore(Controller c,ProcuratoreSportivo proc) throws SQLException, EccezioneCF {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 700, 87);
		contentPane.add(scrollPane);
		
		JLabel InfoLabel = new JLabel("Informazioni del procuratore:"+" "+proc.getNome()+" "+proc.getCognome());
		InfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		InfoLabel.setBounds(10, 33, 509, 21);
		contentPane.add(InfoLabel);
		
		Object[] ColonneTabContratti= {"Id Contratto", "Club/Sponsor", "Entita Stipulante","Data Inizio", "Data Fine", "Guadagno P."};
		Object[] ColonneTabStoriaProcuratori= {"Codice Fiscale A.","Nome","Cognome", "Data Inizio", "Data Fine", "Stipendio"};
		
		TabellaStatistiche = new JTable();
		scrollPane.setViewportView(TabellaStatistiche);
		
		TabellaStatistiche.setModel(new DefaultTableModel(
				PopolaTabellaContrattiAttivi(proc,ColonneTabContratti.length),ColonneTabContratti 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		
		JRadioButton ContrattiAttiviRadioButton = new JRadioButton("Contratti Attivi\r\n");
		ContrattiAttiviRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabellaStatistiche.setModel(new DefaultTableModel(
						PopolaTabellaContrattiAttivi(proc,ColonneTabContratti.length),ColonneTabContratti 
				){
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int r,int c) {
						return false;
					}
				});
				
			}
		});
		ContrattiAttiviRadioButton.setBounds(17, 110, 109, 23);
		contentPane.add(ContrattiAttiviRadioButton);
		ContrattiAttiviRadioButton.setSelected(true);
		
		
		JRadioButton StoriaContrattiRadioButton = new JRadioButton("Storia Contratti");
		StoriaContrattiRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabellaStatistiche.setModel(new DefaultTableModel(
						PopolaTabellaContratti(proc,ColonneTabContratti.length),ColonneTabContratti 
				){
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int r,int c) {
						return false;
					}
				});
			}
		});
		StoriaContrattiRadioButton.setBounds(128, 110, 109, 23);
		contentPane.add(StoriaContrattiRadioButton);
		
		JRadioButton StoriaAtletiRadioButton = new JRadioButton("Storia Atleti ingaggiati");
		StoriaAtletiRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabellaStatistiche.setModel(new DefaultTableModel(
						PopolaTabellaIngaggiAtleti(proc, ColonneTabStoriaProcuratori.length),ColonneTabStoriaProcuratori
				){
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int r,int c) {
						return false;
					}
				});
				TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
			}
		});
	    StoriaAtletiRadioButton.setBounds(338, 110, 167, 23);
	    contentPane.add(StoriaAtletiRadioButton);
	    
	    JRadioButton AtletiAttiviRadioButton = new JRadioButton("Ingaggi Attivi\r\n");
	    AtletiAttiviRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabellaStatistiche.setModel(new DefaultTableModel(
						PopolaTabellaIngaggiAtletiAttivi(proc, ColonneTabStoriaProcuratori.length),ColonneTabStoriaProcuratori
				){
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int r,int c) {
						return false;
					}
				});
				TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
			}
		});
		AtletiAttiviRadioButton.setBounds(239, 110, 109, 23);
		contentPane.add(AtletiAttiviRadioButton);
		
		
	    ButtonGroup GroupTabella = new ButtonGroup();
		    GroupTabella.add(ContrattiAttiviRadioButton);
		    GroupTabella.add(StoriaContrattiRadioButton);
		    GroupTabella.add(StoriaAtletiRadioButton);
		    GroupTabella.add(AtletiAttiviRadioButton);
		    
		    JDateChooser dateChooser = new JDateChooser();
		    dateChooser.setBounds(56, 313, 150, 20);
		    contentPane.add(dateChooser);
		    
		    JDateChooser dateChooser_1 = new JDateChooser();
		    dateChooser_1.setBounds(262, 313, 144, 20);
		    contentPane.add(dateChooser_1);
		    
		    JButton HomeButton = new JButton("Home");
			HomeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.GotoHomeFromInfoProcuratore();
				}
			});
			HomeButton.setBounds(510, 313, 89, 23);
			contentPane.add(HomeButton);
			
			JButton indietroBtn = new JButton("Indietro");
			indietroBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.GotoVisualizzaProcFromInfoProcuratore();
				}
			});
			indietroBtn.setBounds(610, 313, 89, 23);
			contentPane.add(indietroBtn);
			
		
	}
	
	public Object[][] PopolaTabellaContrattiAttivi(ProcuratoreSportivo proc, int NumColonne) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContrattiAttivi();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if(contrattiAttivo.getProcuratoreInteressato()==null)
			    	continue;
			    if (!(contrattiAttivo.getProcuratoreInteressato().getCF().equals(proc.getCF()))) {
			        it.remove();
			    }
			}
			Contenutotab=new Object [ContrattiAttivi.size()][NumColonne];		
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
	
	public Object[][] PopolaTabellaContratti(ProcuratoreSportivo proc,int NumColonne) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContratti();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if(contrattiAttivo.getProcuratoreInteressato()==null)
			    	continue;
			    if (!(contrattiAttivo.getProcuratoreInteressato().getCF().equals(proc.getCF()))) {
			        it.remove();
			    }
			}
			Contenutotab=new Object [ContrattiAttivi.size()][NumColonne];		
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


	public Object[][] PopolaTabellaIngaggiAtletiAttivi(ProcuratoreSportivo proc,int NumColonne) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Ingaggio> Ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByProcuratoreAttivi(proc);	
			Contenutotab=new Object [Ingaggi.size()][NumColonne];		
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
	
	public Object[][] PopolaTabellaIngaggiAtleti(ProcuratoreSportivo proc,int NumColonne) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Ingaggio> Ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByProcuratore(proc);	
			Contenutotab=new Object [Ingaggi.size()][NumColonne];		
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

	

