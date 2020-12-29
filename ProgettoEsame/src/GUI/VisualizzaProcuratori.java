package GUI;

import java.awt.Color;
import java.awt.Dimension;
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
import Entità.Persona;
import Entità.ProcuratoreSportivo;
import ImplementationDAO.ImplementationDAO;

public class VisualizzaProcuratori extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabellaProcuratori;
	private Controller controller;
	private JLabel labelTitolo;
	private ArrayList<ProcuratoreSportivo> ListaProcuratoriVisualizzati = new ArrayList<ProcuratoreSportivo>();
	
	public VisualizzaProcuratori(Controller c) {
		controller=c;
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
		
		tabellaProcuratori = new JTable();
		panel.add(tabellaProcuratori);
		tabellaProcuratori.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabellaProcuratori.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CF","Nome","Cognome","Sesso","DataNascita","Nazione", 
				"Provincia","Comune" 
			}
			
		) {private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int r,int c) {return false;}});
		
		tabellaProcuratori.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabellaProcuratori.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabellaProcuratori.getColumnModel().getColumn(2).setPreferredWidth(81);
		tabellaProcuratori.getColumnModel().getColumn(3).setPreferredWidth(44);
		tabellaProcuratori.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		tabellaProcuratori.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		tabellaProcuratori.setPreferredScrollableViewportSize(new Dimension(670, 240));
        tabellaProcuratori.setFillsViewportHeight(true);
		
		JScrollPane js=new JScrollPane(tabellaProcuratori);
		js.setVisible(true);
		panel.add(js);
		
		tabellaProcuratori.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	ProcuratoreSelezionato();
	        }
	    });
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomePageFromViewProcuratori();
			}
		});
		HomeButton.setBounds(542, 338, 89, 23);
		contentPane.add(HomeButton);
		
		popolaTabellaProcuratori();
		
	}
	
	public void popolaTabellaProcuratori() {
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ListaProcuratoriVisualizzati = (ArrayList<ProcuratoreSportivo>) dao.GetProcuratori();
			DefaultTableModel model = (DefaultTableModel) tabellaProcuratori.getModel();
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
	
	public void ProcuratoreSelezionato() {
		try {
    		int i = tabellaProcuratori.getSelectedRow();
    		if(i==-1)return;
        	Persona personaSelezionata = ListaProcuratoriVisualizzati.get(i);
        	JOptionPane.showMessageDialog(contentPane,personaSelezionata);  
    	} catch (IndexOutOfBoundsException e) {
    		JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Selezionare Procuratore",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 100);
			Dialog.setVisible(true);
		}
    	tabellaProcuratori.clearSelection(); // deseleziona
	}
	
}

