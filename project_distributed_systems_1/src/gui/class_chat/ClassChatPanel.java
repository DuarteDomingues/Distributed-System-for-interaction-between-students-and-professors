package gui.class_chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import util.Paths;

public class ClassChatPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton backButton;
	private JButton writeButton;
	private String fileName;
	private JTextArea receivedMessagesArea;
	private JTextField inputTextArea;
	private int textAreaCounter;
	

	
	
	public ClassChatPanel() {
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		textFields = new JTextField[2];
		createScrollableMessages();
		createInputTextArea();
		this.textAreaCounter = 0;
		addBackBtn();


		
	}
	
	public void setLabels(String uc,String turma) {
		
		addJlabel(uc, 120, 10 , 250, 30, 16,  37, 101, 74);
		
		addJlabel(turma, 410, 10 , 200, 30, 16,  37, 101, 74);

		

	}
	
	private void createScrollableMessages() {
		
		addJlabel("Chat", 50, 10 , 200, 30, 16 ,  15, 82, 152);
		
		
	
		// adiciona uma textArea com scroll
		JScrollPane sbrText = new JScrollPane();
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sbrText.setBounds(50, 50, 395, 220);
		receivedMessagesArea = new JTextArea();
		receivedMessagesArea.setEditable(false);
		sbrText.setViewportView(receivedMessagesArea);
		this.add(sbrText);
	    
	    
	}
	
	
	private void createInputTextArea() {
		
		
		inputTextArea = new JTextField();
		inputTextArea.setBounds(50, 300, 250, 20);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
		inputTextArea.setBorder(border);
		this.add(inputTextArea);
		
		writeButton = new JButton("send");
		writeButton.setBounds(320, 300, 130, 20);
		this.add(writeButton);
		

	}
	
	

	private void addBackBtn() {
		backButton = new JButton("Back");
		backButton.setBounds(50, 500, 130, 25);
		this.add(backButton);
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
		g.drawImage(new ImageIcon(Paths.background3Path).getImage(), 0, 0, null);

	}
	
	public void setUsersPanel(ArrayList<String> usersList, String admin) {
		addJlabel("Users", 650, 10 , 200, 30, 16,  15, 82, 152);
		
		int count = 20;
		for (int i = 0; i < usersList.size(); i++) {
			count = count + 40;
			
			if (usersList.get(i).equals(admin)) {
				addJlabel(usersList.get(i) +" (admin)", 650, count , 200, 30, 16, 212,175,75);

			}
			else {
			addJlabel(usersList.get(i), 650, count , 200, 30, 16, 60, 153, 220);
			}
		}
		
	}
	
	
	
	
	
	public JButton getWriteButton() {
		
		return this.writeButton;
	}
	
	public void setTextToTextArea(String txt) {
		receivedMessagesArea.setText(txt);
	}
	
	
	public JTextArea getTextArea() {
		
		return this.receivedMessagesArea;
	}
	
	
	public String getTextFromTextArea() {
		return receivedMessagesArea.getText();
	}
	
	
	public void setTextFromInputTextArea(String text) {
		
		 inputTextArea.setText(text);
	}
	
	public String getTextFromInputTextArea() {
		
		return inputTextArea.getText();
	}
	
	

	
	public JTextField[] getTextFields() {
		return this.textFields;
	}
	
	public JButton getBackButton() {
		return this.backButton;
	}
	
	public int getTextAreaCounter() {
		
		return this.textAreaCounter;
	}
	
	public void incrementTextAreaCounter() {
		
		this.textAreaCounter++;
	}
	

}
