package GUI;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Persona;
import Entità.ProcuratoreSportivo;
import ImplementationDAO.ImplementationDAO;

public class VisualizzaProcuratori extends VisualizzaAtleti {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ProcuratoreSportivo> listaPersoneVisualizzate = new ArrayList<ProcuratoreSportivo>();
	public VisualizzaProcuratori(GUI.Controller controller) {
		super(controller);
		labelTitolo.setText("Elenco Procuratori");
		// TODO Auto-generated constructor stub
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VisualizzaProcuratori frame = new VisualizzaProcuratori(new Controller());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	@Override
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
	@Override
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
