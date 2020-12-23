package GUI;

import java.util.ArrayList;

import java.util.Date;
import java.util.Properties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.desktop.UserSessionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JTable;

import java.util.Iterator;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Controller.ControllerQuery;
import EccezioniPersona.EccezioneCF;
import Entit�.*;
import ImplementationDAO.ImplementationDAO;

import java.awt.event.ActionEvent;
import com.toedter.components.JLocaleChooser;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Insert_Atleta extends JFrame {
	
	private JPanel contentPane;
	pri
		Cf_textField.setText(null);
		Sesso_comboBox.setSelectedIndex(0);
		dateChooser.removeAll();
		Nazione_comboBox.setSelectedIndex(0);
		Provincia_comboBox.setSelectedIndex(0);
		Comune_comboBox.setSelectedIndex(0);
	}
}
