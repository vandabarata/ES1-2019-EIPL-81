package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import main.java.controller.Metric;

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

	private JPanel mainPanel;
	private JPanel namePanel;
	private JPanel metricsPanel;
	private JPanel editorComplexityTogglePanel;
	private JPanel metricsListPanel;
	private JPanel addNewMetricPanel;
	private JPanel controlPanel;
	private JPanel centerPanel;

	private boolean advancedMode;

	private final int FRAME_X = 685;
	private final int FRAME_Y = 300;

	/**
	 * Constructs and initializes the GUI pop-up.
	 */
	public EditRulePopup() {
		advancedMode = false;
		frame = new JFrame("Personalized Rules");
		frame.add(createMainPanel());
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(FRAME_X, FRAME_Y));
		frame.setSize(new Dimension(FRAME_X, FRAME_Y));
		frame.setVisible(true);
	}

	/**
	 * @return Returns the JPanel where all other JPanels are nested.
	 */
	private JPanel createMainPanel() {
//TODO
		metricsPanel = new JPanel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		createNamePanel();
		createCenterPanel();
		createControlPanel();
		mainPanel.add(namePanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		return mainPanel;
	}

	/**
	 * @return Returns the JPanel responsible for holding the rule's name.
	 */
	private JPanel createNamePanel() {
		namePanel = new JPanel();
		JLabel nameLabel = new JLabel("Name: ", SwingConstants.LEFT);
		namePanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		nameText = new JTextField();
		nameText.setMinimumSize(new Dimension(500, 25));
		nameText.setPreferredSize(new Dimension(500, 25));
		namePanel.add(nameLabel, BorderLayout.CENTER);
		namePanel.add(nameText, BorderLayout.EAST);
		return namePanel;
	}

	private JPanel createCenterPanel() {
		createMetricsPanel();
		createEditorComplexityTogglePanel();
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(metricsPanel, BorderLayout.CENTER);
		centerPanel.add(editorComplexityTogglePanel, BorderLayout.EAST);
		return centerPanel;
	}

	/**
	 * @return Returns the JPanel responsible for holding both the current list of
	 *         rule metrics, and the line which allows the addition of more metrics.
	 */
	private JPanel createMetricsPanel() {
		metricsListPanel = new JPanel();
		if (advancedMode) {
			metricsPanel.removeAll();
			JTextPane metricText = new JTextPane();
			metricsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			metricsPanel.add(metricText);
		} else {
			metricsPanel.removeAll();
			metricsListPanel.setLayout(new BoxLayout(metricsListPanel, BoxLayout.Y_AXIS));
			metricsListPanel.setMinimumSize(new Dimension(500, 500));
			metricsScrollpane = new JScrollPane(metricsListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			addNewMetricPanel = new JPanel();
			createAddMetricPanel();
			metricsPanel.setLayout(new BorderLayout());
			metricsPanel.add(addNewMetricPanel, BorderLayout.NORTH);
			metricsPanel.add(metricsScrollpane, BorderLayout.CENTER);
		}
		return metricsPanel;
	}

	/**
	 * 
	 * @return Returns the JPanel holding the line which allows users to add new
	 *         metrics into the metrics list.
	 */
	private JPanel createAddMetricPanel() {
		addNewMetricPanel.removeAll();
		addNewMetricPanel.setLayout(new GridLayout(1, 6));
		condition = new JComboBox<>();
		setConditionVisibility();
		JLabel ifCondition = new JLabel("IF", SwingConstants.CENTER);
		JComboBox<String> value = new JComboBox<>();
		for (Metric metric : Metric.values()) {
			value.addItem(metric.name());
		}

		JComboBox<String> comparison = new JComboBox<>();
		comparison.addItem(">");
		comparison.addItem("<");
		comparison.addItem("=");
		comparison.addItem("!=");

		JTextField threshold = new JTextField("");

		JButton addMetricButton = new JButton("Add");
		addMetricButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String metric;
				try {
					Integer.parseInt(threshold.getText());
					if (ruleMetrics.isEmpty()) {
						metric = "IF " + value.getSelectedItem() + " " + comparison.getSelectedItem() + " "
								+ threshold.getText();
					} else {
						metric = condition.getSelectedItem() + " " + value.getSelectedItem() + " "
								+ comparison.getSelectedItem() + " " + threshold.getText();
					}
					ruleMetrics.add(metric);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please check if your threshold input is correct!");
				}

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

	// TODO:
	private JPanel createEditorComplexityTogglePanel() {
		editorComplexityTogglePanel = new JPanel();
		editorComplexityTogglePanel.setLayout(new BoxLayout(editorComplexityTogglePanel, BoxLayout.Y_AXIS));
		JButton basicComplexity = new JButton("Basic");
		JButton advancedComplexity = new JButton("Advanced");
		basicComplexity.setAlignmentX(Component.CENTER_ALIGNMENT);
		advancedComplexity.setAlignmentX(Component.CENTER_ALIGNMENT);
		basicComplexity.setPreferredSize(advancedComplexity.getPreferredSize());
		basicComplexity.setMaximumSize(advancedComplexity.getMaximumSize());
		basicComplexity.setMinimumSize(advancedComplexity.getMinimumSize());

		basicComplexity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				advancedMode = false;
				createMetricsPanel();
				metricsPanel.revalidate();
				metricsPanel.repaint();
			}
		});

		advancedComplexity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				advancedMode = true;
				createMetricsPanel();
				metricsPanel.revalidate();
				metricsPanel.repaint();

			}
		});
		editorComplexityTogglePanel.add(basicComplexity);
		editorComplexityTogglePanel.add(advancedComplexity);
		return editorComplexityTogglePanel;
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
					// TODO: Add functionality here.
				}
			}
		});

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please insert a rule name!");
				} else {
					// TODO: Add functionality here.
				}
			}
		});

		controlPanel.add(clearButton);
		controlPanel.add(deleteButton);
		controlPanel.add(saveButton);
		return controlPanel;

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
	 * list. Basically, it stops the first metric, and the first metric only, from
	 * having an AND or an OR attached to it.
	 */
	private void setConditionVisibility() {
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

	/**
	 * @return Returns the popup's main SWING frame.
	 */
	public JFrame getFrame() {
		return frame;
	}
}
