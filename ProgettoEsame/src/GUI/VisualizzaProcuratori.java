package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Persona;
import Entità.ProcuratoreSportivo;
import ImplementationDAO.ImplementationDAO;

public class VisualizzaProcuratori extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTable tabellaPersone;
	protected Controller Controller;
	protected JLabel labelTitolo;
	private ArrayList<ProcuratoreSportivo> listaPersoneVisualizzate = new ArrayList<ProcuratoreSportivo>();
	public VisualizzaProcuratori(Controller controller) {
		Controller=controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 410);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTitolo = new JLabel("Elenco Procuratori");
		labelTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitolo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTitolo.setBounds(311, 10, 250, 33);
		contentPane.add(labelTitolo);
		
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
		
		tabellaPersone.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
//	        	String text = "Selezionato: ";
//	        	for(int i=0;i<tabellaPersone.getColumnCount();i++)
//	        		text+=tabellaPersone.getValueAt(tabellaPersone.getSelectedRow(), i)+" ";
	        	personaSelezionata();
	            //System.out.println(tabellaPersone.getValueAt(tabellaPersone.getSelectedRow(), 0).toString());
	        }
	    });
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
			
			try {
				listaPersoneVisualizzate = (ArrayList<ProcuratoreSportivo>) dao.GetProcuratori();
				DefaultTableModel model = (DefaultTableModel) tabellaPersone.getModel();
				for(int i=0;i<listaPersoneVisualizzate.size();i++){
					Persona p  =listaPersoneVisualizzate.get(i);
					
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
	public void personaSelezionata() {
		try
    	{
    		int i = tabellaPersone.getSelectedRow();
    		if(i==-1)return;
        	Persona personaSelezionata = listaPersoneVisualizzate.get(i);
        	JOptionPane.showMessageDialog(contentPane,personaSelezionata);  
        	
    	}
    	catch (IndexOutOfBoundsException e) {
    		
    		e.printStackTrace();
		}
    	tabellaPersone.clearSelection(); // deseleziona
	}
	
}
