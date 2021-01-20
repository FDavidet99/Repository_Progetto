package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Entity.Atleta;
import Entity.ProcuratoreSportivo;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
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
	private JTable TabellaMiglioriContratti;
	private String[] ColonneTabContratti= {"Id Contratto", "Club/Sponsor", "Entita Stipulante","Data Inizio","Data Fine", "Compenso/Gettone Presenza Nazionale"};
	private String[] ColonneContrattiMigliori= {"Id Contratto","Club/Sponsor","Nome Club/Sponsor","Data Inizio","Data Fine","Compenso"};
	private String[] ColonneTabStoriaProcuratori= {"Codice Fiscale P.","Nome","Cognome", "Data Inizio", "Data Fine", "Stipendio"};
	private String[] ColonneContrattiNazionale= {"Id Contratto","Nazionale","Data Inizio","Data Fine","Gettone Presenza Nazionale"};
	private JLabel TotContratti;
	private JLabel TotContrattiMigliori;
	private JDateChooser DataInizioDateChooser;
	private JDateChooser DataFineDateChooser;

	/**
	 * Create the frame.
	 */
	public InfoAtleta(Controller c,Atleta atleta) {
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
		procuratore = controller.GetProcuratoreAtletaAttivo(atleta);
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
		
		SetTabContrattiAttivi();
		SetLabelTotContratti();
		
		JRadioButton ContrattiAttiviRadioButton = new JRadioButton("Contratti Attivi\r\n");
		ContrattiAttiviRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTabContrattiAttivi();
				SetLabelTotContratti();
			}
		});
		ContrattiAttiviRadioButton.setBounds(17, 110, 109, 23);
		contentPane.add(ContrattiAttiviRadioButton);
		ContrattiAttiviRadioButton.setSelected(true);
		
		JRadioButton StoriaContrattiRadioButton = new JRadioButton("Storia Contratti");
		StoriaContrattiRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTabContratti();
				SetLabelTotContratti();
			}
		});
		StoriaContrattiRadioButton.setBounds(128, 110, 126, 23);
		contentPane.add(StoriaContrattiRadioButton);
		
		JRadioButton StoriaProcuratoriRadioButton = new JRadioButton("Storia Procuratori");
		StoriaProcuratoriRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTabProcuratori();
				TotContratti.setText("");
			}
		});
	    StoriaProcuratoriRadioButton.setBounds(423, 110, 144, 23);
	    contentPane.add(StoriaProcuratoriRadioButton);
		
	    JRadioButton TabellaGettoniNazionaliRadioButton = new JRadioButton("Contratti con Nazionali");
	    TabellaGettoniNazionaliRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		SetTabContrattiNazionali();
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
	    		SetTabContrattiMigliori();
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
	    		SetTabContrattiMigliori();
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
				SetTabContrattiMigliori();
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
	    		SetTabContrattiNazionaliMigliori();
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
		
	public void SetTabContrattiAttivi() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				controller.GetDatiTabContrattiAttiviAtleta(atleta, ColonneTabContratti),ColonneTabContratti
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(5).setPreferredWidth(210);
	}
			
	public void SetTabContratti() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				controller.GetDatiTabContrattiAtleta(atleta, ColonneTabContratti),ColonneTabContratti 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(5).setPreferredWidth(210);

	}
	
	public void SetTabContrattiNazionali() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				controller.GetDatiTabContrattiNazionaliAtleta(atleta,ColonneContrattiNazionale),ColonneContrattiNazionale
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
	
	public void SetTabProcuratori() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				controller.GetDatiTabProcuratoriAtleta(atleta,ColonneTabStoriaProcuratori),ColonneTabStoriaProcuratori
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
	}
	
	public void SetTabContrattiMigliori() {
		LocalDate DataInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate DataFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
	    TabellaMiglioriContratti.setModel(new DefaultTableModel(
	    		controller.GetDatiTabContrattiMiglioriAtleta(atleta,ColonneContrattiMigliori,DataInizio,DataFine),ColonneContrattiMigliori
	    		){
	    		private static final long serialVersionUID = 1L;
	    		public boolean isCellEditable(int r,int c) {
	    			return false;
				}
	    });	    
}

	public void SetTabContrattiNazionaliMigliori() {
		LocalDate DataInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate DataFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		TabellaMiglioriContratti.setModel(new DefaultTableModel(
				controller.GetDatiTabContrattiNazionaleMiglioriAtleta(atleta,ColonneContrattiNazionale,DataInizio,DataFine),ColonneContrattiNazionale
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});	   
		TabellaMiglioriContratti.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
}	
	


	

