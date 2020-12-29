package GUI;

import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Comune;
import Entità.Nazione;
import Entità.Persona;
import Entità.Provincia;
import Entità.Sesso;
import ImplementationDAO.ImplementationDAO;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class InsertProcuratoreSportivo extends JFrame {

	private JPanel contentPane;
	private JTextField Nome_textField;
	private JTextField Cognome_textField;
	private JTextField Cf_textField;
	private JComboBox Sesso_comboBox;
	private JDateChooser DataNascitaDateChooser;
	private JComboBox<Nazione> Nazione_comboBox;
	private JComboBox<Provincia> Provincia_comboBox;
	private JComboBox<Comune>Comune_comboBox;
	Controller Controller;


	public InsertProcuratoreSportivo(Controller c) throws SQLException {
		Controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label TitoloLabel = new Label();
		TitoloLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		TitoloLabel.setBackground(UIManager.getColor("Panel.background"));
		TitoloLabel.setText("Inserire i dati del nuovo atleta");
		TitoloLabel.setBounds(10, 6, 267, 22);
		contentPane.add(TitoloLabel);
		
		Nome_textField = new JTextField();
		Nome_textField.setBounds(57, 37, 86, 20);
		contentPane.add(Nome_textField);
		Nome_textField.setColumns(10);
		
		
		Label NomeLabel = new Label();
		NomeLabel.setBackground(UIManager.getColor("Panel.background"));
		NomeLabel.setText("Nome");
		NomeLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		NomeLabel.setBounds(10, 37, 37, 22);
		contentPane.add(NomeLabel);
		
		Label CognomeLabel = new Label();
		CognomeLabel.setBackground(UIManager.getColor("Panel.background"));
		CognomeLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		CognomeLabel.setText("Cognome\r\n");
		CognomeLabel.setBounds(172, 37, 68, 22);
		contentPane.add(CognomeLabel);
		
		Cognome_textField = new JTextField();
		Cognome_textField.setBounds(240, 37, 86, 20);
		contentPane.add(Cognome_textField);
		Cognome_textField.setColumns(10);
		
		Label SessoLabel = new Label();
		SessoLabel.setBackground(UIManager.getColor("Panel.background"));
		SessoLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		SessoLabel.setText("Sesso");
		SessoLabel.setBounds(10, 72, 45, 22);
		contentPane.add(SessoLabel);
		
		Sesso_comboBox = new JComboBox(Sesso.values());
		Sesso_comboBox.setSelectedIndex(-1);
		Sesso_comboBox.setBounds(57, 70, 58, 22);
		contentPane.add(Sesso_comboBox);
		
		Label DataLabel = new Label();
		DataLabel.setText("Data di nascita");
		DataLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		DataLabel.setBackground(SystemColor.menu);
		DataLabel.setBounds(170, 70, 125, 22);
		contentPane.add(DataLabel);
		
		DataNascitaDateChooser = new JDateChooser();
		DataNascitaDateChooser.setBounds(301, 68, 115, 20);
		DataNascitaDateChooser.setDateFormatString("YYYY/MM/dd");
		contentPane.add(DataNascitaDateChooser);
		
		ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();
		
		Label NazioneLabel = new Label();
		NazioneLabel.setBackground(UIManager.getColor("Panel.background"));
		NazioneLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		NazioneLabel.setText("Nazione");
		NazioneLabel.setBounds(10, 103, 80, 22);
		contentPane.add(NazioneLabel);
	
	     ArrayList sql=new ArrayList();
	     sql=(ArrayList) OggettoConnessione.GetNazioni();
		
    	Nazione_comboBox = new JComboBox (sql.toArray());
    	Nazione_comboBox.setSelectedIndex(-1);
		Nazione_comboBox.setBounds(100, 105, 113, 20);
    	contentPane.add(Nazione_comboBox);
		
		Label ProvinciaLabel = new Label();
		ProvinciaLabel.setBackground(UIManager.getColor("Panel.background"));
		ProvinciaLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		ProvinciaLabel.setText("Provincia");
		ProvinciaLabel.setBounds(10, 136, 80, 22);
		contentPane.add(ProvinciaLabel);
		
		Provincia_comboBox=new JComboBox();
		Provincia_comboBox.setBounds(100, 137, 115, 22);
		contentPane.add(Provincia_comboBox);
		Provincia_comboBox.setSelectedIndex(-1);
		
		Label ComuneLabel = new Label();
		ComuneLabel.setBackground(UIManager.getColor("Panel.background"));
		ComuneLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		ComuneLabel.setText("Comune");
		ComuneLabel.setBounds(10, 169, 58, 22);
		contentPane.add(ComuneLabel);
		
		Comune_comboBox = new JComboBox();
		Comune_comboBox.setBounds(100, 170, 113, 22);
		contentPane.add(Comune_comboBox);
		
		Cf_textField = new JTextField();
		Cf_textField.setEditable(false);
		Cf_textField.setBounds(10, 202, 239, 20);
		contentPane.add(Cf_textField);
		Cf_textField.setColumns(10);
		
		JButton CalcolaCf_Button = new JButton("Calcola Codice fiscale");
		CalcolaCf_Button.setBounds(259, 201, 162, 22);
		contentPane.add(CalcolaCf_Button);
		
		JButton Nazione_Button = new JButton("Prosegui");
		Nazione_Button.setBounds(240, 103, 93, 24);
		contentPane.add(Nazione_Button);
		
		JButton ProvinciaButton = new JButton("Prosegui");
		ProvinciaButton.setBounds(240, 136, 93, 24);
		contentPane.add(ProvinciaButton);
		ProvinciaButton.setVisible(false);
		
		JButton Insert_Button = new JButton("Inserisci");
		Insert_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date DataScelta=DataNascitaDateChooser.getDate();
				
				Controller.InsertProcuratoreInDB(Nome_textField.getText(), Cognome_textField.getText(), (Sesso)Sesso_comboBox.getSelectedItem(),
						DataNascitaDateChooser.getDate(), (Nazione) Nazione_comboBox.getSelectedItem(), (Provincia)Provincia_comboBox.getSelectedItem(), (Comune)Comune_comboBox.getSelectedItem());
				
				}
			});
		Insert_Button.setBounds(426, 258, 89, 23);
		contentPane.add(Insert_Button);
		
		JButton ReturnHomeButton = new JButton("Home");
		ReturnHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.GotoHomePageFromInsertProcuratoreSportivo();
			}
		});
		ReturnHomeButton.setBounds(241, 258, 89, 23);
		contentPane.add(ReturnHomeButton);
		
		JButton RestoreButton = new JButton("Cancella");
		RestoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SvuotaCampi();
			}
		});
		RestoreButton.setBounds(334, 258, 89, 23);
		contentPane.add(RestoreButton);
		
		Nazione_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
					try {
						if(((Nazione) Nazione_comboBox.getSelectedItem()).getCodiceAt().equals("Z000")) {
							ArrayList sql2=new ArrayList();
							sql2=(ArrayList) OggettoConnessione.GetProvinceByNazione(Nazione_comboBox.getItemAt(Nazione_comboBox.getSelectedIndex()));
							JComboBox<Provincia> Tempo_Provincia=new JComboBox(sql2.toArray());
							ProvinciaLabel.setVisible(true);
							Provincia_comboBox.setVisible(true);
							Tempo_Provincia.setSelectedIndex(-1);
							Provincia_comboBox.removeAllItems();
							Iterator i=sql2.iterator();
							while(i.hasNext())
								Provincia_comboBox.addItem((Provincia) i.next());
							ProvinciaButton.setVisible(true);
							ProvinciaButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									ArrayList sql3=new ArrayList();
									try {
										sql3=(ArrayList) OggettoConnessione.GetComuniByProvincia(Provincia_comboBox.getItemAt(Provincia_comboBox.getSelectedIndex()));
										JComboBox<Comune> Tempo_Comune=new JComboBox(sql3.toArray());
										ComuneLabel.setVisible(true);
										Comune_comboBox.setVisible(true);
										Tempo_Comune.setSelectedIndex(-1);
										Comune_comboBox.removeAllItems();
										Iterator i=sql3.iterator();
										while(i.hasNext())
											Comune_comboBox.addItem((Comune) i.next());
										} catch (SQLException e1) {
												JDialog Dialog = new JDialog(); 
												JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
												Dialog.getContentPane().add(LabelJDialog); 
												Dialog.setBounds(400, 150, 250, 200);
												Dialog.setVisible(true);
										} catch (NullPointerException e1) {
												JDialog Dialog = new JDialog(); 
												JLabel LabelJDialog= new JLabel("Inserire una provincia",SwingConstants.CENTER); 
												Dialog.getContentPane().add(LabelJDialog); 
												Dialog.setBounds(400, 150, 250, 200);
												Dialog.setVisible(true);
										}		
								}
							});
						} else {
							ProvinciaLabel.setVisible(false);
							Provincia_comboBox.setVisible(false);
							ComuneLabel.setVisible(false);
							Comune_comboBox.setVisible(false);
							ProvinciaButton.setVisible(false);
						}
					} catch (SQLException e1) {
						JDialog Dialog = new JDialog(); 
			            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			            Dialog.getContentPane().add(LabelJDialog); 
		                Dialog.setBounds(400, 150, 250, 200);
			            Dialog.setVisible(true);
					} catch (NullPointerException e1) {
						JDialog Dialog = new JDialog(); 
						JLabel LabelJDialog= new JLabel("   Inserire una Nazione",SwingConstants.CENTER);
						Dialog.getContentPane().add(LabelJDialog); 
						Dialog.setBounds(400, 150, 150, 70);
						Dialog.setVisible(true);
					}
			}
		});
		
		CalcolaCf_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String TempNome=Nome_textField.getText();
					String TempCognome=Cognome_textField.getText();
					Sesso TempSesso= (Sesso)Sesso_comboBox.getSelectedItem();
					Date DataScelta=DataNascitaDateChooser.getDate();
					LocalDate TempDate=LocalDate.ofInstant(DataScelta.toInstant(), ZoneId.systemDefault());
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					TempDate.format(formatter);
					Nazione TempNazione=(Nazione) Nazione_comboBox.getSelectedItem();
					Provincia TempProvincia=(Provincia)Provincia_comboBox.getSelectedItem();
				    Comune TempComune=(Comune)Comune_comboBox.getSelectedItem();
					
				    Persona TempoPersona;
				    TempoPersona = new Persona(TempNome,TempCognome,TempSesso,TempDate,TempNazione,TempProvincia,TempComune);
					Cf_textField.setText(TempoPersona.getCF());
					
				} catch (EccezioneCF  e1) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Caratteri incompatibili col sistema",SwingConstants.CENTER); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 240, 150);
		            Dialog.setVisible(true);
		            SvuotaCampi();
				} catch (SQLException e2) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("connessione assente",SwingConstants.CENTER); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 250, 200);
		            Dialog.setVisible(true);
				} catch (NullPointerException e3) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Tutti i campi devono essere compilati",SwingConstants.CENTER); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 230, 150);
		            Dialog.setVisible(true);
				}
			}
		});	
		
	}
	
	public void SvuotaCampi() {
		Nome_textField.setText(null);
		Cognome_textField.setText(null);
		Cf_textField.setText(null);
		Sesso_comboBox.setSelectedIndex(-1);
		DataNascitaDateChooser.setCalendar(null);
		Nazione_comboBox.setSelectedIndex(-1);
		Provincia_comboBox.setSelectedIndex(-1);
		Comune_comboBox.setSelectedIndex(-1);
	}
}

