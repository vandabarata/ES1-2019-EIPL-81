package main.java.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.event.*;


public class EditRulePopup {
	private ArrayList<String> ruleMetrics = new ArrayList<String>();
	private JFrame frame;
	private JTextField nameText;
	private JPanel namePanel;
	private JPanel metricsPanel;
	private JPanel controlPanel;
	
	

	public EditRulePopup() {
		frame = new JFrame("Personalized Rules");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(createMainPanel());
		frame.setPreferredSize(new Dimension(685, 300));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	
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
	
	
	private JPanel createNamePanel() {
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 2));
		JLabel nameLabel = new JLabel("Name: ", SwingConstants.LEFT);
	    nameText = new JTextField();
	    namePanel.add(nameLabel);
	    namePanel.add(nameText);
		return namePanel;
	}
	
	private JPanel createMetricsPanel() {
		metricsPanel = new JPanel();
		metricsPanel.setLayout(new BoxLayout (metricsPanel, BoxLayout.Y_AXIS));
		JScrollPane metricsScrollpane = new JScrollPane(metricsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		fillMetricsPanel(metricsPanel);
		
		JPanel addNewMetricPanel = new JPanel();
	    loadAddMetricLine(addNewMetricPanel, metricsPanel);
		
		JPanel masterMetricsPanel = new JPanel();
		masterMetricsPanel.setLayout(new BorderLayout());
		masterMetricsPanel.add(addNewMetricPanel, BorderLayout.NORTH);
		masterMetricsPanel.add(metricsScrollpane, BorderLayout.CENTER);
		return masterMetricsPanel;
	}
	
	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout (1, 2));
		JButton clearButton = new JButton("Clear Metrics");
		clearButton.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	System.out.println("Clear button pressed!");
		    	clearMetricsPanel(metricsPanel);
		    }
		});
		JButton saveButton = new JButton("Save Rule");
		saveButton.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	if(nameText.getText().isEmpty()) {
		    		JOptionPane.showMessageDialog(null, "Please insert a rule name!");
		    	} else {
		    		System.out.println("Save button pressed!");
		    		System.out.println("Rule name: " + nameText.getText());
		    	}
		    }  
		});
		controlPanel.add(clearButton);
		controlPanel.add(saveButton);
		return controlPanel;
		
	}
	
	
	
	
//TODO: This method handles the creating of the panel which displays the rules' metrics, as well as loading all of the metrics into said JPanel. 
	private void fillMetricsPanel(JPanel metricsPanel) {
		metricsPanel.removeAll();

		for (String metric : ruleMetrics)
		{
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 2));
		    JLabel metricLabel = new JLabel(metric);
		    metricLabel.setHorizontalAlignment(JLabel.CENTER);
		    panel.add(metricLabel);
		    metricsPanel.add(panel);
		}

		metricsPanel.revalidate();
		metricsPanel.repaint();
	}
	
	private void clearMetricsPanel(JPanel metricsPanel) {
		ruleMetrics.clear();
		metricsPanel.removeAll();
		metricsPanel.revalidate();
		metricsPanel.repaint();
	}

 
	public JPanel loadAddMetricLine(JPanel addNewMetricPanel, JPanel metricsPanel) {
		addNewMetricPanel.removeAll();
		addNewMetricPanel.setLayout(new GridLayout(1,6));
		JComboBox<String> condition = new JComboBox<>();
		System.out.println(ruleMetrics.isEmpty());
		if(ruleMetrics.isEmpty()) {
			condition.setVisible(false);
			condition.setSelectedItem("");
		}else{
			condition.addItem("AND ");
			condition.addItem("OR ");
		}
		
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
		addMetricButton.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	if(value.getItemCount() == 0) {
		    		JOptionPane.showMessageDialog(null, "Please check if all metric fields were entered correctly!");
		    	} else {
		    		String metric;
		    		if(ruleMetrics.isEmpty()) {
		    			metric = value.getSelectedItem() + " " + comparison.getSelectedItem() + " " + threshold.getText();

		    		}else {
		    			metric = condition.getSelectedItem() + "" + value.getSelectedItem() + " " + comparison.getSelectedItem() + " " + threshold.getText();
		    		}
		    		System.out.println("Add metric button pressed!");
		    		System.out.println(metric);
		    		ruleMetrics.add(metric);
		    		fillMetricsPanel(metricsPanel);
		    		addNewMetricPanel.revalidate();
		    		addNewMetricPanel.repaint();
		    	}
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
	
}