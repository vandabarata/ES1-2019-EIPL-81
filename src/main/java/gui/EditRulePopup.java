package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import main.java.model.Comparator;
import main.java.model.Condition;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.model.Metric;

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
	private JScrollPane advancedMetricsScrollpane;
	private JComboBox<String> condition;
	private JTextArea metricText;

	private JPanel mainPanel;
	private JPanel namePanel;
	private JPanel metricsPanel;
	private JPanel editorComplexityTogglePanel;
	private JPanel metricsListPanel;
	private JPanel addNewMetricPanel;
	private JPanel controlPanel;
	private JPanel centerPanel;

	private boolean advancedMode;
	private boolean conditionVisibilitySet;

	private final int FRAME_X = 685;
	private final int FRAME_Y = 300;

	/**
	 * Constructs and initializes the GUI pop-up.
	 */
	public EditRulePopup() {
		advancedMode = false;
		initializePanels();
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
		JLabel nameLabel = new JLabel("Name: ", SwingConstants.LEFT);
		namePanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		nameText = new JTextField();
		nameText.setMinimumSize(new Dimension(500, 25));
		nameText.setPreferredSize(new Dimension(500, 25));
		namePanel.add(nameLabel, BorderLayout.CENTER);
		namePanel.add(nameText, BorderLayout.EAST);
		return namePanel;
	}

	/**
	 * @return Returns the JPanel responsible for holding either both the Metrics
	 *         panel, which allows edits to the current metrics, and the complexity
	 *         toggle panel, which allows changing in between the basic and advanced
	 *         modes.
	 */
	private JPanel createCenterPanel() {
		createMetricsPanel();
		createEditorComplexityTogglePanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(metricsPanel, BorderLayout.CENTER);
		centerPanel.add(editorComplexityTogglePanel, BorderLayout.EAST);
		return centerPanel;
	}

	/**
	 * @return Returns the JPanel responsible for holding both the current list of
	 *         rule metrics, and the line which allows the addition of more metrics.
	 *         In advanced mode, this panels hold a JTextArea, which can be freely
	 *         edited by the user.
	 */
	private JPanel createMetricsPanel() {
		if (advancedMode) {
			metricsPanel.removeAll();
			metricsPanel.setLayout(new BorderLayout());
			JLabel metricsTextPaneLabel = new JLabel("Add metrics as follows: ");

			String text = new String();
			for (String line : ruleMetrics) {
				text = text + line + '\n';
			}

			String availableMetricsText = new String();
			for (Metric metric : Metric.values()) {
				availableMetricsText = availableMetricsText + metric.name() + " ";
			}
			if (text.isEmpty()) {
				text = "Enter your text here as follows. Delete anything that doesn't follow the following format, including this helping text: \nIF LOC > 10\nAND LAA == 15";
			}
			metricText.setText(text);
			advancedMetricsScrollpane = new JScrollPane(metricText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			metricsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

			JLabel availableMetrics = new JLabel("Available metrics: \n" + availableMetricsText);

			metricsPanel.add(advancedMetricsScrollpane, BorderLayout.CENTER);
			metricsPanel.add(metricsTextPaneLabel, BorderLayout.NORTH);
			metricsPanel.add(availableMetrics, BorderLayout.SOUTH);
		} else {
			metricsPanel.removeAll();
			metricsListPanel.setLayout(new BoxLayout(metricsListPanel, BoxLayout.Y_AXIS));
			metricsListPanel.setMinimumSize(new Dimension(500, 500));
			metricsScrollpane = new JScrollPane(metricsListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			createAddMetricPanel();
			metricsPanel.setLayout(new BorderLayout());
			metricsPanel.add(addNewMetricPanel, BorderLayout.NORTH);
			metricsPanel.add(metricsScrollpane, BorderLayout.CENTER);
			fillMetricsListPanel();
			setConditionVisibility();
			metricsListPanel.revalidate();
			metricsListPanel.repaint();
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
		for (Comparator comp : Comparator.values()) {
			comparison.addItem(comp.getSymbol());
		}

		JTextField threshold = new JTextField("");

		JButton addMetricButton = new JButton("Add");
		addMetricButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String metric;
				String baseMetric = " " + comparison.getSelectedItem() + " " + threshold.getText() + " ";

				try {
					Integer.parseInt(threshold.getText());
					if (ruleMetrics.isEmpty()) {
						metric = "IF " + value.getSelectedItem() + baseMetric;
					} else {
						metric = condition.getSelectedItem() + " " + value.getSelectedItem() + baseMetric;
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

	/**
	 * 
	 * @return Returns the JPanel which holds the buttons to switch between advanced
	 *         and basic modes.
	 */
	private JPanel createEditorComplexityTogglePanel() {
		editorComplexityTogglePanel.setLayout(new BoxLayout(editorComplexityTogglePanel, BoxLayout.Y_AXIS));
		JButton basicComplexity = new JButton("Basic");
		JButton advancedComplexity = new JButton("Advanced");
		basicComplexity.setAlignmentX(Component.CENTER_ALIGNMENT);
		advancedComplexity.setAlignmentX(Component.CENTER_ALIGNMENT);
		basicComplexity.setPreferredSize(advancedComplexity.getPreferredSize());
		basicComplexity.setMaximumSize(advancedComplexity.getMaximumSize());
		basicComplexity.setMinimumSize(advancedComplexity.getMinimumSize());
		basicComplexity.setEnabled(false);

		basicComplexity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				advancedComplexity.setEnabled(true);
				advancedMode = false;
				createMetricsPanel();
				metricsPanel.revalidate();
				metricsPanel.repaint();
				basicComplexity.setEnabled(false);
			}
		});

		advancedComplexity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basicComplexity.setEnabled(true);
				advancedMode = true;
				createMetricsPanel();
				metricsPanel.revalidate();
				metricsPanel.repaint();
				advancedComplexity.setEnabled(false);

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
					JOptionPane.showMessageDialog(null, "Rule has been deleted!");
				}
			}
		});

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please insert a rule name!");
				} else {
					if (advancedMode) {
						ruleMetrics.clear();
						for (String aString : metricText.getText().split("\n")) {
							aString.replaceAll("\n", " ");
							ruleMetrics.add(aString);
						}
					}
					// TODO: Add functionality here. The method getJavascriptString() should be used
					// here as necessary.
					JOptionPane.showMessageDialog(null, "Rule has been added successfuly!");
				}
			}
		});

		controlPanel.add(clearButton);
		controlPanel.add(deleteButton);
		controlPanel.add(saveButton);
		return controlPanel;

	}

	/**
	 * Initializes all the JPanels at once.
	 */
	private void initializePanels() {
		mainPanel = new JPanel();
		namePanel = new JPanel();
		metricsPanel = new JPanel();
		editorComplexityTogglePanel = new JPanel();
		metricsListPanel = new JPanel();
		addNewMetricPanel = new JPanel();
		controlPanel = new JPanel();
		centerPanel = new JPanel();
		metricText = new JTextArea();
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
		metricText.setText("");
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
		if (!ruleMetrics.isEmpty()) {
			condition.setVisible(true);
			if (!conditionVisibilitySet)
				for (Condition cond : Condition.values()) {
					condition.addItem(cond.toString());
					conditionVisibilitySet = true;
				}
		} else {
			condition.setVisible(false);
			condition.setSelectedItem("");
		}
	}

	/**
	 * @return Returns the popup's main SWING frame.
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * 
	 * @return Returns a javascript-ready string for evaluation.
	 */
	public String getJavascriptString() {
		String javascriptString = "";
		for (String metricString : ruleMetrics) {
			javascriptString = javascriptString + metricString;
		}
		javascriptString = javascriptString.replaceAll("IF", "").replaceAll("AND", "&&").replaceAll("OR", "||");
		return javascriptString;
	}
}
