package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.event.*;

/**
 * GUI pop-up responsible for allowing the user to edit and add different rules
 * for visualizing the excel table.
 * 
 * @author Hugo Barroca
 */
public class EditRulePopup {
	private ArrayList<String> ruleMetrics = new ArrayList<String>();
	private JFrame frame;
	private JTextField nameText;
	private JScrollPane metricsScrollpane;
	private JComboBox<String> condition;

	private JPanel namePanel;
	private JPanel metricsPanel;
	private JPanel metricsListPanel;
	private JPanel addNewMetricPanel;
	private JPanel controlPanel;

	private final int FRAME_X = 685;
	private final int FRAME_Y = 300;

	/**
	 * Constructs and initializes the GUI pop-up.
	 */
	public EditRulePopup() {
		frame = new JFrame("Personalized Rules");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(createMainPanel());
		frame.setLocationRelativeTo(null);
		frame.setSize(new Dimension(FRAME_X, FRAME_Y));
		frame.setVisible(true);
	}

	/**
	 * 
	 * @return Returns the popup's main SWING frame.
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @return Returns the JPanel where all other JPanels are nested.
	 */
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		createNamePanel();
		createMetricsPanel();
		createControlPanel();
		mainPanel.add(namePanel, BorderLayout.NORTH);
		mainPanel.add(metricsPanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		return mainPanel;
	}

	/**
	 * @return Returns the JPanel responsible for holding the rule's name.
	 */
	private JPanel createNamePanel() {
		namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 2));
		JLabel nameLabel = new JLabel("Name: ", SwingConstants.LEFT);
		nameText = new JTextField();
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		return namePanel;
	}

	/**
	 * @return Returns the JPanel responsible for holding both the current list of
	 *         rule metrics, and the line which allows the addition of more metrics.
	 */
	private JPanel createMetricsPanel() {
		metricsListPanel = new JPanel();
		metricsListPanel.setLayout(new BoxLayout(metricsListPanel, BoxLayout.Y_AXIS));
		metricsScrollpane = new JScrollPane(metricsListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		fillMetricsListPanel();

		addNewMetricPanel = new JPanel();
		createAddMetricPanel();

		metricsPanel = new JPanel();
		metricsPanel.setLayout(new BorderLayout());
		metricsPanel.add(addNewMetricPanel, BorderLayout.NORTH);
		metricsPanel.add(metricsScrollpane, BorderLayout.CENTER);
		return metricsPanel;
	}

	/**
	 * @return Returns the JPanel responsible for holding the JButtons which allow
	 *         all metrics to be cleared, or the rule to be saved.
	 */
	private JPanel createControlPanel() {
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 3));

		JButton clearButton = new JButton("Clear Metrics");
		JButton deleteButton = new JButton("Delete Rule");
		JButton saveButton = new JButton("Save Rule");

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMetricsListPanel();
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please insert a rule name!");
				} else {
					// Add functionality here.
				}
			}
		});

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please insert a rule name!");
				} else {
					// Add functionality here.
				}
			}
		});

		controlPanel.add(clearButton);
		controlPanel.add(deleteButton);
		controlPanel.add(saveButton);
		return controlPanel;

	}

	/**
	 * TODO: The currently * hard-coded values of the JBoxes are to be replaced with
	 * the correct, final values once the ENUMs are created.
	 * 
	 * @return Returns the JPanel holding the line which allows users to add new
	 *         metrics into the metrics list.
	 */
	public JPanel createAddMetricPanel() {
		addNewMetricPanel.removeAll();
		addNewMetricPanel.setLayout(new GridLayout(1, 6));
		condition = new JComboBox<>();
		setConditionVisibility();
		JLabel ifCondition = new JLabel("IF", SwingConstants.CENTER);
		JComboBox<String> value = new JComboBox<>();
		value.addItem("LOC");
		value.addItem("MET");

		JComboBox<String> comparison = new JComboBox<>();
		comparison.addItem(">");
		comparison.addItem("<");
		comparison.addItem("=");
		comparison.addItem("!=");

		JTextField threshold = new JTextField("10");

		JButton addMetricButton = new JButton("Add");
		addMetricButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String metric;
				try {
					Integer.parseInt(threshold.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please check if your threshold input is correct!");
				}
				if (ruleMetrics.isEmpty()) {
					metric = "IF " + value.getSelectedItem() + " " + comparison.getSelectedItem() + " "
							+ threshold.getText();
				} else {
					metric = condition.getSelectedItem() + " " + value.getSelectedItem() + " "
							+ comparison.getSelectedItem() + " " + threshold.getText();
				}
				ruleMetrics.add(metric);
				fillMetricsListPanel();
				setConditionVisibility();
				metricsListPanel.revalidate();
				metricsListPanel.repaint();
			}
		});

		addNewMetricPanel.add(condition);
		addNewMetricPanel.add(ifCondition);
		addNewMetricPanel.add(value);
		addNewMetricPanel.add(comparison);
		addNewMetricPanel.add(threshold);
		addNewMetricPanel.add(addMetricButton);
		addNewMetricPanel.setPreferredSize(new Dimension(650, 25));
		addNewMetricPanel.setMaximumSize(new Dimension(650, 25));
		return addNewMetricPanel;

	}

	/**
	 * This method fills the metric's list in the UI with the contents found in the
	 * ruleMetrics ArrayList.
	 * 
	 * TODO: This method will have to be changed once a separate class for the rules
	 * is created.
	 */
	private void fillMetricsListPanel() {
		metricsListPanel.removeAll();

		for (String metric : ruleMetrics) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 2));
			JLabel metricLabel = new JLabel(metric);
			metricLabel.setHorizontalAlignment(JLabel.CENTER);
			panel.add(metricLabel);
			metricsListPanel.add(panel);
		}

		metricsListPanel.revalidate();
		metricsListPanel.repaint();
	}

	/**
	 * This method clears all current metrics from both the GUI and the ruleMetrics
	 * ArrayList.
	 * 
	 */
	private void clearMetricsListPanel() {
		ruleMetrics.clear();
		metricsListPanel.removeAll();
		setConditionVisibility();
		metricsListPanel.revalidate();
		metricsListPanel.repaint();
	}

	/**
	 * This method handles setting the visibility of the Condition button in the
	 * line responsible for allowing the user to add new metrics to the metrics'
	 * list.
	 */
	public void setConditionVisibility() {
		if (ruleMetrics.isEmpty()) {
			condition.setVisible(false);
			condition.setSelectedItem("");
		} else {
			condition.setVisible(true);
			// TODO: This two lines prevent multiple ANDs and ORs from being introduced. A
			// better solution may exist, but this issue should be resolved once ENUMs are
			// added.
			condition.removeItem("AND ");
			condition.removeItem("OR ");
			condition.addItem("AND ");
			condition.addItem("OR ");
		}
	}
}
