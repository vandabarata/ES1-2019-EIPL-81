/**
 * 
 */
package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import main.java.model.QualityIndicator;

/**
 * The Code Quality Rules Results Frame will display a table with the results
 * for running the CodeQualityRules against the methods data uploaded by the
 * user.
 */
public class QualityRulesResultFrame {

	/** Frame that displays the results of the code quality rules and tools */
	private JFrame qualityFrame;

	/** Panel in the center of the frame where the results are presented */
	private JPanel centralPanel;

	/** Table with the rules and tools results for code quality */
	private JTable resultsTable;

	/** Panel in the bottom of the frame presenting the quality indicators stats */
	private JPanel southPanel;

	/**
	 * qualityIndicator - Object responsible for calculating the quality indicators
	 * such as DCI, DII, ADCI and ADII
	 */
	private QualityIndicator qualityIndicators;

	/** Constants that help with dimensioning and positioning the frame */
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	private static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int SCREEN_WIDTH = dimension.width;
	private static final int SCREEN_HEIGHT = dimension.height;

	/**
	 * Frame that presents the results for code quality, based on the tools, rules
	 * and given indicators
	 */
	public QualityRulesResultFrame() {
		buildFrame();
	}

	/**
	 * Displays the table with the results, as well as the quality indicators
	 * 
	 * @param data              - An Array of String arrays where which line is a
	 *                          row in the results table, and each column is the
	 *                          value of that result line for that column
	 * @param colNames          - An array of strings with the column names for each
	 *                          column of the data parameter
	 * @param qualityIndicators - A model holding the needed quality indicators
	 *                          after performing calculations for the DCI, DII, ADCI
	 *                          and ADII stats
	 */
	public void fillFrame(String[][] data, String[] colNames, QualityIndicator qualityIndicators) {
		resultsTable = new JTable(data, colNames);
		this.qualityIndicators = qualityIndicators;
		updateCentralPanel();
		updateSouthPanel();
	}

	/**
	 * Displays the QualityRulesResultFrame
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
		qualityFrame.dispose();
	}

	/**
	 * Updates the rule results whenever the frame is (re)created
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
	 * Fills a panel with a grid layout of 3 by 5 with JLabels. Each one of the
	 * JLabels is added accordingly with the layout pretended and displays the
	 * quality indicators extracted from reading the excel file.
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

		fileResultsPanel.add(new JLabel("custom_is_long_method"));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getCustomLongDCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getCustomLongDII())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getCustomLongADCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getCustomLongADII())));

		fileResultsPanel.add(new JLabel("custom_is_feature_envy"));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getCustomEnvyDCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getCustomEnvyDII())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getCustomEnvyADCI())));
		fileResultsPanel.add(new JLabel(String.valueOf(qualityIndicators.getCustomEnvyADII())));
	}

	/**
	 * Builds the main frame for QualityRulesResultFrame and positions it in the
	 * center of the screen
	 */
	private void buildFrame() {
		qualityFrame = new JFrame();
		qualityFrame.setTitle("Quality Code Results");
		qualityFrame.setLayout(new BorderLayout(5, 5));

		Dimension windowSize = new Dimension(WIDTH, HEIGHT);
		qualityFrame.setMinimumSize(windowSize);
		qualityFrame.setLocation(SCREEN_WIDTH / 2 - (WIDTH / 2), SCREEN_HEIGHT / 2 - (HEIGHT / 2));
	}

	/**
	 * Updates the content of the frame with the new results
	 */
	public void updateFrame() {
		updateCentralPanel();
		updateSouthPanel();
		show();
	}
}
