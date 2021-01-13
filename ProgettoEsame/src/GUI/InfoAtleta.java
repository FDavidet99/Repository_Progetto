package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controller.ControllerDAO;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Contratto;
import Entità.Ingaggio;
import Entità.ProcuratoreSportivo;
import Entità.TipoContratto;
import ImplementationDAO.ImplementationDAO;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
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
	private JTable TabellaMiglioriContratti;
	private Object[] ColonneTabContratti= {"Id Contratto", "Club/Sponsor", "Entita Stipulante","Data Inizio","Data Fine", "Compenso/Gettone Presenza Nazionale"};
	private Object[] ColonneContrattiMigliori= {"Id Contratto","Club/Sponsor","Nome Club/Sponsor","Data Inizio","Data Fine","Compenso"};
	private Object[] ColonneTabStoriaProcuratori= {"Codice Fiscale P.","Nome","Cognome", "Data Inizio", "Data Fine", "Stipendio"};
	private Object[] ColonneContrattiNazionale= {"Id Contratto","Nazionale","Data Inizio","Data Fine","Gettone Presenza Nazionale"};
	private JLabel TotContratti;
	private JLabel TotContrattiMigliori;
	private JDateChooser DataInizioDateChooser;
	private JDateChooser DataFineDateChooser;

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
	    TotContratti.setBounds(635, 233, 109, 18);
	    contentPane.add(TotContratti);
	    
	    TotContrattiMigliori = new JLabel("Totale= - ");
	    TotContrattiMigliori.setBounds(656, 438, 109, 18);
	    contentPane.add(TotContrattiMigliori);
		
		ImplementationDAO DAO = ControllerDAO.getInstance().getDAO();
		
		JLabel NomeProcuratoreLabel = new JLabel("Procuratore Attuale:");
		NomeProcuratoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		NomeProcuratoreLabel.setBounds(10, 71, 293, 18);
		contentPane.add(NomeProcuratoreLabel);
		
		JButton InfoProcuratoreButton = new JButton("Info Procuratore");
		InfoProcuratoreButton.setBounds(341, 70, 144, 23);
		contentPane.add(InfoProcuratoreButton);
		
		ProcuratoreAttivoField = new JTextField();
		ProcuratoreAttivoField.setEditable(false);
		ProcuratoreAttivoField.setBounds(128, 71, 175, 20);
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
		scrollPane.setBounds(10, 140, 794, 87);
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
		StoriaContrattiRadioButton.setBounds(128, 110, 126, 23);
		contentPane.add(StoriaContrattiRadioButton);
		
		JRadioButton StoriaProcuratoriRadioButton = new JRadioButton("Storia Procuratori");
		StoriaProcuratoriRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiempiTabProcuratori();
				TotContratti.setText("");
			}
		});
	    StoriaProcuratoriRadioButton.setBounds(423, 110, 144, 23);
	    contentPane.add(StoriaProcuratoriRadioButton);
		
	    JRadioButton TabellaGettoniNazionaliRadioButton = new JRadioButton("Contratti con Nazionali");
	    TabellaGettoniNazionaliRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		RiempiTabContrattiNazionali();
	    		SetLabelTotContratti();
	    	}
	    });
		TabellaGettoniNazionaliRadioButton.setBounds(252, 110, 175, 23);
		contentPane.add(TabellaGettoniNazionaliRadioButton);
	    
	    ButtonGroup GroupTabella = new ButtonGroup();
	    GroupTabella.add(ContrattiAttiviRadioButton);
	    GroupTabella.add(StoriaContrattiRadioButton);
	    GroupTabella.add(StoriaProcuratoriRadioButton);
	    GroupTabella.add(TabellaGettoniNazionaliRadioButton);
	    
		ContrattiAttiviRadioButton.setBounds(17, 110, 109, 23);
		contentPane.add(ContrattiAttiviRadioButton);
		ContrattiAttiviRadioButton.setSelected(true);
		
	    JLabel SpiegazioneLabel = new JLabel("Selezionare periodo di ricerca, per avere i migliori contratti stipulati dall'atleta in quel periodo");
	    SpiegazioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    SpiegazioneLabel.setBounds(20, 248, 547, 14);
	    contentPane.add(SpiegazioneLabel);	    
	    
	    DataInizioDateChooser = new JDateChooser();
	    DataInizioDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(DataFineDateChooser==null || DataInizioDateChooser == null)
	    			return;
	    		if(DataInizioDateChooser.getCalendar() == null ||DataFineDateChooser.getCalendar() == null)
	    			return;
	    		RiempiTabContrattiMigliori();
	    		SetLabelTotContrattiMigliori();
	    	}
	    });
	    DataInizioDateChooser.setBounds(56, 283, 98, 20);
	    DataInizioDateChooser.setDateFormatString("yyyy/MM/dd");
	    contentPane.add(DataInizioDateChooser);
	    
	    DataFineDateChooser = new JDateChooser();
	    DataFineDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(DataFineDateChooser==null || DataInizioDateChooser == null)
	    			return;
	    		if(DataInizioDateChooser.getCalendar() == null ||DataFineDateChooser.getCalendar() == null)
	    			return;
	    		RiempiTabContrattiMigliori();
	    		SetLabelTotContrattiMigliori();
	    	}
	    });
	    DataFineDateChooser.setBounds(226, 283, 109, 20);
	    DataFineDateChooser.setDateFormatString("yyyy/MM/dd");
	    contentPane.add(DataFineDateChooser);
	       
	    JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 340, 794, 87);
		contentPane.add(scrollPane2);
		
		TabellaMiglioriContratti = new JTable();
		scrollPane2.setViewportView(TabellaMiglioriContratti);
		
		TabellaMiglioriContratti.setModel(new DefaultTableModel(
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
		
		JLabel Label2 = new JLabel("al");
		Label2.setHorizontalAlignment(SwingConstants.CENTER);
		Label2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Label2.setBounds(158, 289, 61, 14);
		contentPane.add(Label2);
		
		JLabel Label1 = new JLabel("Dal");
		Label1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Label1.setBounds(17, 289, 46, 14);
		contentPane.add(Label1);
		
		JRadioButton ContrattiMiglioriRadioButton = new JRadioButton("Contratti Migliori");
		ContrattiMiglioriRadioButton.setSelected(true);
		ContrattiMiglioriRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DataInizioDateChooser==null || DataFineDateChooser == null)
					return;
				if(DataInizioDateChooser.getCalendar() == null || DataFineDateChooser.getCalendar() == null)
					return;
				RiempiTabContrattiMigliori();
				SetLabelTotContrattiMigliori();
					}
				});
		ContrattiMiglioriRadioButton.setBounds(27, 310, 127, 23);
				contentPane.add(ContrattiMiglioriRadioButton);
			
		JRadioButton ContrattiNazionaliMiglioriRadioButton = new JRadioButton("Contratti con Nazionale migliori");
		ContrattiNazionaliMiglioriRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DataInizioDateChooser==null || DataFineDateChooser == null)
	    			return;
	    		if(DataInizioDateChooser.getCalendar() == null || DataFineDateChooser.getCalendar() == null)
	    			return;
	    		RiempiTabContrattiNazionaliMigliori();
	    		SetLabelTotContrattiMigliori();
				
			}
		});
		ContrattiNazionaliMiglioriRadioButton.setBounds(154, 310, 203, 23);
		contentPane.add(ContrattiNazionaliMiglioriRadioButton);
		    
		ButtonGroup GroupTabellaMiglioriContratti = new ButtonGroup();
		GroupTabellaMiglioriContratti.add(ContrattiMiglioriRadioButton);
		GroupTabellaMiglioriContratti.add(ContrattiNazionaliMiglioriRadioButton);
			
	}
	
	
	private void SetLabelTotContratti(){
		int rows = TabellaStatistiche.getModel().getRowCount();
		double tot=0;
		for(int i=0;i<rows;i++){
			if(TabellaStatistiche.getModel().getColumnName(1).equals("Nazionale"))
				tot+=Double.parseDouble(String.valueOf(TabellaStatistiche.getValueAt(i, 4)));
			else
				tot+=Double.parseDouble(String.valueOf(TabellaStatistiche.getValueAt(i, 5)));	
		}
		TotContratti.setText("Totale = "+tot+" €");
	}
	
	private void SetLabelTotContrattiMigliori(){
		int rows = TabellaMiglioriContratti.getModel().getRowCount();
		double tot=0;
		for(int i=0;i<rows;i++){
			if(TabellaMiglioriContratti.getModel().getColumnName(1).equals("Nazionale"))
				tot+=Double.parseDouble(String.valueOf(TabellaMiglioriContratti.getValueAt(i, 4)));
			else
				tot+=Double.parseDouble(String.valueOf(TabellaMiglioriContratti.getValueAt(i, 5)));	
		}
		TotContrattiMigliori.setText("Totale = "+tot+" €");
	}
	
	public Object[][] GetDatiContrattiAttivi(Atleta atleta) {
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
	
	public void RiempiTabContrattiAttivi() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiContrattiAttivi(atleta),ColonneTabContratti
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(5).setPreferredWidth(210);
	}
	
	public Object[][] GetDatiContratti(Atleta atleta) {
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
		
	public void RiempiTabContratti() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiContratti(atleta),ColonneTabContratti 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(5).setPreferredWidth(210);

	}
	
	public Object[][] GetDatiTabContrattiNazionali(Atleta atleta){
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

	public void RiempiTabContrattiNazionali() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				GetDatiTabContrattiNazionali(atleta),ColonneContrattiNazionale
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
	
	public Object[][] GetDatiTabellaProcuratori(Atleta atleta) {
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

	public Object[][] GetDatiContrattiMigliori() {
		Object[][] ContenutoTab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerDAO.getInstance().getDAO();
			LocalDate TempDate1=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate TempDate2=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		    ArrayList<Contratto> ContrattiMigliori = (ArrayList) DAO.GetMaxContrattiAtleta(atleta,Date.valueOf(TempDate1),Date.valueOf(TempDate2));  
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
	
	public void RiempiTabContrattiMigliori() {
			TabellaMiglioriContratti.setModel(new DefaultTableModel(
					GetDatiContrattiMigliori(),ColonneContrattiMigliori
			){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r,int c) {
					return false;
				}
			});	    
}

	public Object[][] GetDatiTabContrattiNazionaleMigliori(Atleta atleta){
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO DAO = ControllerDAO.getInstance().getDAO();
			LocalDate TempDate1=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate TempDate2=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		    ArrayList<Contratto> ContrattiMigliori = (ArrayList) DAO.GetMaxContrattiAtleta(atleta,Date.valueOf(TempDate1),Date.valueOf(TempDate2));
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

	public void RiempiTabContrattiNazionaliMigliori() {
		TabellaMiglioriContratti.setModel(new DefaultTableModel(
				GetDatiTabContrattiNazionaleMigliori(atleta),ColonneContrattiNazionale
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});	   
		TabellaMiglioriContratti.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
}	
	


	

