package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Entity.ProcuratoreSportivo;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class InfoProcuratore extends JFrame {

	private JPanel contentPane;
	private ProcuratoreSportivo Procuratore;
	private JTable TabellaStatistiche;
	Controller controller;
	private JTable TabellaMigliori;
	private String[] ColonneTabContratti= {"Id Contratto", "Club/Sponsor", "Entita Stipulante","Data Inizio", "Data Fine", "Guadagno P."};
	private String[] ColonneTabStoriaAtleti= {"Codice Fiscale A.","Nome","Cognome", "Data Inizio", "Data Fine", "Stipendio"};
	private String[] ColonneContrattiMigliori= {"Id Contratto","Nome Club/Sponsor", "Club/Sponsor", "Guadagno P."};
	private String[] ColonneIngaggiMigliori= {"Nome atleta", "Cognome atleta","CF atleta","Data inizio","Data fine", "Stipendio P."};
	private JLabel TotaleTabStatisticheLabel;
	private JLabel TotaleTabMigioriLabel;
	private JDateChooser DataInizioDateChooser;
	private JDateChooser DataFineDateChooser;
	private JRadioButton radioBtnIngaggiMigliori;
	private JRadioButton radioBtnContrattiMigliori;
	
	/**
	 * Create the frame. 
	 */
	public InfoProcuratore(Controller c,ProcuratoreSportivo procuratore) {
		controller=c;
		Procuratore = procuratore;
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
		
		JLabel InfoLabel = new JLabel("Informazioni del procuratore:"+" "+procuratore.getNome()+" "+procuratore.getCognome());
		InfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoLabel.setBounds(10, 33, 509, 21);
		contentPane.add(InfoLabel);
		
		TabellaStatistiche = new JTable();
		scrollPane.setViewportView(TabellaStatistiche);
		
		SetTabContrattiAttivi();
		SetLabelTabStatistiche();
		
		JRadioButton ContrattiAttiviRadioButton = new JRadioButton("Contratti Attivi\r\n");
		ContrattiAttiviRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTabContrattiAttivi();
				SetLabelTabStatistiche();
			}
		});
		ContrattiAttiviRadioButton.setBounds(10, 75, 109, 23);
		contentPane.add(ContrattiAttiviRadioButton);
		ContrattiAttiviRadioButton.setSelected(true);
			
		JRadioButton StoriaContrattiRadioButton = new JRadioButton("Storia Contratti");
		StoriaContrattiRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTabContratti();
				SetLabelTabStatistiche();
			}
		});
		StoriaContrattiRadioButton.setBounds(121, 75, 130, 23);
		contentPane.add(StoriaContrattiRadioButton);
		
		JRadioButton StoriaAtletiRadioButton = new JRadioButton("Storia Atleti ingaggiati");
		StoriaAtletiRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTabIngaggiAtleti();
				SetLabelTabStatistiche();
			}
		});
	    StoriaAtletiRadioButton.setBounds(350, 75, 167, 23);
	    contentPane.add(StoriaAtletiRadioButton);
	    
	    JRadioButton AtletiAttiviRadioButton = new JRadioButton("Ingaggi Attivi\r\n");
	    AtletiAttiviRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTabIngaggiAttivi();
				SetLabelTabStatistiche();
			}
		});
		AtletiAttiviRadioButton.setBounds(250, 75, 101, 23);
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
				SetTabIngaggiMigliori();
				SetLabelTotIngaggiMigliori();
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
				SetTabContrattiMigliori();
	    		SetLabelTotContrattiMigliori();
			}
		});
	    radioBtnContrattiMigliori.setBounds(10, 297, 127, 23);
		contentPane.add(radioBtnContrattiMigliori);
		radioBtnContrattiMigliori.setSelected(true);
	    
	    ButtonGroup GroupTabellaMigliori = new ButtonGroup();
	    GroupTabellaMigliori.add(radioBtnIngaggiMigliori);
	    GroupTabellaMigliori.add(radioBtnContrattiMigliori);
	    
	    DataInizioDateChooser = new JDateChooser();
	    DataInizioDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(DataInizioDateChooser==null || DataFineDateChooser == null)
	    			return;
	    		if(DataInizioDateChooser.getCalendar() == null || DataFineDateChooser.getCalendar() == null)
	    			return;
	    		if(radioBtnContrattiMigliori.isSelected()){
	    			SetTabContrattiMigliori();
		    		SetLabelTotContrattiMigliori();
	    		}
	    		else if(radioBtnIngaggiMigliori.isSelected()){
	    			SetTabIngaggiMigliori();
	    			SetLabelTotIngaggiMigliori();
	    		}
	    	}
	    });
	    DataInizioDateChooser.setBounds(43, 253, 109, 20);
	    DataInizioDateChooser.setDateFormatString("yyyy/MM/dd");
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
	    			SetTabContrattiMigliori();
		    		SetLabelTotContrattiMigliori();
	    		}
	    		else if(radioBtnIngaggiMigliori.isSelected()){
	    			SetTabIngaggiMigliori();
	    			SetLabelTotIngaggiMigliori();
	    		}
	    	}
	    });
	    DataFineDateChooser.setBounds(215, 253, 89, 20);
	    DataFineDateChooser.setDateFormatString("yyyy/MM/dd");
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
				controller.GotoVisualizzaProcuratoreFromInfoProcuratore();
			}
		});
		IndietroButton.setBounds(514, 466, 89, 23);
		contentPane.add(IndietroButton);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 340, 700, 87);
		contentPane.add(scrollPane2);
		
		TabellaMigliori = new JTable();
		scrollPane2.setViewportView(TabellaMigliori);
		
		JLabel SpiegazioneLabel = new JLabel("Inserire il perido per avere i migliori contratti o ingaggi di quel periodo");
		SpiegazioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SpiegazioneLabel.setBounds(10, 219, 443, 15);
		contentPane.add(SpiegazioneLabel);
		
		JLabel DalLabel = new JLabel("Dal");
		DalLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DalLabel.setBounds(10, 259, 31, 14);
		contentPane.add(DalLabel);
		
		JLabel lblNewLabel_1 = new JLabel("al");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(162, 259, 46, 14);
		contentPane.add(lblNewLabel_1);
	
	}
	
	public void SetLabelTotContrattiMigliori() {
		int rows = TabellaMigliori.getModel().getRowCount();
		double Tot=0;
		for(int i=0;i<rows;i++){
			Tot+=Double.parseDouble(String.valueOf(TabellaMigliori.getValueAt(i, 3)));
		}
		TotaleTabMigioriLabel.setText("Totale = "+Tot+" €");
	}
	
	private void SetLabelTotIngaggiMigliori() {
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
		
	public void SetTabContrattiMigliori(){
		LocalDate DataInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate DataFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
	    TabellaMigliori.setModel(new DefaultTableModel(
	    		controller.GetDatiTabContrattiMiglioriProcuratore(Procuratore, ColonneContrattiMigliori, DataInizio, DataFine),ColonneContrattiMigliori
			){
	    	private static final long serialVersionUID = 1L;
	    	public boolean isCellEditable(int r,int c) {
	    		return false;
	    	}
	    });	
	}	
	
	public void SetTabIngaggiMigliori() {
		LocalDate DataInizo=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate DataFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		TabellaMigliori.setModel(new DefaultTableModel(
				controller.GetDatiTabIngaggiMiglioriProcuratore(Procuratore,ColonneIngaggiMigliori,DataInizo,DataFine),ColonneIngaggiMigliori
			){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r,int c) {
					return false;
				}
		});    	
	}
	
	public void SetTabContrattiAttivi() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				controller.GetDatiTabContrattiAttiviProcuratore(Procuratore,ColonneTabContratti),ColonneTabContratti 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		
	}
	
	public void SetTabContratti() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				controller.GetDatiTabContrattiProcuratore(Procuratore,ColonneTabContratti),ColonneTabContratti 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
	}
	
	public void SetTabIngaggiAttivi() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				controller.GetDatiTabIngaggiAtletiAttiviProcuratore(Procuratore,ColonneTabStoriaAtleti),ColonneTabStoriaAtleti
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
	}

	public void SetTabIngaggiAtleti() {
		TabellaStatistiche.setModel(new DefaultTableModel(
				controller.GetDatiTabIngaggiAtletiProcuratore(Procuratore,ColonneTabStoriaAtleti),ColonneTabStoriaAtleti
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaStatistiche.getColumnModel().getColumn(0).setPreferredWidth(100);
	}
}

	

