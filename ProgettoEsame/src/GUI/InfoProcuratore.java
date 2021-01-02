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

public class InfoProcuratore extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private ProcuratoreSportivo procuratore;
	private JPanel contentPane;
	private JTable tabellaAtletiHistory;

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
	public InfoProcuratore(Controller c,ProcuratoreSportivo procuratore) {
		controller =c;
		this.procuratore = procuratore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		tabellaAtletiHistory = new JTable();
		tabellaAtletiHistory.setBounds(35, 0, 711, 297);
		contentPane.add(tabellaAtletiHistory);
		tabellaAtletiHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabellaAtletiHistory.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CF","Nome","Cognome","Sesso","DataNascita","Nazione", 
				"Provincia","Comune" 
			}
			
		) {private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int r,int c) {return false;}});
		
		tabellaAtletiHistory.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabellaAtletiHistory.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabellaAtletiHistory.getColumnModel().getColumn(2).setPreferredWidth(81);
		tabellaAtletiHistory.getColumnModel().getColumn(3).setPreferredWidth(44);
		tabellaAtletiHistory.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JLabel lblNewLabel = new JLabel("History Atleti");
		lblNewLabel.setBounds(25, 136, 85, 13);
		contentPane.setLayout(null);
		
		tabellaAtletiHistory.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		tabellaAtletiHistory.setPreferredScrollableViewportSize(new Dimension(670, 240));
        tabellaAtletiHistory.setFillsViewportHeight(true);
		
		JScrollPane js=new JScrollPane(tabellaAtletiHistory);
		js.setBounds(115, 10, 672, 266);
		js.setVisible(true);
		
		
		JButton HomeButton = new JButton("Home");
		HomeButton.setBounds(792, 132, 59, 21);
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomeFromInfoProcuratore();
			}
		});
		contentPane.add(lblNewLabel);
		contentPane.add(js);
		contentPane.add(HomeButton);
		popolaTabellaHistoryAtleti();
	}
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

