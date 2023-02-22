package server_data;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import user.Client;

@WebListener
public class ServerData implements ServletContextListener {

	private static ArrayList<Client> usersConnected;
	
	private static String[] arrUserInfo;
	private static String[] arrOtherUserInfo;
	private static String password;
	private static  ArrayList<String> chatIds;
	private static  ArrayList<String> chatNames;
	private static HashMap<String,String> users;
	private static ArrayList<String> usersInChat;
	private static ArrayList<String> dataMembers;
	private static HashMap<String,String> dataStudents;

	public static ArrayList<String> getDataMembers() {
		return dataMembers;
	}

	public static void setDataMembers(ArrayList<String> dataMembers) {
		ServerData.dataMembers = dataMembers;
	}

	public static HashMap<String, String> getDataStudents() {
		return dataStudents;
	}

	public static void setDataStudents(HashMap<String, String> dataStudents) {
		ServerData.dataStudents = dataStudents;
	}

	public static ArrayList<String> getUsersInChat() {
		return usersInChat;
	}

	public static void setUsersInChat(ArrayList<String> usersInChat) {
		ServerData.usersInChat = usersInChat;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		for (int i = 0; i < usersConnected.size(); i++) {
			usersConnected.remove(0);
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		// System.out.println("CONA INCOMING");

		usersConnected = new ArrayList<Client>();
		arrUserInfo = new  String[10];

	}
	
	public static void logout(String clientName) {
		
		int index = getIndexClientByName(clientName);
		usersConnected.remove(index);
	}
	
	
	

	public static void addClient(Client client) {

		usersConnected.add(client);
	}

	public static Client getClientByName(String ClientName) {

		for (int i = usersConnected.size() - 1; i > -1; i--) {

			if (usersConnected.get(i).getClientName().equals(ClientName)) {

				return usersConnected.get(i);
			}

		}
		return null;

	}
	
	public static int getIndexClientByName(String clientName) {
		
		for (int i = usersConnected.size() - 1; i > -1; i--) {

			if (usersConnected.get(i).getClientName().equals(clientName)) {

				return i;
			}

		}
		return -1;
	}
	
	
	
	
	

	public static void removeClientByName(String ClientName) {

		for (int i = usersConnected.size() - 1; i > -1; i--) {


			if (usersConnected.get(i).getClientName().equals(ClientName)) {
				
				usersConnected.remove(i);

			}

		}

	}
	
	
	public static void updateUserInfo(String[] userInfo) {
		
		arrUserInfo = userInfo;
	}
	
	public static String[] getArrUserInfo() {
		
		return arrUserInfo;
	}
	
	
	
	public static void updateOtherUserInfo(String[] otherUserInfo) {
		
		arrOtherUserInfo = otherUserInfo;
	}
	
public static String[] getOtherUserInfo() {
		
		return arrOtherUserInfo;
	}
	
	
	
	public static void setPassword(String pass) {
		
		password = pass;
		
	}
	
	public static String getPassword() {
		return password;
	}

	public static ArrayList<String> getChatIds() {
		return chatIds;
	}

	public static void setChatIds(ArrayList<String> chatIds) {
		ServerData.chatIds = chatIds;
	}

	public static ArrayList<String> getChatNames() {
		return chatNames;
	}

	public static void setChatNames(ArrayList<String> chatNames) {
		ServerData.chatNames = chatNames;
	}
	
	public static HashMap<String,String> getUsers() {
		return users;
	}

	public static void setUsers(HashMap<String,String> users) {
		ServerData.users = users;
	}
	

}