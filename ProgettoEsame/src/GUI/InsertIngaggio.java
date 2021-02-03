package GUI;

import java.awt.Label;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.toedter.calendar.JDateChooser;

import Entity.Atleta;
import Entity.Ingaggio;
import Entity.ProcuratoreSportivo;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;

public class InsertIngaggio extends JFrame {

	private JPanel contentPane;
	private JTextField StipendiotextField;
	private JComboBox ProcuratoreComboBox;
	private JComboBox AtletaComboBox;
	private JDateChooser DataInizioDateChooser;
	private JDateChooser DataFineDateChooser;
	Controller controller;

	/**
	 * Create the frame. 
	 */
	public InsertIngaggio(Controller c) {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label ProcuratoreLabel = new Label();
		ProcuratoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ProcuratoreLabel.setBackground(UIManager.getColor("Button.background"));
		ProcuratoreLabel.setText("Procuratore");
		ProcuratoreLabel.setBounds(37, 70, 71, 22);
		contentPane.add(ProcuratoreLabel);
		
		Label AtletaLabel = new Label();
		AtletaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AtletaLabel.setBackground(UIManager.getColor("Button.background"));
		AtletaLabel.setText("Atleta");
		AtletaLabel.setBounds(300, 70, 44, 22);
		contentPane.add(AtletaLabel);
		
		Label DataInizioLabel = new Label();
		DataInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DataInizioLabel.setBackground(UIManager.getColor("Button.background"));
		DataInizioLabel.setText("Data Inizio");
		DataInizioLabel.setBounds(37, 120, 71, 22);
		contentPane.add(DataInizioLabel);
		
		ArrayList<ProcuratoreSportivo> ElencoProcuratori=(ArrayList) controller.GetProcuratori();
	    ArrayList<String> nomiProcuratori = new ArrayList<String>();
		for(ProcuratoreSportivo a:ElencoProcuratori)
			nomiProcuratori.add(a.getNome()+" "+a.getCognome());
	
		ProcuratoreComboBox = new JComboBox(nomiProcuratori.toArray());
		ProcuratoreComboBox.setBounds(114, 70, 175, 22);
		ProcuratoreComboBox.setSelectedIndex(-1);
		contentPane.add(ProcuratoreComboBox);
		
		ArrayList<Atleta> ElencoAtleti;
	    ElencoAtleti=(ArrayList) controller.GetAtleti();
		ArrayList<String> nomiAtleti = new ArrayList<String>();
		for(Atleta a:ElencoAtleti)
			nomiAtleti.add(a.getNome()+" "+a.getCognome());
		
		AtletaComboBox = new JComboBox(nomiAtleti.toArray());
		AtletaComboBox.setBounds(345, 70, 175, 22);
		AtletaComboBox.setSelectedIndex(-1);
		contentPane.add(AtletaComboBox);
		
		DataInizioDateChooser = new JDateChooser();
		DataInizioDateChooser.setBounds(110, 120, 100, 20);
		DataInizioDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataInizioDateChooser);
		
		Label DataFineLabel = new Label();
		DataFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DataFineLabel.setBackground(UIManager.getColor("Button.background"));
		DataFineLabel.setText("Data Fine");
		DataFineLabel.setBounds(293, 120, 67, 22);
		contentPane.add(DataFineLabel);
		
		DataFineDateChooser = new JDateChooser();
		DataFineDateChooser.setBounds(366, 122, 100, 20);
		DataFineDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataFineDateChooser);
		
		StipendiotextField = new JTextField();
		StipendiotextField.setBounds(139, 165, 86, 20);
		contentPane.add(StipendiotextField);
		StipendiotextField.setColumns(10);
		
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomePageFromInsertIngaggio();
			}
		});
		HomeButton.setBounds(263, 224, 89, 23);
		contentPane.add(HomeButton);
		
		JButton ResetButton = new JButton("Cancella");
		ResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SvuotaCampi();
			}
		});
		ResetButton.setBounds(354, 224, 89, 23);
		contentPane.add(ResetButton);
		
		JButton IngaggioButton = new JButton("Registra");
		IngaggioButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				try {
					int indexProcuratore = ProcuratoreComboBox.getSelectedIndex();
					int indexAtleta = AtletaComboBox.getSelectedIndex();					
					ProcuratoreSportivo procuratore = ElencoProcuratori.get(indexProcuratore);
					Atleta atleta = ElencoAtleti.get(indexAtleta);
					LocalDate TempDateInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate TempDateFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					double stipendioProcuratore = Double.parseDouble(StipendiotextField.getText());
					Ingaggio ingaggio=new Ingaggio(procuratore,atleta,TempDateInizio, TempDateFine,stipendioProcuratore);
					controller.InsertIngaggioInDB(ingaggio);	
				} catch (IndexOutOfBoundsException e1) {	
					if(ProcuratoreComboBox.getSelectedIndex()==-1) {
						JDialog Dialog = new JDialog(); 
						JLabel LabelJDialog= new JLabel("Inserire procuratore interessato",SwingConstants.CENTER); 
						Dialog.getContentPane().add(LabelJDialog); 
						Dialog.setBounds(400, 150, 250, 200);
						Dialog.setVisible(true);
					} else if( AtletaComboBox.getSelectedIndex()==-1) {
						JDialog Dialog = new JDialog(); 
						JLabel LabelJDialog= new JLabel("Inserire atleta interessato",SwingConstants.CENTER); 
						Dialog.getContentPane().add(LabelJDialog); 
						Dialog.setBounds(400, 150, 250, 100);
						Dialog.setVisible(true);
					} else {
						JDialog Dialog = new JDialog(); 
						JLabel LabelJDialog= new JLabel("Inserire procuratore e atleta interessato",SwingConstants.CENTER); 
						Dialog.getContentPane().add(LabelJDialog); 
						Dialog.setBounds(400, 150, 250, 200);
						Dialog.setVisible(true);
					}
					
				} catch (NullPointerException e3) {
					JDialog Dialog = new JDialog(); 
					JLabel LabelJDialog= new JLabel("Tutti i campi devono essere compilati",SwingConstants.CENTER);
		            Dialog.getContentPane().add(LabelJDialog); 
		            Dialog.setBounds(400, 150, 230, 150);
		            Dialog.setVisible(true);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					JDialog Dialog = new JDialog(); 
					JLabel LabelJDialog= new JLabel("Cifra non compatibile con i dati ",SwingConstants.CENTER);
		            Dialog.getContentPane().add(LabelJDialog); 
		            Dialog.setBounds(400, 150, 230, 150);
		            Dialog.setVisible(true);
			    }
				
			}
		});
		IngaggioButton.setBounds(445, 224, 89, 23);
		contentPane.add(IngaggioButton);
		
		JLabel StipendioLabel = new JLabel("Stipendio mensile");
		StipendioLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		StipendioLabel.setBounds(37, 165, 136, 18);
		contentPane.add(StipendioLabel);
		
		JLabel TitoloLabel = new JLabel("Inserire un nuovo ingaggio");
		TitoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TitoloLabel.setBounds(37, 29, 208, 18);
		contentPane.add(TitoloLabel);
		
		JLabel lblNewLabel = new JLabel("\u20AC");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(235, 167, 28, 18);
		contentPane.add(lblNewLabel);
		
	}
	
	public void SvuotaCampi() {
		
		DataInizioDateChooser.setCalendar(null);
		DataFineDateChooser.setCalendar(null);
		ProcuratoreComboBox.setSelectedIndex(-1);
		AtletaComboBox.setSelectedIndex(-1);
		StipendiotextField.setText(null);
		
	}
}
