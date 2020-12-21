package GUI;

import java.util.ArrayList;

import java.util.Date;
import java.util.Properties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JTable;

import java.util.Iterator;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Controller.ControllerQuery;
import EccezioniPersona.EccezioneCF;
import Entità.*;
import ImplementationDAO.ImplementationDAO;

import java.awt.event.ActionEvent;
import com.toedter.components.JLocaleChooser;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DEMO_TEST_QUERY_CODICEFISCALE extends JFrame {

	private JPanel contentPane;
	private JTextField Nome_textField;
	private JTextField Cognome_textField;
	private JTextField Cf_textField;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DEMO_TEST_QUERY_CODICEFISCALE frame = new DEMO_TEST_QUERY_CODICEFISCALE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DEMO_TEST_QUERY_CODICEFISCALE() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Nome_textField = new JTextField();
		Nome_textField.setBounds(57, 28, 86, 20);
		contentPane.add(Nome_textField);
		Nome_textField.setColumns(10);
		
		JTextArea Nome_textArea = new JTextArea();
		Nome_textArea.setText("Nome");
		Nome_textArea.setEditable(false);
		Nome_textArea.setBounds(10, 26, 37, 22);
		contentPane.add(Nome_textArea);
		
		JTextArea Cognome_textArea = new JTextArea();
		Cognome_textArea.setText("Cognome\r\n");
		Cognome_textArea.setBounds(193, 26, 68, 22);
		contentPane.add(Cognome_textArea);
		
		Cognome_textField = new JTextField();
		Cognome_textField.setBounds(282, 28, 86, 20);
		contentPane.add(Cognome_textField);
		Cognome_textField.setColumns(10);
		
		JTextArea Sesso_textArea = new JTextArea();
		Sesso_textArea.setText("Sesso");
		Sesso_textArea.setBounds(10, 58, 45, 22);
		contentPane.add(Sesso_textArea);
		
		JComboBox Sesso_comboBox = new JComboBox(Sesso.values());
		Sesso_comboBox.setBounds(67, 59, 58, 22);
		contentPane.add(Sesso_comboBox);
		
		JTextArea Data_textArea = new JTextArea();
		Data_textArea.setText("Data");
		Data_textArea.setBounds(188, 59, 52, 22);
		contentPane.add(Data_textArea);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(241, 59, 127, 20);
		contentPane.add(dateChooser);
		
		ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();
		
		JTextArea Nazione_textArea = new JTextArea();
		Nazione_textArea.setText("Nazione");
		Nazione_textArea.setBounds(10, 89, 80, 22);
		contentPane.add(Nazione_textArea);
		
		
		
	     ArrayList sql=new ArrayList();
	     sql=(ArrayList) OggettoConnessione.GetNazioni();
		
    	JComboBox<Nazione> Nazione_comboBox = new JComboBox (sql.toArray());
    	Nazione_comboBox.setSelectedIndex(-1);
		Nazione_comboBox.setBounds(100, 91, 113, 20);
    	contentPane.add(Nazione_comboBox);
		
		
		JTextArea Provincia_textArea = new JTextArea();
		Provincia_textArea.setText("Provincia\r\n");
		Provincia_textArea.setBounds(10, 122, 80, 22);
		contentPane.add(Provincia_textArea);
		
		JComboBox<Provincia> Provincia_comboBox=new JComboBox();
		Provincia_comboBox.setBounds(102, 123, 115, 22);
		contentPane.add(Provincia_comboBox);
		Provincia_comboBox.setSelectedIndex(-1);
		
		JTextArea Comune_TextArea = new JTextArea();
		Comune_TextArea.setText("Comune");
		Comune_TextArea.setBounds(10, 155, 58, 22);
		contentPane.add(Comune_TextArea);
		
		JComboBox Comune_comboBox = new JComboBox();
		Comune_comboBox.setBounds(102, 156, 113, 22);
		contentPane.add(Comune_comboBox);
		
		Cf_textField = new JTextField();
		Cf_textField.setEditable(false);
		Cf_textField.setBounds(129, 213, 239, 20);
		contentPane.add(Cf_textField);
		Cf_textField.setColumns(10);
		
		JButton CalcolaCf_Button = new JButton("Calcola Cf");
		CalcolaCf_Button.setBounds(10, 212, 89, 23);
		contentPane.add(CalcolaCf_Button);
		
		JButton Nazione_Button = new JButton("Prosegui");
		Nazione_Button.setBounds(240, 92, 93, 24);
		contentPane.add(Nazione_Button);
		
		JButton btnNewButton_1 = new JButton("Prosegui");
		btnNewButton_1.setBounds(241, 122, 93, 24);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
		
		Nazione_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((Nazione) Nazione_comboBox.getSelectedItem()).getCodiceAt().equals("Z000")) {
					ArrayList sql2=new ArrayList();
					try {
						sql2=(ArrayList) OggettoConnessione.GetProvinceByNazione(Nazione_comboBox.getItemAt(Nazione_comboBox.getSelectedIndex()));
						JComboBox<Provincia> Tempo_Provincia=new JComboBox(sql2.toArray());
						Provincia_textArea.setVisible(true);
						Provincia_comboBox.setVisible(true);
						Tempo_Provincia.setSelectedIndex(-1);
						Provincia_comboBox.removeAllItems();
						Iterator i=sql2.iterator();
						while(i.hasNext())
						Provincia_comboBox.addItem((Provincia) i.next());
						btnNewButton_1.setVisible(true);
						btnNewButton_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								ArrayList sql3=new ArrayList();
								try {
									sql3=(ArrayList) OggettoConnessione.GetComuniByProvincia(Provincia_comboBox.getItemAt(Provincia_comboBox.getSelectedIndex()));
									JComboBox<Comune> Tempo_Comune=new JComboBox(sql3.toArray());
									Comune_TextArea.setVisible(true);
									Comune_comboBox.setVisible(true);
									Tempo_Comune.setSelectedIndex(-1);
									Comune_comboBox.removeAllItems();
									Iterator i=sql3.iterator();
									while(i.hasNext())
										Comune_comboBox.addItem((Comune) i.next());
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}		
							}
						});
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					Provincia_textArea.setVisible(false);
					Provincia_comboBox.setVisible(false);
					Comune_TextArea.setVisible(false);
					Comune_comboBox.setVisible(false);
					btnNewButton_1.setVisible(false);
				}
			}
		});
		
		CalcolaCf_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TempNome=Nome_textField.getText();
				String TempCognome=Cognome_textField.getText();
				Sesso TempSesso= (Sesso)Sesso_comboBox.getSelectedItem();
				
				Date DataScelta=dateChooser.getDate();
				LocalDate TempDate=LocalDate.ofInstant(DataScelta.toInstant(), ZoneId.systemDefault());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				TempDate.format(formatter);
				
				Nazione TempNazione=(Nazione) Nazione_comboBox.getSelectedItem();
				Provincia TempProvincia=(Provincia)Provincia_comboBox.getSelectedItem();
				Comune TempComune=(Comune)Comune_comboBox.getSelectedItem();
				
				Persona Tempo_Persona;
				try {
					Tempo_Persona = new Persona(TempNome,TempCognome,TempSesso,TempDate,TempNazione,TempProvincia,TempComune);
					Cf_textField.setText(Tempo_Persona.getCF());
				} catch (SQLException | EccezioneCF e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

	}
}
