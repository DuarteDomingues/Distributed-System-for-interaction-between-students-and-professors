package gui.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.Paths;

public class LoginPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton continueButton;
	private String fileName;
	
	public LoginPanel() {
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		textFields = new JTextField[2];
		addJlabel("Log In", 435, 30, 100, 30, 26);
		addNameTextField();
		passwordTextField();
		addContinuteBtn();
		

	}
	
	private void addNameTextField() {
		addJlabel("User Name:", 270, 100, 100, 30, 16);
		JLabel name = new JLabel();
		name.setText("User name");
		textFields[0] = new JTextField();
		//int x-coordinate, int y-coordinate, int width, int height
		textFields[0].setBounds(380, 100, 240, 30);
		this.add(textFields[0]);
		this.add(name);

	}
	
	private void passwordTextField() {
		addJlabel("Password:", 270, 185, 100, 30, 16);

		textFields[1] = new JTextField();
		textFields[1].setBounds(380, 185, 240, 30);
		this.add(textFields[1]); 
	}


	private void addContinuteBtn() {
		continueButton = new JButton("Continue");
		continueButton.setBounds(270, 260, 130, 25);
		this.add(continueButton);
	}
	
	private void addJlabel(String text, int posX, int posY, int sizeX, int sizeY, int fontSize) {
		JLabel jlbl = new JLabel(text);
		jlbl.setFont(new Font("Arial", Font.BOLD, fontSize));
		jlbl.setForeground(new Color(150, 55, 39));
		jlbl.setBounds(posX, posY, sizeX, sizeY);
		this.add(jlbl);

	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon(Paths.bakcgroundPath).getImage(), 0, 0, null);

	}
	
	
	public JTextField[] getTextFields() {
		return this.textFields;
	}
	
	public JButton getContinueButton() {
		return this.continueButton;
	}
	
	

}
