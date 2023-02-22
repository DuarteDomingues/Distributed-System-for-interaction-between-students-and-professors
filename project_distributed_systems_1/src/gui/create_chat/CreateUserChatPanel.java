package gui.create_chat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import client.ClientTCP;

public class CreateUserChatPanel extends JPanel {

	private JTextField textField;
	private JButton createButton;
	private ArrayList<JButton> checkButtonsClicked;
	int c=-1;
	private HashMap<String, Boolean> checkAddedUsers;
	public CreateUserChatPanel() {

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		addJlabel("CREATE CHAT", 50, 10, 170, 60, 18, 15, 82, 152);
		addJlabel("ADD STUDENTS", 450, 10, 170, 60, 16, 15, 82, 152);

		nameChat();
		addCreateBtn();
	}

	public JButton getCreateButton() {

		return this.createButton;
	}

	private void addJlabel(String text, int posX, int posY, int sizeX, int sizeY, int fontSize, int r, int g, int b) {
		JLabel jlbl = new JLabel(text);
		jlbl.setFont(new Font("Arial", Font.BOLD, fontSize));
		jlbl.setForeground(new Color(r, g, b));
		jlbl.setBounds(posX, posY, sizeX, sizeY);
		this.add(jlbl);

	}

	private void nameChat() {

		addJlabel("CHAT NAME:", 50, 70, 170, 60, 14, 15, 82, 152);

		textField = new JTextField();
		textField.setBounds(50, 130, 250, 20);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		textField.setBorder(border);
		this.add(textField);

	}
	
	public JTextField getTextField() {
		return this.textField;
	}
	
	
	private JButton createJButton(int x, int y, int w, int h) {

		JButton turmaButton = new JButton("add");
		turmaButton.setBounds(x, y, w, h);
		this.add(turmaButton);
		return turmaButton;

	}

	private void addCreateBtn() {
		createButton = new JButton("Create");
		createButton.setBounds(50, 500, 130, 25);
		this.add(createButton);
	}

	public void addStudentList(HashMap<String, String> mapUsers) {
		checkButtonsClicked = new ArrayList<JButton>();
		checkAddedUsers = new HashMap<String,Boolean>();

		int count = 0;

		for (String i : mapUsers.keySet()) {
			count = count + 70;

			this.addJlabel(mapUsers.get(i), 450, count, 220, 60, 16, 102, 211, 250);

			JButton btn = createJButton(450, count + 50, 110, 20);

			checkAddedUsers.put(i, false);
			checkButtonsClicked.add(btn);

		
		}
		manageButtons();

	}
	
	public HashMap<String,Boolean> getCheckAddedUsers(){
		
		return this.checkAddedUsers;
	}
	
	private void manageButtons() {

		for (String i : checkAddedUsers.keySet()) {
			c++;
			JButton btn = checkButtonsClicked.get(c);
			
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkAddedUsers.get(i) == false) {

						btn.setText("added");
						checkAddedUsers.replace(i, true);

					}

					else if (checkAddedUsers.get(i) == true) {
						
						checkAddedUsers.replace(i, false);
						btn.setText("add");

					}

				}

			});

		}

	}

}
