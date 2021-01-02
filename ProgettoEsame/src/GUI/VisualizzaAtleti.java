package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Persona;
import ImplementationDAO.ImplementationDAO;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizzaAtleti extends JFrame {

	private JPanel contentPane;
	private JTable tabellaAtleti;
	private JLabel labelTitolo;
	private ArrayList<Atleta> ListaAtletiVisualizzati = new ArrayList<Atleta>();
	Controller Controller;

	/**
	 * Create the frame.
	 */
	public VisualizzaAtleti(Controller c) {
		Controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 410);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTitolo = new JLabel("Elenco Atleti");
		labelTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitolo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTitolo.setBounds(311, 10, 250, 33);
		contentPane.add(labelTitolo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 761, 275);
		contentPane.add(panel);
		
		tabellaAtleti = new JTable();
		panel.add(tabellaAtleti);
		tabellaAtleti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabellaAtleti.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"CF","Nome","Cognome","Sesso","DataNascita","Nazione", 
				"Provincia","Comune" 
			}
			
		) {private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int r,int c) {return false;}});
		
		tabellaAtleti.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabellaAtleti.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabellaAtleti.getColumnModel().getColumn(2).setPreferredWidth(81);
		tabellaAtleti.getColumnModel().getColumn(3).setPreferredWidth(44);
		tabellaAtleti.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		tabellaAtleti.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		tabellaAtleti.setPreferredScrollableViewportSize(new Dimension(670, 240));
        tabellaAtleti.setFillsViewportHeight(true);
		
		JScrollPane js=new JScrollPane(tabellaAtleti);
		js.setVisible(true);
		panel.add(js);
		
		tabellaAtleti.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	AtletaSelezionato();
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
		
		popolaTabellaAtleti();
		
	}
	
	public void popolaTabellaAtleti() {
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ListaAtletiVisualizzati = (ArrayList<Atleta>) dao.GetAtleti();
			DefaultTableModel model = (DefaultTableModel) tabellaAtleti.getModel();
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
	
	public void AtletaSelezionato() {
		try {
    		int i = tabellaAtleti.getSelectedRow();
    		if(i==-1)return;
        	Atleta atletaSel = ListaAtletiVisualizzati.get(i);
        	//JOptionPane.showMessageDialog(contentPane,atletaSel);  
        	Controller.GotoInfoAtletaFromVisualizzaAtleta(atletaSel);
        	
    	}
    	catch (IndexOutOfBoundsException e) {
    		JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Selezionare atleta",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 100);
			Dialog.setVisible(true);
		}
    	tabellaAtleti.clearSelection(); // deseleziona
	}
}
 