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
import Entità.TipoContratto;
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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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

public class InfoAtleta extends JFrame {

	private JPanel contentPane;
	private JTextField ProcuratoreAttivoField;
	private JTable TabellaStatistiche;
	Controller controller;
	private JTextField textField;
	private JTextField ClubField_1;
	private JTextField SponsorField_2;

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
	public InfoAtleta(Controller c,Atleta atleta) throws SQLException, EccezioneCF {
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
		
		JLabel InfoLabel = new JLabel("Informazioni dell'atleta:"+" "+atleta.getNome()+" "+atleta.getCognome());
		InfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		InfoLabel.setBounds(10, 33, 509, 21);
		contentPane.add(InfoLabel);
					
		Object[] ColonneTabContrattiAttivi= {"Id Contratto", "Club/Sponsor", "Entita Stipulante", "Data Fine", "Compenso"};
		Object[] ColonneTabContratti= {"Id Contratto", "Club/Sponsor", "Entita Stipulante","Data Inizio","Data Fine", "Compenso"};
		Object[] ColonneTabStoriaProcuratori= {"Codice Fiscale P.","Nome","Cognome", "Data Inizio", "Data Fine", "Stipendio"};
		
		TabellaStatistiche = new JTable();
		scrollPane.setViewportView(TabellaStatistiche);
		
		TabellaStatistiche.setModel(new DefaultTableModel(
				PopolaTabellaContrattiAttivi(atleta,ColonneTabContrattiAttivi.length),ColonneTabContrattiAttivi
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
						PopolaTabellaContrattiAttivi(atleta,ColonneTabContrattiAttivi.length),ColonneTabContrattiAttivi
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
						PopolaTabellaContratti(atleta,ColonneTabContratti.length),ColonneTabContratti 
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
		
		JRadioButton StoriaProcuratoriRadioButton = new JRadioButton("Storia Procuratori");
		StoriaProcuratoriRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabellaStatistiche.setModel(new DefaultTableModel(
						PopolaTabellaProcuratori(atleta, ColonneTabStoriaProcuratori.length),ColonneTabStoriaProcuratori
				){
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int r,int c) {
						return false;
					}
				});
				TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
			}
		});
	    StoriaProcuratoriRadioButton.setBounds(239, 110, 144, 23);
	    contentPane.add(StoriaProcuratoriRadioButton);
		
		
	    ButtonGroup GroupTabella = new ButtonGroup();
		    GroupTabella.add(ContrattiAttiviRadioButton);
		    GroupTabella.add(StoriaContrattiRadioButton);
		    GroupTabella.add(StoriaProcuratoriRadioButton);
		    
		  
		    
		    JDateChooser DateInizioDateChooser = new JDateChooser();
		    DateInizioDateChooser.setBounds(10, 283, 150, 20);
		    contentPane.add(DateInizioDateChooser);
		    
		    JDateChooser DateFineDateChooser_1 = new JDateChooser();
		    DateFineDateChooser_1.setBounds(201, 283, 144, 20);
		    contentPane.add(DateFineDateChooser_1);
		    
		    ClubField_1 = new JTextField();
		    ClubField_1.setBounds(235, 338, 187, 20);
		    contentPane.add(ClubField_1);
		    ClubField_1.setColumns(10);
		    
		    SponsorField_2 = new JTextField();
		    SponsorField_2.setBounds(235, 368, 187, 20);
		    contentPane.add(SponsorField_2);
		    SponsorField_2.setColumns(10);
		    
		    JButton btnNewButton = new JButton("New button");
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
				try {
					LocalDate TempDate1=DateInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				    LocalDate TempDate2=DateFineDateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
				    ArrayList<Contratto> Prova = (ArrayList) DAO.GetMaxContrattiAtleta(atleta,Date.valueOf(TempDate1),Date.valueOf(TempDate2));
					Iterator i=Prova.iterator();
					while(i.hasNext()) {
					Contratto Tmp=(Contratto) i.next();
					System.out.println(Tmp);
					if(Tmp.getTipo().equals(TipoContratto.Club))
					    ClubField_1.setText(String.valueOf(Tmp.getCompensoAtleta()));
					else
						SponsorField_2.setText(String.valueOf(Tmp.getCompensoAtleta()));
					}
				} catch (EccezioneCF e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  
					    
		    	}
		    });
		    btnNewButton.setBounds(451, 283, 89, 23);
		    contentPane.add(btnNewButton);	
		
	}
	
	public Object[][] PopolaTabellaContrattiAttivi(Atleta atleta, int NumColonne) {
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
	
	public Object[][] PopolaTabellaContratti(Atleta atleta,int NumColonne) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContratti();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if (!(contrattiAttivo.getAtletaSottosritto().getCF().equals(atleta.getCF()))) {
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
						Contenutotab[i][5]=TmpContratto.getCompensoAtleta();
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


	public Object[][] PopolaTabellaProcuratori(Atleta atleta,int NumColonne) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Ingaggio> Ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByAtleta(atleta);	
			Contenutotab=new Object [Ingaggi.size()][NumColonne];		
			for(int i=0;i<Ingaggi.size();i++){
					Ingaggio TmpIngaggio=Ingaggi.get(i);	

					Contenutotab[i][0]=TmpIngaggio.getProcuratore().getCF();
					Contenutotab[i][1]=TmpIngaggio.getProcuratore().getNome();
					Contenutotab[i][2]=TmpIngaggio.getProcuratore().getCognome();
					Contenutotab[i][3]=TmpIngaggio.getDataInizio();
					Contenutotab[i][4]=TmpIngaggio.getDataInizio();
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

	

