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
 * <h1>Pop up Upload File!</h1> This pop up allows to import a file When it's
 * clicked on the Import Button. It is opened the new pop up that it allows to
 * choose a file and import it If it is a valid Excel format!
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more user
 * friendly and it is assumed as a high quality code.
 *
 * /**
 * 
 * @author djsouza
 * @since 2019-11-08
 */
public class Popup_UploadFile {
	private JFrame frame;
	private JPanel panel;
	private JButton import_button;

	/**
	 * This method is used to generate the frame of first pop up File Upload.
	 */
	public Popup_UploadFile() {
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
	 * This method is used to set the label with information and Import button.
	 */
	private void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("Choose excel file to import", SwingConstants.CENTER);
		panel.add(label, BorderLayout.CENTER);

		import_button = new JButton("Import");
		panel.add(import_button, BorderLayout.SOUTH);
	}

	/**
	 * This method is used to communicate the instance of button to MainController.
	 * The MainController is responsible to run the action of button.
	 * 
	 * @return JButton
	 */
	public JButton getImportJButton() {
		return import_button;
	}

	/**
	 * This method is used to close the Upload File Pop up after selecting the file
	 * and the file is a valid Excel format!
	 */
	public void close() {
		frame.dispose();
	}

	/**
	 * This method is used to show a warning message when the selected file isn't a
	 * valid Excel format!
	 */
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
	}
}
