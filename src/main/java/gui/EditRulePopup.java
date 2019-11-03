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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class EditRulePopup {
	private JFrame frame;
	private ArrayList<String> ruleMetrics;

	public EditRulePopup() {
		frame = new JFrame("Personalized Rules");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createPopup();
		frame.setPreferredSize(new Dimension(685, 300));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void createPopup() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel ruleNamePanel = new JPanel();
		ruleNamePanel.setLayout(new GridLayout(1, 2));
		JLabel nameLabel = new JLabel("Name: ", SwingConstants.LEFT);
	    JTextField nameText = new JTextField(20);
	    ruleNamePanel.add(nameLabel);
	    ruleNamePanel.add(nameText);
	    
		JPanel metricsPanel = new JPanel();
		metricsPanel.setLayout(new BoxLayout (metricsPanel, BoxLayout.Y_AXIS));
		JScrollPane metricsScrollpane = new JScrollPane(metricsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		fillMetricsPanel(metricsPanel);

		JButton button = new JButton("Save");
		
		mainPanel.add(ruleNamePanel, BorderLayout.NORTH);
		mainPanel.add(metricsScrollpane, BorderLayout.CENTER);
		mainPanel.add(button, BorderLayout.SOUTH);
	    frame.add(mainPanel);
	}
	
//TODO: This method handles the creating of the panel which displays the rules' metrics, as well as loading all of the metrics into said JPanel. 
	private void fillMetricsPanel(JPanel metricsPanel) {
		updateMetrics();
		metricsPanel.removeAll();
		JPanel addNewMetricPanel = new JPanel();
		addNewMetricPanel.setLayout(new GridLayout(1,5));
		JComboBox<String> condition = new JComboBox<>();
		JComboBox<String> value = new JComboBox<>();
		JComboBox<String> comparison = new JComboBox<>();
		JTextField threshold = new JTextField();
		JButton add = new JButton("Add");
		addNewMetricPanel.add(condition);
		addNewMetricPanel.add(value);
		addNewMetricPanel.add(comparison);
		addNewMetricPanel.add(threshold);
		addNewMetricPanel.add(add);
		addNewMetricPanel.setPreferredSize(new Dimension(650, 25));
        addNewMetricPanel.setMaximumSize(new Dimension(650, 25));
        metricsPanel.add(addNewMetricPanel);
        
		for (String rule : ruleMetrics) 
		{
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 2));
			
		    JLabel metric = new JLabel(rule);
		    metric.setHorizontalAlignment(JLabel.CENTER);
		    JButton remove = new JButton("Remove");
		    panel.add(metric);
		    panel.add(remove);
		    metricsPanel.add(panel);
		}
	}
	
	public void updateMetrics() {
		//TODO: Currently hard coded for quick testing purposes of the GUI, this method should update the current rules' associated list of metrics. 
		ruleMetrics = new ArrayList<String>();
		ruleMetrics.add("IF LOC > 10");
		ruleMetrics.add("AND IF < 15");
		ruleMetrics.add("AND IF < 25");
		ruleMetrics.add("AND IF < 35");
		ruleMetrics.add("AND IF < 45");
		ruleMetrics.add("AND IF < 55");
		ruleMetrics.add("AND IF < 65");
		ruleMetrics.add("AND IF < 75");
		ruleMetrics.add("AND IF < 85");
		ruleMetrics.add("AND IF < 95");
		ruleMetrics.add("AND IF < 105");
		ruleMetrics.add("AND IF < 115");
		ruleMetrics.add("AND IF < 125");
		ruleMetrics.add("AND IF < 135");
		ruleMetrics.add("AND IF < 145");
		ruleMetrics.add("AND IF < 155");
		ruleMetrics.add("AND IF < 165");
	}
	

}
