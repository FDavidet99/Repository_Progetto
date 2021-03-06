package GUI;

import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import Entity.*;
import MyExceptions.EccezioneCF;

public class InsertAtleta extends JFrame {

	private JPanel contentPane;
	private JTextField NomeTextField;
	private JTextField CognomeTextField;
	private JTextField CfTextField;
	private JComboBox SessoComboBox;
	private JDateChooser DataNascitaDateChooser;
	private JComboBox NazioneComboBox;
	private JComboBox ProvinciaComboBox;
	private JComboBox ComuneComboBox;
	Controller controller;

	/**
	 * Create the frame.
	 */
	public InsertAtleta(Controller c) {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label TitoloLabel = new Label();
		TitoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TitoloLabel.setBackground(UIManager.getColor("Panel.background"));
		TitoloLabel.setText("Inserire i dati del nuovo atleta");
		TitoloLabel.setBounds(10, 6, 267, 22);
		contentPane.add(TitoloLabel);
		
		NomeTextField = new JTextField();
		NomeTextField.setBounds(57, 37, 86, 20);
		contentPane.add(NomeTextField);
		NomeTextField.setColumns(10);
		
		Label NomeLabel = new Label();
		NomeLabel.setBackground(UIManager.getColor("Panel.background"));
		NomeLabel.setText("Nome");
		NomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		NomeLabel.setBounds(10, 37, 37, 22);
		contentPane.add(NomeLabel);
		
		Label CognomeLabel = new Label();
		CognomeLabel.setBackground(UIManager.getColor("Panel.background"));
		CognomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CognomeLabel.setText("Cognome\r\n");
		CognomeLabel.setBounds(172, 37, 68, 22);
		contentPane.add(CognomeLabel);
		
		CognomeTextField = new JTextField();
		CognomeTextField.setBounds(240, 37, 86, 20);
		contentPane.add(CognomeTextField);
		CognomeTextField.setColumns(10);
		
		Label SessoLabel = new Label();
		SessoLabel.setBackground(UIManager.getColor("Panel.background"));
		SessoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SessoLabel.setText("Sesso");
		SessoLabel.setBounds(10, 72, 45, 22);
		contentPane.add(SessoLabel);
		
		SessoComboBox = new JComboBox(Sesso.values());
		SessoComboBox.setSelectedIndex(-1);
		SessoComboBox.setBounds(57, 70, 58, 22);
		contentPane.add(SessoComboBox);
		
		Label DataLabel = new Label();
		DataLabel.setText("Data di nascita");
		DataLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DataLabel.setBackground(SystemColor.menu);
		DataLabel.setBounds(170, 70, 93, 22);
		contentPane.add(DataLabel);
		
		DataNascitaDateChooser = new JDateChooser();
		DataNascitaDateChooser.setBounds(269, 74, 115, 20);
		DataNascitaDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataNascitaDateChooser);
		
		Label NazioneLabel = new Label();
		NazioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		NazioneLabel.setBackground(UIManager.getColor("Panel.background"));
		NazioneLabel.setText("Nazione");
		NazioneLabel.setBounds(10, 103, 68, 22);
		contentPane.add(NazioneLabel);
		 
	    ArrayList<Nazione> QueryNazioni = (ArrayList) c.GetDatiComboboxNazioni();
	    ArrayList<String> NomiNazioni = new ArrayList<String>();
		for(Nazione nazione:QueryNazioni)
			NomiNazioni.add(nazione.getNomeNazione());
		
		Label ProvinciaLabel = new Label();
		ProvinciaLabel.setBackground(UIManager.getColor("Panel.background"));
		ProvinciaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ProvinciaLabel.setText("Provincia");
		ProvinciaLabel.setBounds(10, 136, 68, 22);
		contentPane.add(ProvinciaLabel);	
		
		Label ComuneLabel = new Label();
		ComuneLabel.setBackground(UIManager.getColor("Panel.background"));
		ComuneLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ComuneLabel.setText("Comune");
		ComuneLabel.setBounds(10, 170, 58, 22);
		contentPane.add(ComuneLabel);
		
		ComuneComboBox = new JComboBox();
		ComuneComboBox.setBounds(86, 170, 160, 22);
		ComuneComboBox.setSelectedIndex(-1);
		contentPane.add(ComuneComboBox);
		
		JButton NazioneButton = new JButton("Prosegui");
		NazioneButton.setBounds(260, 103, 93, 24);
		contentPane.add(NazioneButton);
		NazioneButton.setVisible(false);
		
		JButton ProvinciaButton = new JButton("Prosegui");
		ProvinciaButton.setBounds(260, 136, 93, 24);
		contentPane.add(ProvinciaButton);
		ProvinciaButton.setVisible(false);
		
		ProvinciaComboBox=new JComboBox();
		ProvinciaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComuneLabel.setVisible(false);
				ComuneComboBox.setVisible(false);
			}
		});
		ProvinciaComboBox.setBounds(84, 136, 160, 22);
		ProvinciaComboBox.setSelectedIndex(-1);
		contentPane.add(ProvinciaComboBox);	
		
		NazioneComboBox = new JComboBox (NomiNazioni.toArray());
		NazioneComboBox.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			ProvinciaLabel.setVisible(false);
    			ProvinciaComboBox.setVisible(false);
    			ComuneLabel.setVisible(false);
    			ComuneComboBox.setVisible(false);
    			ProvinciaButton.setVisible(false);
    			int IndexNazione = NazioneComboBox.getSelectedIndex();
    			if(IndexNazione==-1)
    				return;
				if (controller.NazioneIsItalia(IndexNazione))
					NazioneButton.setVisible(true);
				else
					NazioneButton.setVisible(false);
    		}
    	});
    	NazioneComboBox.setSelectedIndex(-1);
		NazioneComboBox.setBounds(84, 103, 160, 20);
    	contentPane.add(NazioneComboBox);
		
		CfTextField = new JTextField();
		CfTextField.setEditable(false);
		CfTextField.setBounds(10, 202, 239, 20);
		contentPane.add(CfTextField);
		CfTextField.setColumns(10);
		
		JButton CalcolaCf_Button = new JButton("Calcola Codice fiscale");
		CalcolaCf_Button.setBounds(259, 201, 162, 22);
		contentPane.add(CalcolaCf_Button);
		
		JButton Insert_Button = new JButton("Inserisci");
		Insert_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int IndexNazione = NazioneComboBox.getSelectedIndex();
					int IndexProvincia=ProvinciaComboBox.getSelectedIndex();
					int IndexComune=ComuneComboBox.getSelectedIndex();	
					Nazione TempNazione=(controller.GetDatiComboboxNazioni()).get(IndexNazione);
					Provincia TempProvincia=null;
					Comune TempComune=null;
					if(TempNazione.getCodiceAt().equalsIgnoreCase("Z000")) {
						 TempProvincia=(controller.GetDatiComboBoxProvince(IndexNazione)).get(IndexProvincia);
					     TempComune=(controller.GetDatiComboBoxComuni(IndexNazione,IndexProvincia)).get(IndexComune);
					}
					LocalDate TempDate=DataNascitaDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					
					Atleta TempAtleta = new Atleta (NomeTextField.getText(), CognomeTextField.getText(), (Sesso)SessoComboBox.getSelectedItem(),
							TempDate,TempNazione, TempProvincia, TempComune, false);
					controller.InsertAtletaInDB(TempAtleta);
				} catch (NullPointerException | IndexOutOfBoundsException e1) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Tutti i campi devono essere compilati",SwingConstants.CENTER); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 230, 150);
		            Dialog.setVisible(true);
				} catch (EccezioneCF e2) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Errori di inserimento dati",SwingConstants.CENTER); 
	                Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 250, 250, 200);
		            Dialog.setVisible(true); 	
				} catch (SQLException e2) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
	                Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 250, 250, 200);
		            Dialog.setVisible(true);
				}    
			}
		});
		Insert_Button.setBounds(426, 258, 89, 23);
		contentPane.add(Insert_Button);
		
		JButton ReturnHomeButton = new JButton("Home");
		ReturnHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomePageFromInsertAtleta();
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
		
		NazioneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
					try {
						int IndexNazione = NazioneComboBox.getSelectedIndex();
						if (controller.NazioneIsItalia(IndexNazione)) {	
							ArrayList<String> NomiProvince= new ArrayList<String>();
							ArrayList<Provincia> QueryProvince=(ArrayList) controller.GetDatiComboBoxProvince(IndexNazione);
							for(Provincia provincia:QueryProvince)
								NomiProvince.add(provincia.getNome());
							JComboBox TmpProvincia=new JComboBox(NomiProvince.toArray());
							ProvinciaLabel.setVisible(true);
							ProvinciaComboBox.setVisible(true);
							TmpProvincia.setSelectedIndex(-1);
							ProvinciaComboBox.removeAllItems();
							Iterator i=NomiProvince.iterator();
							while(i.hasNext())
								ProvinciaComboBox.addItem(i.next());
							ProvinciaComboBox.setSelectedIndex(-1);
							ProvinciaButton.setVisible(true);
							ProvinciaButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									int IndexProvincia=ProvinciaComboBox.getSelectedIndex();
									try {	
										ArrayList<String> NomiComuni = new ArrayList<String>();
										ArrayList<Comune> QueryComuni=(ArrayList) controller.GetDatiComboBoxComuni(IndexNazione, IndexProvincia);
										for(Comune comune:QueryComuni)
											NomiComuni.add(comune.getNome());
										JComboBox TmpComune=new JComboBox(NomiComuni.toArray());
										ComuneLabel.setVisible(true);
										ComuneComboBox.setVisible(true);
										TmpComune.setSelectedIndex(-1);
										ComuneComboBox.removeAllItems();
										Iterator i=NomiComuni.iterator();
										while(i.hasNext())
											ComuneComboBox.addItem((i.next()));
										ComuneComboBox.setSelectedIndex(-1);
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
							NazioneButton.setVisible(false);
							ProvinciaLabel.setVisible(false);
							ProvinciaComboBox.setVisible(false);
							ComuneLabel.setVisible(false);
							ComuneComboBox.setVisible(false);
							ProvinciaButton.setVisible(false);
						}
					} catch (NullPointerException e1) {
						JDialog Dialog = new JDialog(); 
						JLabel LabelJDialog= new JLabel("Inserire una Nazione",SwingConstants.CENTER);
						Dialog.getContentPane().add(LabelJDialog); 
						Dialog.setBounds(400, 150, 150, 70);
						Dialog.setVisible(true);
					}
			}
		});
								
		CalcolaCf_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int IndexNazione = NazioneComboBox.getSelectedIndex();
					int IndexProvincia=ProvinciaComboBox.getSelectedIndex();
					int IndexComune=ComuneComboBox.getSelectedIndex();
					String TempNome=NomeTextField.getText();
					String TempCognome=CognomeTextField.getText();
					Sesso TempSesso= (Sesso)SessoComboBox.getSelectedItem();
					LocalDate TempDate=DataNascitaDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					Nazione TempNazione=(c.GetDatiComboboxNazioni()).get(IndexNazione);
					Provincia TempProvincia=null;
					Comune TempComune=null;
					if(TempNazione.getCodiceAt().equalsIgnoreCase("Z000")) {
						 TempProvincia=(c.GetDatiComboBoxProvince(IndexNazione)).get(IndexProvincia);
					     TempComune=(c.GetDatiComboBoxComuni(IndexNazione, IndexProvincia)).get(IndexComune);
					}
						
				    Persona TmpPersona;
				    TmpPersona = new Persona(TempNome,TempCognome,TempSesso,TempDate,TempNazione,TempProvincia,TempComune);
					CfTextField.setText(TmpPersona.getCF());
					
				} catch (EccezioneCF  e1) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Caratteri incompatibili col sistema",SwingConstants.CENTER); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 240, 150);
		            Dialog.setVisible(true);
		            SvuotaCampi();
				} catch (SQLException e2) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 250, 200);
		            Dialog.setVisible(true);
				} catch (NullPointerException | IndexOutOfBoundsException e3) {
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
		NomeTextField.setText(null);
		CognomeTextField.setText(null);
		CfTextField.setText(null);
		SessoComboBox.setSelectedIndex(-1);
		DataNascitaDateChooser.setCalendar(null);
		NazioneComboBox.setSelectedIndex(-1);
		ProvinciaComboBox.setSelectedIndex(-1);
		ComuneComboBox.setSelectedIndex(-1);
	}
}

