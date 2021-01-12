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
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class InfoAtleta extends JFrame {
	private Atleta atleta;
	private JPanel contentPane;
	private JTextField ProcuratoreAttivoField;
	private JTable TabellaStatistiche;
	Controller controller;
	private JTextField ClubTextField;
	private JTextField SponsorTextField;
	private JTextField SommaGuadagniTextField;
	private JTextField NomeClubTextField;
	private JTextField NomeSponsorTextField;
	private JTable TabellaVantaggi;
	private Object[] ColonneContrattiMigliori= {"Id Contratto","Nome Club/Sponsor", "Club/Sponsor", "Compenso"};
	private Object[] ColonneTabContratti= {"Id Contratto", "Club/Sponsor", "Entita Stipulante","Data Inizio","Data Fine", "Compenso"};
	private Object[] ColonneTabStoriaProcuratori= {"Codice Fiscale P.","Nome","Cognome", "Data Inizio", "Data Fine", "Stipendio"};
	private JLabel TotContratti;
	private JLabel TotContrattiMigliori;
	private JDateChooser DateInizioDateChooser;
	private JDateChooser DateFineDateChooser;

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
		this.atleta = atleta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    TotContratti = new JLabel("Totale= -");
	    TotContratti.setBounds(601, 232, 109, 18);
	    contentPane.add(TotContratti);
	    
	    TotContrattiMigliori = new JLabel("Totale= - ");
	    TotContrattiMigliori.setBounds(601, 437, 109, 18);
	    contentPane.add(TotContrattiMigliori);
		
		ImplementationDAO DAO = ControllerQuery.getInstance().getDAO();
		
		JLabel NomeProcuratoreLabel = new JLabel("Procuratore Attuale:");
		NomeProcuratoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		
		InfoProcuratoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoInfoProcuratoreFromInfoAtleta(procuratore);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 700, 87);
		contentPane.add(scrollPane);
		
		JLabel InfoLabel = new JLabel("Informazioni dell'atleta:"+" "+atleta.getNome()+" "+atleta.getCognome());
		InfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoLabel.setBounds(10, 33, 509, 21);
		contentPane.add(InfoLabel);
		
		TabellaStatistiche = new JTable();
		scrollPane.setViewportView(TabellaStatistiche);
		
		RiempiTabContrattiAttivi();
		SetLabelTotContratti();
		
		JRadioButton ContrattiAttiviRadioButton = new JRadioButton("Contratti Attivi\r\n");
		ContrattiAttiviRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiempiTabContrattiAttivi();
				SetLabelTotContratti();
			}
		});
		ContrattiAttiviRadioButton.setBounds(17, 110, 109, 23);
		contentPane.add(ContrattiAttiviRadioButton);
		ContrattiAttiviRadioButton.setSelected(true);
		
		JRadioButton StoriaContrattiRadioButton = new JRadioButton("Storia Contratti");
		StoriaContrattiRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiempiTabContratti();
				SetLabelTotContratti();
			}
		});
		StoriaContrattiRadioButton.setBounds(128, 110, 109, 23);
		contentPane.add(StoriaContrattiRadioButton);
		
		JRadioButton StoriaProcuratoriRadioButton = new JRadioButton("Storia Procuratori");
		StoriaProcuratoriRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiempiTabProcuratori();
				TotContratti.setText("");
			}
		});
	    StoriaProcuratoriRadioButton.setBounds(239, 110, 144, 23);
	    contentPane.add(StoriaProcuratoriRadioButton);
		
	    ButtonGroup GroupTabella = new ButtonGroup();
	    GroupTabella.add(ContrattiAttiviRadioButton);
	    GroupTabella.add(StoriaContrattiRadioButton);
	    GroupTabella.add(StoriaProcuratoriRadioButton);
	    
		ContrattiAttiviRadioButton.setBounds(17, 110, 109, 23);
		contentPane.add(ContrattiAttiviRadioButton);
		ContrattiAttiviRadioButton.setSelected(true);
		
	    JLabel lblNewLabel_2 = new JLabel("Selezionare periodo di ricerca");
	    lblNewLabel_2.setBounds(20, 248, 178, 14);
	    contentPane.add(lblNewLabel_2);	    
	    
	    DateInizioDateChooser = new JDateChooser();
	    DateInizioDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(DateFineDateChooser==null || DateInizioDateChooser == null)
	    			return;
	    		if(DateInizioDateChooser.getCalendar() == null ||
	    				DateFineDateChooser.getCalendar() == null)
	    			return;
	    		RiempiTabContrattiMigliori();
	    		SetLabelTotContrattiMigliori();
	    	}
	    });
	    DateInizioDateChooser.setBounds(10, 283, 150, 20);
	    DateInizioDateChooser.setDateFormatString("yyyy/MM/dd");
	    contentPane.add(DateInizioDateChooser);
	    
	    DateFineDateChooser = new JDateChooser();
	    DateFineDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(DateFineDateChooser==null || DateInizioDateChooser == null)
	    			return;
	    		if(DateInizioDateChooser.getCalendar() == null ||
	    				DateFineDateChooser.getCalendar() == null)
	    			return;
	    		RiempiTabContrattiMigliori();
	    		SetLabelTotContrattiMigliori();
	    	}
	    });
	    DateFineDateChooser.setBounds(201, 283, 144, 20);
	    DateFineDateChooser.setDateFormatString("yyyy/MM/dd");
	    contentPane.add(DateFineDateChooser);
	       
	    JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 340, 700, 87);
		contentPane.add(scrollPane2);
		
		TabellaVantaggi = new JTable();
		scrollPane2.setViewportView(TabellaVantaggi);
		
		TabellaVantaggi.setModel(new DefaultTableModel(
				new Object[][] {},ColonneContrattiMigliori
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});	
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoVisualizzaAtletiFromInfoAtleta();
			}
		});
		IndietroButton.setBounds(516, 466, 89, 23);
		contentPane.add(IndietroButton);
		
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomeFromInfoAtleta();
			}
		});
		HomeButton.setBounds(635, 466, 89, 23);
		contentPane.add(HomeButton);
		
	}
	
	private void SetLabelTotContrattiMigliori(){
		int rows = TabellaVantaggi.getModel().getRowCount();
		double tot=0;
		for(int i=0;i<rows;i++){
			tot+=Double.parseDouble(String.valueOf(TabellaVantaggi.getValueAt(i, 3)));
		}
		TotContrattiMigliori.setText("Totale = "+tot+" €");
	}
	
	private void SetLabelTotContratti(){
		int rows = TabellaStatistiche.getModel().getRowCount();
		double tot=0;
		for(int i=0;i<rows;i++){
			tot+=Double.parseDouble(String.valueOf(TabellaStatistiche.getValueAt(i, 5)));
			
		}
		TotContratti.setText("Totale = "+tot+" €");
	}
	
	public Object[][] GetDatiContrattiMigliori() {
		Object[][] ContenutoTab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerQuery.getInstance().getDAO();
			LocalDate TempDate1=DateInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate TempDate2=DateFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		    ArrayList<Contratto> ContrattiMigliori = (ArrayList) DAO.GetMaxContrattiAtleta(atleta,Date.valueOf(TempDate1),Date.valueOf(TempDate2));
			ContenutoTab = new Object[ContrattiMigliori.size()][ColonneContrattiMigliori.length];
			for(int i=0;i<ContrattiMigliori.size();i++){
				TipoContratto tipoContratto = ContrattiMigliori.get(i).getTipo();
				if(tipoContratto.equals(TipoContratto.Club)){
					if(ContrattiMigliori.get(i).getClub()==null)
						continue;
					ContenutoTab[i][0] = ContrattiMigliori.get(i).getIdContratto();
					ContenutoTab[i][1] = ContrattiMigliori.get(i).getClub().getNomeClub();
					ContenutoTab[i][2] = "Club";
					ContenutoTab[i][3] = ContrattiMigliori.get(i).getCompensoAtleta();
				}
				else {
					if(ContrattiMigliori.get(i).getSponsor()==null)
						continue;
					ContenutoTab[i][0] = ContrattiMigliori.get(i).getIdContratto();
					ContenutoTab[i][1] = ContrattiMigliori.get(i).getSponsor().getNomeSponsor();
					ContenutoTab[i][2] = "Sponsor";
					ContenutoTab[i][3] = ContrattiMigliori.get(i).getCompensoAtleta();
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
	
	private void RiempiTabContrattiMigliori() {
			TabellaVantaggi.setModel(new DefaultTableModel(
					GetDatiContrattiMigliori(),ColonneContrattiMigliori
			){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r,int c) {
					return false;
				}
			});	    
	}
	
	public Object[][] GetDatiContrattiAttivi(Atleta atleta) {
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
	
	public void RiempiTabContrattiAttivi() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiContrattiAttivi(atleta),ColonneTabContratti
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
	}
	
	public Object[][] GetDatiContratti(Atleta atleta) {
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
	
	public void RiempiTabContratti() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiContratti(atleta),ColonneTabContratti 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
	}
	
	public Object[][] GetDatiTabellaProcuratori(Atleta atleta) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
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
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
		return Contenutotab;
	}
	
	public void RiempiTabProcuratori() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiTabellaProcuratori(atleta),ColonneTabStoriaProcuratori
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
	}

}

	

