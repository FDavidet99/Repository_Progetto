package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton InsertAtleti_Button = new JButton("Inserire nuovo atleta");
		InsertAtleti_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoFrameInsertAtleta();
			}
		});
		InsertAtleti_Button.setBounds(32, 59, 264, 23);
		contentPane.add(InsertAtleti_Button);
		
		JButton btnNewButton_1 = new JButton("Inserire nuovo procuratore");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GotoFrameInsertProcuratore();
			}
		});
		btnNewButton_1.setBounds(32, 25, 264, 23);
		contentPane.add(btnNewButton_1);
		
		JButton VisualizzaAtletiButton = new JButton("Visualizza atleti");
		VisualizzaAtletiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GoToPageViewAtleti();
			}
		});
		VisualizzaAtletiButton.setBounds(32, 93, 264, 23);
		contentPane.add(VisualizzaAtletiButton);
		
		JButton InsertIngaggioButton = new JButton("Aggiungi Ingaggio");
		InsertIngaggioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controller.GoToInsertIngaggio();	
			}
		});
		InsertIngaggioButton.setBounds(32, 161, 264, 23);
		contentPane.add(InsertIngaggioButton);
		
		JButton btnNewButton = new JButton("Visualizza Procuratori");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GoToViewProcuratori();
			}
		});
		btnNewButton.setBounds(32, 127, 264, 23);
		contentPane.add(btnNewButton);
		
		JButton InsertContrattoButton = new JButton("Registra Contratto");
		InsertContrattoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.GoToPageInsertContratto();
			}
		});
		InsertContrattoButton.setBounds(32, 195, 264, 23);
		contentPane.add(InsertContrattoButton);
		
	}
}
