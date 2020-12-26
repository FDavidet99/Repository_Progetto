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
import EccezioniPersona.EccezioneCF;
import Entità.Atleta;
import Entità.Persona;
import ImplementationDAO.ImplementationDAO;

import javax.swing.border.LineBorder;

public class VisualizzaAtleta extends JFrame {

	private JPanel contentPane;
	private JTable tabellaPersone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaAtleta frame = new VisualizzaAtleta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VisualizzaAtleta() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Elenco persone");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitolo.setBounds(311, 10, 158, 33);
		contentPane.add(lblTitolo);

		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 761, 292);
		contentPane.add(panel);
		
		tabellaPersone = new JTable();
		panel.add(tabellaPersone);
		tabellaPersone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabellaPersone.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CF","Nome","Cognome","Sesso","DataNascita","Nazione", 
//				"Provincia","Comune" 
			}
			
		) {private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int r,int c) {return false;}});
		
		tabellaPersone.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabellaPersone.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabellaPersone.getColumnModel().getColumn(2).setPreferredWidth(81);
		tabellaPersone.getColumnModel().getColumn(3).setPreferredWidth(44);
		
		
		tabellaPersone.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		tabellaPersone.setPreferredScrollableViewportSize(new Dimension(670, 240));
        tabellaPersone.setFillsViewportHeight(true);
		
		JScrollPane js=new JScrollPane(tabellaPersone);
		js.setVisible(true);
		panel.add(js);
		
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
					model.addRow(new Object[] {
							p.getCF(), p.getNome(),
							p.getCognome(), p.getSessoPersona(),
							p.getDataNascita(),p.getNazioneNascita().getNomeNazione(),
//							p.getProvinciaNascita().getNome(),
//							p.getComuneNascita().getNome(),
						});
				}
			} catch (EccezioneCF e) {
				e.printStackTrace();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 }