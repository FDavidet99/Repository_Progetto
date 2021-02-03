package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import Entity.Atleta;
import Entity.ClubSportivo;
import Entity.Contratto;
import Entity.ProcuratoreSportivo;
import Entity.Sponsor;
import Entity.TipoContratto;
import javax.swing.JButton;
import javax.swing.UIManager;

public class InsertContratto extends JFrame {

	private JPanel contentPane;
	private final JLabel CompensoAtletaLabel = new JLabel("Compenso atleta\r\n");
	private JTextField CompensoAtletaTextField;
	private JTextField CompensoProcuratoreTextField;
	private JTextField GettonePresenzaNazionaleTextField;
	private JComboBox AtletaComboBox;
	private JComboBox TipoContrattoComboBox;
	private JComboBox SponsorComboBox;
	private JComboBox ClubComboBox;
	private JDateChooser DataInizioDateChooser;
	private JDateChooser DataFineDateChooser;
	private JTextField NomeProcuratoretextField;
	Controller controller;
	
	/**
	 * Create the frame.
	 */
	public InsertContratto(Controller c) {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel AtletaLabel = new JLabel("Atleta");
		AtletaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AtletaLabel.setBounds(24, 52, 54, 20);
		contentPane.add(AtletaLabel);
		
		JLabel CompensoAtletaLabel = new JLabel("Compenso Atleta");
		CompensoAtletaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CompensoAtletaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CompensoAtletaLabel.setText("<html>Compenso Atleta<br/><br/>Totale</html>");
		CompensoAtletaLabel.setBounds(24, 178, 134, 48);
		contentPane.add(CompensoAtletaLabel);
		
		JLabel EuroLabel = new JLabel("\u200E\u20AC");
		EuroLabel.setBounds(191, 209, 16, 14);
		contentPane.add(EuroLabel);
		
		JLabel EuroLabel2 = new JLabel("\u200E\u20AC");
		EuroLabel2.setBounds(422, 206, 16, 20);
		contentPane.add(EuroLabel2);
		EuroLabel2.setVisible(false);
		
		JLabel GettonePresenzaNazionaleLabel = new JLabel("Compenso Atleta");
		GettonePresenzaNazionaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GettonePresenzaNazionaleLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GettonePresenzaNazionaleLabel.setText("<html>Gettone presenza<br/><br/>nazionale</html>");
		GettonePresenzaNazionaleLabel.setBounds(24, 178, 134, 48);
		GettonePresenzaNazionaleLabel.setVisible(false);
		contentPane.add(GettonePresenzaNazionaleLabel);
		
		CompensoAtletaTextField = new JTextField();
		CompensoAtletaTextField.setBounds(95, 206, 86, 20);
		contentPane.add(CompensoAtletaTextField);
		CompensoAtletaTextField.setColumns(10);
		
		GettonePresenzaNazionaleTextField = new JTextField();
		GettonePresenzaNazionaleTextField.setBounds(95, 206, 86, 20);
		GettonePresenzaNazionaleTextField.setColumns(10);
		GettonePresenzaNazionaleTextField.setVisible(false);
		contentPane.add(GettonePresenzaNazionaleTextField);
		
		NomeProcuratoretextField = new JTextField();
		NomeProcuratoretextField.setBackground(UIManager.getColor("MenuBar.shadow"));
		NomeProcuratoretextField.setEditable(false);
		NomeProcuratoretextField.setBounds(441, 179, 117, 20);
		contentPane.add(NomeProcuratoretextField);
		NomeProcuratoretextField.setColumns(10);
		NomeProcuratoretextField.setVisible(false);
		
		CompensoProcuratoreTextField = new JTextField();
		CompensoProcuratoreTextField.setBounds(326, 206, 86, 20);
		contentPane.add(CompensoProcuratoreTextField);
		CompensoProcuratoreTextField.setColumns(10);
		CompensoProcuratoreTextField.setVisible(false);
		
		JLabel CompensoProcuratoreLabel = new JLabel("Compenso Procuratore");
		CompensoProcuratoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CompensoProcuratoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CompensoProcuratoreLabel.setBounds(276, 178, 160, 48);
		CompensoProcuratoreLabel.setText("<html>Compenso del Procuratore : <br/> <br/>Totale : </html>");
		contentPane.add(CompensoProcuratoreLabel);
		CompensoProcuratoreLabel.setVisible(false);
		
		List<Atleta> ElencoAtleti=(ArrayList) controller.GetAtleti();
		ArrayList<String> NomiAtleti = new ArrayList<String>();
		for(Atleta a:ElencoAtleti)
			NomiAtleti.add(a.getNome()+" "+a.getCognome());
		
		AtletaComboBox = new JComboBox(NomiAtleti.toArray());
		AtletaComboBox.setBounds(76, 52, 190, 22);
		AtletaComboBox.setSelectedIndex(-1);
		contentPane.add(AtletaComboBox);
		
		JLabel ClubSportivoLabel = new JLabel("Club");
		ClubSportivoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ClubSportivoLabel.setBounds(24, 92, 29, 20);
		ClubSportivoLabel.setVisible(false);
		contentPane.add(ClubSportivoLabel);
		
		JLabel SponsorLabel = new JLabel("Sponsor");
		SponsorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SponsorLabel.setBounds(24, 92, 54, 20);
		SponsorLabel.setVisible(false);
		contentPane.add(SponsorLabel);
		
		JLabel TipoContrattoLabel = new JLabel("Parti Contratto");
		TipoContrattoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TipoContrattoLabel.setBounds(302, 53, 134, 20);
		contentPane.add(TipoContrattoLabel);
		
		TipoContrattoComboBox = new JComboBox(TipoContratto.values());
		TipoContrattoComboBox.setBounds(392, 50, 86, 22);
		TipoContrattoComboBox.setSelectedIndex(-1);
		TipoContrattoComboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	CompensoProcuratoreLabel.setVisible(false);
		    	NomeProcuratoretextField.setVisible(false);
		    	CompensoProcuratoreTextField.setVisible(false);
		    	EuroLabel2.setVisible(false);
		    	GettonePresenzaNazionaleTextField.setText(null);
		    	CompensoAtletaTextField.setText(null);
				CompensoProcuratoreTextField.setText(null);
		    	TipoContratto tipocontratto=(TipoContratto) TipoContrattoComboBox.getSelectedItem();
		    	if(tipocontratto==null)
		    		return;
				if(tipocontratto.toString().equals("Club")) {
					SponsorComboBox.setVisible(false);
					ClubComboBox.setVisible(true);
					ClubSportivoLabel.setVisible(true);
					SponsorLabel.setVisible(false);	
					SponsorComboBox.setSelectedIndex(-1);	
				}	
				else {	
					SponsorComboBox.setVisible(true);
					ClubComboBox.setVisible(false);
					ClubSportivoLabel.setVisible(false);
					SponsorLabel.setVisible(true);
					int IndexAtleta=AtletaComboBox.getSelectedIndex();
					ClubComboBox.setSelectedIndex(-1);
				}
				GettonePresenzaNazionaleLabel.setVisible(false);
				GettonePresenzaNazionaleTextField.setVisible(false);
				CompensoAtletaLabel.setVisible(true);
				CompensoAtletaTextField.setVisible(true);
		    }
		});
		contentPane.add(TipoContrattoComboBox);

		 DataInizioDateChooser = new JDateChooser();
		 DataInizioDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			 public void propertyChange(PropertyChangeEvent evt) {
				 if(DataFineDateChooser==null || DataInizioDateChooser == null)
					 return;
				 if(DataInizioDateChooser.getCalendar() == null ||DataFineDateChooser.getCalendar() == null)
					 return;
				 LocalDate TempDateInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				 LocalDate TempDateFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				 if(TempDateFine.compareTo(TempDateInizio)<0) {
					 NomeProcuratoretextField.setVisible(false);
						CompensoProcuratoreLabel.setVisible(false);
						CompensoProcuratoreTextField.setVisible(false);
						EuroLabel2.setVisible(false);
						return;	
				 }
				 int IndexAtleta=AtletaComboBox.getSelectedIndex();
				 if(IndexAtleta!=-1) {
					 ProcuratoreSportivo ProcuratoreAttivo=controller.GetProcuratoreAtletaAttivo(ElencoAtleti.get(IndexAtleta),java.sql.Date.valueOf(TempDateInizio),java.sql.Date.valueOf(TempDateFine));
					 if(ProcuratoreAttivo == null) {
						 CompensoProcuratoreLabel.setVisible(false);
						 CompensoProcuratoreTextField.setVisible(false);
						 NomeProcuratoretextField.setVisible(false);
						 EuroLabel2.setVisible(false);
					 }
					 else {	
						 CompensoProcuratoreLabel.setVisible(true);
						 CompensoProcuratoreTextField.setVisible(true);
						 NomeProcuratoretextField.setVisible(true);
						 EuroLabel2.setVisible(true);
						 NomeProcuratoretextField.setText(ProcuratoreAttivo.getNome() +" "+ ProcuratoreAttivo.getCognome());
					 }
				 }
			 }
		});
		DataInizioDateChooser.setBounds(93, 136, 114, 20);
		DataInizioDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataInizioDateChooser);
		
		DataFineDateChooser = new JDateChooser();
		DataFineDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(DataFineDateChooser==null || DataInizioDateChooser == null)
					return;
				if(DataInizioDateChooser.getCalendar() == null ||DataFineDateChooser.getCalendar() == null)
					return;
				LocalDate TempDateInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate TempDateFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if(TempDateFine.compareTo(TempDateInizio)<0) {
					NomeProcuratoretextField.setVisible(false);
					CompensoProcuratoreLabel.setVisible(false);
					CompensoProcuratoreTextField.setVisible(false);
					EuroLabel2.setVisible(false);
					return;
				}	
				int IndexAtleta=AtletaComboBox.getSelectedIndex();
				if(IndexAtleta!=-1) {
					ProcuratoreSportivo ProcuratoreAttivo=controller.GetProcuratoreAtletaAttivo(ElencoAtleti.get(IndexAtleta),java.sql.Date.valueOf(TempDateInizio),java.sql.Date.valueOf(TempDateFine));
					if( ProcuratoreAttivo == null) {
						CompensoProcuratoreLabel.setVisible(false);
						CompensoProcuratoreTextField.setVisible(false);
						NomeProcuratoretextField.setVisible(false);
						EuroLabel2.setVisible(false);
					}
					else {	
						CompensoProcuratoreLabel.setVisible(true);
						CompensoProcuratoreTextField.setVisible(true);
						NomeProcuratoretextField.setVisible(true);
						EuroLabel2.setVisible(true);
						NomeProcuratoretextField.setText(ProcuratoreAttivo.getNome() +" "+ ProcuratoreAttivo.getCognome());
					}
				}
			}
		});
		DataFineDateChooser.setBounds(335, 136, 117, 20);
		DataFineDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataFineDateChooser);
		
		JLabel DataInizioLabel = new JLabel("Data inizio\r\n");
		DataInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DataInizioLabel.setBounds(24, 136, 61, 20);
		contentPane.add(DataInizioLabel);
		
		JLabel DataFineLabel = new JLabel("Data fine");
		DataFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DataFineLabel.setBounds(266, 136, 77, 20);
		contentPane.add(DataFineLabel);
		
		List<Sponsor> ElencoSponsor=(ArrayList) controller.GetSponsorForContratto();
		ArrayList<String> NomiSponsor = new ArrayList<String>();
		for(Sponsor sponsor:ElencoSponsor)
			NomiSponsor.add(sponsor.getNomeSponsor());
		
		SponsorComboBox = new JComboBox(NomiSponsor.toArray());
		SponsorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NomeProcuratoretextField.setVisible(false);
				CompensoProcuratoreLabel.setVisible(false);
				CompensoProcuratoreTextField.setVisible(false);
				EuroLabel2.setVisible(false);
				CompensoAtletaTextField.setText(null);
				CompensoProcuratoreTextField.setText(null);
				GettonePresenzaNazionaleTextField.setText(null);
				DataInizioDateChooser.setCalendar(null);
				DataFineDateChooser.setCalendar(null);
			}
		});
		SponsorComboBox.setBounds(76, 89, 180, 22);
		SponsorComboBox.setSelectedIndex(-1);
		SponsorComboBox.setVisible(false);
		contentPane.add(SponsorComboBox);
		
		List<ClubSportivo> ElencoClub=(ArrayList)controller.GetClubForContratto();
		ArrayList<String> NomiClub = new ArrayList<String>();
		for(ClubSportivo club:ElencoClub)
			NomiClub.add(club.getNomeClub());
		
		ClubComboBox = new JComboBox(NomiClub.toArray());
		ClubComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompensoAtletaTextField.setText(null);
				CompensoProcuratoreTextField.setText(null);
				GettonePresenzaNazionaleTextField.setText(null);
				DataInizioDateChooser.setCalendar(null);
				DataFineDateChooser.setCalendar(null);
				int IndexClub=ClubComboBox.getSelectedIndex();
				ClubSportivo club;
				if(IndexClub!=-1) {
					club = ElencoClub.get(IndexClub);
					if(club.isIsNazionale()) {
						CompensoAtletaLabel.setVisible(false);
						CompensoAtletaTextField.setVisible(false);
						GettonePresenzaNazionaleLabel.setVisible(true);
						GettonePresenzaNazionaleTextField.setVisible(true);
						NomeProcuratoretextField.setVisible(false);
						CompensoProcuratoreLabel.setVisible(false);
						CompensoProcuratoreTextField.setVisible(false);
						EuroLabel2.setVisible(false);
					}
					else {
						CompensoAtletaLabel.setVisible(true);
						CompensoAtletaTextField.setVisible(true);
						GettonePresenzaNazionaleLabel.setVisible(false);
						GettonePresenzaNazionaleTextField.setVisible(false);
					}
				}
			}
		});
		ClubComboBox.setBounds(76, 89, 250, 22);
		ClubComboBox.setSelectedIndex(-1);
		ClubComboBox.setVisible(false);
		contentPane.add(ClubComboBox);
		
		AtletaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataInizioDateChooser.setCalendar(null);
				DataFineDateChooser.setCalendar(null);
				TipoContrattoComboBox.setSelectedIndex(-1);
				CompensoAtletaTextField.setText(null);
				CompensoProcuratoreTextField.setText(null);
				ClubComboBox.setSelectedIndex(-1);
				SponsorComboBox.setSelectedIndex(-1);
				GettonePresenzaNazionaleTextField.setText(null);
				NomeProcuratoretextField.setText(null);
				CompensoProcuratoreLabel.setVisible(false);
				CompensoProcuratoreTextField.setVisible(false);
				NomeProcuratoretextField.setVisible(false);
				EuroLabel2.setVisible(false);
			}
		});
			
		JButton RegistraButton = new JButton("Registra");
		RegistraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {			
					int IndexAtleta=AtletaComboBox.getSelectedIndex();
					LocalDate TempDateInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate TempDateFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();					
					int IndexClub=ClubComboBox.getSelectedIndex();
					int IndexSponsor=SponsorComboBox.getSelectedIndex();
					Sponsor sponsor=null;
					ClubSportivo club=null;
					if(IndexSponsor!=-1)
						sponsor = ElencoSponsor.get(IndexSponsor);
					if(IndexClub!=-1)
						club = ElencoClub.get(IndexClub);
					double CompAtleta=0.0;
					if(!(CompensoAtletaTextField.getText().isEmpty()))
						CompAtleta=Double.parseDouble(CompensoAtletaTextField.getText());
					double ComProcuratore=0.0;
					if(!(CompensoProcuratoreTextField.getText().isEmpty()))
						ComProcuratore=Double.parseDouble(CompensoProcuratoreTextField.getText());
					double GettonePresenzaNazionale=0.0;
					if(!(GettonePresenzaNazionaleTextField.getText().isEmpty()))
						GettonePresenzaNazionale=Double.parseDouble(GettonePresenzaNazionaleTextField.getText());
					
					Contratto TmpContratto = new Contratto(controller.GetProcuratoreAtletaAttivo(ElencoAtleti.get(IndexAtleta),java.sql.Date.valueOf(TempDateInizio),java.sql.Date.valueOf(TempDateFine)),ElencoAtleti.get(IndexAtleta), 
							TempDateInizio, TempDateFine, (TipoContratto) TipoContrattoComboBox.getSelectedItem(),club,sponsor,
							CompAtleta,ComProcuratore, GettonePresenzaNazionale);
			    	controller.InsertContratto(TmpContratto);
			    } catch (NullPointerException | IndexOutOfBoundsException e1) {
					JDialog Dialog = new JDialog(); 
					JLabel LabelJDialog= new JLabel("Tutti i campi devono essere compilati",SwingConstants.CENTER);
		            Dialog.getContentPane().add(LabelJDialog); 
		            Dialog.setBounds(400, 150, 230, 150);
		            Dialog.setVisible(true);
			    } catch (NumberFormatException e1) {
					JDialog Dialog = new JDialog(); 
					JLabel LabelJDialog= new JLabel("Cifra non compatibile con i dati ",SwingConstants.CENTER);
		            Dialog.getContentPane().add(LabelJDialog); 
		            Dialog.setBounds(400, 150, 230, 150);
		            Dialog.setVisible(true);
			    }
			}
		});
		RegistraButton.setBounds(504, 257, 89, 23);
		contentPane.add(RegistraButton);
		
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomeFromPageInsertContratto();
			}
		});
		HomeButton.setBounds(310, 257, 89, 23);
		contentPane.add(HomeButton);
		
		JButton RestoreButton = new JButton("Cancella");
		RestoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SvuotaCampi();
			}
		});
		RestoreButton.setBounds(409, 257, 89, 23);
		contentPane.add(RestoreButton);
		
		JLabel TitoloLabel = new JLabel("Inserire nuovo contratto");
		TitoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TitoloLabel.setBounds(24, 11, 192, 18);
		contentPane.add(TitoloLabel);
		
	}
	
	public void SvuotaCampi() {
		AtletaComboBox.setSelectedIndex(-1);
		DataInizioDateChooser.setCalendar(null);
		DataFineDateChooser.setCalendar(null);
		TipoContrattoComboBox.setSelectedIndex(-1);
		CompensoAtletaTextField.setText(null);
		CompensoProcuratoreTextField.setText(null);
		ClubComboBox.setSelectedIndex(-1);
		SponsorComboBox.setSelectedIndex(-1);
		GettonePresenzaNazionaleTextField.setText(null);
		NomeProcuratoretextField.setText(null);
	}
}
