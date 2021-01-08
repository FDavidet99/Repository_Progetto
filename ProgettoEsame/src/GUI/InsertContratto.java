package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entit�.Atleta;
import Entit�.ClubSportivo;
import Entit�.Contratto;
import Entit�.ProcuratoreSportivo;
import Entit�.TipoContratto;
import Entit�.Sponsor;
import ImplementationDAO.ImplementationDAO;
import javax.swing.JButton;

public class InsertContratto extends JFrame {

	private JPanel contentPane;
	private final JLabel CompensoAtletaLabel = new JLabel("Compenso atleta\r\n");
	private JTextField CompensoAtletaTextField;
	private JTextField CompensoProcuratoreTextField;
	private JTextField GettonePresenzaNazionaleTextField;
	private List<Atleta> QueryAtleti;
	private List<ClubSportivo> QueryClub;
	private List<Sponsor> QuerySponsor;
	private JComboBox AtletaComboBox;
	private JComboBox TipoContrattoComboBox;
	private JComboBox SponsorComboBox;
	private JComboBox ClubComboBox;
	private JDateChooser DataInizioDateChooser;
	private JDateChooser DataFineDateChooser;
	Controller controller;
	private JTextField textField;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InsertContratto frame = new InsertContratto();
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
	public InsertContratto(Controller c) throws SQLException, EccezioneCF {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();
		
		JLabel AtletaLabel = new JLabel("Atleta");
		AtletaLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		AtletaLabel.setBounds(24, 53, 54, 14);
		contentPane.add(AtletaLabel);
		
		JLabel CompensoAtletaLabel = new JLabel("Compenso Atleta");
		CompensoAtletaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CompensoAtletaLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		CompensoAtletaLabel.setText("<html>Compenso Atleta<br/>totale</html>");
		CompensoAtletaLabel.setBounds(24, 178, 134, 37);
		contentPane.add(CompensoAtletaLabel);
		
		JLabel GettonePresenzaNazionaleLabel = new JLabel("Compenso Atleta");
		GettonePresenzaNazionaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GettonePresenzaNazionaleLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		GettonePresenzaNazionaleLabel.setText("<html>Gettone presenza<br/>nazionale</html>");
		GettonePresenzaNazionaleLabel.setBounds(24, 178, 134, 37);
		GettonePresenzaNazionaleLabel.setVisible(false);
		contentPane.add(GettonePresenzaNazionaleLabel);
		
		CompensoAtletaTextField = new JTextField();
		CompensoAtletaTextField.setBounds(159, 187, 86, 20);
		contentPane.add(CompensoAtletaTextField);
		CompensoAtletaTextField.setColumns(10);
		
		GettonePresenzaNazionaleTextField = new JTextField();
		GettonePresenzaNazionaleTextField.setBounds(159, 187, 86, 20);
		GettonePresenzaNazionaleTextField.setColumns(10);
		GettonePresenzaNazionaleTextField.setVisible(false);
		contentPane.add(GettonePresenzaNazionaleTextField);
		
		textField = new JTextField();
		textField.setBounds(412, 218, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		CompensoProcuratoreTextField = new JTextField();
		CompensoProcuratoreTextField.setBounds(446, 187, 86, 20);
		contentPane.add(CompensoProcuratoreTextField);
		CompensoProcuratoreTextField.setColumns(10);
		
		JLabel CompensoProcuratoreLabel = new JLabel("Compenso Procuratore");
		CompensoProcuratoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CompensoProcuratoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		CompensoProcuratoreLabel.setBounds(276, 178, 160, 37);
		CompensoProcuratoreLabel.setText("<html>Compenso Procuratore<br/>totale</html>");
		contentPane.add(CompensoProcuratoreLabel);
		
		QueryAtleti=new ArrayList();
	    QueryAtleti=(ArrayList) OggettoConnessione.GetAtleti();
		ArrayList<String> NomiAtleti = new ArrayList<String>();
		for(Atleta a:QueryAtleti)
			NomiAtleti.add(a.getNome()+" "+a.getCognome());
		
		AtletaComboBox = new JComboBox(NomiAtleti.toArray());
		AtletaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int IndexAtleta=AtletaComboBox.getSelectedIndex();
				if(IndexAtleta!=-1) {
//					return;
				try {
					ProcuratoreSportivo ProcuratoreAttivo=OggettoConnessione.GetProcuratoreAttivo(QueryAtleti.get(IndexAtleta));
					if( ProcuratoreAttivo == null) {
						CompensoProcuratoreLabel.setVisible(false);
						CompensoProcuratoreTextField.setVisible(false);
						textField.setVisible(false);
					}
					else {
						CompensoProcuratoreLabel.setVisible(true);
						CompensoProcuratoreTextField.setVisible(true);
						textField.setVisible(true);
						textField.setText(ProcuratoreAttivo.getNome() +" "+ ProcuratoreAttivo.getCognome());
					}
				} catch (EccezioneCF e1) {
					JDialog Dialog = new JDialog(); 
			        JLabel LabelJDialog= new JLabel("Errore di inserimento dati",SwingConstants.CENTER); 
			        Dialog.getContentPane().add(LabelJDialog); 
		            Dialog.setBounds(400, 150, 240, 150);
			        Dialog.setVisible(true);
				} catch (SQLException e1) {
					JDialog Dialog = new JDialog(); 
			        JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
			        Dialog.getContentPane().add(LabelJDialog); 
		            Dialog.setBounds(400, 150, 240, 150);
			        Dialog.setVisible(true);
				}			
			}
				}
		});
		AtletaComboBox.setBounds(107, 50, 134, 22);
		AtletaComboBox.setSelectedIndex(-1);
		contentPane.add(AtletaComboBox);
		
		JLabel ClubSportivoLabel = new JLabel("Club");
		ClubSportivoLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		ClubSportivoLabel.setBounds(24, 92, 54, 14);
		ClubSportivoLabel.setVisible(false);
		contentPane.add(ClubSportivoLabel);
		
		JLabel SponsorLabel = new JLabel("Sponsor");
		SponsorLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		SponsorLabel.setBounds(266, 92, 93, 14);
		SponsorLabel.setVisible(false);
		contentPane.add(SponsorLabel);
	
		QuerySponsor=new ArrayList();
	    QuerySponsor=(ArrayList) OggettoConnessione.GetSponsor();
		ArrayList<String> NomiSponsor = new ArrayList<String>();
		for(Sponsor sponsor:QuerySponsor)
			NomiSponsor.add(sponsor.getNomeSponsor());
		
		SponsorComboBox = new JComboBox(NomiSponsor.toArray());
		SponsorComboBox.setBounds(364, 89, 134, 22);
		SponsorComboBox.setSelectedIndex(-1);
		SponsorComboBox.setVisible(false);
		contentPane.add(SponsorComboBox);
		
		QueryClub=new ArrayList();
	    QueryClub=(ArrayList) OggettoConnessione.GetClubSportivi();
		ArrayList<String> NomiClub = new ArrayList<String>();
		for(ClubSportivo club:QueryClub)
			NomiClub.add(club.getNomeClub());
		
		ClubComboBox = new JComboBox(NomiClub.toArray());
		ClubComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int IndexClub=ClubComboBox.getSelectedIndex();
				ClubSportivo club;
				if(IndexClub!=-1) {
					club = QueryClub.get(IndexClub);
					if(club.isIsNazionale()) {
						CompensoAtletaLabel.setVisible(false);
						CompensoAtletaTextField.setVisible(false);
						GettonePresenzaNazionaleLabel.setVisible(true);
						GettonePresenzaNazionaleTextField.setVisible(true);
						textField.setVisible(false);
						CompensoProcuratoreLabel.setVisible(false);
						CompensoProcuratoreTextField.setVisible(false);
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
		ClubComboBox.setBounds(88, 89, 153, 22);
		ClubComboBox.setSelectedIndex(-1);
		ClubComboBox.setVisible(false);
		contentPane.add(ClubComboBox);
		
		JLabel TipoContrattoLabel = new JLabel("Parti Contratto");
		TipoContrattoLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		TipoContrattoLabel.setBounds(302, 53, 134, 14);
		contentPane.add(TipoContrattoLabel);
		
		TipoContrattoComboBox = new JComboBox(TipoContratto.values());
		TipoContrattoComboBox.setBounds(446, 50, 86, 22);
		TipoContrattoComboBox.setSelectedIndex(-1);
		TipoContrattoComboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
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
		DataInizioDateChooser.setBounds(127, 130, 114, 20);
		DataInizioDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataInizioDateChooser);
		
		DataFineDateChooser = new JDateChooser();
		DataFineDateChooser.setBounds(353, 130, 117, 20);
		DataFineDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataFineDateChooser);
		
		JLabel DataInizioLabel = new JLabel("Data inizio\r\n");
		DataInizioLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		DataInizioLabel.setBounds(24, 136, 93, 14);
		contentPane.add(DataInizioLabel);
		
		JLabel DataFineLabel = new JLabel("Data fine");
		DataFineLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		DataFineLabel.setBounds(266, 136, 77, 14);
		contentPane.add(DataFineLabel);
		
		JButton RegistraButton = new JButton("Registra");
		RegistraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {		
					int IndexAtleta=AtletaComboBox.getSelectedIndex();
					LocalDate TempDateInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate TempDateFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				///////////////////////////////////////////////////////	
//					System.out.println(
//							Period.between(TempDateInizio,TempDateFine).getYears());
					//////////////////////////////////////////////////////////////////////////////////
					
					int IndexClub=ClubComboBox.getSelectedIndex();
					int IndexSponsor=SponsorComboBox.getSelectedIndex();
					Sponsor sponsor=null;
					ClubSportivo club=null;
					if(IndexSponsor!=-1)
						sponsor = QuerySponsor.get(IndexSponsor);
					if(IndexClub!=-1)
						club = QueryClub.get(IndexClub);
					double CompAtleta=0.0;
					if(!(CompensoAtletaTextField.getText().isEmpty()))
						CompAtleta=Double.parseDouble(CompensoAtletaTextField.getText());
					double ComProcuratore=0.0;
					if(!(CompensoProcuratoreTextField.getText().isEmpty()))
						ComProcuratore=Double.parseDouble(CompensoProcuratoreTextField.getText());
					double GettonePresenzaNazionale=0.0;
					if(!(GettonePresenzaNazionaleTextField.getText().isEmpty()))
						GettonePresenzaNazionale=Double.parseDouble(GettonePresenzaNazionaleTextField.getText());
					
					Contratto TmpContratto = new Contratto(OggettoConnessione.GetProcuratoreAttivo(QueryAtleti.get(IndexAtleta)),QueryAtleti.get(IndexAtleta), 
							TempDateInizio, TempDateFine, (TipoContratto) TipoContrattoComboBox.getSelectedItem(),club,sponsor,
							CompAtleta,ComProcuratore, GettonePresenzaNazionale);
			    	controller.InsertContratto(TmpContratto);
			    } catch (EccezioneCF e1) {
			    	JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Errore di inserimento dati",SwingConstants.CENTER); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 250, 200);
		            Dialog.setVisible(true);
			    	e1.printStackTrace();
			    } catch (SQLException e1) {
			    	JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Errore di connessione",SwingConstants.CENTER); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 250, 200);
		            Dialog.setVisible(true);
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
		TitoloLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
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
	}
}
