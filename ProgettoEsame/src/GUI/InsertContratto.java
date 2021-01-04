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
import Entità.Atleta;
import Entità.ClubSportivo;
import Entità.Contratto;
import Entità.TipoContratto;
import Entità.Sponsor;
import ImplementationDAO.ImplementationDAO;
import javax.swing.JButton;

public class InsertContratto extends JFrame {

	private JPanel contentPane;
	private final JLabel CompensoLabel = new JLabel("Compenso atleta");
	private JTextField CompensoAtletaTextField;
	private JTextField CompensoProcuratoreField;
	private List<Atleta> QueryAtleti;
	private List<ClubSportivo> QueryClub;
	private List<Sponsor> QuerySponsor;
	Controller controller;

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
		setBounds(100, 100, 629, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImplementationDAO OggettoConnessione = ControllerQuery.getInstance().getDAO();
		
		JLabel AtletaLabel = new JLabel("Atleta");
		AtletaLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		AtletaLabel.setBounds(24, 53, 54, 14);
		contentPane.add(AtletaLabel);
		
		CompensoProcuratoreField = new JTextField();
		CompensoProcuratoreField.setBounds(446, 187, 86, 20);
		contentPane.add(CompensoProcuratoreField);
		CompensoProcuratoreField.setColumns(10);
		
		JLabel CompensoProcuratoreLabel = new JLabel("Compenso Procuratore");
		CompensoProcuratoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		CompensoProcuratoreLabel.setBounds(266, 185, 170, 22);
		contentPane.add(CompensoProcuratoreLabel);
		
		QueryAtleti=new ArrayList();
	    QueryAtleti=(ArrayList) OggettoConnessione.GetAtleti();
		ArrayList<String> NomiAtleti = new ArrayList<String>();
		for(Atleta a:QueryAtleti)
			NomiAtleti.add(a.getNome()+" "+a.getCognome());
		
		JComboBox AtletaComboBox = new JComboBox(NomiAtleti.toArray());
		AtletaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int IndexAtleta=AtletaComboBox.getSelectedIndex();
				if(IndexAtleta==-1)
					return;
				try {
					if(OggettoConnessione.GetProcuratoreAttivo(QueryAtleti.get(IndexAtleta)) == null) {
						CompensoProcuratoreLabel.setVisible(false);
						CompensoProcuratoreField.setVisible(false);
					}
					else {
						CompensoProcuratoreLabel.setVisible(true);
						CompensoProcuratoreField.setVisible(true);
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
		
		JComboBox SponsorComboBox = new JComboBox(NomiSponsor.toArray());
		SponsorComboBox.setBounds(364, 89, 72, 22);
		SponsorComboBox.setSelectedIndex(-1);
		SponsorComboBox.setVisible(false);
		contentPane.add(SponsorComboBox);
		
		QueryClub=new ArrayList();
	    QueryClub=(ArrayList) OggettoConnessione.GetClubSportivi();
		ArrayList<String> NomiClub = new ArrayList<String>();
		for(ClubSportivo club:QueryClub)
			NomiClub.add(club.getNomeClub());
		
		JComboBox ClubComboBox = new JComboBox(NomiClub.toArray());
		ClubComboBox.setBounds(88, 89, 93, 22);
		ClubComboBox.setSelectedIndex(-1);
		ClubComboBox.setVisible(false);
		contentPane.add(ClubComboBox);
		
		JLabel TipoContrattoLabel = new JLabel("Parti Contratto");
		TipoContrattoLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		TipoContrattoLabel.setBounds(302, 53, 134, 14);
		contentPane.add(TipoContrattoLabel);
		
		JComboBox TipoContrattoComboBox = new JComboBox(TipoContratto.values());
		TipoContrattoComboBox.setBounds(446, 50, 61, 22);
		TipoContrattoComboBox.setSelectedIndex(-1);
		TipoContrattoComboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
				if(TipoContrattoComboBox.getSelectedItem().toString().equals("Club")) {
					SponsorComboBox.setVisible(false);
					ClubComboBox.setVisible(true);
					ClubSportivoLabel.setVisible(true);
					SponsorLabel.setVisible(false);	
				}	
				else {	
					SponsorComboBox.setVisible(true);
					ClubComboBox.setVisible(false);
					ClubSportivoLabel.setVisible(false);
					SponsorLabel.setVisible(true);
				}
		    }
		});
		contentPane.add(TipoContrattoComboBox);
		
		CompensoLabel.setBounds(24, 185, 125, 22);
		contentPane.add(CompensoLabel);
		CompensoLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		CompensoAtletaTextField = new JTextField();
		CompensoAtletaTextField.setBounds(159, 187, 86, 20);
		contentPane.add(CompensoAtletaTextField);
		CompensoAtletaTextField.setColumns(10);
		
		JDateChooser DataInizioDateChooser = new JDateChooser();
		DataInizioDateChooser.setBounds(127, 130, 70, 20);
		DataInizioDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataInizioDateChooser);
		
		JDateChooser DataFineDateChooser = new JDateChooser();
		DataFineDateChooser.setBounds(353, 130, 70, 20);
		DataFineDateChooser.setDateFormatString("yyyy/MM/dd");
		contentPane.add(DataFineDateChooser);
		
		JLabel DataInizioLabel = new JLabel("Data inizio\r\n");
		DataInizioLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		DataInizioLabel.setBounds(24, 136, 93, 14);
		contentPane.add(DataInizioLabel);
		
		JLabel lblDataFine = new JLabel("Data fine");
		lblDataFine.setFont(new Font("Monospaced", Font.PLAIN, 13));
		lblDataFine.setBounds(266, 136, 77, 14);
		contentPane.add(lblDataFine);
		
		
		
		JButton RegistraButton = new JButton("Registra");
		RegistraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int IndexAtleta=AtletaComboBox.getSelectedIndex();
				LocalDate TempDateInizio=DataInizioDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate TempDateFine=DataFineDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int IndexClub=ClubComboBox.getSelectedIndex();
				int IndexSponsor=SponsorComboBox.getSelectedIndex();
				Sponsor sponsor=null;
				ClubSportivo club=null;
				if(IndexSponsor!=-1)
					sponsor = QuerySponsor.get(IndexSponsor);
				if(IndexClub!=-1)
					club = QueryClub.get(IndexClub);
				Contratto TmpContratto;
			    try {
			    	TmpContratto = new Contratto(OggettoConnessione.GetProcuratoreAttivo(QueryAtleti.get(IndexAtleta)),QueryAtleti.get(IndexAtleta), 
			    			TempDateInizio, TempDateFine, (TipoContratto) TipoContrattoComboBox.getSelectedItem(),club,sponsor,
			    			10.00, 10.00);
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
			    }	
			}
		});
		RegistraButton.setBounds(443, 235, 89, 23);
		contentPane.add(RegistraButton);
		
		JButton HomeButton = new JButton("Home");
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoHomeFromPageInsertContratto();
			}
		});
		HomeButton.setBounds(292, 235, 89, 23);
		contentPane.add(HomeButton);
		
	}
}
