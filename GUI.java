import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI implements ActionListener {

	JFrame guiFrame;
	JPanel guiPanel, settingsPanel;
	JButton testButton;
	JTextField passwordField;
	JCheckBox containsUppers, containsNumbers, containsSpecials;
	JComboBox lengthSelection;
	int currentSelection = 4, isUpper, isNumber, isSpecial;
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public static void main(String[] args) {
		new GUI();
	}
	
	public GUI() {
		
		
		Integer[] passwordLengths = {4, 6, 8, 10, 12, 16, 20, 24, 32, 64, 128};

		lengthSelection = new JComboBox(passwordLengths);
		lengthSelection.setSelectedIndex(0);

		
		guiFrame = new JFrame();
		guiPanel = new JPanel();
		settingsPanel = new JPanel();
		testButton = new JButton();
		passwordField = new JTextField();
		
		guiFrame.setLayout(new FlowLayout());
		guiPanel.setLayout(new BorderLayout());
		
		lengthSelection.addActionListener(this);
		testButton.addActionListener(this);
		
		
		testButton.setText("Generate");
		
	    containsUppers = new JCheckBox("Uppercase Characters");
	    containsUppers.setMnemonic(KeyEvent.VK_U); 
	    containsUppers.setSelected(false);

	    containsNumbers = new JCheckBox("Numbers");
	    containsNumbers.setMnemonic(KeyEvent.VK_N); 
	    containsNumbers.setSelected(false);

	    containsSpecials = new JCheckBox("Special Characters");
	    containsSpecials.setMnemonic(KeyEvent.VK_S); 
	    containsSpecials.setSelected(false);

	    containsUppers.addActionListener(this);
	    containsNumbers.addActionListener(this);
	    containsSpecials.addActionListener(this);
		
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setMinimumSize(new Dimension(500, 130));
		guiFrame.setPreferredSize(new Dimension(500, 130));
		guiFrame.setLocationRelativeTo(null);
		
		settingsPanel.add(lengthSelection);
		settingsPanel.add(containsUppers);
		settingsPanel.add(containsNumbers);
		settingsPanel.add(containsSpecials);
		guiPanel.add(testButton, BorderLayout.NORTH);
		guiPanel.add(passwordField, BorderLayout.SOUTH);
		guiPanel.add(settingsPanel, BorderLayout.CENTER);
		guiFrame.add(guiPanel);
		
		guiFrame.setTitle("Password Generator - fDot");
		guiFrame.pack();
		guiFrame.setVisible(true);
	}

	public String generatePassword(int length) {
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz"; //26
		if(isUpper == 1) {
			alphabet += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //26
		}
		if(isNumber == 1) {
			alphabet += "1234567890"; //10
		}
		if(isSpecial == 1) {
			alphabet += " !\"#$%&'()*+,-./:;<>?@[]\\^_`{}|~"; //31
		}
		int i;
		String password = "";
		Random r = new Random();
		for (i = 0; i < length; i++) {
			char c = alphabet.charAt(r.nextInt(alphabet.length()));
			password += c;
		}
		return password;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == lengthSelection) {
	        JComboBox cb = (JComboBox)e.getSource();
	        currentSelection = (int) cb.getSelectedItem();	
		}
		if(e.getSource() == containsUppers) {
			if(isUpper == 1) {
				isUpper = 0;
			}
			else {
				isUpper = 1;
			}
		}
		if(e.getSource() == containsNumbers) {
			if(isNumber == 1) {
				isNumber = 0;
			}
			else {
				isNumber = 1;
			}	
		}
		if(e.getSource() == containsSpecials) {
			if(isSpecial == 1) {
				isSpecial = 0;
			}
			else {
				isSpecial = 1;
			}
		}
		if(e.getSource() == testButton) {
			passwordField.setText(generatePassword(currentSelection));
		}
	}
	
}
