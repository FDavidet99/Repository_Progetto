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
import java.util.Iterator;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Contratto;
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
	private JTable TabellaAtleti;
	private JLabel labelTitolo;
	private ArrayList<Atleta> ListaAtletiVisualizzati = new ArrayList<Atleta>();
	Controller controller;

	/**
	 * Create the frame.
	 */
	public VisualizzaAtleti(Controller c) {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTitolo = new JLabel("Elenco Atleti");
		labelTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitolo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTitolo.setBounds(10, 10, 794, 33);
		contentPane.add(labelTitolo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 764, 179);
		contentPane.add(scrollPane);
			
        Object[] Colonne= {"CF","Nome","Cognome","Sesso","DataNascita","Nazione","Provincia","Comune" };
		
        TabellaAtleti = new JTable();
		scrollPane.setViewportView(TabellaAtleti);
		
		TabellaAtleti.setModel(new DefaultTableModel(
				PopolaTabellaAtleti(),Colonne 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaAtleti.getColumnModel().getColumn(0).setPreferredWidth(120);
		
		TabellaAtleti.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	AtletaSelezionato(TabellaAtleti,controller);
	        	TabellaAtleti.setBackground(Color.red);
	        }
	    });
		
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomePageFromPageViewAtleti();
			}
		});
		HomeButton.setBounds(599, 244, 89, 23);
		contentPane.add(HomeButton);	
		

	}

	public Object[][] PopolaTabellaAtleti() {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Atleta> Atleti=(ArrayList<Atleta>) dao.GetAtleti();	
			Contenutotab=new Object [Atleti.size()][8];		
			for(int i=0;i<Atleti.size();i++){
					Atleta TmpAtleta=Atleti.get(i);	
					String provincia  = "Estero"; 
					String comune= "Estero";
					if(TmpAtleta.getProvinciaNascita()!=null)
						provincia = TmpAtleta.getProvinciaNascita().getNome();
					if(TmpAtleta.getComuneNascita()!=null)
						comune = TmpAtleta.getComuneNascita().getNome();
				
					Contenutotab[i][0]=TmpAtleta.getCF();
					Contenutotab[i][1]= TmpAtleta.getNome();
					Contenutotab[i][2]=TmpAtleta.getCognome();
					Contenutotab[i][3]=TmpAtleta.getDataNascita();
					Contenutotab[i][4]=TmpAtleta.getDataNascita();
					Contenutotab[i][5]=TmpAtleta.getNazioneNascita().getNomeNazione();
					Contenutotab[i][6]=provincia;
					Contenutotab[i][7]=comune;				
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

	public void AtletaSelezionato(JTable tabellaAtleti,Controller controller) {
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<Atleta> ListaAtletiVisualizzati=(ArrayList<Atleta>) dao.GetAtleti();
    		int i = tabellaAtleti.getSelectedRow();
    		if(i==-1)
    			return;
        	Atleta atletaSel = ListaAtletiVisualizzati.get(i);
        	controller.GotoInfoAtletaFromVisualizzaAtleta(atletaSel);
        	
    	} catch (IndexOutOfBoundsException e) {
    		JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Selezionare atleta",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 100);
			Dialog.setVisible(true);
		} catch (EccezioneCF e) {
    		JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Selezionare atleta",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 100);
			Dialog.setVisible(true);
		} catch (SQLException e) {
    		JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Selezionare atleta",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 100);
			Dialog.setVisible(true);
		}
		
    	tabellaAtleti.clearSelection(); // deseleziona
	}
	
	
}

 