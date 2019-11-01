package Popup_FileUpload;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;


public class Popup_UploadFile {
	private JFrame frame;
	private JPanel panel;

	public Popup_UploadFile() {
		frame = new JFrame("Upload File");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addContents();
		frame.setPreferredSize(new Dimension(300, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void addContents() {
		createCentralPanel();
		frame.add(panel, BorderLayout.CENTER);
	}
	
//TODO: Usar argumento Excel
	private void createCentralPanel() { 
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
	    JLabel label = new JLabel("Choose a file excel to import", SwingConstants.CENTER);
	    panel.add(label, BorderLayout.CENTER );
	    JButton b = new JButton("Click Here");
	    panel.add(b, BorderLayout.SOUTH);
	    
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO create method for listener
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					frame.dispose();
				}
			}
		});  
	}	
}
