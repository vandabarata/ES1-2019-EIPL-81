package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.controller.MainController;
import main.java.model.CodeQualityRule;
import main.java.model.Condition;
import main.java.model.Metric;
import main.java.model.Operator;

/**
 * GUI pop-up responsible for allowing the user to edit and add different rules
 * for visualizing the excel table.
 * 
 * @author Hugo Barroca, Vanda Barata and Franciele Faccin
 */
public class EditRulePopup {

	private CodeQualityRule rule;
	private ArrayList<String> ruleConditions = new ArrayList<String>();
	private JFrame frame;
	private JTextField nameText;
	private JComboBox<String> conditionListBox;
	private JComboBox<String> valueListBox;
	private JComboBox<String> operatorListBox;
	private JTextArea ruleTextArea;

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
	private boolean defaultRule;
	private boolean conditionVisibilitySet;

	/**
	 * Constructs and initializes the GUI pop-up. It opens the Basic or Advanced
	 * Mode depending on the rule it's using.
	 */
	public EditRulePopup(CodeQualityRule r) {
		rule = r;
		advancedMode = rule.isAdvanced();
		defaultRule = rule.isDefault();
		initializePanels();
		frame = new JFrame("Personalized Rules");
		frame.add(createMainPanel());
		frame.setLocationRelativeTo(null);
		final int FRAME_X = 685;
		final int FRAME_Y = 300;
		frame.setMinimumSize(new Dimension(FRAME_X, FRAME_Y));
		frame.setSize(new Dimension(FRAME_X, FRAME_Y));
		final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_WIDTH = dimension.width;
		final int SCREEN_HEIGHT = dimension.height;
		frame.setLocation(SCREEN_WIDTH / 2 - (FRAME_X / 2), SCREEN_HEIGHT / 2 - (FRAME_Y / 2));

		frame.setVisible(true);

	}

	/**
	 * Returns the JPanel where all other JPanels are nested
	 * 
	 * @return JPanel
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
	 * default rule, name is a non editable text field because it cannot be changed.
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
	 * Returns the JPanel responsible for holding either both the Metrics panel,
	 * which allows edits to the current metrics, and the complexity toggle panel,
	 * which allows changing in between the basic and advanced modes.
	 * 
	 * @return JPanel
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
	 * Returns the JPanel responsible for holding both the current list of rule
	 * conditions, and the line which allows the addition of more conditions. In
	 * advanced mode, this panel holds a JTextArea, which can be freely edited by
	 * the user.
	 * 
	 * @return JPanel
	 */
	private JPanel createMetricsPanel() {
		if (advancedMode || defaultRule || rule.getName() != "") {

			metricsPanel.removeAll();
			metricsPanel.setLayout(new BorderLayout());

			String text;
			JLabel ruleConditionsTextPaneLabel;

			if (rule.getRule().isEmpty()) {
				ruleConditionsTextPaneLabel = new JLabel("Add rule conditions as follows: ");

				text = "Delete anything that doesn't follow the following format, including this helping text: \nLOC > 10\nAND LAA == 15";
			}

			else {
				ruleConditionsTextPaneLabel = new JLabel("Rule conditions:");
				String parsedRule = rule.getRule().replaceAll("\\&\\&", "AND").replaceAll("\\|\\|", "OR");
				text = parsedRule;
			}

			ruleTextArea.setText(text);

			final JScrollPane advancedRuleConditionsPane = new JScrollPane(ruleTextArea,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

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
			final JScrollPane metricsScrollpane = new JScrollPane(metricsListPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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
	 * Returns the JPanel holding the line which allows users to add new metrics
	 * into the metrics list. For basic mode.
	 * 
	 * @return JPanel
	 */
	private JPanel createAddMetricPanel() {
		addNewMetricPanel.removeAll();
		addNewMetricPanel.setLayout(new GridLayout(1, 6));
		conditionListBox = new JComboBox<>();
		setConditionVisibility();
		JLabel ifCondition = new JLabel("IF", SwingConstants.CENTER);

		valueListBox = new JComboBox<>();
		for (Metric metric : Metric.values()) {
			valueListBox.addItem(metric.name());
		}

		operatorListBox = new JComboBox<>();
		for (Operator comp : Operator.values()) {
			operatorListBox.addItem(comp.getSymbol());
		}

		JTextField threshold = new JTextField("");

		JButton addMetricButton = new JButton("Add");
		addMetricButton.addActionListener(e -> {
			String metric;
			String baseMetric = " " + operatorListBox.getSelectedItem() + " " + threshold.getText() + " ";
			try {
				Integer.parseInt(threshold.getText());
				if (ruleConditions.isEmpty()) {
					metric = "IF " + valueListBox.getSelectedItem() + baseMetric;
				} else {
					metric = conditionListBox.getSelectedItem() + " " + valueListBox.getSelectedItem() + baseMetric;
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

		addNewMetricPanel.add(conditionListBox);
		addNewMetricPanel.add(ifCondition);
		addNewMetricPanel.add(valueListBox);
		addNewMetricPanel.add(operatorListBox);
		addNewMetricPanel.add(threshold);
		addNewMetricPanel.add(addMetricButton);
		addNewMetricPanel.setPreferredSize(new Dimension(650, 25));
		addNewMetricPanel.setMaximumSize(new Dimension(650, 25));
		return addNewMetricPanel;

	}

	/**
	 * 
	 * Returns the JPanel which holds the buttons to switch between advanced and
	 * basic modes.
	 * 
	 * @return JPanel
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
			if (!(rule.isAdvanced() || rule.isDefault() || rule.getName() != "")) {
				advancedComplexity.setEnabled(true);
				advancedMode = false;
				createMetricsPanel();
				metricsPanel.revalidate();
				metricsPanel.repaint();
				basicComplexity.setEnabled(false);
			}

		});

		advancedComplexity.addActionListener(e -> {
			if (rule.isAdvanced() || rule.isDefault() || rule.getName() == "") {
				basicComplexity.setEnabled(false);
			} else {
				basicComplexity.setEnabled(true);
			}
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
	 * Returns the JPanel responsible for holding the JButtons which allow all
	 * metrics to be cleared, or the rule to be saved.
	 * 
	 * @return JPanel
	 */
	private JPanel createControlPanel() {
		controlPanel.setLayout(new GridLayout(1, 3));

		JButton clearButton = new JButton("Clear Metrics");
		deleteButton = new JButton("Delete Rule");
		saveButton = new JButton("Save Rule");

		clearButton.addActionListener(e -> {
			clearMetricsListPanel();
		});

		controlPanel.add(clearButton);
		controlPanel.add(deleteButton);
		controlPanel.add(saveButton);
		return controlPanel;

	}

	/**
	 * Initialises all the JPanels at once.
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
		ruleTextArea = new JTextArea();
	}

	/**
	 * This method fills the metric's list in the UI with the contents found in the
	 * ruleMetrics ArrayList.
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
	 */
	private void clearMetricsListPanel() {
		ruleConditions.clear();
		ruleTextArea.setText("");
		metricsListPanel.removeAll();
		setConditionVisibility();
		metricsListPanel.revalidate();
		metricsListPanel.repaint();
	}

	/**
	 * This method handles setting the visibility of the Condition button in the
	 * line responsible for allowing the user to add new metrics to the metrics'
	 * list. Basically, it stops the first condition, and the first condition only,
	 * from having an AND or an OR attached to it.
	 */
	private void setConditionVisibility() {
		if (!ruleConditions.isEmpty()) {
			conditionListBox.setVisible(true);
			if (!conditionVisibilitySet)
				for (Condition cond : Condition.values()) {
					conditionListBox.addItem(cond.toString());
					conditionVisibilitySet = true;
				}
		} else {
			conditionListBox.setVisible(false);
			conditionListBox.setSelectedItem("");
		}
	}

	/**
	 * Method that generates an alert with the received message
	 * 
	 * @param message
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Returns the popup's main SWING frame.
	 * 
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * 
	 * Returns the JComboBox which holds the conditions for a new metric (AND and
	 * OR).
	 * 
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getCondition() {
		return conditionListBox;
	}

	/**
	 * 
	 * eturns the JComboBox which holds the values for a new metric (LOC, LAA, etc).
	 * 
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getValue() {
		return valueListBox;
	}

	/**
	 * 
	 * Returns the JComboBox which holds the possible comparisons for a new operator
	 * (>, >=, <, <=, ==, !=).
	 * 
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getComparison() {
		return operatorListBox;
	}

	/**
	 * Returns the JButton for saving the rule changes
	 * 
	 * @return JButton
	 */
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	 * Returns the JButton for deleting the rule changes
	 * 
	 * @return JButton
	 */
	public JButton getDeleteButton() {
		return deleteButton;
	}

	/**
	 * Returns the Rule's name
	 * 
	 * @return String
	 */
	public String getRuleName() {
		return nameText.getText();
	}

	/**
	 * Returns if GUI is in advanced mode or not
	 * 
	 * @return boolean
	 */
	public boolean isAdvancedMode() {
		return advancedMode;
	}

	/**
	 * Get the correct rule conditions, based on edition mode enabled,
	 * with no parsing of the string.
	 * 
	 * @return String The rule conditions string with no parsing
	 */
	public String getRawRuleConditions() {
		String rawRuleConditions = "";

		if (isAdvancedMode()) {
			rawRuleConditions = ruleTextArea.getText().replaceAll("\n", " ");
		} else {
			rawRuleConditions = String.join(" ", ruleConditions);
		}
		return rawRuleConditions;
	}

}
