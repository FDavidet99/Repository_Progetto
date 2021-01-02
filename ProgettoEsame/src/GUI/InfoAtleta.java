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

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
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

public class InfoAtleta extends JFrame {
	private Controller controller;
	private Atleta atleta;
	private JPanel contentPane;
	private JTable tabellaProcuratoriHistory;

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
	public InfoAtleta(Controller c,Atleta atleta) {
		controller =c;
		this.atleta = atleta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		tabellaProcuratoriHistory = new JTable();
		tabellaProcuratoriHistory.setBounds(35, 0, 711, 297);
		contentPane.add(tabellaProcuratoriHistory);
		tabellaProcuratoriHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabellaProcuratoriHistory.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CF","Nome","Cognome","Sesso","DataNascita","Nazione", 
				"Provincia","Comune" 
			}
			
		) {private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int r,int c) {return false;}});
		
		tabellaProcuratoriHistory.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabellaProcuratoriHistory.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabellaProcuratoriHistory.getColumnModel().getColumn(2).setPreferredWidth(81);
		tabellaProcuratoriHistory.getColumnModel().getColumn(3).setPreferredWidth(44);
		tabellaProcuratoriHistory.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JLabel lblNewLabel = new JLabel("History Procuratori");
		lblNewLabel.setBounds(25, 136, 85, 13);
		contentPane.setLayout(null);
		
		tabellaProcuratoriHistory.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		tabellaProcuratoriHistory.setPreferredScrollableViewportSize(new Dimension(670, 240));
        tabellaProcuratoriHistory.setFillsViewportHeight(true);
		
		JScrollPane js=new JScrollPane(tabellaProcuratoriHistory);
		js.setBounds(115, 10, 672, 266);
		js.setVisible(true);
		
		
		JButton HomeButton = new JButton("Home");
		HomeButton.setBounds(792, 132, 59, 21);
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomeFromInfoAtleta();
			}
		});
		contentPane.add(lblNewLabel);
		contentPane.add(js);
		contentPane.add(HomeButton);
		popolaTabellaHistoryProcuratori();
	}
	public void popolaTabellaHistoryProcuratori() {
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<ProcuratoreSportivo> ListaProcuratoriVisualizzati = new ArrayList<ProcuratoreSportivo>();
			ArrayList<Ingaggio> ingaggi=(ArrayList<Ingaggio>) dao.GetIngaggiByAtleta(atleta);
			System.out.println("tot "+ingaggi.size());
			for(Ingaggio ing:ingaggi)
			{
				ListaProcuratoriVisualizzati.add(ing.getProcuratore());
			}
			DefaultTableModel model = (DefaultTableModel) tabellaProcuratoriHistory.getModel();
			for(int i=0;i<ListaProcuratoriVisualizzati.size();i++){
				Persona p  =ListaProcuratoriVisualizzati.get(i);		
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
