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


public class Popup_UploadFile {
	private JFrame frame;
	private JPanel panel;
	private JButton import_button;
	
	public Popup_UploadFile() {
		frame = new JFrame("Upload File");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createCentralPanel();
		frame.add(panel, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(300, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	
//TODO: Usar argumento Excel
	private void createCentralPanel() { 
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
	    JLabel label = new JLabel("Choose a file excel to import", SwingConstants.CENTER);
	    panel.add(label, BorderLayout.CENTER );
	    
	    import_button = new JButton("Click Here");
	    panel.add(import_button, BorderLayout.SOUTH);    	    
	}	
	
	public JButton getJButton() {
		return import_button;
	}
	
	public void close() {
		frame.dispose();
	}
	
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(frame,
			    message,
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
	}
}
