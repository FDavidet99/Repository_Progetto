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

public class Demo_Menu_Principale extends JFrame {

	private JPanel contentPane;
	Controller PrimoController;

	/**
	 * Create the frame.
	 * @param controller 
	 */
	public Demo_Menu_Principale(Controller controller) {
		PrimoController=controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton InsertAtleti_Button = new JButton("Inserire nuovo atleta");
		InsertAtleti_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrimoController.GotoFrameInsertAtleta();
				} catch (SQLException e1) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 250, 200);
		            Dialog.setVisible(true);
				}
			}
		});
		InsertAtleti_Button.setBounds(32, 59, 264, 23);
		contentPane.add(InsertAtleti_Button);
		
		JButton btnNewButton_1 = new JButton("Inserire nuovo procuratore");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrimoController.GotoFrameInsertProcuratore();
				} catch (SQLException e1) {
					JDialog Dialog = new JDialog(); 
		            JLabel LabelJDialog= new JLabel("Errore di connessione"); 
		            Dialog.getContentPane().add(LabelJDialog); 
	                Dialog.setBounds(400, 150, 250, 200);
		            Dialog.setVisible(true);
				}
			}
		});
		btnNewButton_1.setBounds(32, 25, 264, 23);
		contentPane.add(btnNewButton_1);
		
		JButton VisualizzaAtletiButton = new JButton("Visualizza atleti\r\n");
		VisualizzaAtletiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrimoController.GoToPageViewAtleti();
			}
		});
		VisualizzaAtletiButton.setBounds(32, 93, 264, 23);
		contentPane.add(VisualizzaAtletiButton);
		
	}
}
