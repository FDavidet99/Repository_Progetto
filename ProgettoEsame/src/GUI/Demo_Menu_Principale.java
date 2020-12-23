package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Demo_Menu_Principale extends JFrame {

	private JPanel contentPane;
	Controller PrimoController;

	/**
	 * Launch the application.
	 */
//Ci deve essere solo il main del controller	
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Demo_Menu_Principale frame = new Demo_Menu_Principale();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
				PrimoController.GotoFrameInsertAtleta();
			}
		});
		InsertAtleti_Button.setBounds(32, 59, 264, 23);
		contentPane.add(InsertAtleti_Button);
		
		JButton btnNewButton_1 = new JButton("Inserire nuovo procuratore\r\n");
		btnNewButton_1.setBounds(32, 25, 264, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
