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

import org.postgresql.translation.messages_bg;

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
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class InfoProcuratore extends JFrame {

	private JPanel contentPane;
	private ProcuratoreSportivo proc;
	private JTable TabellaStatistiche;
	Controller controller;
	private JTable TabellaMigliori;
	private Object[] ColonneTabContratti= {"Id Contratto", "Club/Sponsor", "Entita Stipulante","Data Inizio", "Data Fine", "Guadagno P."};
	private Object[] ColonneTabStoriaAtleti= {"Codice Fiscale A.","Nome","Cognome", "Data Inizio", "Data Fine", "Stipendio"};
	private Object[] ColonneContrattiMigliori= {"Id Contratto","Nome Club/Sponsor", "Club/Sponsor", "Guadagno P."};
	private Object[] ColonneIngaggiMigliori= {"Nome atleta", "Cognome atleta","CF atleta","Data inizio","Data fine", "Stipendio P."};
	private JLabel TotaleTabStatisticheLabel;
	private JLabel TotaleTabMigioriLabel;
	private JDateChooser DataInizioDateChooser;
	private JDateChooser DataFineDateChooser;
	private JRadioButton radioBtnIngaggiMigliori;
	private JRadioButton radioBtnContrattiMigliori;
	private List<Ingaggio> ingaggiVant = new ArrayList<Ingaggio>();
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws EccezioneCF 
	 */
	public InfoProcuratore(Controller c,ProcuratoreSportivo proc) throws SQLException, EccezioneCF {
		controller=c;
		this.proc = proc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	    TotaleTabStatisticheLabel = new JLabel("Totale= -");
	    TotaleTabStatisticheLabel.setBounds(532, 203, 178, 18);
	    contentPane.add(TotaleTabStatisticheLabel);
	    
	    TotaleTabMigioriLabel = new JLabel("Totale= - ");
	    TotaleTabMigioriLabel.setBounds(532, 437, 178, 18);
	    contentPane.add(TotaleTabMigioriLabel);
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 700, 87);
		contentPane.add(scrollPane);
		
		JLabel InfoLabel = new JLabel("Informazioni del procuratore:"+" "+proc.getNome()+" "+proc.getCognome());
		InfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		InfoLabel.setBounds(10, 33, 509, 21);
		contentPane.add(InfoLabel);
		
		TabellaStatistiche = new JTable();
		scrollPane.setViewportView(TabellaStatistiche);
		
		RiempiTabContrattiAttivi();
		SetLabelTabStatistiche();
		
		JRadioButton ContrattiAttiviRadioButton = new JRadioButton("Contratti Attivi\r\n");
		ContrattiAttiviRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiempiTabContrattiAttivi();
				SetLabelTabStatistiche();
			}
		});
		ContrattiAttiviRadioButton.setBounds(10, 75, 109, 23);
		contentPane.add(ContrattiAttiviRadioButton);
		ContrattiAttiviRadioButton.setSelected(true);
			
		JRadioButton StoriaContrattiRadioButton = new JRadioButton("Storia Contratti");
		StoriaContrattiRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiempiDatiTabContratti();
				SetLabelTabStatistiche();
			}
		});
		StoriaContrattiRadioButton.setBounds(121, 75, 109, 23);
		contentPane.add(StoriaContrattiRadioButton);
		
		JRadioButton StoriaAtletiRadioButton = new JRadioButton("Storia Atleti ingaggiati");
		StoriaAtletiRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiempiTabIngaggiAtleti();
				SetLabelTabStatistiche();
			}
		});
	    StoriaAtletiRadioButton.setBounds(335, 75, 167, 23);
	    contentPane.add(StoriaAtletiRadioButton);
	    
	    JRadioButton AtletiAttiviRadioButton = new JRadioButton("Ingaggi Attivi\r\n");
	    AtletiAttiviRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiempiTabIngaggiAttivi();
				SetLabelTabStatistiche();
			}
		});
		AtletiAttiviRadioButton.setBounds(232, 75, 101, 23);
		contentPane.add(AtletiAttiviRadioButton);
			
	    ButtonGroup GroupTabella = new ButtonGroup();
	    GroupTabella.add(ContrattiAttiviRadioButton);
	    GroupTabella.add(StoriaContrattiRadioButton);
	    GroupTabella.add(StoriaAtletiRadioButton);
	    GroupTabella.add(AtletiAttiviRadioButton);
	    
	    radioBtnIngaggiMigliori = new JRadioButton("Ingaggi migliori\r\n");
	    radioBtnIngaggiMigliori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DataInizioDateChooser==null || DataFineDateChooser == null)
	    			return;
	    		if(DataInizioDateChooser.getCalendar() == null || DataFineDateChooser.getCalendar() == null)
	    			return;
				RiempiTabIngaggiMigliori();
				SetLabelIngaggiMigliori();
			}
		});
	    radioBtnIngaggiMigliori.setBounds(151, 297, 127, 23);
		contentPane.add(radioBtnIngaggiMigliori);
		
	    radioBtnContrattiMigliori = new JRadioButton("Contratti migliori\r\n");
	    radioBtnContrattiMigliori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DataInizioDateChooser==null || DataFineDateChooser == null)
	    			return;
	    		if(DataInizioDateChooser.getCalendar() == null || DataFineDateChooser.getCalendar() == null)
	    			return;
				RiempiTabContrattiMigliori();
	    		SetLabelContrattiMigliori();
			}
		});
	    radioBtnContrattiMigliori.setBounds(10, 297, 127, 23);
		contentPane.add(radioBtnContrattiMigliori);
		radioBtnContrattiMigliori.setSelected(true);
	    
	    ButtonGroup GroupTabellaVantaggi = new ButtonGroup();
	    GroupTabellaVantaggi.add(radioBtnIngaggiMigliori);
	    GroupTabellaVantaggi.add(radioBtnContrattiMigliori);
	    
	    DataInizioDateChooser = new JDateChooser();
	    DataInizioDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(DataInizioDateChooser==null || DataFineDateChooser == null)
	    			return;
	    		if(DataInizioDateChooser.getCalendar() == null || DataFineDateChooser.getCalendar() == null)
	    			return;
	    		if(radioBtnContrattiMigliori.isSelected()){
	    			RiempiTabContrattiMigliori();
		    		SetLabelContrattiMigliori();
	    		}
	    		else if(radioBtnIngaggiMigliori.isSelected()){
	    			RiempiTabIngaggiMigliori();
	    			SetLabelIngaggiMigliori();
	    		}
	    	}
	    });
	    DataInizioDateChooser.setBounds(10, 253, 150, 20);
	    contentPane.add(DataInizioDateChooser);
	    
	    DataFineDateChooser = new JDateChooser();
	    DataFineDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(DataFineDateChooser==null || DataInizioDateChooser == null)
	    			return;
	    		if(DataFineDateChooser.getCalendar() == null ||
	    				DataInizioDateChooser.getCalendar() == null)
	    			return;
	    		if(radioBtnContrattiMigliori.isSelected()){
	    			RiempiTabContrattiMigliori();
		    		SetLabelContrattiMigliori();
	    		}
	    		else if(radioBtnIngaggiMigliori.isSelected()){
	    			RiempiTabIngaggiMigliori();
	    			SetLabelIngaggiMigliori();
	    		}
	    	}
	    });
	    DataFineDateChooser.setBounds(172, 253, 144, 20);
	    contentPane.add(DataFineDateChooser);
	    
	    JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomeFromInfoProcuratore();
			}
		});
		HomeButton.setBounds(621, 466, 89, 23);
		contentPane.add(HomeButton);
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoVisualizzaProcFromInfoProcuratore();
			}
		});
		IndietroButton.setBounds(514, 466, 89, 23);
		contentPane.add(IndietroButton);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 340, 700, 87);
		contentPane.add(scrollPane2);
		
		TabellaMigliori = new JTable();
		scrollPane2.setViewportView(TabellaMigliori);
		
		JLabel SpiegazioneLabel = new JLabel("Inserire il perido per avere i migliori contratti o ingaggi relativi");
		SpiegazioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SpiegazioneLabel.setBounds(10, 219, 372, 15);
		contentPane.add(SpiegazioneLabel);
	
	}
	
	public void SetLabelContrattiMigliori() {
		int rows = TabellaMigliori.getModel().getRowCount();
		double Tot=0;
		for(int i=0;i<rows;i++){
			Tot+=Double.parseDouble(String.valueOf(TabellaMigliori.getValueAt(i, 3)));
		}
		TotaleTabMigioriLabel.setText("Totale = "+Tot+" €");
	}
	
	private void SetLabelIngaggiMigliori() {
		int rows = TabellaMigliori.getModel().getRowCount();
		double Tot=0;
		for(int i=0;i<rows;i++){
			long diff=1;
			if(TabellaMigliori.getModel().getColumnName(5).toString().equals("Stipendio P.")) 
				diff=(ChronoUnit.MONTHS.between((LocalDate)TabellaMigliori.getValueAt(i, 3),(LocalDate) TabellaMigliori.getValueAt(i, 4)));		
			Tot+=diff*Double.parseDouble(String.valueOf(TabellaMigliori.getValueAt(i, 5)));
		}
		TotaleTabMigioriLabel.setText("Totale stipendio = "+Tot+" €");
	}
	
	private void SetLabelTabStatistiche(){
		int rows = TabellaStatistiche.getModel().getRowCount();
		double tot=0;
		for(int i=0;i<rows;i++){
			long diff=1;
			if(TabellaStatistiche.getModel().getColumnName(5).toString().equals("Stipendio")) 
				diff=(ChronoUnit.MONTHS.between((LocalDate)TabellaStatistiche.getValueAt(i, 3),(LocalDate) TabellaStatistiche.getValueAt(i, 4)));
			tot+=diff*Double.parseDouble(String.valueOf(TabellaStatistiche.getValueAt(i, 5)));
		}
		if(TabellaStatistiche.getModel().getColumnName(5).toString().equals("Stipendio")) 
			TotaleTabStatisticheLabel.setText("Totale stipendio= "+tot+" €");
		else 
			TotaleTabStatisticheLabel.setText("Totale = "+tot+" €");
	}
		
	public Object[][] GetDatiTabContrattiMigliori(){
		Object[][] ContenutoTab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerQuery.getInstance().getDAO();
			LocalDate TempDate1=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate TempDate2=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		    ArrayList<Contratto> ContrattiMigliori = (ArrayList) DAO.GetMaxContrattiProcuratori(proc,Date.valueOf(TempDate1),Date.valueOf(TempDate2));
		    ContenutoTab = new Object[ContrattiMigliori.size()][ColonneContrattiMigliori.length];
			for(int i=0;i<ContrattiMigliori.size();i++) {
				TipoContratto tipoContratto = ContrattiMigliori.get(i).getTipo();
				if(tipoContratto.equals(TipoContratto.Club)){
					if(ContrattiMigliori.get(i).getClub()==null)
						continue;
					ContenutoTab[i][0] = ContrattiMigliori.get(i).getIdContratto();
					ContenutoTab[i][1] = ContrattiMigliori.get(i).getClub().getNomeClub();
					ContenutoTab[i][2] = "Club";
					ContenutoTab[i][3] = ContrattiMigliori.get(i).getCompensoProcuratore();
				}
				else {
					if(ContrattiMigliori.get(i).getSponsor()==null)
						continue;
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
	
	public void RiempiTabContrattiMigliori(){
			TabellaMigliori.setModel(new DefaultTableModel(
					GetDatiTabContrattiMigliori(),ColonneContrattiMigliori
			){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r,int c) {
					return false;
				}
			});	
	}	
	
	public Object[][] GetDatiTabIngaggiMigliori(){
		Object[][] ContenutoTab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerQuery.getInstance().getDAO();
			LocalDate TempDate1=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate TempDate2=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		    ingaggiVant = (ArrayList) DAO.GetIngaggiMigliori(proc,Date.valueOf(TempDate1),Date.valueOf(TempDate2));
			ContenutoTab = new Object[ingaggiVant.size()][ColonneIngaggiMigliori.length];
			for(int i=0;i<ingaggiVant.size();i++){
				ContenutoTab[i][0] = ingaggiVant.get(i).getAtleta().getNome();
				ContenutoTab[i][1] = ingaggiVant.get(i).getAtleta().getCognome();
				ContenutoTab[i][2] = ingaggiVant.get(i).getAtleta().getCF();
				ContenutoTab[i][3] = ingaggiVant.get(i).getDataInizio();
				ContenutoTab[i][4] = ingaggiVant.get(i).getDataFine();
				ContenutoTab[i][5] = ingaggiVant.get(i).getStipendioProcuratore();
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
	
	public void RiempiTabIngaggiMigliori() {
			TabellaMigliori.setModel(new DefaultTableModel(
					 GetDatiTabIngaggiMigliori(),ColonneIngaggiMigliori
			){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r,int c) {
					return false;
				}
			});    	
	}
	
	public Object[][] GetDatiTabContrattiAttivi(ProcuratoreSportivo proc) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContrattiAttivi();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if(contrattiAttivo.getProcuratoreInteressato()==null) {
			    	it.remove();
			    	continue;
			    }	
			    if (!(contrattiAttivo.getProcuratoreInteressato().getCF().equals(proc.getCF()))) {
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
	
	public void RiempiTabContrattiAttivi() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiTabContrattiAttivi(proc),ColonneTabContratti 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		
	}
	
	public Object[][] GetDatiTabContratti(ProcuratoreSportivo proc) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Contratto> ContrattiAttivi=(ArrayList<Contratto>) dao.GetContratti();	
			for (Iterator it = ContrattiAttivi.iterator(); it.hasNext();) {
			    Contratto contrattiAttivo = (Contratto) it.next();
			    if(contrattiAttivo.getProcuratoreInteressato()==null) {
			    	it.remove();
			    	continue;}
			    	if (!(contrattiAttivo.getProcuratoreInteressato().getCF().equals(proc.getCF()))) {
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

	public void RiempiDatiTabContratti() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiTabContratti(proc),ColonneTabContratti 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
	}
	
	public Object[][] GetDatiTabIngaggiAtletiAttivi(ProcuratoreSportivo proc) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Ingaggio> Ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByProcuratoreAttivi(proc);	
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
	
	public void RiempiTabIngaggiAttivi() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiTabIngaggiAtletiAttivi(proc),ColonneTabStoriaAtleti
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
	}

	public Object[][] GetDatiTabIngaggiAtleti(ProcuratoreSportivo proc) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Ingaggio> Ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByProcuratore(proc);	
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

	public void RiempiTabIngaggiAtleti() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiTabIngaggiAtleti(proc),ColonneTabStoriaAtleti
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
	}
}

	

