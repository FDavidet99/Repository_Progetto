package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;

import Controller.ControllerQuery;
import Eccezioni.EccezioneCF;
import Entità.Atleta;
import Entità.ClubSportivo;
import Entità.TipoContratto;
import Entità.Sponsor;
import ImplementationDAO.ImplementationDAO;

public class InsertContratto extends JFrame {

	private JPanel contentPane;
	private final JLabel CompensoLabel = new JLabel("Compenso atleta");
	private JTextField CompensoAtletaTextField;
	private JTextField textField;
	private List<Atleta> QueryAtleti;
	private List<ClubSportivo> QueryClub;
	private List<Sponsor> QuerySponsor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertContratto frame = new InsertContratto();
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
	 * @throws EccezioneCF 
	 */
	public InsertContratto() throws SQLException, EccezioneCF {
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
		
		QueryAtleti=new ArrayList();
	    QueryAtleti=(ArrayList) OggettoConnessione.GetAtleti();
		ArrayList<String> NomiAtleti = new ArrayList<String>();
		for(Atleta a:QueryAtleti)
			NomiAtleti.add(a.getNome()+" "+a.getCognome());
		
		JComboBox AtletaComboBox = new JComboBox(NomiAtleti.toArray());
		AtletaComboBox.setBounds(107, 50, 134, 22);
		AtletaComboBox.setSelectedIndex(-1);
		contentPane.add(AtletaComboBox);
		
		JLabel ClubSportivoLabel = new JLabel("Club");
		ClubSportivoLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		ClubSportivoLabel.setBounds(24, 92, 54, 14);
		contentPane.add(ClubSportivoLabel);
		
		JLabel SponsorLabel = new JLabel("Sponsor");
		SponsorLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		SponsorLabel.setBounds(266, 92, 93, 14);
		contentPane.add(SponsorLabel);
		
		JLabel TipoContrattoLabel = new JLabel("Parti Contratto");
		TipoContrattoLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		TipoContrattoLabel.setBounds(302, 53, 134, 14);
		contentPane.add(TipoContrattoLabel);
		
		JComboBox TipoContrattoComboBox = new JComboBox(TipoContratto.values());
		TipoContrattoComboBox.setBounds(446, 50, 61, 22);
		TipoContrattoComboBox.setSelectedIndex(-1);
		contentPane.add(TipoContrattoComboBox);
		
		QuerySponsor=new ArrayList();
	    QuerySponsor=(ArrayList) OggettoConnessione.GetSponsor();
		ArrayList<String> NomiSponsor = new ArrayList<String>();
		for(Sponsor sponsor:QuerySponsor)
			NomiSponsor.add(sponsor.getNomeSponsor());
		
		JComboBox SponsorComboBox = new JComboBox(NomiSponsor.toArray());
		SponsorComboBox.setBounds(364, 89, 72, 22);
		contentPane.add(SponsorComboBox);
		
		QueryClub=new ArrayList();
	    QueryClub=(ArrayList) OggettoConnessione.GetClubSportivi();
		ArrayList<String> NomiClub = new ArrayList<String>();
		for(ClubSportivo club:QueryClub)
			NomiClub.add(club.getNomeClub());
		
		JComboBox ClubComboBox = new JComboBox(NomiClub.toArray());
		ClubComboBox.setBounds(88, 89, 93, 22);
		contentPane.add(ClubComboBox);
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
		
		textField = new JTextField();
		textField.setBounds(446, 187, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel CompensoProcuratoreLabel = new JLabel("Compenso Procuratore");
		CompensoProcuratoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		CompensoProcuratoreLabel.setBounds(266, 185, 170, 22);
		contentPane.add(CompensoProcuratoreLabel);
	}
}
