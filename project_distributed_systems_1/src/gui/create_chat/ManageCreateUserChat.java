package gui.create_chat;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gui.class_chat.ClassChatPanel;
import xml_read_write.XMLReadWrite;

public class ManageCreateUserChat {
	
	HashMap<String, String> usersMap;
	CreateUserChatPanel createUserChatPanel;
	
	public ManageCreateUserChat(CreateUserChatPanel createUserChatPanel) {
		
		this.createUserChatPanel = createUserChatPanel;
	}
	
	
	
	public HashMap<String, String> getUsersMap() {
		return this.usersMap;
	}
	public void processStudentMessage(String info) {
		
		
		usersMap = new HashMap<String, String>();
		
		Document doc = XMLReadWrite.documentFromString(info);

		NodeList userNodes = doc.getElementsByTagName("user");
	
		
		for (int i =0; i < userNodes.getLength(); i++) {
			
			Element user_i = (Element) userNodes.item(i);
			String id = user_i.getAttribute("id");
			String name = user_i.getElementsByTagName("name").item(0).getTextContent();
			usersMap.put(id, name);
			
		}
		createUserChatPanel.addStudentList(usersMap);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		CreateUserChatPanel c = new CreateUserChatPanel();
		ManageCreateUserChat m = new ManageCreateUserChat(c);
		m.processStudentMessage("<sendAlunos><user id='2'> <name>duda</name></user><user id='3'> <name>Boi</name></user></sendAlunos>");
		
	}
	

}
