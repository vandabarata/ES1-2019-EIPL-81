/**
 * 
 */
package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author franciele.faccin
 *
 */
public class QualityRulesResultFrame {
	private JFrame qualityFrame;
	private JPanel centralPanel;
	private JTable resultsTable;
	

	// Window Dimensions
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	private static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int SCREEN_WIDTH = dimension.width;
	private static final int SCREEN_HEIGHT = dimension.height;
	
	public QualityRulesResultFrame() {
		buildFrame();
	}
	
	
	public void fillTable(Object[][] data, Object []colNames) {
		resultsTable = new JTable(data, colNames);
		updateCentralPanel();
	}
	
	public void show() {
		qualityFrame.repaint();
		qualityFrame.setVisible(true);
	}
	
	public void hide() {
		qualityFrame.setVisible(false);
	}
	
	private void updateCentralPanel() { 
		centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout());
		JScrollPane tableScrollPane = new JScrollPane(resultsTable);
		centralPanel.add(tableScrollPane);
		qualityFrame.add(centralPanel, BorderLayout.CENTER);
	}
	
	private void buildFrame() {
		qualityFrame = new JFrame();
		qualityFrame.setTitle("Quality Code Results"); 
		qualityFrame.setLayout(new BorderLayout(5,5));

		Dimension windowSize = new Dimension(WIDTH, HEIGHT);
		qualityFrame.setMinimumSize(windowSize);
		qualityFrame.setLocation(SCREEN_WIDTH / 2 - (WIDTH / 2), SCREEN_HEIGHT / 2 - (HEIGHT / 2));
	}
}
