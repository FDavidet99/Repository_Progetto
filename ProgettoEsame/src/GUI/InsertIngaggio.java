package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InsertIngaggio extends JFrame {

	private JPanel contentPane;
	private JTextField StipendioProvatextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertIngaggio frame = new InsertIngaggio();
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
	public InsertIngaggio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea ProcuratoreTextArea = new JTextArea();
		ProcuratoreTextArea.setText("Procuratore");
		ProcuratoreTextArea.setBounds(37, 39, 93, 22);
		contentPane.add(ProcuratoreTextArea);
		
		JTextArea AtletaTextArea = new JTextArea();
		AtletaTextArea.setText("Atleta");
		AtletaTextArea.setBounds(341, 39, 53, 22);
		contentPane.add(AtletaTextArea);
		
		JTextArea txtrDataInizio = new JTextArea();
		txtrDataInizio.setText("Data Inizio");
		txtrDataInizio.setBounds(37, 129, 118, 22);
		contentPane.add(txtrDataInizio);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(151, 40, 30, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(404, 40, 30, 22);
		contentPane.add(comboBox_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(151, 129, 70, 20);
		contentPane.add(dateChooser);
		
		JTextArea txtrDataFine = new JTextArea();
		txtrDataFine.setText("Data Fine");
		txtrDataFine.setBounds(291, 129, 118, 22);
		contentPane.add(txtrDataFine);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(441, 131, 70, 20);
		contentPane.add(dateChooser_1);
		
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
		IngaggioButton.setBounds(470, 276, 89, 23);
		contentPane.add(IngaggioButton);
	}
}
