package user;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.Expressions;
import xml_read_write.XMLReadWrite;

public class ClientXml {

	public ClientXml() {

	}

	public String createLoginMessage(String user, String password) {

		String loginStart = Expressions.login;
		String loginEnd = Expressions.loginEnd;

		String requestLogin = loginStart + " <username>" + user + "</username>" + " <password>" + password
				+ "</password>" + " " + loginEnd;
		return requestLogin;
	}

	public String createRequestChatTurmaMessage(String ucId, String turmaId) {

		String requestChatStart = Expressions.requestChatTurma;
		String requestChatEnd = Expressions.requestChatTurmaEnd;

		String chatReq = requestChatStart + "<uc uc_id='" + ucId + "'> <turma turma_id='" + turmaId + "'/> </uc>"
				+ requestChatEnd;
		return chatReq;

	}
	
	public String requestChangeTurmaMessage(String ucId, String turmaId) {
		
		String requestChatStart = Expressions.reqChangeTurma;
		String requestChatEnd = Expressions.reqChangeTurmaEnd;

		String chatReq = requestChatStart + "<uc uc_id='" + ucId + "'> <turma turma_id='" + turmaId + "'/> </uc>"
				+ requestChatEnd;
		return chatReq;
	}

	
	
	
	
	public String requestTurmas() {

		String req = "<request_ucs></request_ucs>";

		return req;

	}

	public String createRequestChatUcMessage(String ucId) {

		String requestChatStart = Expressions.requestChatUc;
		String requestChatEnd = Expressions.requestChatUcEnd;

		String chatReq = requestChatStart + "<uc uc_id='" + ucId + "'>  </uc>" + requestChatEnd;
		return chatReq;

	}

	public String createRequestChatGroupUsersMessage(String ucId) {

		String requestChatStart = Expressions.requestChatGroup;
		String requestChatEnd = Expressions.requestChatGroupEnd;

		String chatReq = requestChatStart + "<chat id='" + ucId + "'>  </chat>" + requestChatEnd;
		return chatReq;

	}

	public String goBackFromChatMessage() {

		return "<back> </back>";

	}

	public String createEditUserMessage(String name, String surname, String address, String email, String telephone,
			String language, String password, String nationality, String gender,String id) {

		return Expressions.updateUserInfo + "<name>" + name + "</name>" + "<id>" + id +"</id>" + "<surname>" + surname + "</surname>"
				+ "<address>" + address + "</address>" + "<email>" + email + "</email>" + "<telephone>" + telephone
				+ "</telephone>" + "<language>" + language + "</language> <password>" + password + "</password>"
				+ "<gender>" + gender + "</gender>" + "<nationality>" + nationality + "</nationality>"
				+ Expressions.updateUserInfoEnd;

	}
	
	public String createStudentMessage(String name, String surname, String address, String email, String telephone,
			String language, String password, String nationality, String gender, String number) {

		return Expressions.reqCreateStudent + "<name>" + name + "</name>"  + "<surname>" + surname + "</surname>"
				+ "<address>" + address + "</address>" + "<email>" + email + "</email>" + "<telephone>" + telephone
				+ "</telephone>" + "<language>" + language + "</language> <password>" + password + "</password>"
				+ "<gender>" + gender + "</gender>" + "<number>"+ number + "</number>"+ "<nationality>" + nationality + "</nationality>"
				+ Expressions.reqCreateStudentEnd;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String createStudentEditUserMessage(String name,  String address,  String telephone) {

		return Expressions.updateStudentInfo + "<name>" + name + "</name>" 
				+ "<address>" + address + "</address>" +  "<telephone>" + telephone
				+ "</telephone>" 
				+ Expressions.updateStudentInfoEnd;

	}

	public String createUserCreateGroup() {

		return Expressions.alunoCreateGrupo + Expressions.alunoCreateGrupoEnd;
	}

	public String createUcTurmaGroup(int type, String uc_id, String turma_id) {

		return Expressions.professorCreateGroup + "<type type = '" + type + "'/> <uc uc_id='" + uc_id
				+ "'></uc> <turma turma_id ='" + turma_id + "'/> " + Expressions.professorCreateGroupEnd + "";
	}

	public String createAddStudentGroupMessage(ArrayList<String> students, String groupName) {

		System.out.println("GROUP NAME" + groupName);

		String strInicial = Expressions.sendStudentCreateGroup + "<Groupname>" + groupName + "</Groupname>"
				+ "<students>";

		for (int i = 0; i < students.size(); i++) {

			strInicial = strInicial + "<student id='" + students.get(i) + "'/>";

		}
		return strInicial + "</students>" + Expressions.sendStudentCreateGroupEnd + "";

	}

	public String createTextMessage(String userId, String texto, String date, int chatType, String uc_id,
			String turma_id) {

		String sendMessage = null;
		if (chatType == 0) {
			sendMessage = "" + Expressions.sendTurmaMessage + " <chat id_uc='" + uc_id + "' id_turma='" + turma_id
					+ "'/> <content> <user id_user= '" + userId + "'/><texto>" + texto + "</texto><data>" + date
					+ "</data></content> " + Expressions.sendTurmaMessageEnd + "";

		}

		if (chatType == 1) {
			sendMessage = "" + Expressions.sendUcMessage + " <chat id_uc='" + uc_id + "' id_turma='" + turma_id
					+ "'/> <content> <user id_user= '" + userId + "'/><texto>" + texto + "</texto><data>" + date
					+ "</data></content> " + Expressions.sendUcMessageEnd + "";

		}
		
		if (chatType == 2) {
			sendMessage = "" + Expressions.sendGroupChatMessage + " <chat id_group='" + uc_id
					+ "' /> <content> <user id_user= '" + userId + "'/><texto>" + texto + "</texto><data>" + date
					+ "</data></content> " + Expressions.sendGroupChatMessageEnd + "";


		}
		
		return sendMessage;

	}
	
	
	public HashMap<String,String> processStudentMessage(String info) {
		
		
		HashMap<String,String> usersMap = new HashMap<String, String>();
		
		Document doc = XMLReadWrite.documentFromString(info);

		NodeList userNodes = doc.getElementsByTagName("user");
	
		
		for (int i =0; i < userNodes.getLength(); i++) {
			
			Element user_i = (Element) userNodes.item(i);
			String id = user_i.getAttribute("id");
			String name = user_i.getElementsByTagName("name").item(0).getTextContent();
			usersMap.put(id, name);
			
		}
		return usersMap;
	}
	
	public String requestOtherUser(String id) {
		
		String inicial = Expressions.requestOtherUser;
		String finalStr = Expressions.requestOtherUserEnd;
		
		String req = inicial + id+ finalStr;
		
		return req;
		
	}
	
	
	public String requestEditChatUser(String id,String newName, ArrayList<String> users) {
		
		
		String inicial = Expressions.reqEditUserChat;
		String finalStr = Expressions.reqEditUserChatFinal;
		
		String req  = inicial + "<newName>"+newName+ "</newName>" + "<id>"+id+"</id>"+ "<users>";

		
		for (int i =0; i < users.size();i++) {
			
			req = req + "<user>"+users.get(i)+"</user>";
			
		}
		
		req = req +"</users>" + finalStr;
		return req;
		
		
	}
	
	public String requestChangeTurmaEdit(ArrayList<String> addStudents, ArrayList<String> removeStudents, String idTurma) {
		
		
		String req = Expressions.reqChangeTurmaEdit;
		
		
		req = req + "<idTurma>"+ idTurma + "</idTurma>";
		
		
		req = req + "<addStudents>";
		
		for (int i=0; i < addStudents.size();i++) {
			
			req = req + "<add>"+addStudents.get(i) + "</add>";
			
		}
		
		req = req + "</addStudents>";
		
		req = req+ "<removeStudents>";
		
		for (int i=0; i < removeStudents.size();i++) {
			
			req = req + "<remove>" + removeStudents.get(i) + "</remove>";
		}
		
		req = req+ "</removeStudents>";
		
		req = req + Expressions.reqChangeTurmaEditEnd;
		
		return req;
		
		
		
		
	}
	
	
	

}
