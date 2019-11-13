package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import main.java.gui.MainFrame;
import main.java.gui.Popup_UploadFile;

/**
 * <h1>Main Controller</h1> Accepts input and converts it to commands and action
 * for the model or view. In addition to dividing the application into these
 * components, the model–view–controller design defines the interactions between
 * them.
 * <p>
 * <b>Note Model–View–Controller (MVC):</b> The Model is responsible for
 * managing the data of the application. It receives user input from the
 * controller. The View means presentation of the model in a particular format.
 * The controller receives the input, optionally validates it and then passes
 * the input to the model.
 */

public class MainController {
	private MainFrame gui;
	private String path;

	/**
	 * This method is used to initiate the button listener
	 */
	public void init() {
		Popup_UploadFile uploadFile = new Popup_UploadFile();
		JButton import_button = uploadFile.getJButton();
		initImportButtonAction(import_button, uploadFile);

	}

	/**
	 * This method is used to run the action of the Import Button.
	 */
	public void initImportButtonAction(JButton import_button, Popup_UploadFile uploadFile) {
		import_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validateFile(uploadFile);

			}
		});
	}

	/**
	 * This method is used to validate if the selected file is a valid Excel format,
	 * it is started the importing of the file otherwise it's showed a warning
	 * message.
	 */

	public void validateFile(Popup_UploadFile uploadFile) {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			path = selectedFile.getAbsolutePath();

			if (path.endsWith(".xlsx") || path.endsWith(".xls")) {
				System.out.println(selectedFile.getAbsolutePath());
				uploadFile.close();
				// 1º Inserir o código para ler o ficheiro para abrir
				// 2ª Abrir o segundo Popup que irá apresentar o Excel
			} else {
				uploadFile.displayErrorMessage("File selected is not a valid Excel format!");
			}
		}

	}

}
