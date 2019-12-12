package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;

/**
 * This is the first frame shown to the user when initialising our tool. It
 * allows the user to import an excel file when selecting the "Import" button. A
 * validation is done at the time of importing to avoid files of different
 * formats (ie. not .xls or .xlsx)
 */
public class PopupUploadFile {

	/** The frame for the file upload GUI */
	private JFrame frame;

	/**
	 * The panel at the center of the frame, asking the user to select an excel file
	 * to import
	 */
	private JPanel panel;

	/** The JButton used for importing the selected excel file */
	private JButton importButton;

	/**
	 * Constructor to generate the frame with the necessary elements for importing
	 * an excel file
	 */
	public PopupUploadFile() {
		frame = new JFrame("Upload File");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createPanel();
		frame.add(panel, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(300, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Creates a panel occupying the whole frame with instructions to import an
	 * excel file and an "Import" button
	 */
	private void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("Choose excel file to import", SwingConstants.CENTER);
		panel.add(label, BorderLayout.CENTER);

		importButton = new JButton("Import");
		panel.add(importButton, BorderLayout.SOUTH);
	}

	/**
	 * Returns the JButton used for importing a file
	 * 
	 * @return JButton importButton
	 */
	public JButton getImportJButton() {
		return importButton;
	}

	/**
	 * Method that allows for the disposal of this frame
	 */
	public void close() {
		frame.dispose();
	}

	/**
	 * Displays a warning with the given message
	 * 
	 * @param message - the message to be displayed on the warning
	 */
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
	}
}
