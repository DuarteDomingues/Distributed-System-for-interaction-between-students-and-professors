package gui.user;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.text.html.HTML.Tag;

public class UserEditPanel extends JPanel {
	
	private ArrayList<JTextField> textFields;
	//private JTextField[] textFields;
	private JButton sumbitButton;
	private JButton createButton;
	private ArrayList<String> arrayPlaceholders;
	private boolean isProfessor;
	
	
	public UserEditPanel(ArrayList<String> labelInfo, boolean isProfessor) {

		this.arrayPlaceholders = labelInfo;
		this.isProfessor = isProfessor;
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		textFields = new ArrayList<JTextField>();
		addJlabel("EDIT INFO", 20, 10, 170, 60, 16, 15, 82, 152);

		setJlabels();
		addSubmitButton();
		addCreateButton();
	}

	private void addJlabel(String text, int posX, int posY, int sizeX, int sizeY, int fontSize, int r, int g, int b) {
		JLabel jlbl = new JLabel(text);
		jlbl.setFont(new Font("Arial", Font.BOLD, fontSize));
		jlbl.setForeground(new Color(r, g, b));
		jlbl.setBounds(posX, posY, sizeX, sizeY);
		this.add(jlbl);

	}
	
	public void setIsProfessor(boolean isProfessor) {
		
		this.isProfessor = isProfessor;
	}
	
	public JButton getSubmitButton() {
		return this.sumbitButton;
	}

	public JButton getCreateButton() {

		return this.createButton;
	}

	private void addSubmitButton() {

		sumbitButton = new JButton("Submit");
		sumbitButton.setBounds(20, 500, 130, 25);
		this.add(sumbitButton);
	}

	private void addCreateButton() {
		
		if (isProfessor) {
			createButton = new JButton("Create Student");
			
			createButton.setBounds(180, 500, 250, 25);
			this.add(createButton);
			
		}
		else {
			createButton = new JButton("Create Group");
			
			createButton.setBounds(180, 500, 250, 25);
			this.add(createButton);
		}
	}

	
	
	public void setArrayPlaceholder(ArrayList<String> placeholders) {

		this.arrayPlaceholders = placeholders;
	}
	
	
	public ArrayList<JTextField> getTextFields() {
		
		return this.textFields;
	}

	public String JTextFieldText(JTextField textField) {

		return textField.getText();

	}
	

	public void isProfessor(String type) {
		
		if (type.equals("prof")) {
			this.isProfessor = true;
			
		}
		else {
			
			this.isProfessor = false;
		}
		
		
	}

	public void setJlabels() {

		ArrayList<String> tagArr = new ArrayList<String>();
		
		
		tagArr.add(0, "EDIT NAME:");
		tagArr.add(1, "EDIT SURNAME:");
		tagArr.add(2,"EDIT ADDRESS:");
		tagArr.add(3,"EDIT EMAIL:");
		tagArr.add(4,"EDIT TELEPHONE:");
		tagArr.add(5, "EDIT LANGUAGE:");
		tagArr.add(6, "EDIT PASSWORD:");
		if (isProfessor) {
			tagArr.add(7, "NATIONALITY:");
			tagArr.add(8, "GENDER;");
			
		}
	
		int count = 20;
		for (int i = 0; i < tagArr.size(); i++) {
			
			count = count + 40;
			addJlabel(tagArr.get(i), 20, count + 16, 200, 30, 16, 37, 101, 174);
			
			JTextField text_field_i = new JTextField(this.arrayPlaceholders.get(i));
			text_field_i.setBounds(180, count + 20, 250, 20);
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

			text_field_i.setBorder(border);
			this.add(text_field_i);

		 	textFields.add(i,text_field_i);

		}

		addTextFieldPlaceholders();

	}

	private HashMap createHashMap() {

		// this.textFields;
		// this.arrayPlaceholders;
		HashMap<JTextField, String> map = new HashMap();

		for (int i = 0; i < textFields.size(); i++) {

			map.put(textFields.get(i), arrayPlaceholders.get(i));
		}
		return map;
	}

	private void addTextFieldPlaceholders() {
		// JTextField[] textFields
		HashMap map = createHashMap();

		for (Object t : map.keySet()) {

			((JTextField) t).setForeground(Color.gray);

			((JTextField) (t)).addFocusListener(new FocusListener() {

				String str = (String) map.get(t);

				@Override
				public void focusGained(FocusEvent e) {

					if (((JTextField) t).getText().equals(str)) {
						((JTextField) t).setText("");
						((JTextField) t).setForeground(Color.BLACK);
					}

				}

				@Override
				public void focusLost(FocusEvent e) {
					if (((JTextField) t).getText().isEmpty()) {
						((JTextField) t).setForeground(Color.GRAY);
						((JTextField) t).setText(str);
					}

				}

			});
		}

	}

}
