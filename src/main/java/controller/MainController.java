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

public class MainController {
	
	private MainFrame gui;
	
	public void init() {
		Popup_UploadFile m = new Popup_UploadFile();
		JButton import_button = m.getJButton();
		
		import_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO create method for listener
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					
					if(path.endsWith(".xlsx") || path.endsWith(".xls"))
					{
						System.out.println(selectedFile.getAbsolutePath());
						m.close();
						
					}
					else {
						m.displayErrorMessage("Não é um ficheiro Excel!");
					}
					
				}
			}
		});  
		
		// TODO add action listeners to gui
		//gui.getAdd_editButton().addActionListener(e -> {
			// TODO action listener to add/edit btn
	//	});
		//gui.getCheckQualityButton().addActionListener(e -> {
			// TODO action listener to check quality btn
	//	});
	}
}
