package server.data_info;

import java.util.ArrayList;

public class ClassChat {
	
	private ArrayList<Message> messages;
	private ArrayList<User> usersConnected;
	private ArrayList<String> usersId;
	private String ucId; 
	private String turmaId;
	private String adminId;
	private String id_studentGroup;
	
	public ClassChat(ArrayList<Message> messages , String ucId, String turmaId, String adminId, ArrayList<String> usersId, String id_studentGroup) {
		
		this.messages = messages;
		this.ucId = ucId;
		this.turmaId = turmaId;
		this.adminId = adminId;
		this.usersId= usersId;
		this.usersConnected = new ArrayList<User>();
		this.id_studentGroup = id_studentGroup;
		
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	public void addUserConnected(User u) {
		
		usersConnected.add(u);
	}
	
	
	

	public ArrayList<User> getUsersConnected() {
		return usersConnected;
	}
	
	public ArrayList<String> getUsersId() {
		return usersId;
	}

	public String getUcId() {
		return ucId;
	}
	
	public String getTurmaId() {
		return turmaId;
	}

	public String getAdminId() {
		return adminId;
	}
	
	public String getId_studentGroup() {
		return this.id_studentGroup;
	}
	
	public void setUsersConnected(ArrayList<User> usersConnected) {
		
		this.usersConnected = usersConnected;
	}

}
