package gui.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.Paths;

public class UserPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton continueButton;
	private String fileName;
	private String[] userInfoArr;
	private JButton editButton;
	private boolean isProfessor;
	

	private HashMap<String, JButton> turmaChatButtons;
	private HashMap<String, JButton> ucChatButtons;
	private HashMap<String, JButton> turmaCreateButtons;
	private HashMap<String, JButton> ucCreateButtons;
	private HashMap<String, JButton> groupChatButtons;
	
	
	public UserPanel() {

		this.userInfoArr = new String[10];
		this.userInfoArr[0] = "";
		this.userInfoArr[1] = "";

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		textFields = new JTextField[2];

		addJlabel("CURRICULAR UNITS", 460, 10, 170, 60, 16, 15, 82, 152);
		addJlabel("CLASS", 730, 10, 150, 60, 18, 15, 82, 152);

		addEditBtn();
	}


	
	
	public JButton getEditButton() {

		return this.editButton;
	}
	
	public boolean getIsProfessor() {
		
		return this.isProfessor;
	}

	private void addJlabel(String text, int posX, int posY, int sizeX, int sizeY, int fontSize, int r, int g, int b) {
		JLabel jlbl = new JLabel(text);
		jlbl.setFont(new Font("Arial", Font.BOLD, fontSize));
		jlbl.setForeground(new Color(r, g, b));
		jlbl.setBounds(posX, posY, sizeX, sizeY);
		this.add(jlbl);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon(Paths.bakcground2Path).getImage(), 0, 0, null);

	}

	public JTextField[] getTextFields() {
		return this.textFields;
	}
	
	private void addEditBtn() {
		editButton = new JButton("edit info");
		editButton.setBounds(20, 500, 130, 25);
		this.add(editButton);
	}
	
	
	public void isProfessor(String type) {
		
		if (type.equals("prof")) {
			this.isProfessor = true;
			
		}
		else {
			
			this.isProfessor = false;
			addJlabel("WORK GROUPS", 920, 10, 200, 60, 18, 15, 82, 152);

		}
		
		
	}
	public void setUserInfo(String[] info, ArrayList<String> groupsStudents, ArrayList<String> chatNames) {
		
		groupChatButtons = new HashMap<String, JButton>();

		String[] tagArr = new String[10];

		tagArr[0] = "SURNAME:";
		tagArr[1] = "ADDRESS:";
		tagArr[2] = "NATIONALITY:";
		tagArr[3] = "NUMBER:";
		tagArr[4] = "EMAIL:";
		tagArr[5] = "TELEPHONE:";
		tagArr[6] = "TYPE:";
		tagArr[7] = "LANGUAGE:";
		tagArr[8] = "GENDER:";
				
		
		int count = 20;
		for (int i = 0; i < tagArr.length; i++) {
			count = count + 40;
			addJlabel(tagArr[i], 20, count + 16, 200, 30, 16, 37, 101, 174);

		}

		this.userInfoArr = info;
		int c = 20;
		for (int i = 0; i < userInfoArr.length - 1; i++) {
			c = c + 40;
			addJlabel(userInfoArr[i + 1], 135, c, 270, 60, 16, 60, 153, 220);

		}
		int count_c = 20;

		if (groupsStudents!= null && groupsStudents.size()!=0) {
		for (int i = 0; i < groupsStudents.size() ; i++) {
			count_c = count_c + 80;
			
			addJlabel(chatNames.get(i), 920, count_c, 200, 60, 16, 60, 153, 220);
			
			if (isProfessor==false) {
				
				JButton groupChatButton = createJButtonChat(920, count_c + 50, 110, 20);
				groupChatButtons.put(groupsStudents.get(i), groupChatButton);
				//turmaChatButtons.put(i, turmaButton);
			}

		}
		}
		if (!isProfessor) {
		addJlabel(userInfoArr[0], 20, 10, 300, 60, 22, 15, 82, 152);
		}
		
		

	}
	


	public JButton createJButtonChat(int x, int y, int w, int h) {

		JButton turmaButton = new JButton("chat");
		turmaButton.setBounds(x, y, w, h);
		this.add(turmaButton);
		return turmaButton;

	}

	public JButton createJButtonCreateChat(int x, int y, int w, int h) {

		JButton turmaButton = new JButton("create chat");
		turmaButton.setBounds(x, y, w, h);
		this.add(turmaButton);
		return turmaButton;

	}

	public void setTurmaInfoUcInfo(HashMap<String, String> ucMap, HashMap<String, String> turmaMap,
			HashMap<String, Boolean> turmaChatInfo, HashMap<String, Boolean> ucChatInfo) {

		turmaChatButtons = new HashMap<String, JButton>();
		ucChatButtons = new HashMap<String, JButton>();
		turmaCreateButtons = new HashMap<String, JButton>();
		ucCreateButtons = new HashMap<String, JButton>();

		int c = 20;
		for (String i : turmaMap.keySet()) {
			c = c + 80;

			this.addJlabel(turmaMap.get(i), 730, c, 50, 60, 16, 213, 243, 254);

			if (turmaChatInfo.get(i) == true) {

				JButton turmaButton = createJButtonChat(730, c + 50, 110, 20);
				turmaChatButtons.put(i, turmaButton);

			}

			else {
				if (isProfessor) {
				JButton createTurmaButton = createJButtonCreateChat(730, c + 50, 110, 20);
				turmaCreateButtons.put(i, createTurmaButton);
				}
			}

		}

		int count = 20;
		for (String i : ucMap.keySet()) {
			count = count + 80;

			this.addJlabel(ucMap.get(i), 460, count, 220, 60, 16, 102, 211, 250);

			if (ucChatInfo.get(i) == true) {
				JButton ucChat = createJButtonChat(460, count + 50, 110, 20);
				ucChatButtons.put(i, ucChat);

			}

			else {
				if (isProfessor) {
				JButton ucCreateButton = createJButtonCreateChat(460, count + 50, 110, 20);
				ucCreateButtons.put(i, ucCreateButton);
				}
			}

		}

	}

	public HashMap<String, JButton> getturmaChatButtons() {

		return this.turmaChatButtons;
	}

	public HashMap<String, JButton> getucChatButtons() {

		return this.ucChatButtons;
	}

	public HashMap<String, JButton> getTurmaCreateButtons() {

		return this.turmaCreateButtons;
	}

	public HashMap<String, JButton> getucCreateButtons() {

		return this.ucCreateButtons;
	}
	
	public HashMap<String, JButton> getStudentsChatButtons(){
		return this.groupChatButtons;
	}
	
	
	

}
