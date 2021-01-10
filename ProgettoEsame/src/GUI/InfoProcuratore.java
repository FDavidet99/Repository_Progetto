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
	private JTable TabellaVantaggi;
	private Object[] ColonneContrattiVantaggiosi= {"Nome Club/Sponsor", "Club/Sponsor", "Guadagno P."};
	private Object[] ColonneIngaggiVantaggiosi= {"Nome atleta", "Cognome atleta","CF atleta","Data inizio","Data fine", "Stipendio P."};
	private JLabel lblTotStat;
	private JLabel lblTotIntroiti;
	private JDateChooser dateChooserdataInizio;
	private JDateChooser dateChooserdataFine;
	private JRadioButton radioBtnIngaggiVantaggiosi;
	private JRadioButton radioBtnContrattiVantaggiosi;
	private List<Ingaggio> ingaggiVant = new ArrayList<Ingaggio>();

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
		this.proc = proc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	    lblTotStat = new JLabel("Totale= -");
	    lblTotStat.setBounds(601, 232, 109, 18);
	    contentPane.add(lblTotStat);
	    
	    lblTotIntroiti = new JLabel("Totale= - ");
	    lblTotIntroiti.setBounds(532, 437, 178, 18);
	    contentPane.add(lblTotIntroiti);
		
		
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
		setLblTotStat();
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
				setLblTotStat();
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
				setLblTotStat();
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
				setLblTotStat();
			}
		});
		AtletiAttiviRadioButton.setBounds(239, 110, 109, 23);
		contentPane.add(AtletiAttiviRadioButton);
		
		
	    ButtonGroup GroupTabella = new ButtonGroup();
	    GroupTabella.add(ContrattiAttiviRadioButton);
	    GroupTabella.add(StoriaContrattiRadioButton);
	    GroupTabella.add(StoriaAtletiRadioButton);
	    GroupTabella.add(AtletiAttiviRadioButton);
	    
	    radioBtnIngaggiVantaggiosi = new JRadioButton("Ingaggi vantaggiosi\r\n");
	    radioBtnIngaggiVantaggiosi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dateChooserdataInizio==null || dateChooserdataFine == null)
	    			return;
	    		if(dateChooserdataInizio.getCalendar() == null ||
	    				dateChooserdataFine.getCalendar() == null)
	    			return;
				calcolaIngaggiVantaggiosi();
				setLblTotIngaggiVantaggiosi();
			}
		});
	    radioBtnIngaggiVantaggiosi.setBounds(17, 280, 144, 23);
		contentPane.add(radioBtnIngaggiVantaggiosi);
		radioBtnIngaggiVantaggiosi.setSelected(true);
		
	    radioBtnContrattiVantaggiosi = new JRadioButton("Contratti vantaggiosi\r\n");
	    radioBtnContrattiVantaggiosi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dateChooserdataInizio==null || dateChooserdataFine == null)
	    			return;
	    		if(dateChooserdataInizio.getCalendar() == null ||
	    				dateChooserdataFine.getCalendar() == null)
	    			return;
				calcolaContrattiVantaggiosi();
	    		setLblTotContrattiVantaggiosi();
			}
		});
	    radioBtnContrattiVantaggiosi.setBounds(163, 280, 200, 23);
		contentPane.add(radioBtnContrattiVantaggiosi);
		radioBtnContrattiVantaggiosi.setSelected(false);
	    
	    ButtonGroup GroupTabellaVantaggi = new ButtonGroup();
	    GroupTabellaVantaggi.add(radioBtnIngaggiVantaggiosi);
	    GroupTabellaVantaggi.add(radioBtnContrattiVantaggiosi);
	    
	    dateChooserdataInizio = new JDateChooser();
	    dateChooserdataInizio.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(dateChooserdataInizio==null || dateChooserdataFine == null)
	    			return;
	    		if(dateChooserdataInizio.getCalendar() == null ||
	    				dateChooserdataFine.getCalendar() == null)
	    			return;
	    		if(radioBtnContrattiVantaggiosi.isSelected())
	    		{
	    			calcolaContrattiVantaggiosi();
		    		setLblTotContrattiVantaggiosi();
	    		}
	    		else if(radioBtnIngaggiVantaggiosi.isSelected())
	    		{
	    			calcolaIngaggiVantaggiosi();
	    			setLblTotIngaggiVantaggiosi();
	    		}
	    		
	    	}
	    });
	    dateChooserdataInizio.setBounds(56, 313, 150, 20);
	    contentPane.add(dateChooserdataInizio);
	    
	    dateChooserdataFine = new JDateChooser();
	    dateChooserdataFine.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent evt) {
	    		if(dateChooserdataFine==null || dateChooserdataInizio == null)
	    			return;
	    		if(dateChooserdataFine.getCalendar() == null ||
	    				dateChooserdataInizio.getCalendar() == null)
	    			return;
	    		if(radioBtnContrattiVantaggiosi.isSelected())
	    		{
	    			calcolaContrattiVantaggiosi();
		    		setLblTotContrattiVantaggiosi();
	    		}
	    		else if(radioBtnIngaggiVantaggiosi.isSelected())
	    		{
	    			calcolaIngaggiVantaggiosi();
	    			setLblTotIngaggiVantaggiosi();
	    		}
	    	}
	    });
	    dateChooserdataFine.setBounds(262, 313, 144, 20);
	    contentPane.add(dateChooserdataFine);
	    
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
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 340, 700, 87);
		contentPane.add(scrollPane2);
	    
		
		
		TabellaVantaggi = new JTable();
		scrollPane2.setViewportView(TabellaVantaggi);
		
		TabellaVantaggi.setModel(new DefaultTableModel(
				new Object[][] {},ColonneContrattiVantaggiosi
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});	
	
	}
	
	private void setLblTotContrattiVantaggiosi()
	{
		
		int rows = TabellaVantaggi.getModel().getRowCount();
		double tot=0;
		for(int i=0;i<rows;i++)
		{
			tot+=Double.parseDouble(String.valueOf(TabellaVantaggi.getValueAt(i, 2)));
		}
		lblTotIntroiti.setText("Totale = "+tot+" €");
	}
	private void setLblTotIngaggiVantaggiosi()
	{
		int rows = TabellaVantaggi.getModel().getRowCount();
		double tot=0;
		for(int i=0;i<rows;i++)
		{
			int mesiIngaggio = (int)ChronoUnit.MONTHS.between(
			        ((Ingaggio) ingaggiVant.get(i)).getDataInizio().withDayOfMonth(1),
			        ((Ingaggio) ingaggiVant.get(i)).getDataInizio().withDayOfMonth(1)) + 1;
			tot +=mesiIngaggio *Double.parseDouble(String.valueOf(TabellaVantaggi.getValueAt(i, 3)));
		}
		lblTotIntroiti.setText("Totale stipendio = "+tot+" €");
	}
	private void setLblTotStat()
	{
		int rows = TabellaStatistiche.getModel().getRowCount();
		double tot=0;
		for(int i=0;i<rows;i++)
		{
			long diff=1;
			System.out.println(TabellaStatistiche.getValueAt(0, 0).toString());
			if(TabellaStatistiche.getModel().getColumnName(0).toString().equals("Codice Fiscale A.")) 
				diff=(ChronoUnit.MONTHS.between((LocalDate)TabellaStatistiche.getValueAt(i, 3),(LocalDate) TabellaStatistiche.getValueAt(i, 4)));
				
			tot+=diff*Double.parseDouble(String.valueOf(TabellaStatistiche.getValueAt(i, 5)));
		}
		
		lblTotStat.setText("Totale = "+tot+" €");
	}
		
	private void calcolaContrattiVantaggiosi()
	{
		try {
			ImplementationDAO DAO = ControllerQuery.getInstance().getDAO();
			LocalDate TempDate1=dateChooserdataInizio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate TempDate2=dateChooserdataFine.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		    ArrayList<Contratto> introiti = (ArrayList) DAO.GetMaxContrattiProc(proc,Date.valueOf(TempDate1),Date.valueOf(TempDate2));
			Iterator i=introiti.iterator();
			Object[][] dati = new Object[introiti.size()][3];
			for(int k=0;k<introiti.size();k++)
			{
				TipoContratto tipoContratto = introiti.get(k).getTipo();
				if(tipoContratto.equals(TipoContratto.Club))
				{
					if(introiti.get(k).getClub()==null)
						continue;
					dati[k][0] = introiti.get(k).getClub().getNomeClub();
					dati[k][1] = "Club";
					dati[k][2] = introiti.get(k).getCompensoProcuratore();
				}
				else
				{
					if(introiti.get(k).getSponsor()==null)
						continue;
					dati[k][0] = introiti.get(k).getSponsor().getNomeSponsor();
					dati[k][1] = "Sponsor";
					dati[k][2] = introiti.get(k).getCompensoProcuratore();
				}
				
			}
			TabellaVantaggi.setModel(new DefaultTableModel(
					dati,
					ColonneContrattiVantaggiosi
			){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r,int c) {
					return false;
				}
			});
			
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
    	
	}
	private void calcolaIngaggiVantaggiosi()
	{
		try {
			ImplementationDAO DAO = ControllerQuery.getInstance().getDAO();
			LocalDate TempDate1=dateChooserdataInizio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate TempDate2=dateChooserdataFine.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		    ingaggiVant = (ArrayList) DAO.GetIngaggiVantaggiosi(proc,Date.valueOf(TempDate1),Date.valueOf(TempDate2));
			Iterator i=ingaggiVant.iterator();
			Object[][] dati = new Object[ingaggiVant.size()][6];
			for(int k=0;k<ingaggiVant.size();k++)
			{
				dati[k][0] = ingaggiVant.get(k).getAtleta().getNome();
				dati[k][1] = ingaggiVant.get(k).getAtleta().getCognome();
				dati[k][2] = ingaggiVant.get(k).getAtleta().getCF();
				dati[k][3] = ingaggiVant.get(k).getDataInizio();
				dati[k][4] = ingaggiVant.get(k).getDataFine();
				dati[k][5] = ingaggiVant.get(k).getStipendioProcuratore();
			}
			TabellaVantaggi.setModel(new DefaultTableModel(
					dati,
					ColonneIngaggiVantaggiosi
			){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r,int c) {
					return false;
				}
			});
			
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

	

