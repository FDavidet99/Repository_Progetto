package GUI;

import javax.swing.JFrame;

public class FrameForJDialog extends JFrame {
	Controller Controller;
	/**
	 * Create the frame.
	 */
	public FrameForJDialog(Controller c) {
		Controller=c;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 559, 131);
		getContentPane().setLayout(null);
		
	}
}
