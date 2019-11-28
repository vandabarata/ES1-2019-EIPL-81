package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import main.java.model.Operator;
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
import main.java.model.CodeQualityRule;
import main.java.model.Metric;

/**
 * GUI pop-up responsible for allowing the user to edit and add different rules
 * for visualizing the excel table.
 * 
 * @author Hugo Barroca
 */
public class EditRulePopup {

	private CodeQualityRule rule;
	private ArrayList<String> ruleConditions = new ArrayList<String>();
	private JFrame frame;
	private JTextField nameText;
	private JScrollPane metricsScrollpane;
	private JScrollPane advancedRuleConditionsPane;
	private JComboBox<String> condition;
	private JComboBox<String> value;
	private JComboBox<String> comparison;
	private JTextArea metricText;

	private JButton deleteButton;
	private JButton saveButton;

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
	private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private final int SCREEN_WIDTH = dimension.width;
	private final int SCREEN_HEIGHT = dimension.height;

	/**
	 * Constructs and initializes the GUI pop-up.
	 */
	public EditRulePopup(CodeQualityRule r) {
		rule = r;
		advancedMode = rule.isAdvanced();
		initializePanels();
		frame = new JFrame("Personalized Rules");
		frame.add(createMainPanel());
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(FRAME_X, FRAME_Y));
		frame.setSize(new Dimension(FRAME_X, FRAME_Y));
		frame.setLocation(SCREEN_WIDTH / 2 - (FRAME_X / 2), SCREEN_HEIGHT / 2 - (FRAME_Y / 2));

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
	 * Creates the JPanel responsible for holding the rule's name. In case of
	 * default rule, name is a label because it cannot be edited.
	 */
	private void createNamePanel() {
		JLabel nameLabel = new JLabel("Name: ", SwingConstants.LEFT);
		namePanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		namePanel.add(nameLabel, BorderLayout.CENTER);
		nameText = new JTextField();
		nameText.setText(rule.getName());
		nameText.setMinimumSize(new Dimension(500, 25));
		nameText.setPreferredSize(new Dimension(500, 25));
		if (rule.isDefault()) {
			nameText.setEditable(false);
		}
		namePanel.add(nameText, BorderLayout.EAST);
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

			String text;
			JLabel ruleConditionsTextPaneLabel;

			if (rule.getRule().equals("")) {
				ruleConditionsTextPaneLabel = new JLabel("Add rule conditions as follows: ");

				text = "Enter your text here as follows. Delete anything that doesn't follow the following format, including this helping text: \nIF LOC > 10\nAND LAA == 15";
			}

			else {
				ruleConditionsTextPaneLabel = new JLabel("Rule conditions:");

				text = rule.getRule();
			}
			metricText.setText(text);

			advancedRuleConditionsPane = new JScrollPane(metricText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			metricsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

			String availableMetricsText = new String();
			for (Metric metric : Metric.values()) {
				availableMetricsText = availableMetricsText + metric.name() + " ";
			}
			JLabel availableMetrics = new JLabel("Available metrics: \n" + availableMetricsText);

			metricsPanel.add(advancedRuleConditionsPane, BorderLayout.CENTER);
			metricsPanel.add(ruleConditionsTextPaneLabel, BorderLayout.NORTH);
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
		value = new JComboBox<>();
		for (Metric metric : Metric.values()) {
			value.addItem(metric.name());
		}

		comparison = new JComboBox<>();
		for (Operator comp : Operator.values()) {
			comparison.addItem(comp.getSymbol());
		}

		JTextField threshold = new JTextField("");

		JButton addMetricButton = new JButton("Add");
		addMetricButton.addActionListener(e -> {
			String metric;
			String baseMetric = " " + comparison.getSelectedItem() + " " + threshold.getText() + " ";
			try {
				Integer.parseInt(threshold.getText());
				if (ruleConditions.isEmpty()) {
					metric = "IF " + value.getSelectedItem() + baseMetric;
				} else {
					metric = condition.getSelectedItem() + " " + value.getSelectedItem() + baseMetric;
				}
				ruleConditions.add(metric);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Please check if your threshold input is correct!");
			}
			fillMetricsListPanel();
			setConditionVisibility();
			metricsListPanel.revalidate();
			metricsListPanel.repaint();
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

		basicComplexity.addActionListener(e -> {
			advancedComplexity.setEnabled(true);
			advancedMode = false;
			createMetricsPanel();
			metricsPanel.revalidate();
			metricsPanel.repaint();
			basicComplexity.setEnabled(false);

		});

		advancedComplexity.addActionListener(e -> {
			basicComplexity.setEnabled(true);
			advancedMode = true;
			createMetricsPanel();
			metricsPanel.revalidate();
			metricsPanel.repaint();
			advancedComplexity.setEnabled(false);
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
		deleteButton = new JButton("Delete Rule");
		saveButton = new JButton("Save Rule");

		clearButton.addActionListener(e -> {
			clearMetricsListPanel();
		});

		deleteButton.addActionListener(e -> {
			if (nameText.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please insert a rule name!");
			} else {
				// TODO: Add functionality here.
				JOptionPane.showMessageDialog(null, "Rule has been deleted!");
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

		for (String metric : ruleConditions) {
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
		ruleConditions.clear();
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
		if (!ruleConditions.isEmpty()) {
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
	 * @param message A message to be displayed on an alert
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * @return Returns the popup's main SWING frame.
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * 
	 * @return Returns the JComboBox which holds the conditions for a new metric
	 *         (AND and OR).
	 */
	public JComboBox<String> getCondition() {
		return condition;
	}

	/**
	 * 
	 * @return Returns the JComboBox which holds the values for a new metric (LOC,
	 *         LAA, etc).
	 */
	public JComboBox<String> getValue() {
		return value;
	}

	/**
	 * 
	 * @return Returns the JComboBox which holds the possible comparisons for a new
	 *         metric (>, <, ==, !=).
	 */
	public JComboBox<String> getComparison() {
		return comparison;
	}

	/**
	 * @return Returns the JButton for saving the rule changes
	 */
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	 * @return Returns the JButton for deleting the rule changes
	 */
	public JButton getDeleteButton() {
		return deleteButton;
	}

	public String getRuleName() {
		return nameText.getText();
	}

	public boolean isAdvancedMode() {
		return advancedMode;
	}

	public String getRawRuleConditions() {
		String rawRuleConditions = "";
		if (isAdvancedMode()) {
			rawRuleConditions = metricText.getText().replaceAll("\n", " ");
		} else {
			rawRuleConditions = String.join(" ", ruleConditions);
		}
		return rawRuleConditions;
	}
}
