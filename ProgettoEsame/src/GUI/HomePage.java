package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomePage extends JFrame {

	private JPanel contentPane;
	Controller controller;

	/**
	 * Create the frame.
	 * @param controller 
	 */
	public static void main(String[] args) {
		new HomePage(new Controller());
	}
	
	public HomePage(Controller c) {
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton InsertAtletiButton = new JButton("Inserire nuovo atleta");
		InsertAtletiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoFrameInsertAtleta();
			}
		});
		InsertAtletiButton.setBounds(85, 87, 264, 23);
		contentPane.add(InsertAtletiButton);
		
		JButton InsertProcuratoreButton = new JButton("Inserire nuovo procuratore");
		InsertProcuratoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoFrameInsertProcuratore();
			}
		});
		InsertProcuratoreButton.setBounds(85, 53, 264, 23);
		contentPane.add(InsertProcuratoreButton);
		
		JButton ViewAtletiButton = new JButton("Visualizza atleti");
		ViewAtletiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GoToPageViewAtleti();
			}
		});
		ViewAtletiButton.setBounds(85, 121, 264, 23);
		contentPane.add(ViewAtletiButton);
		
		JButton InsertIngaggioButton = new JButton("Aggiungi Ingaggio");
		InsertIngaggioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controller.GoToInsertIngaggio();	
			}
		});
		InsertIngaggioButton.setBounds(85, 189, 264, 23);
		contentPane.add(InsertIngaggioButton);
		
		JButton ViewProcuratoriButton = new JButton("Visualizza Procuratori");
		ViewProcuratoriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GoToViewProcuratori();
			}
		});
		ViewProcuratoriButton.setBounds(85, 155, 264, 23);
		contentPane.add(ViewProcuratoriButton);
		
		JButton InsertContrattoButton = new JButton("Registra Contratto");
		InsertContrattoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GoToPageInsertContrattoInDB();
			}
		});
		InsertContrattoButton.setBounds(85, 223, 264, 23);
		contentPane.add(InsertContrattoButton);
		
		JLabel TitoloLabel = new JLabel("Gestione atleti e procuratori");
		TitoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TitoloLabel.setBounds(10, 11, 414, 23);
		contentPane.add(TitoloLabel);
		
	}
}
