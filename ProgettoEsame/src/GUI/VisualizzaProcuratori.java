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
import Entità.ProcuratoreSportivo;
import ImplementationDAO.ImplementationDAO;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizzaProcuratori extends JFrame {

	private JPanel contentPane;
	private JTable TabellaProcuratori;
	public JTable getTabellaProcuratori() {
		return TabellaProcuratori;
	}

	private JLabel labelTitolo;
	private ArrayList<Atleta> ListaProcuratoriVisualizzati = new ArrayList<Atleta>();
	Controller controller;

	/**
	 * Create the frame.
	 */
	public VisualizzaProcuratori(Controller c) {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTitolo = new JLabel("Scegli Procuratore");
		labelTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitolo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTitolo.setBounds(10, 10, 794, 33);
		contentPane.add(labelTitolo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 764, 179);
		contentPane.add(scrollPane);
			
        Object[] Colonne= {"CF","Nome","Cognome","Sesso","DataNascita","Nazione","Provincia","Comune" };
		
        TabellaProcuratori = new JTable();
		scrollPane.setViewportView(TabellaProcuratori);
		
		TabellaProcuratori.setModel(new DefaultTableModel(
				PopolaTabellaProcuratori(Colonne.length),Colonne 
		){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r,int c) {
				return false;
			}
		});
		TabellaProcuratori.getColumnModel().getColumn(0).setPreferredWidth(110);
		
		TabellaProcuratori.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	ProcuratoreSelezionato(TabellaProcuratori,controller);
	        }
	    });
		
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomePageFromViewProcuratori();
			}
		});
		HomeButton.setBounds(599, 244, 89, 23);
		contentPane.add(HomeButton);	
		

	}

	public Object[][] PopolaTabellaProcuratori(int NumColonne) {
		Object[][] Contenutotab=new Object [0][0];
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<ProcuratoreSportivo> Procuratori=(ArrayList<ProcuratoreSportivo>) dao.GetProcuratori();	
			Contenutotab=new Object [Procuratori.size()][NumColonne];		
			for(int i=0;i<Procuratori.size();i++){
					ProcuratoreSportivo TmpProcuratore=Procuratori.get(i);	
					String provincia  = "Estero"; 
					String comune= "Estero";
					if(TmpProcuratore.getProvinciaNascita()!=null)
						provincia = TmpProcuratore.getProvinciaNascita().getNome();
					if(TmpProcuratore.getComuneNascita()!=null)
						comune = TmpProcuratore.getComuneNascita().getNome();
				
					Contenutotab[i][0]=TmpProcuratore.getCF();
					Contenutotab[i][1]= TmpProcuratore.getNome();
					Contenutotab[i][2]=TmpProcuratore.getCognome();
					Contenutotab[i][3]=TmpProcuratore.getDataNascita();
					Contenutotab[i][4]=TmpProcuratore.getDataNascita();
					Contenutotab[i][5]=TmpProcuratore.getNazioneNascita().getNomeNazione();
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
			JLabel LabelJDialog= new JLabel("Non � stato trovato nulla",SwingConstants.CENTER); 
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

	public void ProcuratoreSelezionato(JTable TabProcuratori,Controller controller) {
		try {
			ImplementationDAO dao = ControllerQuery.getInstance().getDAO();
			ArrayList<ProcuratoreSportivo> ProcuratoriVisualizzati=(ArrayList<ProcuratoreSportivo>) dao.GetProcuratori();	
    		int i = TabProcuratori.getSelectedRow();
    		if(i==-1)
    			return;
        	ProcuratoreSportivo procuratoreSelezionato = ProcuratoriVisualizzati.get(i);
        	int dialogButton = 0;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi visualizzare le info del procuratore '"
					+ procuratoreSelezionato.getNome()+ " "+procuratoreSelezionato.getCognome()+"'?","Warning",dialogButton);
        	if(dialogResult == JOptionPane.YES_OPTION){
        		controller.GotoInfoProcuratoreFromVisualizzaProcuratore(procuratoreSelezionato);
        	}	
        	
    	} catch (IndexOutOfBoundsException | EccezioneCF e) {
    		e.printStackTrace();
    		JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Selezionare procuratore",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 100);
			Dialog.setVisible(true);
		} catch (SQLException e) {
			JDialog Dialog = new JDialog(); 
			JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			Dialog.getContentPane().add(LabelJDialog); 
			Dialog.setBounds(400, 150, 250, 100);
			Dialog.setVisible(true);
			
		}
		
    	TabProcuratori.clearSelection(); // deseleziona
	}
	
	
}

