/**
 * 
 */
package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import main.java.model.QualityIndicator;

/**
 * <h1>Code Quality Rules Results Frame</h1>
 * This pop up will display a table with the results for running the CodeQualityRules
 * against the methods data uploaded by the user.
 *
 *
 * 
 * @author Franciele Faccin
 * @since 2019-11-18
 */
public class QualityRulesResultFrame {
	private JFrame qualityFrame;
	private JPanel centralPanel;
	private JTable resultsTable;
	private JPanel southPanel;
	/** qualityIndicator - Object responsible for calculating the quality indicators such as DCI, DII, ADCI and ADII */
	private QualityIndicator qualityIndicators;

	// Window Dimensions
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	private static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int SCREEN_WIDTH = dimension.width;
	private static final int SCREEN_HEIGHT = dimension.height;

	public QualityRulesResultFrame() {
		buildFrame();
	}

	/**
	 * @param data     An Array of String arrays where which line is a row in the
	 *                 results table, and each column is the value of that result
	 *                 line for that column
	 * @param colNames An array of strings with the column names for each column of
	 *                 the data param
	 */
	public void fillFrame(String[][] data, Object[] colNames, QualityIndicator qualityIndicators) {
		resultsTable = new JTable(data, colNames);
		this.qualityIndicators = qualityIndicators;
		updateCentralPanel();
		updateSouthPanel();
	}

	/**
	 * Display the QualityRulesResultFrame in the screen
	 */
	public void show() {
		qualityFrame.repaint();
		qualityFrame.setVisible(true);
	}

	/**
	 * Closes the QualityRulesResultFrame
	 */
	public void hide() {
		qualityFrame.setVisible(false);
	}

	/**
	 * Updates the results table to the frame
	 */
	private void updateCentralPanel() {
		centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout());
		JScrollPane tableScrollPane = new JScrollPane(resultsTable);
		centralPanel.add(tableScrollPane);
		qualityFrame.add(centralPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Updates the quality indicators panel
	 */
	private void updateSouthPanel() {
		southPanel = new JPanel();
		southPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
		southPanel.setLayout(new BorderLayout(5, 5));

		JPanel fileResultsPanel = new JPanel();
		fileResultsPanel.setLayout(new GridLayout(5, 5, 5, 5));

		addContentToFileResultsforPanel(fileResultsPanel);
		southPanel.add(fileResultsPanel, BorderLayout.CENTER);
		qualityFrame.add(southPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * This method fills a panel with a grid layout of 3 by 5 with JLabels. Each one
	 * of the JLabels is added accordingly with the layout pretended and displays
	 * the quality indicator correspondent extracted from reading the excel file.
	 * 
	 * @param fileResultsPanel- the panel used to add the JLabels and display the
	 *                          results.
	 */
	private void addContentToFileResultsforPanel(JPanel fileResultsPanel) {
		fileResultsPanel.add(new JLabel(""));
		fileResultsPanel.add(new JLabel("DCI"));
		fileResultsPanel.add(new JLabel("DII"));
		fileResultsPanel.add(new JLabel("ADCI"));
		fileResultsPanel.add(new JLabel("ADII"));
		fileResultsPanel.add(new JLabel("iPlasma"));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getIPlasmaDCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getIPlasmaDII())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getIPlasmaADCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getIPlasmaADII())));
		fileResultsPanel.add(new JLabel("PMD"));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getPMDDCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getPMDDII())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getPMDADCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getPMDADII())));
		//TODO change this with the correct values for custom methods
		fileResultsPanel.add(new JLabel("custom_is_long_method"));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getIPlasmaDCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getIPlasmaDII())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getIPlasmaADCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getIPlasmaADII())));
		fileResultsPanel.add(new JLabel("custom_is_feature_envy"));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getPMDDCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getPMDDII())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getPMDADCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getPMDADII())));
	}

	/**
	 * Builds the main frame for QualityRulesResultFrame and positions it in the
	 * centre of the screen
	 */
	private void buildFrame() {
		qualityFrame = new JFrame();
		qualityFrame.setTitle("Quality Code Results");
		qualityFrame.setLayout(new BorderLayout(5, 5));

		Dimension windowSize = new Dimension(WIDTH, HEIGHT);
		qualityFrame.setMinimumSize(windowSize);
		qualityFrame.setLocation(SCREEN_WIDTH / 2 - (WIDTH / 2), SCREEN_HEIGHT / 2 - (HEIGHT / 2));
	}
	
}
