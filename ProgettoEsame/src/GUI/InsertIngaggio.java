package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.Ingaggio;
import Entità.ProcuratoreSportivo;
import ImplementationDAO.ImplementationDAO;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;

public class InsertIngaggio extends JFrame {

	private JPanel contentPane;
	private JTextField StipendioProvatextField;
	Controller controller;
	private List<ProcuratoreSportivo> QueryProcuratori;
	private List<Atleta> QueryAtleti;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InsertIngaggio frame = new InsertIngaggio();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
		
		JTextArea ProcuratoreTextArea = new JTextArea();
		ProcuratoreTextArea.setEditable(false);
		ProcuratoreTextArea.setBackground(UIManager.getColor("Button.background"));
		ProcuratoreTextArea.setText("Procuratore");
		ProcuratoreTextArea.setBounds(37, 39, 93, 22);
		contentPane.add(ProcuratoreTextArea);
		
		JTextArea AtletaTextArea = new JTextArea();
		AtletaTextArea.setEditable(false);
		AtletaTextArea.setBackground(UIManager.getColor("Button.background"));
		AtletaTextArea.setText("Atleta");
		AtletaTextArea.setBounds(341, 39, 53, 22);
		contentPane.add(AtletaTextArea);
		
		JTextArea txtrDataInizio = new JTextArea();
		txtrDataInizio.setEditable(false);
		txtrDataInizio.setBackground(UIManager.getColor("Button.background"));
		txtrDataInizio.setText("Data Inizio");
		txtrDataInizio.setBounds(33, 92, 118, 22);
		contentPane.add(txtrDataInizio);
		
		QueryProcuratori=new ArrayList <ProcuratoreSportivo>();
	    QueryProcuratori=(ArrayList) OggettoConnessione.GetProcuratori();
	    ArrayList<String> nomiProcuratori = new ArrayList<String>();
		for(ProcuratoreSportivo a:QueryProcuratori)
			nomiProcuratori.add(a.getNome()+" "+a.getCognome());
		
		JComboBox ProcuratoreComboBox = new JComboBox(nomiProcuratori.toArray());
		ProcuratoreComboBox.setBounds(151, 40, 100, 22);
		contentPane.add(ProcuratoreComboBox);
		
		QueryAtleti=new ArrayList();
	    QueryAtleti=(ArrayList) OggettoConnessione.GetAtleti();
		ArrayList<String> nomiAtleti = new ArrayList<String>();
		for(Atleta a:QueryAtleti)
			nomiAtleti.add(a.getNome()+" "+a.getCognome());
		
		JComboBox AtletaComboBox = new JComboBox(nomiAtleti.toArray());
		AtletaComboBox.setBounds(404, 40, 100, 22);
		contentPane.add(AtletaComboBox);
		
		JDateChooser DataInizioDateChooser = new JDateChooser();
		DataInizioDateChooser.setBounds(161, 94, 100, 20);
		DataInizioDateChooser.setDateFormatString("YYYY/MM/dd");
		contentPane.add(DataInizioDateChooser);
		
		
		JTextArea txtrDataFine = new JTextArea();
		txtrDataFine.setEditable(false);
		txtrDataFine.setBackground(UIManager.getColor("Button.background"));
		txtrDataFine.setText("Data Fine");
		txtrDataFine.setBounds(293, 98, 86, 22);
		contentPane.add(txtrDataFine);
		
		JDateChooser DataFineDateChooser = new JDateChooser();
		DataFineDateChooser.setBounds(389, 136, 100, 20);
		contentPane.add(DataFineDateChooser);
		
		StipendioProvatextField = new JTextField();
		StipendioProvatextField.setBounds(44, 180, 86, 20);
		contentPane.add(StipendioProvatextField);
		StipendioProvatextField.setColumns(10);
		
		JButton HomeButton = new JButton("Home");
		HomeButton.setBounds(263, 276, 89, 23);
		contentPane.add(HomeButton);
		
		JButton ResetButton = new JButton("Cancella");
		ResetButton.setBounds(371, 276, 89, 23);
		contentPane.add(ResetButton);
		
		JButton IngaggioButton = new JButton("Registra");
		IngaggioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate TempDateInizio=LocalDate.ofInstant(DataInizioDateChooser.getDate().toInstant(), ZoneId.systemDefault());
				LocalDate TempDateFine=LocalDate.ofInstant(DataFineDateChooser.getDate().toInstant(), ZoneId.systemDefault());
				int indexProcuratore = ProcuratoreComboBox.getSelectedIndex();
				int indexAtleta = AtletaComboBox.getSelectedIndex();
				
				Ingaggio TmpIngaggio=new Ingaggio(QueryProcuratori.get(indexProcuratore),
						QueryAtleti.get(indexAtleta),
						TempDateInizio, TempDateFine,Double.parseDouble(StipendioProvatextField.getText()));

				controller.InsertIngaggio(TmpIngaggio);
			}
		});
		IngaggioButton.setBounds(470, 276, 89, 23);
		contentPane.add(IngaggioButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(177, 178, 124, 22);
		contentPane.add(textArea);
	}
}
