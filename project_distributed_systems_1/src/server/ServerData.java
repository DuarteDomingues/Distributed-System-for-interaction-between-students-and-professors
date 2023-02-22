package server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import server.data_info.ClassChat;
import server.data_info.Message;
import server.data_info.User;
import server.xml.XmlDocument;
import util.Vars;

public class ServerData {

	// List of connected users
	private ArrayList<User> users;

	// List of class chats
	private ArrayList<ClassChat> classChats;

	// List of uc chats
	private ArrayList<ClassChat> ucChats;

	private ArrayList<ClassChat> studentGroupChats;

	private XmlDocument doc;

	public ServerData() {
		try {
			doc = new XmlDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// users Connected to Server
		this.users = new ArrayList<User>();

		// initially get class chats , the messages in the chat are only the last 5 ones
		// by default
		try {
			this.classChats = doc.getRecentChatsTurma();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ucChats = doc.getRecentChatsUc();

		this.studentGroupChats = doc.getRecentChatsGroupUsers();

	}

	public void getRecentChatsTurma() {

		try {
			this.classChats = doc.getRecentChatsTurma();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void getRecentChatsUc() {
		this.ucChats=null;

		this.ucChats = doc.getRecentChatsUc();

	}

	public boolean checkUserExistsInChat(int id, ClassChat chat) {

		for (User u : chat.getUsersConnected()) {

			if (u.getIdUser() == id) {
				return true;
			}
		}

		return false;

	}

	public ClassChat getClassChatByIds(String ucId, String turmaId) {

		for (int i = 0; i < classChats.size(); i++) {

			if (classChats.get(i).getUcId().equals(ucId) && classChats.get(i).getTurmaId().equals(turmaId)) {

				return classChats.get(i);
			}
		}
		return null;

	}

	public ClassChat getUcChatByIds(String ucId) {

		for (int i = 0; i < ucChats.size(); i++) {

			if (ucChats.get(i).getUcId().equals(ucId)) {

				return ucChats.get(i);
			}
		}
		return null;

	}

	public ClassChat getGroupStudentsChatByIds(String id) {
		for (int i = 0; i < studentGroupChats.size(); i++) {

			if (studentGroupChats.get(i).getId_studentGroup().equals(id)) {

				return studentGroupChats.get(i);
			}
		}
		return null;

	}

	public void updateChatMessages(String ucId, String turmaId, Message msg, int type) {

		ClassChat chat = null;

		if (type == 0) {
			chat = getClassChatByIds(ucId, turmaId);
		}
		if (type == 1) {
			chat = getUcChatByIds(ucId);
		}
		if (type == 2) {
			chat = getGroupStudentsChatByIds(ucId);
		}

		if (chat.getMessages().size() == Vars.numberOfMessagesInHistory) {

			chat.getMessages().remove(chat.getMessages().size() - 1);
			chat.getMessages().add(0, msg);

		} else {

			chat.getMessages().add(0, msg);

		}

	}

	public ArrayList<User> getUsers() {
		return this.users;
	}

}
