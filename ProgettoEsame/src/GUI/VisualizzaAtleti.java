package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Persona;
import ImplementationDAO.ImplementationDAO;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizzaAtleti extends JFrame {

	private JPanel contentPane;
	private JTable tabellaPersone;
	Controller Controller;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VisualizzaAtleti frame = new VisualizzaAtleti();
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
	public VisualizzaAtleti(Controller controller) {
		Controller=controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 410);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Elenco persone");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitolo.setBounds(311, 10, 158, 33);
		contentPane.add(lblTitolo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 761, 275);
		contentPane.add(panel);
		
		tabellaPersone = new JTable();
		panel.add(tabellaPersone);
		tabellaPersone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabellaPersone.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CF","Nome","Cognome","Sesso","DataNascita","Nazione", 
				"Provincia","Comune" 
			}
			
		) {private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int r,int c) {return false;}});
		
		tabellaPersone.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabellaPersone.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabellaPersone.getColumnModel().getColumn(2).setPreferredWidth(81);
		tabellaPersone.getColumnModel().getColumn(3).setPreferredWidth(44);
		tabellaPersone.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		tabellaPersone.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		tabellaPersone.setPreferredScrollableViewportSize(new Dimension(670, 240));
        tabellaPersone.setFillsViewportHeight(true);
		
		JScrollPane js=new JScrollPane(tabellaPersone);
		js.setVisible(true);
		panel.add(js);
		
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.GotoHomePageFromPageViewAtleti();
			}
		});
		HomeButton.setBounds(542, 338, 89, 23);
		contentPane.add(HomeButton);
		
		popolaTabellaPersone();
		
	}
	public void popolaTabellaPersone() {
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Atleta> persone = new ArrayList<Atleta>();
			try {
				persone = (ArrayList<Atleta>) dao.getAtleti();
				DefaultTableModel model = (DefaultTableModel) tabellaPersone.getModel();
				for(int i=0;i<persone.size();i++){
					Persona p  =persone.get(i);
					
					String provincia  = "Estero"; 
					String comune= "Estero";
					if(p.getProvinciaNascita()!=null)provincia = p.getProvinciaNascita().getNome();
					if(p.getComuneNascita()!=null) comune = p.getComuneNascita().getNome();
					model.addRow(new Object[] {
							p.getCF(), p.getNome(),
							p.getCognome(), p.getSessoPersona(),
							p.getDataNascita(),p.getNazioneNascita().getNomeNazione(),
							provincia,
							comune,
						});
				}
			} catch (EccezioneCF e) {
				JDialog Dialog = new JDialog(); 
	            JLabel LabelJDialog= new JLabel("Caratteri non visualizzabili"); 
	            Dialog.getContentPane().add(LabelJDialog); 
                Dialog.setBounds(400, 150, 240, 150);
	            Dialog.setVisible(true);}
//			} catch (NullPointerException e1) {
//				JDialog Dialog = new JDialog(); 
//	            JLabel LabelJDialog= new JLabel("Non è stato trovato nulla"); 
//	            Dialog.getContentPane().add(LabelJDialog); 
//                Dialog.setBounds(400, 150, 240, 150);
//	            Dialog.setVisible(true);
			
				
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione"); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 200);
			Dialog.setVisible(true);
		}
	}
 }