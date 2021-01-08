package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JSpinner;

public class InfoProcuratore2 extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private ProcuratoreSportivo procuratore;
	private JPanel contentPane;
	private JTable tabellaAtletiHistory;
	private JTable contrattiProcuratoreTab;
	private JLabel labelInfoProcuratore;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InfoAtleta frame = new InfoAtleta();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public InfoProcuratore2(Controller c,ProcuratoreSportivo procuratore) {
		controller =c;
		this.procuratore = procuratore;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
//		tabellaAtletiHistory = new JTable();
//		tabellaAtletiHistory.setBounds(1, 25, 670, 240);
//		contentPane.add(tabellaAtletiHistory);
//		tabellaAtletiHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		tabellaAtletiHistory.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"CF","Nome","Cognome","Sesso","DataNascita","Nazione", 
//				"Provincia","Comune" 
//			}
//			
//		) {private static final long serialVersionUID = 1L;
//		public boolean isCellEditable(int r,int c) {return false;}});
//		
//		tabellaAtletiHistory.getColumnModel().getColumn(0).setPreferredWidth(150);
//		tabellaAtletiHistory.getColumnModel().getColumn(1).setPreferredWidth(120);
//		tabellaAtletiHistory.getColumnModel().getColumn(2).setPreferredWidth(81);
//		tabellaAtletiHistory.getColumnModel().getColumn(3).setPreferredWidth(44);
//		tabellaAtletiHistory.getColumnModel().getColumn(4).setPreferredWidth(100);
//		
//		JLabel lblNewLabel = new JLabel("History Atleti");
//		lblNewLabel.setBounds(38, 136, 58, 13);
//		contentPane.setLayout(null);
//		
//		tabellaAtletiHistory.setBorder(new LineBorder(new Color(0, 0, 0), 3));
//		
//		tabellaAtletiHistory.setPreferredScrollableViewportSize(new Dimension(670, 240));
//        tabellaAtletiHistory.setFillsViewportHeight(true);
//		
//		JScrollPane js=new JScrollPane(tabellaAtletiHistory);
//		js.setBounds(101, 10, 672, 266);
//		js.setVisible(true);
//		
//		
//		
//		JButton HomeButton = new JButton("Home");
//		HomeButton.setBounds(778, 132, 59, 21);
//		HomeButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				controller.GotoHomeFromInfoProcuratore();
//			}
//		});
//		contentPane.add(lblNewLabel);
//		contentPane.add(js);
//		contentPane.add(HomeButton);
//		
//		contrattiProcuratoreTab = new JTable();
//		contrattiProcuratoreTab.setBounds(101, 339, 672, 79);
//		
//		contrattiProcuratoreTab.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		contrattiProcuratoreTab.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"Guadagno proc", "Guadagno atleta", "Club", "Data inizio", "Data fine", "Sponsor", "CF atleta", "Gettone presenza"
//			}
//		));
//		
//		
//		contrattiProcuratoreTab.setBorder(new LineBorder(new Color(0, 0, 0), 3));
//		
//		contrattiProcuratoreTab.setPreferredScrollableViewportSize(new Dimension(670, 240));
//		contrattiProcuratoreTab.setFillsViewportHeight(true);
//		
//		contentPane.add(labelInfoProcuratore);
//		popolaTabellaHistoryAtleti();
		//popolaTabellaContratti();
		
//		contentPane.add(contrattiProcuratoreTab);
//		
//		labelInfoProcuratore = new JLabel("info procuratore");
//		labelInfoProcuratore.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		labelInfoProcuratore.setBounds(131, 286, 600, 26);
//		try {
//			labelInfoProcuratore.setText("Visualizzando info del procuratore: "+
//					procuratore.getNome()+ " "+procuratore.getCognome()+ " "+ procuratore.getCF());
//		} catch (EccezioneCF e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		contentPane.add(labelInfoProcuratore);
//		popolaTabellaHistoryAtleti();
	//	popolaTabellaContratti();
	}
	
//	public void popolaTabellaContratti() {
//		TableModel model = contrattiProcuratoreTab.getModel();
//		try {
//			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
//			ArrayList<Contratto> contratti = (ArrayList<Contratto>) dao.getContratti();
//			@SuppressWarnings("unchecked")
//			ArrayList<Contratto> contrattiCopy = (ArrayList<Contratto>) contratti.clone();
//			int rimossi=0,i=0;
//			for(Contratto c:contrattiCopy)
//			{
//				if(c.getProcuratoreInteressato()!=null)
//					if(!c.getProcuratoreInteressato().equals(procuratore))
//					{
//						contratti.remove(i-rimossi);
//						rimossi++;
//					}
//				i++;
//			}
//			for(Contratto c:contratti)
//			{
//				System.out.println(c.getGettonePresenzaNazionale());
//				((DefaultTableModel) model).addRow(new Object[] {
//						c.getDataInizio(),c.getDataFine(),
//						c.getCompensoAtleta(),c.getCompensoProcuratore(),
//						c.getSponsor(),c.getClub(),
//						c.getAtletaSottosritto().getCF(),
//						c.getGettonePresenzaNazionale()
//						
//				});
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (EccezioneCF e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
		
//	}
	public void popolaTabellaHistoryAtleti() {
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Atleta> ListaAtletiVisualizzati = new ArrayList<Atleta>();
			ArrayList<Ingaggio> ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByProcuratore(procuratore);
			for(Ingaggio ing:ingaggi)
			{
				ListaAtletiVisualizzati.add(ing.getAtleta());
			}
			DefaultTableModel model = (DefaultTableModel) tabellaAtletiHistory.getModel();
			for(int i=0;i<ListaAtletiVisualizzati.size();i++){
				Persona p  =ListaAtletiVisualizzati.get(i);		
				String provincia  = "Estero"; 
				String comune= "Estero";
				if(p.getProvinciaNascita()!=null)
					provincia = p.getProvinciaNascita().getNome();
				if(p.getComuneNascita()!=null)
					comune = p.getComuneNascita().getNome();
				model.addRow(new Object[] {
					p.getCF(), p.getNome(),
					p.getCognome(), p.getSessoPersona(),
					p.getDataNascita(),p.getNazioneNascita().getNomeNazione(),
					provincia,comune,
				});
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
	}
}
