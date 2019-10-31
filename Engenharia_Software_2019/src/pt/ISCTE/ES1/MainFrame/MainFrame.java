package pt.ISCTE.ES1.MainFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class MainFrame {
	private JFrame mainFrame;
	private JPanel centralPanel;
	private JPanel southPanel;


	public MainFrame(){ //receber por argumento o ficheiro excel
		mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addContants();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void addContants() {
		createCentralPanel();
		mainFrame.add(centralPanel, BorderLayout.CENTER);
		createSouthPanel();
		mainFrame.add(southPanel, BorderLayout.SOUTH);
	}

	private void createSouthPanel() {
		southPanel= new JPanel();
		southPanel.setLayout(new BorderLayout());
		JPanel fileResultsPanel = new JPanel();
		fileResultsPanel.setLayout(new GridLayout(3,5));
		addContentToFileResultsforPanel(fileResultsPanel);
		southPanel.add(fileResultsPanel, BorderLayout.CENTER);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2,1));;
		addContentToButtonsPanel(buttonsPanel);
		southPanel.add(buttonsPanel, BorderLayout.EAST);
		}

	private void addContentToButtonsPanel(JPanel buttonsPanel) {
		JButton add_editButton = new JButton("Add/Edit rule");
		buttonsPanel.add(add_editButton);
		add_editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO create method for listener
			}
		});		
		JButton checkQualityButton = new JButton("Check quality");
		buttonsPanel.add(checkQualityButton);
		add_editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO create method for listener
			}
		});
	}

	//This Method must receive The results of the excel  
	private void addContentToFileResultsforPanel(JPanel fileResultsPanel) {
		fileResultsPanel.add(new JLabel(""));
		fileResultsPanel.add(new JLabel("DCI"));
		fileResultsPanel.add(new JLabel("DII"));
		fileResultsPanel.add(new JLabel("ADCI"));
		fileResultsPanel.add(new JLabel("ADII"));
		fileResultsPanel.add(new JLabel("     iPlasma  "));
		fileResultsPanel.add(new JLabel("a"));
		fileResultsPanel.add(new JLabel("b"));
		fileResultsPanel.add(new JLabel("c"));
		fileResultsPanel.add(new JLabel("d"));
		fileResultsPanel.add(new JLabel("     PMD"));
		fileResultsPanel.add(new JLabel("e"));
		fileResultsPanel.add(new JLabel("f"));
		fileResultsPanel.add(new JLabel("g"));
		fileResultsPanel.add(new JLabel("h"));
	}

	private void createCentralPanel() { //deve utilizar o argumento excel
		centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout());
		JScrollPane excelScroolPane = new JScrollPane();
		centralPanel.add(excelScroolPane);
	}
	
}
