package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
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

import main.java.model.CodeQualityRule;
import main.java.model.LogicalOperator;
import main.java.model.Metric;
import main.java.model.ComparisonOperator;

/**
 * GUI pop-up responsible for allowing the user to edit and add different rules
 * for visualising the excel table.
 */
public class EditRulePopup {

	/** The rule being edited or created */ 
	private CodeQualityRule rule;
	/** An array of string with the rules conditions where every position
	 *  is a new line in the condition */
	private ArrayList<String> ruleConditions = new ArrayList<String>();
	/** The main frame of the GUI */
	private JFrame frame;
	/** The rule name text field in the GUI */
	private JTextField nameText;
	/** The Combo Box displaying the options of logical operators
	 *  available for rule creation (AND, OR), for Basic Mode */
	private JComboBox<String> logicalOperatorListBox;
	/** The Combo Box displaying the available metrics for
	 * rule creation (LOC, CYCLO, ATFD, LAA), for Basic Mode */
	private JComboBox<String> metricsListBox;
	/** The Combo Box displaying the available comparison operators
	 *  for rule creation (>, <, >=, \<=, ==, !=), in Basic Mode */
	private JComboBox<String> comparisonOperatorListBox;
	/** The Text Area the displays the rule conditions and that can be
	 *  edited by the user */
	private JTextArea ruleTextArea;

	/** The Delete rule button */
	private JButton deleteButton;
	/** The Save rule button */
	private JButton saveButton;

	/** The main JPanel of the GUI */
	private JPanel mainPanel;
	/** The JPanel to display the rule's name */
	private JPanel namePanel;
	/** The JPanel to display the rule's conditions */
	private JPanel ruleConditionsPanel;
	/** The JPanel to display the buttons to switch between
	 *  Basic and Advanced modes */
	private JPanel editorComplexityTogglePanel;
	/** The JPanel to display the current rule conditions added in Basic Mode */
	private JPanel ruleConditionsListPanel;
	/** The JPanel to display the interface to add a new rule condition in Basic Mode */
	private JPanel addNewRuleConditionPanel;
	/** The JPanel to display the action buttons (Clear/Delete/Save) */
	private JPanel controlPanel;
	/** The JPanel for the content in the center of the GUI */
	private JPanel centerPanel;

	/** If the GUI is on Advanced Mode */
	private boolean advancedMode;
	/** If the rule being edited is a Default Rule */
	private boolean defaultRule;
	/** If should display the logical operators ComcoBox in Basic Mode */
	private boolean logicalOperatorsVisibility;

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
	 * Returns the JPanel responsible for holding both the ruleConditionsPanel,
	 * which allows edits to the current rule, and the complexity toggle panel,
	 * which allows changing in between the Basic and Advanced modes.
	 * 
	 * @return JPanel
	 */
	private JPanel createCenterPanel() {
		createRuleConditionsPanel();
		createEditorComplexityTogglePanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(ruleConditionsPanel, BorderLayout.CENTER);
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
	private JPanel createRuleConditionsPanel() {
		if (advancedMode || defaultRule || rule.getName() != "") {

			ruleConditionsPanel.removeAll();
			ruleConditionsPanel.setLayout(new BorderLayout());

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

			ruleConditionsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

			String availableMetricsText = new String();
			for (Metric metric : Metric.values()) {
				availableMetricsText = availableMetricsText + metric.name() + " ";
			}

			JLabel availableMetrics = new JLabel("Available metrics: \n" + availableMetricsText);

			ruleConditionsPanel.add(advancedRuleConditionsPane, BorderLayout.CENTER);
			ruleConditionsPanel.add(ruleConditionsTextPaneLabel, BorderLayout.NORTH);
			ruleConditionsPanel.add(availableMetrics, BorderLayout.SOUTH);

		} else {

			ruleConditionsPanel.removeAll();
			ruleConditionsListPanel.setLayout(new BoxLayout(ruleConditionsListPanel, BoxLayout.Y_AXIS));
			ruleConditionsListPanel.setMinimumSize(new Dimension(500, 500));
			final JScrollPane ruleConditionsScrollpane = new JScrollPane(ruleConditionsListPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			createAddRuleConditionPanel();
			ruleConditionsPanel.setLayout(new BorderLayout());
			ruleConditionsPanel.add(addNewRuleConditionPanel, BorderLayout.NORTH);
			ruleConditionsPanel.add(ruleConditionsScrollpane, BorderLayout.CENTER);
			fillRuleConditionsListPanel();
			setLogicalOperatorBoxVisibility();
			ruleConditionsListPanel.revalidate();
			ruleConditionsListPanel.repaint();
		}
		return ruleConditionsPanel;
	}

	/**
	 * 
	 * Returns the JPanel holding the line which allows users to add new rule conditions
	 * into the rule's conditions list. For Basic Mode.
	 * 
	 * @return JPanel
	 */
	private JPanel createAddRuleConditionPanel() {
		addNewRuleConditionPanel.removeAll();
		addNewRuleConditionPanel.setLayout(new GridLayout(1, 6));
		logicalOperatorListBox = new JComboBox<>();
		setLogicalOperatorBoxVisibility();
		JLabel ifCondition = new JLabel("IF", SwingConstants.CENTER);

		metricsListBox = new JComboBox<>();
		for (Metric metric : Metric.values()) {
			metricsListBox.addItem(metric.name());
		}

		comparisonOperatorListBox = new JComboBox<>();
		for (ComparisonOperator comp : ComparisonOperator.values()) {
			comparisonOperatorListBox.addItem(comp.getSymbol());
		}

		JTextField threshold = new JTextField("");

		JButton addRuleConditionButton = new JButton("Add");
		addRuleConditionButton.addActionListener(e -> {
			String ruleCondition;
			String baseRuleCondition = " " + comparisonOperatorListBox.getSelectedItem() + " " + threshold.getText() + " ";
			try {
				Integer.parseInt(threshold.getText());
				if (ruleConditions.isEmpty()) {
					ruleCondition = "IF " + metricsListBox.getSelectedItem() + baseRuleCondition;
				} else {
					ruleCondition = logicalOperatorListBox.getSelectedItem() + " " + metricsListBox.getSelectedItem() + baseRuleCondition;
				}
				ruleConditions.add(ruleCondition);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Please check if your threshold input is correct!");
			}

			fillRuleConditionsListPanel();
			setLogicalOperatorBoxVisibility();
			ruleConditionsListPanel.revalidate();
			ruleConditionsListPanel.repaint();
		});

		addNewRuleConditionPanel.add(logicalOperatorListBox);
		addNewRuleConditionPanel.add(ifCondition);
		addNewRuleConditionPanel.add(metricsListBox);
		addNewRuleConditionPanel.add(comparisonOperatorListBox);
		addNewRuleConditionPanel.add(threshold);
		addNewRuleConditionPanel.add(addRuleConditionButton);
		addNewRuleConditionPanel.setPreferredSize(new Dimension(650, 25));
		addNewRuleConditionPanel.setMaximumSize(new Dimension(650, 25));
		return addNewRuleConditionPanel;

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
				createRuleConditionsPanel();
				ruleConditionsPanel.revalidate();
				ruleConditionsPanel.repaint();
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
			createRuleConditionsPanel();
			ruleConditionsPanel.revalidate();
			ruleConditionsPanel.repaint();
			advancedComplexity.setEnabled(false);
		});
		editorComplexityTogglePanel.add(basicComplexity);
		editorComplexityTogglePanel.add(advancedComplexity);

		return editorComplexityTogglePanel;
	}

	/**
	 * Returns the JPanel responsible for holding the JButtons which allow all
	 * rule conditions to be cleared, or the rule to be saved.
	 * 
	 * @return JPanel
	 */
	private JPanel createControlPanel() {
		controlPanel.setLayout(new GridLayout(1, 3));

		JButton clearButton = new JButton("Clear Rule Conditions");
		deleteButton = new JButton("Delete Rule");
		saveButton = new JButton("Save Rule");

		clearButton.addActionListener(e -> {
			clearRuleConditionsListPanel();
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
		ruleConditionsPanel = new JPanel();
		editorComplexityTogglePanel = new JPanel();
		ruleConditionsListPanel = new JPanel();
		addNewRuleConditionPanel = new JPanel();
		controlPanel = new JPanel();
		centerPanel = new JPanel();
		ruleTextArea = new JTextArea();
	}

	/**
	 * This method fills the rule's conditions list in the UI with the contents found in the
	 * ruleConditions ArrayList.
	 */
	private void fillRuleConditionsListPanel() {
		ruleConditionsListPanel.removeAll();

		for (String condition : ruleConditions) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 2));
			JLabel conditionLabel = new JLabel(condition);
			conditionLabel.setHorizontalAlignment(JLabel.CENTER);
			panel.add(conditionLabel);
			ruleConditionsListPanel.add(panel);
		}

		ruleConditionsListPanel.revalidate();
		ruleConditionsListPanel.repaint();
	}

	/**
	 * This method clears all current rule conditions from both the GUI and the ruleConditions
	 * ArrayList.
	 */
	private void clearRuleConditionsListPanel() {
		ruleConditions.clear();
		ruleTextArea.setText("");
		ruleConditionsListPanel.removeAll();
		if (!advancedMode) {
			setLogicalOperatorBoxVisibility();
		}
		ruleConditionsListPanel.revalidate();
		ruleConditionsListPanel.repaint();
	}

	/**
	 * This method handles setting the visibility of the Logical Operator ComboBox in the
	 * line responsible for allowing the user to add new rule conditions to the list.
	 * Basically, it stops the first rule condition, and the first rule condition only,
	 * from having an AND or an OR attached to it.
	 */
	private void setLogicalOperatorBoxVisibility() {
		if (!ruleConditions.isEmpty()) {
			logicalOperatorListBox.setVisible(true);
			if (!logicalOperatorsVisibility)
				for (LogicalOperator operator : LogicalOperator.values()) {
					logicalOperatorListBox.addItem(operator.toString());
					logicalOperatorsVisibility = true;
				}
		} else {
			logicalOperatorListBox.setVisible(false);
			logicalOperatorListBox.setSelectedItem("");
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
	 * Returns the JComboBox which holds the logical operators for a new rule condition
	 * (AND and OR).
	 * 
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getCondition() {
		return logicalOperatorListBox;
	}

	/**
	 * 
	 * Returns the JComboBox which holds the values for metrics (LOC, LAA, etc).
	 * 
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getValue() {
		return metricsListBox;
	}

	/**
	 * 
	 * Returns the JComboBox which holds the possible comparisons for a new operator
	 * (>, >=, <, <=, ==, !=).
	 * 
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getComparison() {
		return comparisonOperatorListBox;
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
