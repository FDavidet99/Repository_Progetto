package GUI;

import java.awt.Label;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.toedter.calendar.JDateChooser;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Ingaggio;
import Entità.ProcuratoreSportivo;
import ImplementationDAO.ImplementationDAO;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;

public class InsertIngaggio extends JFrame {

	private JPanel contentPane;
	private JTextField StipendioProvatextField;
	private List<ProcuratoreSportivo> QueryProcuratori;
	private List<Atleta> QueryAtleti;
	private JComboBox ProcuratoreComboBox;
	private JComboBox AtletaComboBox;
	private JDateChooser DataInizioDateChooser;
	private JDateChooser DataFineDateChooser;
	Controller controller;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws EccezioneCF 
	 */
	public InsertIngaggio(Controller c) throws SQLException, EccezioneCF {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();
		
		Label ProcuratoreLabel = new Label();
		ProcuratoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		ProcuratoreLabel.setBackground(UIManager.getColor("Button.background"));
		ProcuratoreLabel.setText("Procuratore");
		ProcuratoreLabel.setBounds(37, 39, 93, 22);
		contentPane.add(ProcuratoreLabel);
		
		Label AtletaLabel = new Label();
		AtletaLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		AtletaLabel.setBackground(UIManager.getColor("Button.background"));
		AtletaLabel.setText("Atleta");
		AtletaLabel.setBounds(341, 39, 53, 22);
		contentPane.add(AtletaLabel);
		
		Label DataInizioLabel = new Label();
		DataInizioLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		DataInizioLabel.setBackground(UIManager.getColor("Button.background"));
		DataInizioLabel.setText("Data Inizio");
		DataInizioLabel.setBounds(37, 94, 93, 22);
		contentPane.add(DataInizioLabel);
		
		QueryProcuratori=new ArrayList <ProcuratoreSportivo>();
	    QueryProcuratori=(ArrayList) OggettoConnessione.GetProcuratori();
	    ArrayList<String> nomiProcuratori = new ArrayList<String>();
		for(ProcuratoreSportivo a:QueryProcuratori)
			nomiProcuratori.add(a.getNome()+" "+a.getCognome());
	
		ProcuratoreComboBox = new JComboBox(nomiProcuratori.toArray());
		ProcuratoreComboBox.setBounds(151, 40, 100, 22);
		ProcuratoreComboBox.setSelectedIndex(-1);
		contentPane.add(ProcuratoreComboBox);
		
		QueryAtleti=new ArrayList();
	    QueryAtleti=(ArrayList) OggettoConnessione.GetAtleti();
		ArrayList<String> nomiAtleti = new ArrayList<String>();
		for(Atleta a:QueryAtleti)
			nomiAtleti.add(a.getNome()+" "+a.getCognome());
		
		AtletaComboBox = new JComboBox(nomiAtleti.toArray());
		AtletaComboBox.setBounds(400, 40, 100, 22);
		AtletaComboBox.setSelectedIndex(-1);
		contentPane.add(AtletaComboBox);
		
		DataInizioDateChooser = new JDateChooser();
		DataInizioDateChooser.setBounds(136, 96, 100, 20);
		DataInizioDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataInizioDateChooser);
		
		Label DataFineLabel = new Label();
		DataFineLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		DataFineLabel.setBackground(UIManager.getColor("Button.background"));
		DataFineLabel.setText("Data Fine");
		DataFineLabel.setBounds(293, 94, 86, 22);
		contentPane.add(DataFineLabel);
		
		DataFineDateChooser = new JDateChooser();
		DataFineDateChooser.setBounds(385, 94, 100, 20);
		DataFineDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataFineDateChooser);
		
		StipendioProvatextField = new JTextField();
		StipendioProvatextField.setBounds(44, 180, 86, 20);
		contentPane.add(StipendioProvatextField);
		StipendioProvatextField.setColumns(10);
		
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomePageFromInsertIngaggio();
			}
		});
		HomeButton.setBounds(263, 276, 89, 23);
		contentPane.add(HomeButton);
		
		JButton ResetButton = new JButton("Cancella");
		ResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SvuotaCampi();
			}
		});
		ResetButton.setBounds(362, 276, 89, 23);
		contentPane.add(ResetButton);
		
		JButton IngaggioButton = new JButton("Registra");
		IngaggioButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				try {
					int indexProcuratore = ProcuratoreComboBox.getSelectedIndex();
					int indexAtleta = AtletaComboBox.getSelectedIndex();					
					ProcuratoreSportivo procuratore = QueryProcuratori.get(indexProcuratore);
					Atleta atleta = QueryAtleti.get(indexAtleta);
					LocalDate TempDateInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate TempDateFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					double stipendioProcuratore = Double.parseDouble(StipendioProvatextField.getText());
					Ingaggio ingaggio=new Ingaggio(procuratore,atleta,TempDateInizio, TempDateFine,stipendioProcuratore);
					controller.InsertIngaggio(ingaggio);
					
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
		            Dialog.add(LabelJDialog); 
		            Dialog.setBounds(400, 150, 230, 150);
		            Dialog.setVisible(true);
				} 
				
			}
		});
		IngaggioButton.setBounds(461, 276, 89, 23);
		contentPane.add(IngaggioButton);
		
	}
	
	public void SvuotaCampi() {
		
		DataInizioDateChooser.setCalendar(null);
		DataFineDateChooser.setCalendar(null);
		ProcuratoreComboBox.setSelectedIndex(-1);
		AtletaComboBox.setSelectedIndex(-1);
		
	}
}
